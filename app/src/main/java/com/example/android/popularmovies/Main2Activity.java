package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class Main2Activity extends AppCompatActivity {

    private NetworkImageView thumbnail;
    private TextView movieTitle;
    private TextView overview;
    private TextView textRating;
    private TextView releaseDate;
    private AppCompatRatingBar movieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        thumbnail = (NetworkImageView) findViewById(R.id.movie_thumbnail);
        movieTitle = (TextView) findViewById(R.id.movie_title2);
        overview = (TextView) findViewById(R.id.movie_plot);
        textRating = (TextView) findViewById(R.id.text_rating);
        releaseDate = (TextView) findViewById(R.id.release_date);
        movieRating = (AppCompatRatingBar) findViewById(R.id.movie_rating);

        Intent intent = getIntent();

        if (intent != null){
            if(intent.hasExtra("key")){
                String title = intent.getStringExtra("key");

                if (Movies_Singleton.getInstance().get(title) != null) {
                    Movie movie = Movies_Singleton.getInstance().get(title);
                    if(movie != null){
                        movieTitle.setText(movie.getOriginalTitle());
                        overview.setText(movie.getOverview());
                        textRating.setText(movie.getVoteAverage()+"/10");
                        releaseDate.setText(movie.getReleaseDate());
                        movieRating.setRating((float)(movie.getVoteAverage()/2));
                        thumbnail.setImageUrl(MovieAdapter.BASE_IMAGE_URL+movie.getPosterPath(), ConnectionManager.getImageLoader(this));
                    }
                }

            }
        }
    }
}
