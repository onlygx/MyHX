package com.example.viewpagerdemo.ui.jlfragmenwork.share;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xingkesi.foodapp.R;


/**
 * Created by Administrator on 2015/11/12 0012.
 */
public class SelectPicPopupWindowShare extends PopupWindow {
    GridView gv;
    private View mMenuView;

    String[] name = {"QQ", "新浪微博", "微信", "朋友圈", "腾讯微博" ,"QQ空间"};
    int[] icon = {R.drawable.share_setting_qq,
            R.drawable.share_setting_sina,
            R.drawable.share_setting_weixin,
            R.drawable.logo_wechatmoments,
            R.drawable.share_setting_weibo,
            R.drawable.logo_qzone,
    };

    MeShareAdapter msa;
    UMImage image ;

    public SelectPicPopupWindowShare(final Activity context, final UMShareListener umShareListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_shered, null);
        gv = (GridView) mMenuView.findViewById(R.id.share_grid);
        msa = new MeShareAdapter(context, name, icon);
        gv.setAdapter(msa);


        final UMShareListener  us =new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(context,platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(context,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(context,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };


        image = new UMImage(context, BitmapFactory.decodeResource(context.getResources(),
                R.drawable.logo));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        new ShareAction(context).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .withTargetUrl(context.getResources().getString(R.string.app_name))
                                .setCallback(us)
                                .share();
                        break;
                    case 1:
                        new ShareAction(context).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withTargetUrl(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .setCallback(us)
                                .share();
                        break;
                    case 2:
                        //微信
                        new ShareAction(context).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withTitle(context.getResources().getString(R.string.app_name))
                                .withTargetUrl(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .setCallback(us)
                                .share();
                        break;
                    case 3:
                        //朋友圈
                        new ShareAction(context).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .setCallback(us)
                                .share();
                        break;
                    case 4:
                        //腾讯微博
                        new ShareAction(context).setPlatform(SHARE_MEDIA.TENCENT).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .setCallback(us)
                                .share();
                        break;

                    case 5:
                        //QQ控件
                        new ShareAction(context).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                                .withText(context.getResources().getString(R.string.app_name))
                                .withMedia(image)
                                .setCallback(us)
                                .share();
                        break;


                }
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // context.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });



    }




}
