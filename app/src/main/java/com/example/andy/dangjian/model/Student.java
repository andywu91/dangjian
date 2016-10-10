package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/7.
 */

public class Student {

    private String name;

    @SerializedName("img_url")
    private String imgUrl;

    @SerializedName("sfz_url")
    private String sfzUrl;

    @SerializedName("xlzm_url")
    private String xlzmUrl;

    private String education;

    @SerializedName("student_id")
    private String studentId;

    private String address;

    private String birthday;

    private String nation;

    @SerializedName("political_status")
    private String politicalStatus;

    @SerializedName("tel")
    private String telephone;

    private String sex;

    public String getName() {
        return name;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSfzUrl() {
        return sfzUrl;
    }

    public String getXlzmUrl() {
        return xlzmUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNation() {
        return nation;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public String getSex() {
        return sex;
    }

    public String getTelephone() {
        return telephone;
    }
}
