package com.example.andy.dangjian.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/5.
 */

public class CustomResponse {

    private String success;

    private String status;

    private Request request;

    private String res;

    @SerializedName("rpcJSON")
    private RpcJson rpcJson;

    public String getSuccess() {
        return success;
    }

    public Request getRequest() {
        return request;
    }

    public String getRes() {
        return res;
    }

    public String getStatus() {
        return status;
    }

    public RpcJson getRpcJson() {
        return rpcJson;
    }
}
