package com.shiv.purplleassessment.network;

import com.shiv.purplleassessment.model.News;
import com.shiv.purplleassessment.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("rewards/vendorlogin.php")
    Call<List<User>> getNews(@Field("phone") String sources,
                             @Field("password") String apiKey,
                             @Field("type") String type

    );
}