package com.namclu.android.popularreels;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by namlu on 7/8/2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, String[]> {

    private static final String TAG = FetchMovieTask.class.getSimpleName();

    @Override
    protected String[] doInBackground(String... params) {

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
                    stringBuffer.append(line + "\n");
                }

                // If StringBuffer is empty, not point in parsing
                if (stringBuffer.length() == 0) {
                    movieJsonString = null;
                }
                movieJsonString = stringBuffer.toString();

            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
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
    *
    * */
    private String[] getMovieDataFromJson(String movieJsonString) throws JSONException {
        // Names of the JSON objects that need to be extracted
        final String MDB_RESULTS = "results";
        final String MDB_TITLE = "title";

        String[] resultsString = new String[50];

        try {
            JSONObject movieObject = new JSONObject(movieJsonString);
            JSONArray resultsArray = movieObject.getJSONArray(MDB_RESULTS);

            // Extract values from the results array if there are any
            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject resultsObject = resultsArray.getJSONObject(i);
                if (resultsObject.has(MDB_TITLE)) {
                    resultsString[i] = resultsObject.getString(MDB_TITLE);
                }
            }
            if (resultsString.length > 0) {
            }
            // Test output
            for (String s: resultsString) {
                Log.v(TAG, "Results string " + s);
            }
            return resultsString;
        } catch (JSONException e) {
            Log.e(TAG, "Problem retrieving JSON results.", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String[] movieResults) {
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>()

        if (movieResults != null) {

        }
    }
}
