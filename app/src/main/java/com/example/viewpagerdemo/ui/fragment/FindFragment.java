package com.example.viewpagerdemo.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.AboutWebActivity;
import com.example.viewpagerdemo.ui.activity.AdviceMainActivity;
import com.example.viewpagerdemo.ui.activity.ColltentMainActivit;
import com.example.viewpagerdemo.ui.activity.ErWMain2Activity;
import com.example.viewpagerdemo.ui.activity.FindActivity;
import com.example.viewpagerdemo.ui.activity.FindDDListActivity;
import com.example.viewpagerdemo.ui.activity.MyOderListActivity;
import com.example.viewpagerdemo.ui.activity.MyReleaseListActivity;
import com.example.viewpagerdemo.ui.activity.ReleaseNeedActivity;
import com.example.viewpagerdemo.ui.activity.ShopWebActivity;
import com.example.viewpagerdemo.ui.bean.UserBeanLO;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.MyDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.CircleFriendsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 我
 */
public class FindFragment extends JLBaseFragment {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.find_layout)
    LinearLayout find_layout;

    @Bind(R.id.cache)
    TextView cache;

    @Bind(R.id.nikc)
    TextView nikc;//昵称
    @Bind(R.id.icon)
    CircleImageView icon; //头像


    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    //----------------------------
    UserBeanLO user;

    @Override
    public int setViewLayout() {
        return R.layout.activity_tab_contacts;
    }

    @Override
    public void InitObject(){
        tv_title.setText("我");
        iv_right_image.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(R.drawable.xinxi);


        if (MyApplication.getInstan().getUser() != null
                && MyApplication.getInstan().getUser().getData().getId() != 0
                ) {
            try {
                String size = Tools.getTotalCacheSize(MyApplication.getContext());
                cache.setText(size);
            }catch (Exception e){}
        }
    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见时加载数据相当于Fragment的onResume
            if (MyApplication.getInstan().getUser() == null &&
                    MyApplication.getInstan().getUser().getData().getThinksId() == null) {

            }
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
       // DD.v("个人onResume:");


        if (MyApplication.getInstan().getUser() != null) {
            user = MyApplication.getInstan().getUser().getData();
            if (user != null && user.getId() != 0) {
               // DD.v("个人:" + user.toString() + "===" + user.getNickName());
                nikc.setText(user.getNickName());
                Picasso.with(getContext()).load(Contantor.Imagepost + user.getHead()).into(icon);

            }
        }else{
            if (!cache.equals("")) {
                Tools.clearAllCache(MyApplication.getContext());
                try {
                    String size = Tools.getTotalCacheSize(MyApplication.getContext());
                    cache.setText(size);
                }catch (Exception e){}
            }
            nikc.setText("");
            icon.setImageResource(R.drawable.touxiang03);
            find_layout.postInvalidate();
        }
    }

    @Override
    public void SetData() {
        super.SetData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ll_find, R.id.ll_frend, R.id.ll_dd, R.id.ll_about,
            R.id.ll_jianyi, R.id.ll_prosion, R.id.ll_tel, R.id.ll_cache,
            R.id.find_new_fbxq, R.id.find_new_xq, R.id.find_new_rw,
            R.id.find_new_sc})
    public void onClick(View view) {
        Intent intent = new Intent();
        // DD.d();
        if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
            intent.putExtra("tag", "finsh");
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }

        switch (view.getId()) {
            case R.id.ll_find://g
                intent.setClass(getActivity(), FindActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_frend://朋友圈
                intent.setClass(getActivity(), CircleFriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dd://订单列表
                intent.setClass(getActivity(), FindDDListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_about://关于我
                intent.setClass(getActivity(), AboutWebActivity.class);
                startActivity(intent);

                break;
            case R.id.ll_jianyi://建议
                intent.setClass(getActivity(), AdviceMainActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_prosion://我开店

                startActivity(new Intent(getActivity(), ShopWebActivity.class));
                break;
            case R.id.ll_tel://客服电话
                final MyDialog md = new MyDialog(getActivity(), R.style.WinDialog);
                md.setContentView(R.layout.calltellayout);
                TextView con = (TextView) md.getWindow().findViewById(R.id.content_tel);
                final String tel = getActivity().getResources().getString(R.string.tel);
                con.setText(String.format(getActivity().getResources().getString(R.string.telhit), tel));
                TextView can = (TextView) md.getWindow().findViewById(R.id.canceltel);
                TextView ok = (TextView) md.getWindow().findViewById(R.id.oktel);
                can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (md != null) {
                            md.dismiss();
                        }
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + tel));
                        startActivity(phoneIntent);
                        md.dismiss();
                    }
                });
                md.show();
                break;
            case R.id.ll_cache:
                if (!cache.equals("")) {
                    Tools.clearAllCache(MyApplication.getContext());
                    try {
                        String size = Tools.getTotalCacheSize(MyApplication.getContext());
                        cache.setText(size);
                    }catch (Exception e){}
                } else {
                    TS.shortTime("不需要清理");
                }
                break;
            case R.id.find_new_fbxq://发布需求
                startActivity(new Intent(getActivity(), ReleaseNeedActivity.class));
                break;
            case R.id.find_new_xq://需求
                startActivity(new Intent(getActivity(), MyReleaseListActivity.class));

                break;
            case R.id.find_new_rw://任务

                startActivity(new Intent(getActivity(), MyOderListActivity.class));

                break;
            case R.id.find_new_sc://收藏列表
                startActivity(new Intent(getActivity(), ColltentMainActivit.class));
                break;



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}
