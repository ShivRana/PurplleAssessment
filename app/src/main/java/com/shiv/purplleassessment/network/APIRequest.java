package com.shiv.purplleassessment.network;


import com.shiv.purplleassessment.model.News;

import retrofit2.Callback;

public class APIRequest {

    private static APIRequest instance;

    public static APIRequest getInstance() {
        if (instance == null) {
            instance = new APIRequest();
        }
        return instance;
    }

    public void getNews(String country, String key, Callback<News> callback) {
        ApiClient.getClient().create(ApiInterface.class).getNews(country, key).enqueue(callback);
    }
}
