package com.example.androidapp_exe.Model;

import com.example.androidapp_exe.Entity.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<NewsResponse> getNews(
            @Query("country") String country, @Query("pageSize") int pagesize,
            @Query("apiKey") String apiKey
    );


    @GET("top-headlines")
    Call<NewsResponse> getCategory(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apiKey
    );
}
