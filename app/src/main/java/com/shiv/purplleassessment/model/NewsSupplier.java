package com.shiv.purplleassessment.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shiv.purplleassessment.BuildConfig;
import com.shiv.purplleassessment.network.APIRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSupplier {
    private static final NewsSupplier INSTANCE = new NewsSupplier();

    public static NewsSupplier getInstance() {
        return INSTANCE;
    }

    public LiveData<News> getNews(String country) {
        final MutableLiveData<News> data = new MutableLiveData<>();
        APIRequest.getInstance().getNews(country, BuildConfig.NEWS_API_KEY, new Callback<News>() {

            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.v("response", response.body().getStatus());
                        data.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;

    }
}