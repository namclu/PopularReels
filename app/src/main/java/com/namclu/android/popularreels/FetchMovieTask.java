package com.namclu.android.popularreels;

import android.os.AsyncTask;
import android.util.Log;

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

public class FetchMovieTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = FetchMovieTask.class.getSimpleName();

    @Override
    protected Void doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream;
        StringBuffer stringBuffer;

        // Will contain the raw JSON response as a string.
        String movieJsonString = null;

        try {
            // Construct URL for themoviedb.org query
            URL url = new URL(
                    "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

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

        return null;
    }
}