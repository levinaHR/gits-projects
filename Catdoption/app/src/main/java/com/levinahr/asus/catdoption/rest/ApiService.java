package com.levinahr.asus.catdoption.rest;

import com.levinahr.asus.catdoption.model.CatModel;
import com.levinahr.asus.catdoption.model.ResponseErrorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("cat/api_get.php?all")
    Call<List<CatModel>> getAllCatList();

    @FormUrlEncoded
    @POST("cat/api_add_cat.php")
    Call<ResponseErrorModel> postAddCat(
            @Field("name") String name,
            @Field("age") String age,
            @Field("gender") int gender,
            @Field("breed") String breed,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("cat/api_update_cat.php")
    Call<ResponseErrorModel> postUpdateCat(@Field("id") String id);

    @FormUrlEncoded
    @POST("cat/api_delete_cat.php")
    Call<ResponseErrorModel> postDeleteCat(@Field("id") String id);
}
