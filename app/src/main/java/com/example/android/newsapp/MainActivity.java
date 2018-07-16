package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {
    String LOG_TAG = MainActivity.class.getName();
    private static final int LOADER_ID = 1;
    public ArticleAdapter anAdapter;
    String aUrl = "https://content.guardianapis.com/search?";

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
            anAdapter = new ArticleAdapter(this, 0, new ArrayList<Article>());
            TextView emptyView = findViewById(R.id.emptyView);
            listView.setEmptyView(emptyView);
            listView.setAdapter(anAdapter);
        }
        TextView emptyView = findViewById(R.id.emptyView);
        emptyView.setText(R.string.no_internet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
//order-by=newest&show-fields=all&page-size=20&q=android%20AND%20(develop%20OR%20development)&api-key=3131c94e-6ac8-4d7e-bc20-49db76abf4d1//*
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String apiKey = BuildConfig.ApiKey;
        String pageSize = sharedPrefs.getString(
                getString(R.string.max_page_key),
                getString(R.string.maximum_page_default));
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));
        Uri baseUri = Uri.parse(aUrl);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("show-fields", "all");
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("q", "android AND (develop OR development)");
        uriBuilder.appendQueryParameter("api-key", apiKey);
        return new ArticleLoader(this, uriBuilder.toString());
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