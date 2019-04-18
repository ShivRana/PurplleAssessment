package com.shiv.purplleassessment.network;

import com.shiv.purplleassessment.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<News> getNews(@Query("country") String sources,
                       @Query("apiKey") String apiKey);
}