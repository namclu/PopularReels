package com.namclu.android.popularreels;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by namlu on 7/8/2017.
 *
 * Class to fetch data from movie database
 */

class FetchMovieTask extends AsyncTask<Movie, Void, List<Movie>> {

    private static final String TAG = FetchMovieTask.class.getSimpleName();

    private final WeakReference<ArrayAdapter<Movie>> mAdapter;

    public FetchMovieTask(ArrayAdapter<Movie> adapter) {
        mAdapter = new WeakReference<>(adapter);
    }

    @Override
    protected List<Movie> doInBackground(Movie... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream;
        StringBuffer stringBuffer;

        // Will contain the raw JSON response as a string.
        String movieJsonString = null;

        try {
            // Construct URL for themoviedb.org query
            final String POPULAR_MOVIES_BASE_URL =
                    "http://api.themoviedb.org/3/movie/popular/?";
            // String that precedes the user's App ID key
            final String APP_KEY_PARAM = "api_key";

            Uri createUri = Uri.parse(POPULAR_MOVIES_BASE_URL).buildUpon()
                    .appendQueryParameter(APP_KEY_PARAM, BuildConfig.MOVIES_DB_API_KEY)
                    .build();

            URL url = new URL(createUri.toString());

            // Log message to check
            Log.v(TAG, "Built URI: " + url.toString());

            // Create the request and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If connection is successful (Response code = 200), read the input stream
            // and parse the response
            if (urlConnection.getResponseCode() == 200) {
                // Read the input stream into a String
                inputStream = urlConnection.getInputStream();
                stringBuffer = new StringBuffer();

                if (inputStream == null) {
                    movieJsonString = null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }

                // If StringBuffer is empty, not point in parsing
                if (stringBuffer.length() == 0) {
                    movieJsonString = null;
                }
                movieJsonString = stringBuffer.toString();

            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getMovieDataFromJson(movieJsonString);
        } catch (JSONException e) {
            Log.e(TAG, "Error closing stream", e);
        }

        return null;
    }

    /*
    * Get a list of movies from JSON
    * */
    private List<Movie> getMovieDataFromJson(String movieJsonString) throws JSONException {
        Gson gson = new Gson();
        Type type = new TypeToken<MovieResponse>() {}.getType();

        MovieResponse movieResponse = gson.fromJson(movieJsonString, type);

        return  movieResponse.getMovies();
    }

    @Override
    protected void onPostExecute(List<Movie> movieResults) {
        final ArrayAdapter<Movie> movieAdapter = mAdapter.get();

        if (movieResults != null) {
            movieAdapter.clear();
            movieAdapter.addAll(movieResults);
            movieAdapter.notifyDataSetChanged();
        }
    }
}
