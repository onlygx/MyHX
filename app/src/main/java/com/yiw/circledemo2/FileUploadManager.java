package com.yiw.circledemo2;

import android.os.Handler;
import android.util.Log;

import com.example.viewpagerdemo.ui.*;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.yiw.circledemo2.bean.ToolsHost;
import com.yiw.circledemo2.widgets.UServer;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by lidong on 2016/1/28.
 */
public class FileUploadManager {

    private static String ENDPOINT = ToolsHost.HEDEUT;
    Retrofit sRetrofit;
    UServer apiManager;

    public FileUploadManager() {
        Interceptor inter = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long t1 = System.nanoTime();
                Log.d("LD", "请求:" + String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));
                okhttp3.Response response = chain.proceed(chain.request());
                return response;
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(inter)
                //.retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(inter)
                .build();

        sRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiManager = sRetrofit.create(UServer.class);
    }


    /**
     * @param paths
     * @param desp
     * @param han
     */
    public void uploadMany(ArrayList<String> paths, String desp, final Handler han) {
        String rul = ToolsHost.HEDEUT + "/app/talk/submit";
        final File f = new File(paths.get(0));
        Log.d("LD", "tp:" + f.toString());
        AjaxParams ap = new AjaxParams();

        try {
            ap.put("files", paths.size() > 0 ? new File(paths.get(0)) : null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ap.put("content", desp);
        ap.put("userId", com.example.viewpagerdemo.ui.MyApplication.getInstan().getUser().getData().getId() + "");
        String pro, cti, dit, add;
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
        //----------------


        ap.put("province", pro);
        ap.put("city", cti);
        ap.put("district", dit);
        ap.put("address", add);
        DD.v("LD发朋友圈:" + rul + "?" + ap.toString());
        new FinalHttp().post(rul, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("LD LD发朋友圈s:" + s);
                try {
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                        TS.shortTime("说说发布成功");
                        han.sendEmptyMessage(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Log.w("LD", "ss:" + strMsg);
            }
        });


      /*Map<String, RequestBody> photos = new HashMap<>();
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                if(!paths.get(i).equals("000000")) {
                    File file = new File(paths.get(i));
                    Log.d("LD", "tup:" + file.toString());
                    photos.put("files", RequestBody.create(MediaType.parse("image*//*"), file));
//                    photos.put("files\"; filename=\"icon.png", RequestBody.create(MediaType.parse("multipart/form-data"), file));
                }
            }
        }
        Call<String> stringCall = apiManager.uploadImage2(desp, "7227619999906416263", "山东省",
                "济南市", "历下区", "经十路", photos);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response.message() + "]");
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Log.e(TAG, "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
            }
        });*/
//        RequestBody requestBody =
//                RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(0)));
//        RequestBody requestBody2 =
//                RequestBody.create(MediaType.parse("multipart/form-data"), new File(paths.get(1)));
       /* RequestBody requestBody3 =
                RequestBody.create(MediaType.parse("multipart/form-data"), ff[2]);*/
        //Map<String, RequestBody> params = new HashMap<String, RequestBody>();
        // params.put("file[]\"; filename=\"" + "11" + "", requestBody);
        // params.put("file[]\"; filename=\"" + ff[1].getName() + "", requestBody2);
        // params.put("file[]\"; filename=\"" + ff[2].getName() + "", requestBody3);
        //Call<String> model = service.upload_3("hello", params);
//        Call<String> stringCall = apiManager.uploadImage3(desp, "7227619999906416263", "山东省",
//                "济南市", "历下区", "经十路", requestBody);
//        stringCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
//                Log.d(TAG, "onResponse() called with: " + "call = [" + call + "], response = [" + response.message() + "]");
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<String> call, Throwable t) {
//                Log.e(TAG, "onFailure() called with: " + "call = [" + call + "], t = [" + t + "]");
//            }
//        });
    }
}
