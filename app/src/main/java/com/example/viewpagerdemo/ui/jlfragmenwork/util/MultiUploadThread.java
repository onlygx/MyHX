package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;

/**
 * �Żݹ���������ϴ����ܣ��ϴ����Żݵ��ļ�����
 * @author yjw
 *
 */
public class MultiUploadThread implements Runnable{
	private FormFile[] formFile;
	private Handler mHandler;

	/**
	 * 
	 * @param formFile
	 * @param mHandler
	 */
	public MultiUploadThread(FormFile[] formFile, Handler mHandler) {
		super();
		this.formFile = formFile;
		this.mHandler = mHandler;
	}
	
	public MultiUploadThread(List<FormFile> formFile, Handler mHandler) {
		super();
		if (formFile == null || formFile.isEmpty()) {
			throw new NullPointerException("param formFile is null or empty");
		}
		FormFile[] tempFile = new FormFile[formFile.size()];
		this.formFile = formFile.toArray(tempFile);
		this.mHandler = mHandler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		for (FormFile file:formFile) {
			String result = uploadFile(formFile);
			Message msg= mHandler.obtainMessage();
			if (msg != null) {
				msg.obj = result;
				msg.arg1 = 11;
				mHandler.sendMessage(msg);
			}
//		}
	}
	
	/**
     * �ϴ�ͼƬ��������
     * 
     * @param formFiles FormFile���顣
     */
    public String uploadFile(FormFile[] formFiles) {
        try {
            //������ͨ��Ϣ
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "upload");
            //����Ǳ���ʹ��10.0.2.2
            String result = SocketHttpRequester.post("http://10.0.2.2:8080/WebAppProject/main.do", params, formFiles,mHandler);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
