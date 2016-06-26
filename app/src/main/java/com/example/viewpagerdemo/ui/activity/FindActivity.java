package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.UserBeanLO;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.BitmapUtils;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CommonUtils;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Request;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.SelectPicPopupWindow2;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.OnClick;

public class FindActivity extends JLBaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;


    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.iv_image)
    CircleImageView iv_image;//头像

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.sex_tv)
    TextView sex_tv;
    @Bind(R.id.wx_tv)
    TextView wx_tv;
    @Bind(R.id.qq_tv)
    TextView qq_tv;
    @Bind(R.id.qm_tv)
    TextView qm_tv;


    String mHeadUrl;

    //-----------------------------------------------------------------
    SelectPicPopupWindow2 menuWindow;
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";

    UserBeanLO user;

    @Override
    public int setViewLayout() {
        return R.layout.activity_find;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("个人中心");


    }

    @Override
    public void initObject() {
        super.initObject();
        if (MyApplication.getInstan().getUser() != null) {
            user = MyApplication.getInstan().getUser().getData();
            if (user != null && user.getId() != 0) {
                //  DD.v("个人:" + user.toString() + "===" + user.getNickName());
                tvName.setText(user.getNickName());
                Picasso.with(FindActivity.this).load(Contantor.Imagepost + user.getHead()).into(iv_image);
                qm_tv.setText(user.getIntro() != null ? user.getIntro() : "");
                qq_tv.setText(user.getQq() != null ? user.getQq() : "");
                wx_tv.setText(user.getWechat() != null ? user.getWechat() : "");
                sex_tv.setText(user.getSex() != null ? user.getSex() : "");
            }
        }


    }


    public Handler uploadHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            // dismissProgressDialog();
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    Picasso.with(FindActivity.this).load(mHeadUrl).into(iv_image);
                    break;
            }
        }
    };

    @OnClick({R.id.rl_tx, R.id.rl_name, R.id.rl_addr, R.id.rl_pwd,
            R.id.rl_sex, R.id.rl_wx, R.id.rl_qq, R.id.rl_qm,R.id.exit})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_tx://头像
                menuWindow = new SelectPicPopupWindow2(this, itemsOnClick, "zhutu");
                //显示窗口
                menuWindow.showAtLocation(iv_image, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

                break;
            case R.id.rl_name://名字
                intent.setClass(FindActivity.this, FindSetNameActivity.class);
                startActivityForResult(intent, 103);
                //startActivity(intent);
                break;
            case R.id.rl_addr://收货地址
                intent.putExtra("check", false);
                intent.setClass(FindActivity.this, FindAddreesActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_pwd://修改密码
                intent.setClass(FindActivity.this, SettingPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_sex://性别
                intent.setClass(FindActivity.this, UpdataSexActivity.class);
                startActivityForResult(intent, 104);

                break;
            case R.id.rl_wx://微信
                intent.setClass(FindActivity.this, UpdataWXActivity.class);
                startActivityForResult(intent, 105);

                break;
            case R.id.rl_qq://QQ
                intent.setClass(FindActivity.this, UpdataQQActivity.class);
                startActivityForResult(intent, 106);

                break;
            case R.id.rl_qm://签名
                intent.setClass(FindActivity.this, UpdataQMActivity.class);
                startActivityForResult(intent, 107);
                break;

            case R.id.exit://推出

                MyApplication.getInstan().setUser(null);
                MyApplication.getInstan().setUserName("");
                MyApplication.getInstan().setUserPwd("");
                MyApplication.exit=0;
                finish();

                // startActivity(new Intent(getActivity(), ColltentMainActivit.class));

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 110:
                if (data != null) {
                    CommonUtils.getPhoto(data, FindActivity.this);
                }
                break;
            case 111: // 图片缩放完成后
                if (data != null) {
                    String newName = CommonUtils.getUploadPhotoName();
                    DD.w("图片----：" + newName);
                    mHeadUrl = Contantor.IMAGEURLHEADER + newName;
                    CommonUtils.getImageToView(FindActivity.this, newName, uploadHandler);
                }
                break;
            case 0://相册 上传图片
                if (data != null) {
                    Tools.transImage(data, 480, 800, 80, han, 203, FindActivity.this);
                }
                break;
            case 1://相机  上传图片
                // 设置文件保存路径
                if (Tools.hasSdcard()) {
                    File picture = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DCIM);
                    File tempFile = new File(picture, IMAGE_FILE_NAME);

                    Intent intent = new Intent();
                    intent.setData(Uri.fromFile(tempFile));
                    Tools.transImage(intent, 200, 200, 80, han, 202, FindActivity.this);
                    /*if (ActivityOther == 1) {
                        Tools.transImage(intent, 480, 800, 80, han, 203, NewHouseReleaseActivity.this);
                    }*/

                } else {
                    Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                }
                break;

            case 103://名字
                if (data != null) {
                    String name = data.getStringExtra("name");
                    tvName.setText(name);
                }
                break;
            case 104://性别
                if (data != null) {
                    String name = data.getStringExtra("sex");
                    sex_tv.setText(name);
                }

                break;
            case 105://微信
                if (data != null) {
                    String name = data.getStringExtra("wx");
                    wx_tv.setText(name);

                }

                break;
            case 106://QQ
                if (data != null) {
                    String name = data.getStringExtra("qq");
                    DD.d("name data:" + name);
                    qq_tv.setText(name);

                }

                break;
            case 107://签名
                if (data != null) {
                    String name = data.getStringExtra("qm");
                    qm_tv.setText(name);

                }

                break;

        }
    }

    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int tag = msg.what;
            if (tag == 202) {//主图
                String path = msg.getData().getString("path");
                Request.UploadImage2(han, FindActivity.this, path, 101);
            } else if (tag == 203) {//附图
                String path = msg.getData().getString("path");
                Request.UploadImage2(han, FindActivity.this, path, 101);
            } else if (tag == 101) {
                String path = msg.getData().getString("path");
                DD.w("头像:" + path);
                int wid = BitmapUtils.dip2px(FindActivity.this, 50);
                Bitmap bm = BitmapUtils.decodeSampledBitmapFromResource(path, wid, wid);
                iv_image.setImageBitmap(bm);
                AjaxParams params = new AjaxParams();
                try {
                    File f = new File(path);
                    DD.d("图片：" + f.toString());
                    if (!f.exists()) {
                        DD.d("图片：不存在");

                    }
                    params.put("file", f);
                    params.put("id", MyApplication.getInstan().getUser().getData().getId() + "");
                    FinalHttp fh = new FinalHttp();
                    DD.v("头像：" + params.toString());
                    fh.post(Contantor.IMAGEURLHEADER, params, new AjaxCallBack<String>() {
                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {
                            super.onFailure(t, errorNo, strMsg);
                            DD.e("头像上传失败:" + strMsg);
                        }

                        @Override
                        public void onSuccess(String s) {
                            super.onSuccess(s);
                            DD.d("头像上传:" + s);
                        }
                    });


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
    };

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                //相册
                case R.id.btn_take_photo:
                    String tag = (String) menuWindow.btn_take_photo.getTag();
                    //
                    Intent intentFromGallery = new Intent();
                    intentFromGallery.setType("image/*"); // 设置文件类型
                    intentFromGallery.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                    break;
                //拍照
                case R.id.btn_pick_photo:
                    // OpenCamera();
                    // String tags = (String) menuWindow.btn_pick_photo.getTag();
                    // 判断存储卡是否可以用，可用进行存储
                    Intent intentFromCapture = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Tools.hasSdcard()) {
                        File path =
                                Environment.getExternalStoragePublicDirectory
                                        (Environment.DIRECTORY_DCIM);
                        File file = new File(path, IMAGE_FILE_NAME);
                        intentFromCapture.putExtra(
                                MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(file));
                    }
                    startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                    break;
                default:
                    break;
            }
        }

    };
}
