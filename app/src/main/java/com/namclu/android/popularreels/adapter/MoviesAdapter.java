package com.namclu.android.popularreels.adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.namclu.android.popularreels.Constants;
import com.namclu.android.popularreels.R;
import com.namclu.android.popularreels.model.Movie;

import java.util.List;
import java.util.Locale;

/**
 * Created by namlu on 06-Sep-17.
 *
 * Adapter to display a Movie object
 */

public class MoviesAdapter implements ListAdapter {
    private static final String IMAGE_SIZE = "w342/";

    private final List<Movie> mMovies;

    // Constructor
    public MoviesAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_movie, viewGroup, false);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Movie movie = getItem(position);
        
        // Initialize views
        Glide.with(viewGroup.getContext())
                .load(Constants.POSTER_PATH + IMAGE_SIZE + movie.getPosterPath())
                .into(viewHolder.mImageMoviePoster);
        viewHolder.mTextMovieTitle.setText(String.format(Locale.ENGLISH, "%s", movie.getTitle()));

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static class ViewHolder {
        public final ImageView mImageMoviePoster;
        public final TextView mTextMovieTitle;

        public ViewHolder(View itemView) {
            mImageMoviePoster = (ImageView) itemView.findViewById(R.id.image_movie_poster);
            mTextMovieTitle = (TextView) itemView.findViewById(R.id.text_movie_title);
        }
    }
}
