package com.thetechguys.android.flixbase.utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tshuutheniemvula on 08/08/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


           public static Retrofit getClient() {
               if (retrofit==null) {
                   HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                   interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                   OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                   retrofit = new Retrofit.Builder()
                           .baseUrl(BASE_URL)
                           .client(client)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();
               }
               return retrofit;
           }
       }























