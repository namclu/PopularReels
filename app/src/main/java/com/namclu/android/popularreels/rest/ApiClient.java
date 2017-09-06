package com.namclu.android.popularreels.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by namlu on 06-Sep-17.
 *
 * To send network requests to an API, use the Retrofit Builder class and
 * specify the base URL for the service.
 */

public class ApiClient {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
