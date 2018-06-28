package com.example.android.newsapp;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {
    String LOG_TAG = MainActivity.class.getName();
    private static final int LOADER_ID = 1;
    public ArticleAdapter anAdapter;
    String aUrl = "https://content.guardianapis.com/search?order-by=newest&show-fields=trailText%2Cheadline%2CshortUrl&page-size=20&q=android%20AND%20(develop%20OR%20development)&api-key=3131c94e-6ac8-4d7e-bc20-49db76abf4d1";

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(LOG_TAG, "onCreate called");
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(LOADER_ID, null, this);
            ListView listView = findViewById(R.id.list);
             anAdapter = new ArticleAdapter(this,0,new ArrayList<Article>());
            TextView emptyView = findViewById(R.id.emptyView);
            listView.setEmptyView(emptyView);
            listView.setAdapter(anAdapter);
        }
        TextView emptyView = findViewById(R.id.emptyView);
        emptyView.setText(R.string.no_internet);


    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        return new ArticleLoader(this, aUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        TextView emptyView = findViewById(R.id.emptyView);
        emptyView.setText(R.string.no_news_found);
        ProgressBar circle = findViewById(R.id.progress);
        circle.setVisibility(View.GONE);
        anAdapter.clear();
        if (data != null && !data.isEmpty()) {
            anAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        anAdapter.clear();
    }

}
