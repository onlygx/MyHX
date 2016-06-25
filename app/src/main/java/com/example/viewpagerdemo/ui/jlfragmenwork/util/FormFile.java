package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * �ϴ��ļ�
 */
public class FormFile {
    /* �ϴ��ļ������� */
    private byte[] data;
    private InputStream inStream;
    private File file;
    //�ϴ��ļ���С
    private int fileSize;
    /* �ϴ��ļ����ƣ�������Ϊ����������ʾ���ļ�����*/
    private String filename;
    /* ����������� <input type="file" name="file" /> ��Ӧ����input�е�name*/
    private String parameterName;
    /* �������� */
    private String contentType = "application/octet-stream";
    
    public FormFile(String filname, File file, String parameterName, String contentType) {
        this.filename = filname;
        this.parameterName = parameterName;
        this.file = file;
        if (this.file == null) {
        	throw new NullPointerException("file ����Ϊ��");
        }
        if(contentType!=null) this.contentType = contentType;
    }
    
	public int getFileSize() {
		return fileSize;
	}

	public File getFile() {
        return file;
    }

    public InputStream getInStream() {
    	if (inStream != null) {
    		try {
				inStream.close();
				inStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	try {
            this.inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inStream;
    }

    public byte[] getData() {
        return data;
    }

    public String getFilname() {
        return filename;
    }

    public void setFilname(String filname) {
        this.filename = filname;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
}