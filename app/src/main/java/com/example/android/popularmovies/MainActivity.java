package com.example.android.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static final String API_KEY  = "";
    public static final String LANGUAGE = "en-US";
    public static final int CURRENT_PAGE = 1;

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
                .appendQueryParameter("api_key",API_KEY)
                .appendQueryParameter("language",LANGUAGE)
                .appendQueryParameter("page", ""+CURRENT_PAGE);

        String API_URL = builder.build().toString();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT);
        /*GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT);*/

        MovieAdapter movieAdapater = new MovieAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapater);
    }
}