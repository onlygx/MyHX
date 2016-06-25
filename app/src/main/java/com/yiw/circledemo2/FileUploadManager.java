package com.yiw.circledemo2;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.FormFile;
import com.yiw.circledemo2.bean.ToolsHost;
import com.yiw.circledemo2.utils.MultipartRequest;
import com.yiw.circledemo2.widgets.UServer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by lidong on 2016/1/28.
 */
public class FileUploadManager {
    private static RequestQueue mSingleQueue;
    private static String ENDPOINT = ToolsHost.HEDEUT;
    Retrofit sRetrofit;
    UServer apiManager;
    private List<String> filesPath = new ArrayList<String>();
    private FormFile[] filesList;

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
    public void uploadMany(final Activity c, ArrayList<String> paths, String desp, final Handler han) {
        String pro, cti, dit, add;
        Map<String, String> ap2 = new HashMap<String, String>();
        try {
            han.sendEmptyMessage(1);
            mSingleQueue = Volley.newRequestQueue(c);
            String rul = ToolsHost.HEDEUT + "/app/talk/submit";
            List<File> f = new ArrayList<File>();
            for (String s : paths) {
                f.add(new File(s));
            }

           // DD.d("tt==:" + new File(paths.get(0)).toString());
           // DD.d("tt==:" + new File(paths.get(1)).toString());

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
            ap2.put("content", desp);
            ap2.put("userId", com.example.viewpagerdemo.ui.MyApplication.getInstan().getUser().getData().getId() + "");
            ap2.put("province", pro);
            ap2.put("city", cti);
            ap2.put("district", dit);
            ap2.put("address", add);
            MultipartRequest request = new MultipartRequest(rul, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(c, "uploadSuccess,response = " + response, Toast.LENGTH_SHORT).show();
                    Log.i("YanZi", "success,response = " + response);
                    han.sendEmptyMessage(0);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    han.sendEmptyMessage(2);
                    Toast.makeText(c, "uploadError,response = " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("YanZi", "error,response = " + error.getMessage());
                }
            }, "files", f, ap2); //注意这个key必须是f_file[],后面的[]不能少
            mSingleQueue.add(request);


        } catch (Exception e) {
        }
    }
}
