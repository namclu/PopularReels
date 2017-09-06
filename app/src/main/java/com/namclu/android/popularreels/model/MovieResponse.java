package com.namclu.android.popularreels.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by namlu on 04-Sep-17.
 *
 * The MovieResponse class defines the JSON structure returned, which includes
 * a List<Movie> when querying popular movies
 * ("http://api.themoviedb.org/3/movie/popular/?") from themoviedb.org
 */

public class MovieResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private List<Movie> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
