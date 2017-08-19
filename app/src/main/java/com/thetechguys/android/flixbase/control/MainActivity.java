package com.thetechguys.android.flixbase.control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.thetechguys.android.flixbase.R;
import com.thetechguys.android.flixbase.model.MovieData;
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

    private final static String API_KEY = "KEYS OPEN DOORS, KEYS KEYS OPEN DOORS";
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView.Adapter mAdapter ;
    private List<MovieData> MovieData = new ArrayList<>();

    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.my_recycler_view)RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);

        final int numCol = 2;
        mLayoutManager = new GridLayoutManager(this, numCol);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieAdapter(MovieData);
        mRecyclerView.setAdapter(mAdapter);


        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please get an API KEY from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        //Get data

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response != null){
                    //Log.d(TAG, "There is something in " + response);
                    List<MovieData> Response = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + Response.size());
                   mRecyclerView.setAdapter(new MovieAdapter(Response));


                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }


    public void updateData(ArrayList<MovieData>movieDatas){






       // mAdapter.notifyDataSetChanged();

    }

    }



