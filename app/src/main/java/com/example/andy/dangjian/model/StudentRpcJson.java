package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */

public class StudentRpcJson {

    @SerializedName("std")
    private List<Student> student;

    public List<Student> getStudent() {
        return student;
    }
}
