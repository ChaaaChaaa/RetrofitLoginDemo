package com.networkpractice.retrofitlogindemo;


import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestMethods {

    @POST("v1/auth/login")
    Call<LoginData> login(@Query("name") String name, @Query("password") String password);

    @POST("v1/auth/register")
    Call<LoginData> register(@Query("name") String name, @Query("password") String password);
}
