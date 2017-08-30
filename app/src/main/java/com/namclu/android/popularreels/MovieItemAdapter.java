package com.namclu.android.popularreels;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

/**
 * Created by namlu on 30-Aug-17.
 */

public class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {
    // Class variables
    private List<Movie> mMovies;

    @Override
    public MovieItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MovieItemAdapter.ViewHolder holder, int position) {
        Movie currentMovie = mMovies.get(position);

        holder.mTextMovieTitle.setText(
                String.format(Locale.ENGLISH, "%s", currentMovie.getMovieTitle()));
        // Todo: get bitmap image
        //holder.mImageMoviePoster.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageMoviePoster;
        public final TextView mTextMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageMoviePoster = (ImageView) itemView.findViewById(R.id.image_movie_poster);
            mTextMovieTitle = (TextView) itemView.findViewById(R.id.text_movie_title);
        }
    }

}
