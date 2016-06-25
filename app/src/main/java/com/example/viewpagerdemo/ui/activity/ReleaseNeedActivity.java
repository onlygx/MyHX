package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.ImageAdapter;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.widget.SelectPicPopupWindow;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CommonUtils;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.SelectPicPopupWindow2;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.utils.MultipartRequest;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 发布需求
 */
public class ReleaseNeedActivity extends JLBaseActivity {


    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right_text)
    TextView tv_right_text;
    @Bind(R.id.province)
    TextView province;
    @Bind(R.id.numz)
    TextView numz;
    @Bind(R.id.title)
    EditText title;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.price)
    EditText price;
    @Bind(R.id.address)
    EditText address;
    @Bind(R.id.imageGV)
    GridView imageGV;


    SelectPicPopupWindow menuWindow;
    String pro, city, dro;//省市县

    SelectPicPopupWindow2 ImagemenuWindow;
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";
    String mHeadUrl;
    ArrayList<String> ioncList;
    ImageAdapter adapter;
    int MAX_LENGTH = 500;                   //最大输入字符数500
    int Rest_Length = MAX_LENGTH;
    RequestQueue mSingleQueue;

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_right_image.setVisibility(View.INVISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("发布需求");
        tv_right_text.setVisibility(View.VISIBLE);
        tv_right_text.setText("发布");
        mSingleQueue = Volley.newRequestQueue(ReleaseNeedActivity.this);
        tv_right_text.setTextColor(getResources().getColor(R.color.logding_bg));
        ioncList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if (MyApplication.getInstan().getSheng() != null) {
            sb.append(MyApplication.getInstan().getSheng() + " ");
        }
        if (MyApplication.getInstan().getCity() != null) {
            sb.append(MyApplication.getInstan().getCity() + " ");
        }
        if (MyApplication.getInstan().getQu() != null) {
            sb.append(MyApplication.getInstan().getQu() + " ");
        }
        province.setText(sb.toString());

    }

    @Override
    public void initObject() {

        content.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (content.length() >= MAX_LENGTH) {
                    TS.shortTime("字数过多");
                    return;
                }
                if (Rest_Length > 0) {
                    Rest_Length = MAX_LENGTH - content.getText().length();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                numz.setText(Rest_Length + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                numz.setText(Rest_Length + "");
            }
        });

        tv_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titles = title.getText().toString();
                String contents = content.getText().toString();
                String prices = price.getText().toString();
                String addresss = address.getText().toString();


                if (pro == null || pro.equals("")) {
                    pro = MyApplication.getInstan().getSheng();
                }
                if (city == null || city.equals("")) {
                    city = MyApplication.getInstan().getCity();
                }
                if (dro == null || dro.equals("")) {
                    dro = MyApplication.getInstan().getQu();
                }

                if (TextUtils.isEmpty(titles)) {
                    TS.shortTime("请填写标题");
                    return;
                }
                if (TextUtils.isEmpty(contents)) {
                    TS.shortTime("请填写内容");
                    return;
                }
                if (TextUtils.isEmpty(prices)) {
                    TS.shortTime("请填写价格");
                    return;
                }
                if (TextUtils.isEmpty(province.getText().toString())) {
                    TS.shortTime("请填选择地区");
                    return;
                }
                if (TextUtils.isEmpty(addresss)) {
                    TS.shortTime("请填写详细地址");
                    return;
                }

                if (ioncList.size() <= 0) {
                    TS.shortTime("请填选择图片");
                    return;
                }
                String url = Contantor.Ralesepay;
                showWait();
                List<File> f = new ArrayList<File>();
                for (String s : ioncList) {
                    if (!s.equals("000000")) {
                        f.add(new File(s));
                    }
                }
                Map<String, String> ap = new HashMap<String, String>();
                ap.put("title", titles);
                ap.put("content", contents);
                ap.put("price", prices);
                ap.put("sendUserId", MyApplication.getInstan().getUser().getData().getId() + "");
                ap.put("province", pro);
                ap.put("city", city);
                ap.put("district", dro);
                ap.put("address", addresss);

                DD.v("Dddddd发布需求s:" + url + "?" + ap.toString());

                MultipartRequest request = new MultipartRequest(url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        closeWait();
                        Log.i("LD", "success,response = " + response);
                        Toast.makeText(ReleaseNeedActivity.this, "uploadSuccess,response = " + response, Toast.LENGTH_SHORT).show();
                        finish();
                        //han.sendEmptyMessage(0);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //han.sendEmptyMessage(2);
                        Log.i("LD", "error,response = " + error.getMessage());
                        closeWait();
                        Toast.makeText(ReleaseNeedActivity.this, "uploadError,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, "files", f, ap);
                mSingleQueue.add(request);
               /* } else {
                    AjaxParams ap = new AjaxParams();
                    ap.put("title", titles);
                    ap.put("content", contents);
                    ap.put("price", prices);
                    ap.put("sendUserId", MyApplication.getInstan().getUser().getData().getId() + "");
                    ap.put("province", pro);
                    ap.put("city", city);
                    ap.put("district", dro);
                    ap.put("address", addresss);
//                    ap.put("files", "");
                    DD.v("1111发布需求s:" + url + "?" + ap.toString());
                    new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            super.onSuccess(s);
                            //DD.v("发布需求s:" + s);
                            try {
                                JSONObject js = new JSONObject(s);
                                if (js.getBoolean("success")) {
                                    TS.shortTime("发布成功");
                                    finish();
                                } else {
                                    TS.shortTime("发布失败");
                                }
                                closeWait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t, int errorNo, String strMsg) {
                            super.onFailure(t, errorNo, strMsg);
                            closeWait();
                        }
                    });
                }*/


            }
        });
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_release_need;
    }


    @OnClick({R.id.province, R.id.check_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.province://省市区
                menuWindow = new SelectPicPopupWindow(ReleaseNeedActivity.this, han);
                menuWindow.showAtLocation(ReleaseNeedActivity.this.findViewById(R.id.Radd), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.check_image://选择图片
                if (ioncList.size() < 6) {
                    ImagemenuWindow = new SelectPicPopupWindow2(this, ImageitemsOnClick, "zhutu");
                    //显示窗口
                    ImagemenuWindow.showAtLocation(imageGV, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                } else {
                    TS.shortTime("最多只能选6张");
                }

                break;


        }
    }

    Handler han = new Handler() {

        public void handleMessage(Message msg) {
            int tag = msg.what;
            if (tag == 1) {
                Bundle data = msg.getData();
//                String pro,city,dro//省市县
                pro = data.getString("pro");
                city = data.getString("city");
                dro = data.getString("dro");
                Toast.makeText(ReleaseNeedActivity.this, pro + "" + city + "" + dro, Toast.LENGTH_SHORT).show();
                province.setText(pro + " " + city + " " + dro);
            }
            if (tag == 202) {//主图
                String path = msg.getData().getString("path");
                DD.d("202:" + path);
                ioncList.add(path);
                adapter = new ImageAdapter(ReleaseNeedActivity.this, ioncList);
                imageGV.setAdapter(adapter);
                //Request.UploadImage2(han, ReleaseNeedActivity.this, path, 101);
            } else if (tag == 203) {//附图
                String path = msg.getData().getString("path");
                DD.d("203:" + path);
                ioncList.add(path);
                adapter = new ImageAdapter(ReleaseNeedActivity.this, ioncList);
                imageGV.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                // Request.UploadImage2(han, ReleaseNeedActivity.this, path, 101);
            } else if (tag == 101) {
                String path = msg.getData().getString("path");
                // int wid = BitmapUtils.dip2px(ReleaseNeedActivity.this, 50);
                DD.d("101:" + path);


            }

        }
    };

    //为弹出窗口实现监听类
    private View.OnClickListener ImageitemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            ImagemenuWindow.dismiss();
            switch (v.getId()) {
                //相册
                case R.id.btn_take_photo:
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 110:
                if (data != null) {
                    CommonUtils.getPhoto(data, ReleaseNeedActivity.this);
                }
            case 111: // 图片缩放完成后
                if (data != null) {
                    String newName = CommonUtils.getUploadPhotoName();
                    DD.w("图片----：" + newName);
                    mHeadUrl = Contantor.IMAGEURLHEADER + newName;
                }
                break;
            case 0://相册 上传图片
                if (data != null) {
                    Tools.transImage(data, 480, 800, 80, han, 203, ReleaseNeedActivity.this);
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
                    Tools.transImage(intent, 200, 200, 80, han, 202, ReleaseNeedActivity.this);
                    /*if (ActivityOther == 1) {
                        Tools.transImage(intent, 480, 800, 80, han, 203, NewHouseReleaseActivity.this);
                    }*/

                } else {
                    Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
        }

    };
}
