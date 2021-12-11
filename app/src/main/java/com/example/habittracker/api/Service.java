package com.example.habittracker.api;

import com.example.habittracker.model.LoginJSON;
import com.example.habittracker.model.RecuperarSenhaJSON;
import com.example.habittracker.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    @GET("user/{id}")
    Call<User> showUser(@Path("id") String id);

    @POST("user")
    Call<User> createUser(@Body User user);

    @POST("login")
    Call<User> loginUser(@Body LoginJSON loginJSON);

    @PUT("user/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

    @PUT("password/{id}")
    Call<User> updatePassword(@Path("id") String id, @Body RecuperarSenhaJSON json);
}
