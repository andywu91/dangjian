package com.example.andy.dangjian.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/10/5.
 */

public enum HttpUtils {

    INSTANCE;

    private static final String BASE_URL = "http://211.149.194.149/SDPL/webAPI/test/interfaces/";
    private Retrofit refrofitInstance;

    public Retrofit getRetrofitInstance() {
        if (refrofitInstance == null) {
            refrofitInstance = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return refrofitInstance;
    }

}
