package com.example.viewpagerdemo.ui.activity;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
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
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;


/**
 * 评论
 */
public class CommentsActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    //----------------------------------
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.tv_right_text)
    TextView tv_right_text;


    String shoppingID;
    @Bind(R.id.comments_et)
    EditText commentsEt;
    @Bind(R.id.commonet_bt)
    TextView commonetBt;
    @Bind(R.id.comments_rb)
    RatingBar comments_rb;


    @Override
    public int setViewLayout() {
        return R.layout.activity_comments;
    }


    @Override
    public void initID() {
        shoppingID = getIntent().getStringExtra("id");
        DD.d("接受到的："+shoppingID);
        commonetBt = (TextView) findViewById(R.id.commonet_bt);
    }

    @Override
    public void initObject() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        iv_right_image.setVisibility(View.VISIBLE);
        tv_right_text.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(R.drawable.cp_xx);
        Drawable drawable= getResources().getDrawable(R.drawable.fenxiang);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_right_text.setCompoundDrawables(drawable,null,null,null);
        tv_title.setText("评论");


        commonetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.d("提交评论");
                if (TextUtils.isEmpty(commentsEt.getText().toString().trim())) {
                    TS.shortTime("请填写评论内容");
                    return;
                }
                SummitComments();
            }
        });

       /* comments_rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                TS.shortTime(rating+"");
            }
        });*/
    }

    @Override
    public void ReustData() {
        super.ReustData();
    }



    void SummitComments() {
        String content = commentsEt.getText().toString().trim();
        AjaxParams map = new AjaxParams();
//        userId:long //用户id
//        goodsId:long //商品id
//        content:string //内容文本
//        score:int // 评分
        int rating = (int) comments_rb.getRating();
        map.put("userId", MyApplication.getInstan().getUser().getData().getId()+"");
        map.put("goodsId", shoppingID);
        map.put("content", content);
        map.put("score", rating+"");

        DD.d("参数："+map.toString());

        String url = Contantor.shoppingComments;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("参数s："+s);
                boolean success =Tools.StrASBollean(s,"success");
                if(success){
                    TS.shortTime("评论成功");
                    finish();
                }else{
                    TS.shortTime("评论失败");

                }


            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }



}
