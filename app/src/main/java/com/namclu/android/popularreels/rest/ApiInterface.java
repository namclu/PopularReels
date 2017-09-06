package com.namclu.android.popularreels.rest;

import com.namclu.android.popularreels.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by namlu on 06-Sep-17.
 *
 * The endpoints are defined inside of an interface using special retrofit annotations
 * to encode details about the parameters and request method. In addition, the return
 * value is always a parameterized Call<T> object such as Call<MovieResponse>
 */

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
}
