package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

/**
 * �ϴ��ļ���������
 * 
 * @author yjw
 *
 */
public class SocketHttpRequester {
	public static String post(String path, Map<String, String> params, FormFile[] files,Handler handler) throws Exception{
        try {
		final String BOUNDARY = "---------------------------7da2137580612";
        final String endline = "--" + BOUNDARY + "--\r\n";//
        int fileDataLength = 0;
        for(FormFile uploadFile : files){
            StringBuilder fileExplain = new StringBuilder();
             fileExplain.append("--");
             fileExplain.append(BOUNDARY);
             fileExplain.append("\r\n");
             fileExplain.append("Content-Disposition: form-data;name=\""+ uploadFile.getParameterName()+"\";filename=\""+ uploadFile.getFilname() + "\"\r\n");
             fileExplain.append("Content-Type: "+ uploadFile.getContentType()+"\r\n\r\n");
             fileExplain.append("\r\n");
             fileDataLength += fileExplain.length();
            if(uploadFile.getInStream()!=null){
            	if (uploadFile.getFile() != null) {
            		fileDataLength += uploadFile.getFile().length();
            	} else {
            		fileDataLength += uploadFile.getFileSize();
            	}
             }else{
                 fileDataLength += uploadFile.getData().length;
             }
        }
        StringBuilder textEntity = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            textEntity.append("--");
            textEntity.append(BOUNDARY);
            textEntity.append("\r\n");
            textEntity.append("Content-Disposition: form-data; name=\""+ entry.getKey() + "\"\r\n\r\n");
            textEntity.append(entry.getValue());
            textEntity.append("\r\n");
        }
        int dataLength = textEntity.toString() .getBytes().length + fileDataLength +  endline.getBytes().length;
        URL url = new URL(path);
        int port = url.getPort()==-1 ? 80 : url.getPort();
        Socket socket = new Socket(InetAddress.getByName(url.getHost()), port);   
        Log.i("LD", "socket connected is " + socket.isConnected());
        OutputStream outStream = socket.getOutputStream();
        String requestmethod = "POST "+ url.getPath()+" HTTP/1.1\r\n";
        outStream.write(requestmethod.getBytes());
        String accept = "Accept: image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n";
        outStream.write(accept.getBytes());
        String language = "Accept-Language: zh-CN\r\n";
        outStream.write(language.getBytes());
        String contenttype = "Content-Type: multipart/form-data; boundary="+ BOUNDARY+ "\r\n";
        outStream.write(contenttype.getBytes());
        String contentlength = "Content-Length: "+ dataLength + "\r\n";
        outStream.write(contentlength.getBytes());
        String alive = "Connection: Keep-Alive\r\n";
        outStream.write(alive.getBytes());
        String host = "Host: "+ url.getHost() +":"+ port +"\r\n";
        outStream.write(host.getBytes());
        outStream.write("\r\n".getBytes());
        outStream.write(textEntity.toString().getBytes());
        int lenTotal = 0;
        for(FormFile uploadFile : files){
            StringBuilder fileEntity = new StringBuilder();
             fileEntity.append("--");
             fileEntity.append(BOUNDARY);
             fileEntity.append("\r\n");
             fileEntity.append("Content-Disposition: form-data;files=\""+ uploadFile.getParameterName()+"\";filename=\""+ uploadFile.getFilname() + "\"\r\n");
             fileEntity.append("Content-Type: "+ uploadFile.getContentType()+"\r\n\r\n");
             outStream.write(fileEntity.toString().getBytes());
             InputStream is = uploadFile.getInStream();
             if(is!=null) {
                 byte[] buffer = new byte[1024];
                 int len = 0;
	                 while((len = is.read(buffer, 0, 1024))!=-1){
	                     outStream.write(buffer, 0, len);
	                     lenTotal += len;	//ÿ���ϴ��ĳ���
	                     Message message = new Message();
	                     message.arg1 = 11;
	                     message.obj = lenTotal;
	                     handler.sendMessage(message);
	                 }
	                 is.close();   
             }else{
                 outStream.write(uploadFile.getData(), 0, uploadFile.getData().length);
             }
             outStream.write("\r\n".getBytes());
        }

        outStream.write(endline.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = "";
        boolean requestCodeSuccess = false;
        boolean uploadSuccess = false;
        int indexResult = 1;
        while((str = reader.readLine()) != null) {
        	Log.d("LD", "upload--->str=" + str);
        	if (indexResult == 1) {
        		if (str.indexOf("200") > 0) {
            		requestCodeSuccess = true;
            	}
        	}
        	if (indexResult == 6) {
        		if ("true".equals(str.trim())) {
            		uploadSuccess = true;
            	}
        	}
        	
        	if (requestCodeSuccess && uploadSuccess) {
        		
        		outStream.flush();
    	        if(null != socket && socket.isConnected())
    	        {
	    	        socket.shutdownInput();
	    	        socket.shutdownOutput();
    	        }
    	        outStream.close();
    	        reader.close();
    	        socket.close();
        		return str.trim();
        	} else if (indexResult == 6) {
        		outStream.flush();
    	        if(null != socket && socket.isConnected())
    	        {
	    	        socket.shutdownInput();
	    	        socket.shutdownOutput();
    	        }
    	        outStream.close();
    	        reader.close();
    	        socket.close();
        		return str.trim();
        	}
        	++indexResult;
        }
        outStream.flush();
       
        if(null != socket && socket.isConnected())
        {
	        socket.shutdownInput();
	        socket.shutdownOutput();
        }
        outStream.close();
        reader.close();
        socket.close();
        return null;
        } catch(Exception e) {
        	e.printStackTrace();
        	 return null;
        }
    }
    
    /**
     * 单张
     */
    public static String post(String path, Map<String, String> params, FormFile file,Handler handler) throws Exception{
       return post(path, params, new FormFile[]{file},handler);
    }
    
}