package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class Utils {
    private static int readTimeOut = 15000;
    private static int connectionTimeOut = 10000;

    public static final String LOG_TAG = Utils.class.getName();

    public Utils() {
    }

    public static List<Article> fetchData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        return extractNews(jsonResponse);
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(readTimeOut);
            urlConnection.setConnectTimeout(connectionTimeOut);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException thrown");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static List<Article> extractNews(String jsonResponse) {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            JSONObject baseJSONResponse = new JSONObject(jsonResponse);
            JSONObject response = baseJSONResponse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject article = results.getJSONObject(i);
                String sectionName = article.getString("sectionName");
                String date = article.getString("webPublicationDate");
                JSONObject fields = article.getJSONObject("fields");
                String author = fields.getString("byline");
                String headline = fields.getString("headline");
                String trailText = fields.getString("trailText");
                String shortUrl = fields.getString("shortUrl");
                int words = fields.getInt("wordcount");
                Article news = new Article(author, headline, trailText, shortUrl, date, sectionName,words);
                articles.add(news);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON ", e);
        }
        return articles;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
}
