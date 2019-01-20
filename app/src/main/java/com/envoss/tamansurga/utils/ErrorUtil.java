package com.envoss.tamansurga.utils;

import com.envoss.tamansurga.helpers.ApiClient;
import com.envoss.tamansurga.helpers.ServiceGenerator;
import com.envoss.tamansurga.models.ApiError;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                ServiceGenerator.retrofit
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
