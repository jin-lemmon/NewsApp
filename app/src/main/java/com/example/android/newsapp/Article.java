package com.example.android.newsapp;

public class Article {

    private String aHeadline;
    private String aTrailText;
    private String aShortUrl;
    private String aThumbnail;
    private String aDate;
    private String aSection;

    public Article(String headline, String trailText, String shortUrl, String thumbnail,
                   String date, String section) {
        aHeadline = headline;
        aTrailText = trailText;
        aShortUrl = shortUrl;
        aThumbnail = thumbnail;
        aDate = date;
        aSection = section;

    }


    public String getaHeadline() {
        return aHeadline;
    }

    public String getaShortUrl() {
        return aShortUrl;
    }

    public String getaThumbnail() {
        return aThumbnail;
    }

    public String getaTrailText() {
        return aTrailText;
    }

    public String getDate() {
        return aDate;
    }
}

