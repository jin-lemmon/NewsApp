package com.example.android.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String LOG_TAG = MainActivity.class.getName();
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
            List<Article> result = Utils.fetchData(aUrl);
            ListView listView = findViewById(R.id.list);
            ArticleAdapter anAdapter = new ArticleAdapter(this, 0, new ArrayList<>(result));
            TextView emptyView = findViewById(R.id.emptyView);
            listView.setEmptyView(emptyView);
            listView.setAdapter(anAdapter);
        }

    }
}
