package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.MyDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 支付
 */
public class PlayMainActivity extends JLBaseActivity {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.fk_money)
    TextView fkMoney;

    String id;

    @Override
    public int setViewLayout() {
        return R.layout.activity_play_main;
    }

    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("确认付款");
        id = getIntent().getStringExtra("id");
        String money = getIntent().getStringExtra("mo");
        fkMoney.setText(money);
    }


    @OnClick(R.id.fk_sit)
    public void onClick() {
        showWait();
        AjaxParams map = new AjaxParams();
        map.put("id", id + "");
        map.put("thinksId", MyApplication.getInstan().getUser().getData().getThinksId());
        String url = Contantor.pay;
        DD.v("付款：" + url + "?" + map.toString());
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("付款s：" + s);
                try {
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                       // TS.shortTime("付款成功");
                        final MyDialog md = new MyDialog(PlayMainActivity.this, R.style.WinDialog);
                        md.setContentView(R.layout.playlayoutdialog);
                        TextView con = (TextView) md.getWindow().findViewById(R.id.content_tel);
                        con.setText("付款成功");
                        TextView ok = (TextView) md.getWindow().findViewById(R.id.oktel);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(PlayMainActivity.this, OrderInfoActivity.class);
                                it.putExtra("id", id);
                                setResult(101, it);
                                md.dismiss();
                                finish();
                            }
                        });
                        md.show();
                    } else {
                        if (js.getInt("code") == 3) {
                            TS.shortTime("余额不足");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                closeWait();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                closeWait();
            }
        });


    }
}
