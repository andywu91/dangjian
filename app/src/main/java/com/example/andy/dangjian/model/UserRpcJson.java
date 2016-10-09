package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/7.
 */

public class UserRpcJson {

    @SerializedName("std")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
