package com.namclu.android.popularreels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root_layout, MovieListFragment.newInstance(), "Movie List")
                    .commit();
        }
    }
}
