package com.namclu.android.popularreels.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.namclu.android.popularreels.Constants;
import com.namclu.android.popularreels.R;
import com.namclu.android.popularreels.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by namlu on 12-Sep-17.
 */

public class MovieDetailsFragment extends Fragment {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();
    private static final String STRING_MOVIE = "STRING_MOVIE";
    private static final String IMAGE_SIZE = "w500/";

    // Class variables
    Movie mMovie;

    public static MovieDetailsFragment newInstance(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(STRING_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMovie = getArguments().getParcelable(STRING_MOVIE);

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
        } else {
            movieTitle.setText(String.format(Locale.ENGLISH, "%s",
                    getResources().getString(R.string.default_no_info)));
        }
        if (!mMovie.getOverview().isEmpty()) {
            movieSynopsis.setText(String.format(Locale.ENGLISH, "%s", mMovie.getOverview()));
        } else {
            movieSynopsis.setText(String.format(Locale.ENGLISH, "%s",
                    getResources().getString(R.string.default_no_info)));
        }
        if (mMovie.getVoteAverage() != 0.0) {
            movieRating.setText(String.format(Locale.ENGLISH, "%s%s",
                    mMovie.getVoteAverage(), "/10"));
        } else {
            movieRating.setText(String.format(Locale.ENGLISH, "%s",
                    getResources().getString(R.string.default_no_info)));
        }
        if (!mMovie.getReleaseDate().isEmpty()) {
            releaseDate.setText(String.format(Locale.ENGLISH, "%s",
                    formatDate(mMovie.getReleaseDate())));
        } else {
            releaseDate.setText(String.format(Locale.ENGLISH, "%s",
                    getResources().getString(R.string.default_no_info)));
        }
        if (!mMovie.getPosterPath().isEmpty()) {
            Glide.with(view.getContext())
                    .load(Constants.POSTER_PATH + IMAGE_SIZE + mMovie.getPosterPath())
                    .into(moviePoster);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getResources().getString(R.string.fragment_title_details));
    }

    /*
     * Format @Movie release date String from 2000-01-01 to 01 Jan 2000
     */
    private String formatDate(String stringDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;

        try {
            date = inputFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }
}
