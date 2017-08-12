package com.namclu.android.popularreels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by namlu on 7/6/2017.
 *
 * Fragment to display a list of movies
 */

public class MovieListFragment extends Fragment {

    // Global variables
    private ArrayAdapter<String> mMovieAdapter;

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
                new FetchMovieTask(mMovieAdapter).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "Mon 6/23 - Sunny - 31/17",
                "Tue 6/24 - Foggy - 21/8",
                "Wed 6/25 - Cloudy - 22/17",
                "Thurs 6/26 - Rainy - 18/11",
                "Fri 6/27 - Foggy - 21/10",
                "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
                "Sun 6/29 - Sunny - 20/7"
        };

        List<String> movieList = new ArrayList<>(Arrays.asList(data));

        mMovieAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_movie,
                R.id.text_list_item_movie,
                movieList
        );

        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Get a reference to the ListView, and attach adapter to it
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_movie);
        gridView.setAdapter(mMovieAdapter);

        return rootView;
    }
}
