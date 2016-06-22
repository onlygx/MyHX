package com.yiw.circledemo2.widgets;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by huaping on 2016/6/21.
 */
public interface UServer {
    /**
     * 上传一张图片
     *
     * @param description
     * @param imgs
     * @return
     */
    @Multipart
    @POST("/app/talk/submit")
    Call<String> uploadImage0(
            @Part("content") String description,
            @Part("userId") String id,
            @Part("province") String pro,
            @Part("city") String ct,
            @Part("district") String dit,
            @Part("address") String add,
            @Part("files") RequestBody imgs);


    /**
     * 上传6张图片
     *
     * @param description
     * @param imgs2
     * @return
     */
    @Multipart
    @POST("/app/talk/submit")
    Call<String> uploadImage(@Part("content") String description,
                             @Part("userId") String id,
                             @Part("province") String pro,
                             @Part("city") String ct,
                             @Part("district") String dit,
                             @Part("address") String add,
                             @Part("file\"; files=\"image.png\"") RequestBody imgs2
    );

    /**
     * 简便写法
     *
     * @param description
     * @param imgs1
     * @return
     */
    @Multipart
    @POST("/app/talk/submit")
    Call<String> uploadImage(@Part("content") String description,
                             @Part("userId") String id,
                             @Part("province") String pro,
                             @Part("city") String ct,
                             @Part("district") String dit,
                             @Part("address") String add,
                             @Part MultipartBody.Part imgs1);

    @Multipart
    @POST("/app/talk/submit")
    Call<String> uploadImage2(@Part("content") String description,
                              @Part("userId") String id,
                              @Part("province") String pro,
                              @Part("city") String ct,
                              @Part("district") String dit,
                              @Part("address") String add,
                              @PartMap Map<String, RequestBody> imgs1);

    @Multipart
    @POST("/app/talk/submit")
    Call<String> uploadImage3(@Part("content") String description,
                              @Part("userId") String id,
                              @Part("province") String pro,
                              @Part("city") String ct,
                              @Part("district") String dit,
                              @Part("address") String add,
                              @Part("files/") RequestBody imgs);

}
