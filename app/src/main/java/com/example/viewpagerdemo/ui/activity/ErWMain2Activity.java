package com.example.viewpagerdemo.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.google.zxing.WriterException;
import com.karics.library.zxing.encode.CodeCreator;
import com.xingkesi.foodapp.R;

import butterknife.Bind;

public class ErWMain2Activity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.erwma)
    ImageView erwma;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    public int setViewLayout() {
        return R.layout.activity_er_wmain2;
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("我的二维码");

        try {
            Bitmap bitmap = CodeCreator.createQRCode(MyApplication.getInstan().getUser().getData().getThinksId() + "");
            erwma.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initObject() {
        super.initObject();
    }
}
