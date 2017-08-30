package com.namclu.android.popularreels;

/**
 * Created by namlu on 27-Aug-17.
 *
 * The Movie class defines a single @Movie object which includes
 * the movie title, image, release date, plot synopsis, among other movie info
 */

public class Movie {
    private String movieTitle;
    private String movieImageUrl;
    private String movieReleaseDate;
    private int movieVoteAverage;
    private String moviePlotSynopsis;


    public Movie(String title, String image) {
        this.movieTitle = getMovieTitle();
        this.movieTitle = getMovieImageUrl();
    }

    public Movie(String movieTitle, String movieImageUrl, String movieReleaseDate,
                 int movieVoteAverage, String moviePlotSynopsis) {
        this.movieTitle = getMovieTitle();
        this.movieImageUrl = getMovieImageUrl();
        this.movieReleaseDate = getMovieReleaseDate();
        this.movieVoteAverage = getMovieVoteAverage();
        this.moviePlotSynopsis = getMoviePlotSynopsis();
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public int getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(int movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    public String getMoviePlotSynopsis() {
        return moviePlotSynopsis;
    }

    public void setMoviePlotSynopsis(String moviePlotSynopsis) {
        this.moviePlotSynopsis = moviePlotSynopsis;
    }
}
