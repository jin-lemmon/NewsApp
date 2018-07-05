package com.example.android.newsapp;

import android.annotation.SuppressLint;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        final String shortUrl = art.getShortUrl();
        TextView art_author = listItem.findViewById(R.id.author);
        TextView art_section = listItem.findViewById(R.id.section);
        TextView art_date = listItem.findViewById(R.id.date);
        TextView art_headline = listItem.findViewById(R.id.headline);
        TextView art_trailText = listItem.findViewById(R.id.trailText);
        String artDate = artTime(art.getDate());
        art_date.setText(artDate);
        art_author.setText(art.getAuthor());
        art_section.setText(art.getSection());
        art_headline.setText(new StringBuilder().append(art.getHeadline()).append(" ").append(getContext().getString(R.string.by)).append(" ").append(art.getAuthor()).toString());
        art_trailText.setText(art.getTrailText());
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shortUrl));
                getContext().startActivity(intent);
            }
        });
        return listItem;
    }

    @SuppressLint("SimpleDateFormat")
    private static String artTime(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}