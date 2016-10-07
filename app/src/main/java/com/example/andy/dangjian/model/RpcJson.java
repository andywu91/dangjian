package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/7.
 */

public class RpcJson {

    @SerializedName("std")
    private Student student;

    public Student getStudent() {
        return student;
    }
}
