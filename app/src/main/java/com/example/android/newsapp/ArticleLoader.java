package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    public ArticleLoader(Context context, String url) {
        super(context);
        aUrl = url;
    }

    @Override
    public List<Article> loadInBackground() {
        if (aUrl == null) {
            return null;
        }
        return Utils.fetchData(aUrl);
    }

    private static final String LOG_TAG = ArticleLoader.class.getName();
    private String aUrl;

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "OnStartLoading");
        forceLoad();
    }
}
