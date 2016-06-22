package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;

import com.example.viewpagerdemo.ui.activity.FindActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by saya on 2016/4/7.
 */
public class CommonUtils {




    public static void getPhoto(Intent data, Context context) {
        try {
            ArrayList<String> imgs = data.getStringArrayListExtra("data");
            File tempFile = new File(imgs.get(0));
            startPhotoZoom(Uri.fromFile(tempFile), context);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError error) {
            System.gc();
            TS.shortTime("上传图片太大了");
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Uri uri, Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/love_temp.png"))); //保存路径
        intent.putExtra("outputFormat", "PNG");// 返回格式
        intent.putExtra("return-data", false);
        ((Activity) context).startActivityForResult(intent, 111);
    }
    public static String getUploadPhotoName(){
        String url = "/mnt/sdcard/love_temp.png";
        String newUrl = ImageUtil.getNewPath(url);
        ImageUtil.save(url, newUrl);
        return newUrl.substring(newUrl.lastIndexOf("/") + 1, newUrl.length());
    }

    public static void getImageToView(FindActivity findActivity, String newName, Handler uploadHandler) {


    }
}
