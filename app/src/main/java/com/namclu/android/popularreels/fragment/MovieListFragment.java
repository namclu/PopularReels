package com.namclu.android.popularreels.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.namclu.android.popularreels.BuildConfig;
import com.namclu.android.popularreels.adapter.MoviesAdapter;
import com.namclu.android.popularreels.R;
import com.namclu.android.popularreels.model.Movie;
import com.namclu.android.popularreels.model.MovieResponse;
import com.namclu.android.popularreels.rest.ApiClient;
import com.namclu.android.popularreels.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by namlu on 7/6/2017.
 *
 * Fragment to display a list of movies
 */

public class MovieListFragment extends Fragment implements MoviesAdapter.OnMovieClickListener {

    private static final String TAG = MovieListFragment.class.getSimpleName();
    private static final String API_KEY = BuildConfig.MOVIES_DB_API_KEY;
    private static final String FRAGMENT_MOVIE_DETAILS = "FRAGMENT_MOVIE_DETAILS";

    // Class variables
    private MoviesAdapter mMoviesAdapter;
    private GridView mGridView;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refreshMovies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Find references to views
        mGridView = (GridView) getView().findViewById(R.id.grid_view_movie);

        refreshMovies();
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    /*
    * Method to get a list of Movies and update GridView
    * */
    private void refreshMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiInterface.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getMovies();
                mMoviesAdapter = new MoviesAdapter(MovieListFragment.this, movies);
                mGridView.setAdapter(mMoviesAdapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void OnMovieClicked(Movie movie) {
        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movie);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, FRAGMENT_MOVIE_DETAILS)
                .addToBackStack(FRAGMENT_MOVIE_DETAILS)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
