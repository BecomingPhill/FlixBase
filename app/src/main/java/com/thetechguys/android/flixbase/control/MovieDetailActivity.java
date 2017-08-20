package com.thetechguys.android.flixbase.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thetechguys.android.flixbase.R;
import com.thetechguys.android.flixbase.utilities.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_activity_poster)ImageView mDetailImageView;
    @BindView(R.id.detail_activity_movie_title)TextView mDetailMovieTitleTextView;
    @BindView(R.id.detail_activity_synopsis)TextView mMovieOverviewView;
    @BindView(R.id.detail_activity_voter_rating)TextView mMovieRatingView;
    @BindView(R.id.detail_activity_release_date)TextView mReleaseDateView;
    @BindView(R.id.detail_activity_error)TextView mDetailErrorView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent startIntent = getIntent();
        Bundle extras = startIntent.getExtras();




        if(extras != null){
            if(extras.containsKey("detail_poster_path")
                    && extras.containsKey("detail_synopsis")
                    && extras.containsKey("detail_voter_average")
                    && extras.containsKey("detail_movie_release_date")
                    && extras.containsKey("detail_movie_title")){
                String posterpath = extras.getString("detail_poster_path");
                String movietitle = extras.getString("detail_movie_title");
                String movieSynopsis = extras.getString("detail_synopsis");
                String movieReleaseDate = Constants.DETAIL_DATE_MESSAGE + extras.getString("detail_movie_release_date");

                Double movieVoteAverage = extras.getDouble("detail_voter_average");
                String voteString = Constants.DETAIL_VOTE_MESSAGE + movieVoteAverage;



                String movieImageUrl = Constants.MOVIE_DB_POSTER_URL + Constants.POSTER_PHONE_SIZE + posterpath;
                Picasso.with(this).load(movieImageUrl).placeholder(R.color.colorPrimary).into(mDetailImageView);
                mDetailMovieTitleTextView.setText(movietitle);
                mReleaseDateView.setText(movieReleaseDate);
                mMovieOverviewView.setText(movieSynopsis);
                mMovieRatingView.setText(voteString);
            }
            else {
                String error = "Shit is null";
                mDetailErrorView.setText(error);
            }
        }







    }
}
