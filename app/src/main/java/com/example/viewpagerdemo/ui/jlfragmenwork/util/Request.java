package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2016/6/4.
 */
public class Request {
    /**
     * 上传相册中的图片
     *
     * @param han
     * @param m_activity
     */
    public static void UploadImage2(final Handler han, final Activity m_activity, final String path,
                                    final int refwath) {
        try {

            //测试
            File img = new File(path);
            Message msg = han.obtainMessage();
            msg.what = refwath;
            Bundle data = new Bundle();
            data.putString("path", img.toString());//物理路径
            String imageUrl = "";
            data.putString("url", imageUrl);//网络路径
            msg.setData(data);
            msg.sendToTarget();



           /* AjaxParams params = new AjaxParams();
            final File img = new File(path);
            params.put("username", YbonApplication.getUser().getYbo_name());
            params.put("password", YbonApplication.getUser().getYbo_pass());
            params.put("batchId", bachtNum + "");
            params.put("upload", img);
            FinalHttp fn = new FinalHttp();
            fn.post(UploadImage, params, new AjaxCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    super.onSuccess(s);
                    try {
                        JSONObject ja = new JSONObject(s);
                        boolean success = ja.getBoolean("success");
                        if (success) {
                            Message msg = han.obtainMessage();
                            msg.what = refwath;
                            Bundle data = new Bundle();
                            data.putString("path", img.toString());//物理路径
                            String imageUrl = "";
                            JSONArray result = ja.getJSONArray("result");
                            JSONObject result1 = result.getJSONObject(0);
                            //String articlePath =result1.getString("articlePath");
                            if (!result1.isNull("articlePath")) {
                                imageUrl = result1.getString("articlePath");
                            }
                            data.putString("url", imageUrl);//网络路径
                            msg.setData(data);
                            msg.sendToTarget();
                            //img.delete();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    Toast.makeText(m_activity, "网络不给力上传失败~请重新上传", Toast.LENGTH_SHORT).show();
                }
            });*/
        } catch (Exception e) {
        }
    }
}
