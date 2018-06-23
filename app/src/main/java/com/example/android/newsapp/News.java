package com.example.android.newsapp;

public class News {

    private String aHeadline;
    private String aTrailText;
    private String aShortUrl;
    private String aThumbnail;


    public News(String headline, String trailText, String shortUrl, String thumbnail) {
        aHeadline = headline;
        aTrailText = trailText;
        aShortUrl = shortUrl;
        aThumbnail = thumbnail;
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
}

