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

/**
 * Created by namlu on 7/6/2017.
 *
 * Fragment to display a list of movies
 */

public class MovieListFragment extends Fragment {

    // Class variables
    private ArrayAdapter<Movie> mMovieAdapter;

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

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid_view_movie);
        mMovieAdapter = new ArrayAdapter<Movie>(
                getActivity(), R.layout.list_item_movie, R.id.text_movie_title, new ArrayList<Movie>());
        gridView.setAdapter(mMovieAdapter);

        new FetchMovieTask(mMovieAdapter).execute();

        return view;
    }
}
