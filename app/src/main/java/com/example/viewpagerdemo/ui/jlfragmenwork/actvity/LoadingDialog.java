package com.example.viewpagerdemo.ui.jlfragmenwork.actvity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.xingkesi.foodapp.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }
    public LoadingDialog(Context context, int theme) {
        super(context,theme);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // instantiate the dialog with the custom Theme
        // 设置点击屏幕Dialog不消失
        setCanceledOnTouchOutside(false);
        View layout = inflater.inflate(R.layout.dialog_loading, null);
        addContentView(layout, new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        setContentView(layout);
    }
    public static class Builder {
        private Context context;
        private LoadingDialog dialog;

        public Builder(Context context) {
            this.context = context;
        }
        public LoadingDialog create() {
            //设置显示dialog后自动弹出输入法
            ( (Activity)context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            dialog = new LoadingDialog(context, R.style.LoadingDialog_style);
            // 设置点击屏幕Dialog不消失
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.dialog_loading, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
       /* public void showDialog(){
            if(dialog!=null)
            dialog.show();
            else{
                create();
                dialog.show();
            }
        }
        public void dismissDialog(){
            if(dialog!=null)
            dialog.dismiss();
        }*/
    }
}
