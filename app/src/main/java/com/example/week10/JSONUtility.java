package com.example.week10;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {
    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();
        try {

            InputStream is = context.getResources().openRawResource(R.raw.movies);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");


            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String title = jsonObject.optString("title", "Unknown Title");
                int year = parseYear(jsonObject.optString("year", "0"));
                String genre = jsonObject.optString("genre", "Unknown Genre");
                String poster = jsonObject.optString("poster", "default_poster");


                movies.add(new Movie(title, year, genre, poster));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }


    private static int parseYear(String yearString) {
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
