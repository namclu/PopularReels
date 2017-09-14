package com.namclu.android.popularreels.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.popularreels.R;
import com.namclu.android.popularreels.model.Movie;

import java.util.Locale;

/**
 * Created by namlu on 12-Sep-17.
 */

public class MovieDetailsFragment extends Fragment {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();
    private static final String MOVIE = "Movie";

    // Class variables
    Movie mMovie;

    public static MovieDetailsFragment newInstance() {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMovie = getArguments().getParcelable(MOVIE);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        // Find views
        TextView movieTitle = (TextView) view.findViewById(R.id.text_movie_title);
        TextView movieSynopsis = (TextView) view.findViewById(R.id.text_movie_synopsis);
        TextView movieRating = (TextView) view.findViewById(R.id.text_movie_rating);
        TextView releaseDate = (TextView) view.findViewById(R.id.text_release_date);
        ImageView moviePoster  = (ImageView) view.findViewById(R.id.image_movie_poster);

        // Set @Movie details
        if (!mMovie.getTitle().isEmpty()) {
            movieTitle.setText(String.format(Locale.ENGLISH, "%s", mMovie.getTitle()));
        }

        return view;
    }
}
