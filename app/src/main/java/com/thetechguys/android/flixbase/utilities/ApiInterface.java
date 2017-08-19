package com.thetechguys.android.flixbase.utilities;

import com.thetechguys.android.flixbase.model.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tshuutheniemvula on 10/08/2017.
 */

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopulardMovies(@Query("api_key") String apiKey);

    @GET("movie/")
    Call<MovieResponse> getMoviesData(@QueryMap Map <String, String> map);




}
