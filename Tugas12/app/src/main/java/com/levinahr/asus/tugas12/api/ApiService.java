package com.levinahr.asus.tugas12.api;

import com.levinahr.asus.tugas12.BuildConfig;
import com.levinahr.asus.tugas12.model.TopRatedResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(BuildConfig.MOVIE + "top_rated?api_key=" + BuildConfig.TOKEN)
    Call<TopRatedResponse> top_rated();
}
