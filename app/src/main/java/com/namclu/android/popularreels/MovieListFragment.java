package com.namclu.android.popularreels;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    private int[] mImageResIds;
    private String[] mNames;
    private String[] mDescriptions;
    private String[] mUrls;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    class MovieAdapter extends RecyclerView.Adapter<ViewHolder> {
        private LayoutInflater mLayoutInflater;

        public MovieAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater
                    .inflate(R.layout.recycler_item_movie, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            final int imageResId = mImageResIds[position];
            final String name = mNames[position];
            final String description = mDescriptions[position];
            final String url = mUrls[position];
            viewHolder.setData(imageResId, name);
        }

        @Override
        public int getItemCount() {
            return mNames.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Views
        private ImageView mImageView;
        private TextView mNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name
            mImageView = (ImageView) itemView.findViewById(R.id.comic_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        private void setData(int imageResId, String name) {
            mImageView.setImageResource(imageResId);
            mNameTextView.setText(name);
        }
    }
}
