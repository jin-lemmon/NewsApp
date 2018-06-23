package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

public ArticleLoader (Context context){
    super(context);
}

    @Override
    public List<Article> loadInBackground() {
        return null;
    }
}
