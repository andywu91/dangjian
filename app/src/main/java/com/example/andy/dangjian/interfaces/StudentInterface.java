package com.example.andy.dangjian.interfaces;

import com.example.andy.dangjian.model.StudentResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/7.
 */

public interface StudentInterface {

    @POST("index.do")
    Call<StudentResponse> registerStudent(@QueryMap Map<String,String> map);

    @POST("index.do")
    Call<StudentResponse> getStudentInfo(@QueryMap Map<String,String> map);

    @Multipart
    @POST("index.do")
    Call<StudentResponse> uploadImage(@QueryMap Map<String,String> map,@Part List<MultipartBody.Part> partList);
}
