package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OPEYEMI OLORUNLEKE on 4/16/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mMovies;
    protected final static String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    private Context context;
    private Clicked mClicked;


    public MovieAdapter(Context context, Clicked clicked) {
        mMovies = new ArrayList<>();
        this.context = context;
        mClicked = clicked;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layoutResourceID = R.layout.single;
        boolean shouldAttachToParent = false;
        View view = inflater.inflate(layoutResourceID, parent, shouldAttachToParent);
        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void addMovies(List<Movie> movies) {

        if (null != movies)
            for (Movie movie : movies) {
                mMovies.add(movie);
            }
            notifyDataSetChanged();

    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        NetworkImageView moviePoster;
        RatingBar mRatingBar;
        TextView movieTitle;
        Movie iMovie;

        public MovieHolder(View itemView) {
            super(itemView);
            moviePoster = (NetworkImageView) itemView.findViewById(R.id.movie_poster);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            iMovie = movie;
            movieTitle.setText(movie.getOriginalTitle());
            float rating = (float) (movie.getVoteAverage()/2);
            mRatingBar.setRating(rating);
            moviePoster.setImageUrl(BASE_IMAGE_URL+movie.getPosterPath(), ConnectionManager.getImageLoader(context));
        }

        @Override
        public void onClick(View v) {
            mClicked.onClick(iMovie.getOriginalTitle());
        }
    }

    public interface Clicked {
        void onClick (String movieTitle);
    }
}
