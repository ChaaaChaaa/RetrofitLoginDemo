package com.networkpractice.retrofitlogindemo;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestMethods {

    //포스트는 Query x
    //Post -> Field 또는 Body 또는 등등
    //Http 다시 공부하시기
    // 동훈님 문서 보는 방법은 음.................음..?음.........언제설명하지 ..
    //갓뎀..

    @FormUrlEncoded //요 녀석이 바로 application/x-www-form-urlencoded 얘를 뜻하는 놈
    @POST("v1/auth/login")
    Call<LoginData> login(@Field("nickname") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("v1//users")
    Call<RegisterData> register(@Field("nickname") String name, @Field("password") String password);
}

