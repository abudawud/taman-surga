package com.envoss.tamansurga.utils;

import com.envoss.tamansurga.helpers.ApiClient;
import com.envoss.tamansurga.interfaces.ApiService;

public class ApiUtil {
    private static final String BASE_URL = "http://192.168.254.22/";

    public ApiUtil() {

    }

    public static ApiService getAPIService() {
        return ApiClient.getRetrofit(BASE_URL).create(ApiService.class);
    }
}
