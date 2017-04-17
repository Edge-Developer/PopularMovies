package com.example.android.popularmovies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OPEYEMI OLORUNLEKE on 4/17/2017.
 */

class Movies_Singleton implements GetData.StoreMovies {
    public static Movies_Singleton movie_singleton;
    private List<Movie> movies = new ArrayList<>();
    public static Content_is_ready mContent_is_ready;

    private Movies_Singleton() {
        movie_singleton = new Movies_Singleton();
    }

    public static Movies_Singleton getInstance(Content_is_ready content_is_ready) {

        if (movie_singleton == null) {
            new Movies_Singleton();
        }
        mContent_is_ready = content_is_ready;
        return movie_singleton;
    }

    public static Movies_Singleton getInstance() {

        if (movie_singleton == null) {
            new Movies_Singleton();
        }
        return movie_singleton;
    }

    public Movie get(String title) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getOriginalTitle().matches(title)) {
                return movies.get(i);
            }
        }
        return null;
    }

    @Override
    public void movies(List<Movie> movies) {
        this.movies = movies;
        mContent_is_ready.updateTheUI(movies);
    }
    interface Content_is_ready{
        void updateTheUI(List<Movie> movies);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
