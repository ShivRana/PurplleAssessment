package com.shiv.purplleassessment.network;


import com.shiv.purplleassessment.model.News;
import com.shiv.purplleassessment.model.User;

import java.util.List;

import retrofit2.Callback;

public class APIRequest {

    private static APIRequest instance;

    public static APIRequest getInstance() {
        if (instance == null) {
            instance = new APIRequest();
        }
        return instance;
    }

    public void getNews(String country, String key, String type, Callback<List<User>> callback) {
        ApiClient.getClient().create(ApiInterface.class).getNews(country, key, type).enqueue(callback);
    }
}
