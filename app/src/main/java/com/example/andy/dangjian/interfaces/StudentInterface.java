package com.example.andy.dangjian.interfaces;

import com.example.andy.dangjian.model.CustomResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/7.
 */

public interface StudentInterface {

    @POST("index.do")
    Call<CustomResponse> registerStudent(@QueryMap Map<String,String> map);

    @POST("index.do")
    Call<CustomResponse> getStudentInfo(@QueryMap Map<String,String> map);
}
