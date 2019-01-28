package com.envoss.tamansurga.interfaces;

import com.envoss.tamansurga.models.Conference;
import com.envoss.tamansurga.models.Place;
import com.envoss.tamansurga.models.Token;
import com.envoss.tamansurga.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("places")
    Call<List<Place>> getPlaces(
            @Query("token") String token
    );

    @GET("{category}/{id}/conferences")
    Call<List<Conference>> getConferenceBy(
            @Path("category") String category,
            @Path("id") Integer id,
            @Query("token") String token

    );

}
