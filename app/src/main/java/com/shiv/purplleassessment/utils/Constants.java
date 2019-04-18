package com.shiv.purplleassessment.utils;

import com.shiv.purplleassessment.BuildConfig;

public class Constants {
    public static final String BASE_URL = "https://newsapi.org/v2/";

    //API Key
    public static final String API_KEY = BuildConfig.NEWS_API_KEY;
    public static final String ARTICLE_DATA = "article";
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    static final String OUTPUT_DATE_FORMAT = "E, d MMM yyyy";
    static final String TIME_FORMAT = "hh:mm a";
}
