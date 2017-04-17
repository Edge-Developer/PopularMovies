package com.example.android.popularmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by OPEYEMI OLORUNLEKE on 4/17/2017.
 */

public class JSONdata {

    @SerializedName("mMovies")
    private List<Movie> mMovies = null;

    public List<Movie> getMovies() {
        return mMovies;
    }
}