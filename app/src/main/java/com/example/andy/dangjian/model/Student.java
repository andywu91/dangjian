package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/7.
 */

public class Student {

    private String name;

    @SerializedName("pid_url")
    private String pidUrl;

    private String education;

    @SerializedName("student_id")
    private String studentId;

    private String address;

    public String getName() {
        return name;
    }

    public String getPidUrl() {
        return pidUrl;
    }

    public String getEducation() {
        return education;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAddress() {
        return address;
    }
}
