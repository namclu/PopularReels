package com.namclu.android.popularreels.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.namclu.android.popularreels.BuildConfig;
import com.namclu.android.popularreels.R;
import com.namclu.android.popularreels.adapter.MoviesAdapter;
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
    private static final int TOP_RATED_MOVIES = 1;
    private static final int POPULAR_MOVIES = 2;

    // Class variables
    private MoviesAdapter mMoviesAdapter;
    private GridView mGridViewMovies;
    private ProgressBar mProgressBar;
    private TextView mTextNoNetwork;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mPreferenceEditor;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mSharedPreferences = getActivity().getSharedPreferences(
                getString(R.string.preference_key), Context.MODE_PRIVATE);
        mPreferenceEditor = mSharedPreferences.edit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_top_rated:
                loadMovies(TOP_RATED_MOVIES);
                mPreferenceEditor.putInt(getString(R.string.preference_selection), TOP_RATED_MOVIES);
                mPreferenceEditor.commit();
                return true;
            case R.id.action_most_popular:
                loadMovies(POPULAR_MOVIES);
                mPreferenceEditor.putInt(getString(R.string.preference_selection), POPULAR_MOVIES);
                mPreferenceEditor.commit();
                return true;
            case R.id.action_refresh:
                loadMovies(mSharedPreferences.getInt(
                        getString(R.string.preference_selection), POPULAR_MOVIES));
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
        mGridViewMovies = (GridView) getView().findViewById(R.id.grid_view_movies);
        mProgressBar = (ProgressBar) getView().findViewById(R.id.progress_bar_spinner);
        mTextNoNetwork = (TextView) getView().findViewById(R.id.text_no_network);

        loadMovies(mSharedPreferences.getInt(
                        getString(R.string.preference_selection), POPULAR_MOVIES));
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    /*
     * Required method for MoviesAdapter.OnMovieClickListener
     */
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

    /*
    * Method to get a list of @Movie and update GridView
    *
    * @param movieQuery     The @MovieResponse query where:
    *   1 = TOP_RATED_MOVIES, 2 = POPULAR_MOVIES (default)
    * */
    private void loadMovies(int movieQuery) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call;
        final int toastMessage;

        switch (movieQuery) {
            case 1:
                call = apiInterface.getTopRatedMovies(API_KEY);
                toastMessage = R.string.toast_load_top_rated;
                break;
            case 2:
            default:
                call = apiInterface.getPopularMovies(API_KEY);
                toastMessage = R.string.toast_load_most_popular;
                break;
        }

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                mTextNoNetwork.setVisibility(View.GONE);

                List<Movie> movies = response.body().getMovies();
                mMoviesAdapter = new MoviesAdapter(MovieListFragment.this, movies);
                mGridViewMovies.setAdapter(mMoviesAdapter);

                Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                mProgressBar.setVisibility(View.GONE);
                mTextNoNetwork.setVisibility(View.VISIBLE);
            }
        });
    }
}
