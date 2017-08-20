package com.thetechguys.android.flixbase.utilities;

import com.thetechguys.android.flixbase.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tshuutheniemvula on 10/08/2017.
 */

public interface ApiInterface {
    /*@GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopulardMovies(@Query("api_key") String apiKey);*/

    @GET("movie/{part}")
    Call<MovieResponse> getMoviesData(@Path ("part")String part, @Query("api_key") String apiKey);




}
