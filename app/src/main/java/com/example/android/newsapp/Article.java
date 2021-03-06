package com.example.android.newsapp;

public class Article {

    private String anAuthor;
    private String aHeadline;
    private String aTrailText;
    private String aShortUrl;
    private String aDate;
    private String aSection;
    private int aWords;

    public Article(String author, String headline, String trailText, String shortUrl, String date, String section,int words) {
        anAuthor = author;
        aHeadline = headline;
        aTrailText = trailText;
        aShortUrl = shortUrl;
        aDate = date;
        aSection = section;
        aWords = words;
    }

    public String getHeadline() {
        return aHeadline;
    }

    public String getShortUrl() {
        return aShortUrl;
    }

    public String getTrailText() {
        return aTrailText;
    }

    public String getDate() {
        return aDate;
    }

    public String getSection() {
        return aSection;
    }

    public String getAuthor() {
        return anAuthor;
    }

    public int getWords() {
        return aWords;
    }
}