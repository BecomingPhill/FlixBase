package com.thetechguys.android.flixbase.control;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.thetechguys.android.flixbase.R;
import com.thetechguys.android.flixbase.model.MovieResponse;
import com.thetechguys.android.flixbase.utilities.ApiClient;
import com.thetechguys.android.flixbase.utilities.ApiInterface;
import com.thetechguys.android.flixbase.view.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "c083761ec20e935b4194ae78e884e62e";
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView.Adapter mAdapter ;
    private List MovieData = new ArrayList<>();

    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.my_recycler_view)RecyclerView mRecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final int numCol = 2;

       mLayoutManager = new GridLayoutManager(this, numCol);

        mRecyclerView.setLayoutManager(mLayoutManager);






        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                MovieData.clear();
                MovieData.add(movies);
                mAdapter = new MovieAdapter(MovieData);



                mRecyclerView.setAdapter(mAdapter);



            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
    }

