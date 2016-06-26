package com.yiw.circledemo2;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.lidong.photopicker.ImageCaptureManager;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.bean.ToolsHost;
import com.yiw.circledemo2.utils.MultipartRequest;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * @author lidong
 * @
 * @date 2016-02-29
 */
public class ReleaseFrendActivity extends JLBaseActivity {

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private static RequestQueue mSingleQueue;
    private static String ENDPOINT = ToolsHost.HEDEUT;

    private GridView gridView;
    private GridAdapter gridAdapter;
    private Button mButton;
    private String depp;
    private EditText textView;
    private String TAG = ReleaseFrendActivity.class.getSimpleName();

    String rul = ToolsHost.HEDEUT + "/app/talk/submit";

    int MAX_LENGTH = 100;                   //最大输入字符数500
    int Rest_Length = MAX_LENGTH;
    @Bind(R.id.content)
    EditText content;

    @Bind(R.id.numz)
    TextView numz;
    @Override
    public int setViewLayout() {
        return R.layout.releasefrendactivitylayout;
    }

    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        gridView = (GridView) findViewById(R.id.gridView);
        mButton = (Button) findViewById(R.id.button);
        textView = (EditText) findViewById(R.id.et_context);
        mSingleQueue = Volley.newRequestQueue(ReleaseFrendActivity.this);
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
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);

        // preview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("000000".equals(imgs)) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(ReleaseFrendActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(ReleaseFrendActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depp = textView.getText().toString().trim() != null ? textView.getText().toString().trim() : "woowoeo";
                uploadMany(imagePaths, depp, han);
            }
        });
    }

    public void uploadMany(ArrayList<String> paths, String desp, final Handler han) {
        String pro, cti, dit, add;
        Map<String, String> ap2 = new HashMap<String, String>();
        try {
            han.sendEmptyMessage(1);
            mSingleQueue = Volley.newRequestQueue(ReleaseFrendActivity.this);
            if (MyApplication.getInstan().getSheng() == null) {
                pro = "";
            } else {
                pro = MyApplication.getInstan().getSheng();
            }
            //----------------
            if (MyApplication.getInstan().getCity() == null) {
                cti = "";
            } else {
                cti = MyApplication.getInstan().getCity();
            }
            //----------------
            if (MyApplication.getInstan().getQu() == null) {
                dit = "";
            } else {
                dit = MyApplication.getInstan().getQu();
            }
            if (MyApplication.getInstan().getAddress() == null) {
                add = "";
            } else {
                add = MyApplication.getInstan().getAddress();
            }
            if (paths.size() > 0) {
                List<File> f = new ArrayList<File>();
                for (String s : paths) {
                    if (!s.equals("000000")) {
                        f.add(new File(s));
                    }
                }

                ap2.put("content", desp);
                ap2.put("userId", com.example.viewpagerdemo.ui.MyApplication.getInstan().getUser().getData().getId() + "");
                ap2.put("province", pro);
                ap2.put("city", cti);
                ap2.put("district", dit);
                ap2.put("address", add);
                MultipartRequest request = new MultipartRequest(rul, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ReleaseFrendActivity.this, "uploadSuccess,response = " + response, Toast.LENGTH_SHORT).show();
                        Log.i("YanZi", "success,response = " + response);
                        han.sendEmptyMessage(0);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        han.sendEmptyMessage(2);
                        Toast.makeText(ReleaseFrendActivity.this, "uploadError,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("YanZi", "error,response = " + error.getMessage());
                    }
                }, "files", f, ap2); //注意这个key必须是f_file[],后面的[]不能少
                mSingleQueue.add(request);
            } else {

                AjaxParams ap = new AjaxParams();
                ap2.put("content", desp);
                ap2.put("userId", com.example.viewpagerdemo.ui.MyApplication.getInstan().getUser().getData().getId() + "");
                ap2.put("province", pro);
                ap2.put("city", cti);
                ap2.put("district", dit);
                ap2.put("address", add);
                ap.put("files", "");

                new FinalHttp().post(rul, ap, new AjaxCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);
                        //DD.v("发布需求s:" + s);
                        try {
                            JSONObject js = new JSONObject(s);
                            if (js.getBoolean("success")) {
                                TS.shortTime("SS发布成功");
                                finish();
                            } else {
                                TS.shortTime("SS发布失败");
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

            }


        } catch (Exception e) {
        }
    }

    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int wt = msg.what;
            switch (wt) {
                case 0:
                    finish();
                    break;
                case 1:
                    showWait();
                    break;
                case 2:
                    closeWait();
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "list: " + "list = [" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    Log.d(TAG, "ListExtra: " + "ListExtra = [" + ListExtra.size());
                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            imagePaths.clear();
        }
        if (paths.contains("000000")) {
            paths.remove("000000");
        }
        paths.add("000000");
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        try {
            JSONArray obj = new JSONArray(imagePaths);
            Log.e("--", obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if (listUrls.size() == 7) {
                listUrls.remove(listUrls.size() - 1);
            }
            inflater = LayoutInflater.from(ReleaseFrendActivity.this);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_image_releasefrend, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);
            if (path.equals("000000")) {
                holder.image.setImageResource(R.drawable.addicon);
            } else {
                Glide.with(ReleaseFrendActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }
}
