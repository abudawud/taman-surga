package com.envoss.tamansurga.interfaces;

import com.envoss.tamansurga.models.Token;
import com.envoss.tamansurga.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("auth")
    Call<Token> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users")
    Call<User> addUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("role") Integer role,
            @Field("email") String email,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName
    );



}
