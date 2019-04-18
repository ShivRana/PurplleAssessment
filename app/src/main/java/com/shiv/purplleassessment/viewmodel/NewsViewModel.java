package com.shiv.purplleassessment.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.shiv.purplleassessment.model.NewsSupplier;
import com.shiv.purplleassessment.model.News;
import com.shiv.purplleassessment.utils.Utils;

public class NewsViewModel extends AndroidViewModel {
    private final LiveData<News> newsListObservable;

    public NewsViewModel(Application application) {
        super(application);
        String country = Utils.getCountry();
        newsListObservable = NewsSupplier.getInstance().getNews(country);
    }

    //sending live data of news to the View
    public LiveData<News> getNewsResponseObservable() {
        return newsListObservable;
    }
}
