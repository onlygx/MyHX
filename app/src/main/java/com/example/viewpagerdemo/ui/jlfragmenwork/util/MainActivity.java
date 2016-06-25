/*
package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xingkesi.foodapp.R;

*/
/**
 * �����ļ��ϴ�
 * @author yjw
 * 20150804
 * ���͵�ַ��http://blog.csdn.net/muyi_amen
 *//*

public class MainActivity extends ActionBarActivity {

	private Button uploadBtn;
	private Button fileBtn;
	private TextView showFileNameTxt;
	private ProgressDialog dialog;
	
	private List<String> filesPath = new ArrayList<String>();
	private List<FormFile> filesList = new ArrayList<FormFile>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		uploadBtn = (Button) findViewById(R.id.upload);
		fileBtn = (Button) findViewById(R.id.file);
		showFileNameTxt = (TextView) findViewById(R.id.show_filename);
		dialog = new ProgressDialog(this);
		fileBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				if (Build.VERSION.SDK_INT < 19) {
					intent.setAction(Intent.ACTION_GET_CONTENT);
				}else{
					//����Intent.ACTION_OPEN_DOCUMENT�İ汾��4.4���ϵ�����
					//����ͻ�ʹ�õĲ���4.4���ϵİ汾����Ϊǰ�����жϣ����Ը���������else��
					//Ҳ�Ͳ�������κ���Ϊ�����������Ĵ���
					intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
				}
				intent.setType("image*/
/*");
				startActivityForResult(intent, 1);
			}
		});
		uploadBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (filesList == null || filesList.isEmpty()){
					Toast.makeText(MainActivity.this, "����ѡ���ϴ��ļ�", Toast.LENGTH_SHORT).show();
					return;
				}
				dialog.show();
				//1��HTTPЭ��ķ�ʽ
//				MultiUploadThread uploadThread = new MultiUploadThread(filesList, handler);
//				new Thread(uploadThread).start();
				//2��HttpClient��ʽ
				new Thread(multiThread).start();
			}
		});
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			dialog.dismiss();
			Log.d("yjw", "--->" + msg);
		}
		
	};
	
	@SuppressLint("NewApi")
	public static String getPathByUri(Context cxt,Uri uri){
		
		//�ж��ֻ�ϵͳ�Ƿ���4.4�����ϵ�sdk
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		//�����4.4���ϵ�ϵͳ����ѡ����ļ���4.4ר�е�������ļ�
		if (isKitKat && DocumentsContract.isDocumentUri(cxt, uri)) {
			// ����Ǵ��ⲿ���濨ѡ����ļ�
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}

			}
			//��������ط��ص�·��
			else if (isDownloadsDocument(uri)) {
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(cxt, contentUri, null, null);
			}
			//�����ѡ���ý����ļ�
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {  //ͼƬ
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {  //��Ƶ
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {  //��Ƶ
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(cxt, contentUri, selection,
						selectionArgs);
			}
		}else if ("content".equalsIgnoreCase(uri.getScheme())) {   //����ǵͶ�4.2���µ��ֻ��ļ�uri��ʽ
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(cxt, uri, null, null);
		}else if ("file".equalsIgnoreCase(uri.getScheme())) {   //�����ͨ��fileת�ɵ�uri�ĸ�ʽ
			return uri.getPath();
		}

		return null;
	}
	
	*/
/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 *//*

	public static String getDataColumn(Context context, Uri uri, String selection,
			String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}
	
	*/
/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 *//*

	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	*/
/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 *//*

	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	*/
/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 *//*

	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	*/
/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 *//*

	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		String path = getPathByUri(this,arg2.getData());
		System.out.println("-->path=" + path);
		showFileNameTxt.setText(showFileNameTxt.getText() + "\n" + path);
		filesPath.add(path);
		File file = new File(path);
		filesList.add(new FormFile(file.getName(), file, System.currentTimeMillis()+".png", "application/octet-stream"));
	}
	
	//����������Ҫ�����µ��̣߳����������߳��в���
	Runnable multiThread = new Runnable() {
		
		@Override
		public void run() {
			try {
				multiUploadFile1();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void multiUploadFile1 () throws UnsupportedEncodingException {
			//HttpClient����
			HttpClient httpClient = new DefaultHttpClient();
			//����POST������ʽ
			//�����ϴ������ַhttp://10.0.2.2:8080/WebAppProject/main.do?method=upload2
			HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/WebAppProject/main.do?method=upload2");
			//MultipartEntity������Ҫhttpmime-4.1.1.jar�ļ���
			MultipartEntity multipartEntity = new MultipartEntity();
			//StringBody���󣬲���
			StringBody param = new StringBody("��������");
			multipartEntity.addPart("param1",param);
			//filesPathΪList<String>���������ŵ�����Ҫ�ϴ����ļ��ĵ�ַ
			for (String path:filesPath) {
				//FileBody������Ҫ�ϴ����ļ�
				ContentBody file = new FileBody( new File(path));
				multipartEntity.addPart("file",file);
			}
			//��MultipartEntity����ֵ��HttpPost
			httpPost.setEntity(multipartEntity);
			HttpResponse response = null;
			try {
				//ִ�����󣬲����ؽ��HttpResponse
				response = httpClient.execute(httpPost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//�ϴ��ɹ��󷵻�
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("-->success");
			} else {
				System.out.println("-->failure");
			}
		}
	
		//�����httpmime-4.3.jar��������ķ�������ΪMultipartEntity��4.3֮�󱻷�����
		//
//	public void multiUploadFile2 () {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//
//		HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/WebAppProject/main.do?method=upload");
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		
//		for (String path:filesPath) {
//			builder.addBinaryBody("file",  new File(path));
//		}
//		httpPost.setEntity(builder.build());
//		HttpResponse response = null;
//		try {
//			response = httpclient.execute(httpPost);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HttpEntity httpEntity = response.getEntity();
//		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
////			Toast.makeText(MainActivity.this, "�ϴ��ɹ�", Toast.LENGTH_SHORT).show();
//			System.out.println("-->success");
//		}
//	}
};
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
*/
