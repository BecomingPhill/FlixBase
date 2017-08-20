package com.thetechguys.android.flixbase.control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private final static String API_KEY = "KEYS OPEN DOORS!";
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView.Adapter mAdapter ;
    private List<MovieData> MovieData = new ArrayList<>();
    private String sort_pop = "popular";

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


        //Test for key
        //TODO 1 Implement app start without API key
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "You best get an API KEY from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        callMovieData(sort_pop,API_KEY);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_high) {
            String sort_top = "top_rated";

            callMovieData(sort_top, API_KEY);
            return true;
        }
        else if(id == R.id.action_sort_pop){

            callMovieData(sort_pop, API_KEY);
            return true;
            }
        return super.onOptionsItemSelected(item);
    }

    public void updateData(List<MovieData>movieDatas){
        mRecyclerView.setAdapter(new MovieAdapter(movieDatas));
        mAdapter.notifyDataSetChanged();

    }

    public void callMovieData(String string, String key){
        //Create Interface
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        //Attach call to interface

        Call<MovieResponse> call = apiService.getMoviesData(string, API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {


                if(response != null){
                    //Log.d(TAG, "There is something in " + response);
                    List<MovieData> Response = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + Response.size());
                    updateData(Response);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
    }



