package com.example.viewpagerdemo.ui.jlfragmenwork.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public final class DD {
    //当前文件名 行号 函数名
    public static String getFileLineMethod() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    StringBuffer toStringBuffer = new StringBuffer("[").append(
    traceElement.getFileName()).append(" | ").append(
    traceElement.getLineNumber()).append(" | ").append(
    traceElement.getMethodName()).append("()").append("]");
    return toStringBuffer.toString();
    }
    // 当前文件名
    public static String _FILE_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getFileName();
    }
 
    // 当前方法名
    public static String _FUNC_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getMethodName();
    }
 
    // 当前行号
    public static int _LINE_() {
    StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
    return traceElement.getLineNumber();
    }
 
    // 当前时间
    public static String _TIME_() {
    Date now = new Date(0);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return sdf.format(now);
 
    }
     
    public static void v(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
        traceElement.getFileName()).append(" | ").append(
        traceElement.getLineNumber()).append(" | ").append(
        traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        Log.v("LD", msg);
    }
    public static  void d(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
        traceElement.getFileName()).append(" | ").append(
        traceElement.getLineNumber()).append(" | ").append(
        traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        
        SpannableStringBuilder TAG_ssb = new SpannableStringBuilder(TAG);
        TAG_ssb.setSpan(new ForegroundColorSpan(Color.RED), 0, TAG.length(),  
                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        
        Log.d("LD",msg);
//        Log.d("\033[0;34m", "("+TAG+")"+msg + "\033[0m");
    }
     
    public static void i(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
        traceElement.getFileName()).append(" | ").append(
        traceElement.getLineNumber()).append(" | ").append(
        traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        Log.i("LD",msg );
    }
     
    public static void w(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
        traceElement.getFileName()).append(" | ").append(
        traceElement.getLineNumber()).append(" | ").append(
        traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        Log.w("LD",msg );
     }
 
     public static void e(String msg) {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
        traceElement.getFileName()).append(" | ").append(
        traceElement.getLineNumber()).append(" | ").append(
        traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        Log.e("LD",msg );
     }
}