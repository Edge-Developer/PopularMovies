package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.MovieAdapter.Clicked;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Clicked {
    public static final String API_KEY = "11f4275ea0f71297a1d33044b675828f";
    public static final String LANGUAGE = "en-US";
    public static final int CURRENT_PAGE = 1;
    private RecyclerView recyclerView;
    private String API_URL;
    private TextView text__view;
    private GridLayoutManager gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath("popular")
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("language", LANGUAGE)
                .appendQueryParameter("page", "" + CURRENT_PAGE);

        text__view = (TextView) findViewById(R.id.text_view);

        API_URL = builder.build().toString();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        int columns = new Utility().CalculateNoOfColumns(this);
        gridLayout = new GridLayoutManager(MainActivity.this, columns);
        recyclerView.setLayoutManager(gridLayout);

        MovieAdapter movieAdapter = new MovieAdapter(this, this);

        recyclerView.setAdapter(movieAdapter);

        Movie movie;
        List<Movie> movies = new ArrayList<>();

        for (int i=0; i<20;i++){
            movie = new Movie(
                    "/tWqifoYuwLETmmasnGHO7xBjEtt.jpg"
                    ,getString(R.string.default_overview)
                    ,"2017-03-16"
                    ,"Beauty and the Beast"
                    ,150.167688
                    ,1690
                    ,6.9);

            movies.add(movie);
        }
        Movies_Singleton.getInstance().setMovies(movies);
        movieAdapter.addMovies(movies);
    }

    void load(String JSON_URL){
        new GetData().loadPosts(JSON_URL, this);
    }

    public void fab(View view) {
        openIntent("The Baby Boomer");
    }

    @Override
    public void onClick(String movieTitle) {
        openIntent(movieTitle);
    }

    void openIntent(String string){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("key ", string);
        startActivity(intent);
    }

    public class Utility {
        public int CalculateNoOfColumns(Context context){
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels/displayMetrics.density;
            int noOfColumnns = (int) (dpWidth/180);
            return noOfColumnns;
        }
    }
}