package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.IndexOrderBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我承接的需求详情
 */
public class IndexJieMainActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    String id;
    @Bind(R.id.index_icon)
    ImageView indexIcon;
    @Bind(R.id.index_tx)
    ImageView index_tx;
    @Bind(R.id.index_title)
    TextView indexTitle;
    @Bind(R.id.index_conten)
    TextView indexConten;
    @Bind(R.id.index_address)
    TextView indexAddress;
    @Bind(R.id.index_name)
    TextView indexName;
    @Bind(R.id.index_money)
    TextView indexMoney;
    @Bind(R.id.index_date)
    TextView indexDate;
    @Bind(R.id.index_sit)
    TextView indexSit;


    IndexOrderBean oder;
    int tag = 0;

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("需求详情");
        tag = getIntent().getIntExtra("tag", -1);
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getContent();
    }

    @Override
    public void initObject() {
        super.initObject();

    }

    //
    void getContent() {
        AjaxParams map = new AjaxParams();
        map.put("id", id + "");
        String url = Contantor.commentInfo;
        DD.d("需求详情:：" + url + "?" + map.toString());


        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                oder = JSONObject.parseObject(s, IndexOrderBean.class);
                DD.d("需求详情s:：" + s);
                if (oder.getStatus() == 2) {
                    indexSit.setEnabled(true);
                    indexSit.setText("去评价");
                } else if(oder.getStatus() == 3){
                    indexSit.setText("已完成");
                    indexSit.setEnabled(false);
                }

                if (oder.getBannerList().size() > 0) {
                    String urls = Contantor.Imagepost + oder.getBannerList().get(0).getUrl();
                    Picasso.with(getApplicationContext()).load(urls).placeholder(R.drawable.aliwx_default_photo_right)
                            .error(R.drawable.aliwx_fail_photo_right).into(indexIcon);
                }

                if (oder.getSendUser() != null) {
                    String toux = Contantor.Imagepost + oder.getSendUser().getHead();
                    Picasso.with(getApplicationContext()).load(toux).placeholder(R.drawable.aliwx_default_photo_right)
                            .error(R.drawable.aliwx_fail_photo_right).into(index_tx);
                }


                indexTitle.setText(oder.getTitle());
                indexConten.setText(oder.getContent());
                indexMoney.setText(oder.getPrice() + "");
                indexAddress.setText(oder.getProvince() + " " + oder.getCity() + " " + oder.getDistrict() + "  " + oder.getAddress());
                indexName.setText(oder.getSendUser().getNickName());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:SS");
                String date = sdf.format(new Date(oder.getSetTime()));
                indexDate.setText(date);


            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
        });
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_index_main2;
    }


    @OnClick(R.id.index_sit)
    public void onClick() {
        if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
            Intent intent = new Intent();
            intent.putExtra("tag", "finsh");
            intent.setClass(IndexJieMainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            if (id.equals("")) {
                return;
            }
            if (oder.getStatus() == 2) {//评价需求
                Intent it = new Intent(IndexJieMainActivity.this, PingJiaMainChengActivity.class);
                it.putExtra("id", id);
                it.putExtra("name",oder.getTitle());
                it.putExtra("goodsId",oder.getSendUserId());
                startActivity(it);
                finish();
            }
        }

    }
}
