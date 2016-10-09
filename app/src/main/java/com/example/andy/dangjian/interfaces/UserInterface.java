package com.example.andy.dangjian.interfaces;

import com.example.andy.dangjian.model.StudentResponse;
import com.example.andy.dangjian.model.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/5.
 */

public interface UserInterface {

    @POST("index.do")
    Call<UserResponse> registerUser(@QueryMap Map<String,String> map);

    @POST("index.do")
    Call<UserResponse> loginUser(@QueryMap Map<String,String> map);
}
