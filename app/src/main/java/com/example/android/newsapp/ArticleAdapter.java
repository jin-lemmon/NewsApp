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
        final String shortUrl = art.getShortUrl();
        TextView art_author = listItem.findViewById(R.id.author);
        TextView art_section = listItem.findViewById(R.id.section);
        TextView art_date = listItem.findViewById(R.id.date);
        TextView art_headline = listItem.findViewById(R.id.headline);
        TextView art_trailText = listItem.findViewById(R.id.trailText);
        art_date.setText(art.getDate());
        art_author.setText(art.getAuthor());
        art_section.setText(art.getSection());
        art_headline.setText(art.getHeadline());
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
}
//    private int getSectionColor (String section) {
//int sectioncolor;
//        switch (section) {
//            case "Technology":
//                sectioncolor =
//                break;
//            case 1:
//                magnitudeColorResourceId = R.color.magnitude1;
//                break;
//            case 2:
//                magnitudeColorResourceId = R.color.magnitude2;
//                break;
//            case 3:
//                magnitudeColorResourceId = R.color.magnitude3;
//                break;
//            case 4:
//                magnitudeColorResourceId = R.color.magnitude4;
//                break;
//            case 5:
//                magnitudeColorResourceId = R.color.magnitude5;
//                break;
//            case 6:
//                magnitudeColorResourceId = R.color.magnitude6;
//                break;
//            case 7:
//                magnitudeColorResourceId = R.color.magnitude7;
//                break;
//            case 8:
//                magnitudeColorResourceId = R.color.magnitude8;
//                break;
//            case 9:
//                magnitudeColorResourceId = R.color.magnitude9;
//                break;
//            default:
//                magnitudeColorResourceId = R.color.magnitude10plus;
//                break;
//        }
//        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
//    }
