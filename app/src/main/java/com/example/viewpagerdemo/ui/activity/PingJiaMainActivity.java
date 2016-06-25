package com.example.viewpagerdemo.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
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
 * 评价商品
 */
public class PingJiaMainActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.PingJia_name)
    TextView PingJiaName;
    @Bind(R.id.PingJia_rat)
    RatingBar PingJiaRat;
    @Bind(R.id.PingJia_content)
    EditText PingJiaContent;


    String id,name;

    @Override
    public int setViewLayout() {
        return R.layout.activity_ping_jia_main;
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("评价");
        id = getIntent().getStringExtra("id");
        name =getIntent().getStringExtra("name");
        PingJiaName.setText(name);
    }

    @Override
    public void initObject() {
        super.initObject();

    }


    void PingjiaReq() {
        showWait();
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId()+"");
        ap.put("goodsId", id);
        ap.put("content", PingJiaContent.getText().toString());
        ap.put("score", PingJiaRat.getRating()+"");
        String url = Contantor.goodsComment;

        DD.v("评价：" + url + "?" + ap.toString());

        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("评价s：" + s);
                JSONObject js = null;
                try {
                    js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                        PingjiaReqFinsh();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    void PingjiaReqFinsh() {
        AjaxParams ap = new AjaxParams();
        ap.put("id", id);
        String url = Contantor.comment;

        DD.v("评价完成：" + url + "?" + ap.toString());

        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    DD.v("评价完成：" + s);
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                        TS.shortTime("评价完成");
                        finish();
                    }
                    closeWait();
                } catch (JSONException e) {
                    e.printStackTrace();
                    closeWait();
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    @OnClick(R.id.PingJia_sit)
    public void onClick() {
        PingjiaReq();
    }
}
