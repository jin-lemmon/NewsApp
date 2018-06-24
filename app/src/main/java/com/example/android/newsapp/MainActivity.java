package com.example.android.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String LOG_TAG = MainActivity.class.getName();
    String aUrl = "https://content.guardianapis.com/search?q=12%20years%20a%20slave&format=json&tag=film/film,tone/reviews&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&show-refinements=all&order-by=relevance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(LOG_TAG, "onCreate called");
        List<Article> result = Utils.fetchData(aUrl);
        ListView listView = findViewById(R.id.list);
        ArticleAdapter anAdapter = new ArticleAdapter(this, 0, new ArrayList<>(result));
        TextView emptyView = findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
        listView.setAdapter(anAdapter);

    }
}
