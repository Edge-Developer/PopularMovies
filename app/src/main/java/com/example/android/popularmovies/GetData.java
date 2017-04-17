package com.example.android.popularmovies;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OPEYEMI OLORUNLEKE on 4/16/2017.
 */

public class GetData {

    private StoreMovies mStoreMovies;

    public GetData() {
    }
    public GetData(StoreMovies mStoreMovies) {
        this.mStoreMovies = mStoreMovies;
    }

    public void loadPosts(final String API_URL, final Context context) {

        final List<Movie> movies = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONdata example = new Gson().fromJson(response, JSONdata.class);

                for (Movie result : example.getMovies()) {
                    movies.add(result);
                }
                mStoreMovies.movies(movies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

        ConnectionManager.getInstance(context).add(stringRequest);
    }


    public interface StoreMovies {
        void movies (List<Movie> movies);
    }
}