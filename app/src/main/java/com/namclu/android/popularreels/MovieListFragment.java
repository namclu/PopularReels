package com.namclu.android.popularreels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        List<String> forecastList = new ArrayList<String>(Arrays.asList(data));

        mMovieAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_movie,
                R.id.text_list_item_movie,
                data
        );

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        // Get a reference to the ListView, and attach adapter to it
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_movie);
        listView.setAdapter(mMovieAdapter);

        return rootView;
    }
}
