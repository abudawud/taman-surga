package com.envoss.tamansurga.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiError {
    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("message")
    @Expose
    private String messege;

    public ApiError() {
    }

    public Integer getCode() {
        return code;
    }

    public String getMessege() {
        return messege;
    }
}
