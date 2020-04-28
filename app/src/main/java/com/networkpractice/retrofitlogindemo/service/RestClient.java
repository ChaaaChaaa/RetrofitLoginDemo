package com.networkpractice.retrofitlogindemo.service;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.networkpractice.retrofitlogindemo.BuildConfig;
import com.networkpractice.retrofitlogindemo.model.AccountData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;


public class RestClient {
    public static RestMethods buildHTTPClient() {
        Retrofit retrofit = new Retrofit.Builder()
                //잘 보세용 이런건 스킬이에요 .
                // .baseUrl("http://question.api-namu.kro.kr:3000/")
                .baseUrl(BuildConfig.BASEURL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RestMethods.class);
    }


    private static OkHttpClient getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }


    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NonNull String message) {
                        Log.d("HTTP", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BODY);
        return httpLoggingInterceptor;
    }

//    private void callTargetResponse(){
//        Call<AccountData> callTargetResponse = buildHTTPClient().getAccountInfo("0034","Bearer "+token);
//        callTargetResponse.enqueue(new Callback<AccountData>() {
//            @Override
//            public void onResponse(Call<AccountData> call, Response<AccountData> response) {
//                AccountData accountData = response.body();
//                Toast.makeText(this," "+response.body(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<AccountData> call, Throwable t) {
//                Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}