package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.xingkesi.foodapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteMainActivity extends JLBaseActivity {

    @Bind(R.id.note)
    EditText note;

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Override
    public int setViewLayout() {
        return R.layout.activity_note_main;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("备注");
    }

    @Override
    public void initObject() {
        super.initObject();
    }



    @OnClick(R.id.note_submit)
    public void onClick() {
        //提交备注
        String  str =note.getText().toString().trim();
        Intent addrees =new Intent(NoteMainActivity.this,ShoppingDDActivity.class);
        addrees.putExtra("bz",str);
        setResult(103,addrees);
        finish();

    }
}
