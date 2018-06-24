package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.article_list, parent, false);
        }
        Article art = getItem(position);
        String date = art.getDate();
        String title = art.getDate();
        String headline = art.getDate();
        final String shortUrl = art.getShortUrl();

        TextView art_date = listItem.findViewById(R.id.date);
        TextView art_title = listItem.findViewById(R.id.headline);
        TextView art_headline = listItem.findViewById(R.id.trailText);
        art_date.setText(date);
        art_headline.setText(headline);
        art_title.setText(title);
               return listItem;
    }
}
