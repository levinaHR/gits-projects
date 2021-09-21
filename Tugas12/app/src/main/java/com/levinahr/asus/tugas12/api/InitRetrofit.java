package com.levinahr.asus.tugas12.api;

import com.levinahr.asus.tugas12.BuildConfig;
import com.levinahr.asus.tugas12.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    private static Retrofit setInit(){
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getInstance(){
        return setInit().create(ApiService.class);
    }
}
