package com.shiv.purplleassessment.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shiv.purplleassessment.BuildConfig;
import com.shiv.purplleassessment.network.APIRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSupplier {
    private static final NewsSupplier INSTANCE = new NewsSupplier();

    public static NewsSupplier getInstance() {
        return INSTANCE;
    }

    public LiveData<List<User>> getNews(String country) {
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        APIRequest.getInstance().getNews("9835412345", "1234", "2", new Callback<List<User>>() {

            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.v("response", response.body().get(0).getStatus());
                        data.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;

    }
}