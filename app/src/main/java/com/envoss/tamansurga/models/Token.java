package com.envoss.tamansurga.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("apiError")
    @Expose
    private ApiError apiError;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("role")
    @Expose
    private Integer role;

    @SerializedName("issued")
    @Expose
    private String issued;

    @SerializedName("expired")
    @Expose
    private String expired;

    @SerializedName("user")
    @Expose
    private User user;


    public Token() {
    }

    public ApiError getApiError() {
        return apiError;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Integer getRole() {
        return role;
    }

    public String getIssued() {
        return issued;
    }

    public String getExpired() {
        return expired;
    }

    public User getUser() {
        return user;
    }
}
