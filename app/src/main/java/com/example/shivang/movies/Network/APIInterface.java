package com.example.shivang.movies.Network;

import com.example.shivang.movies.Movies;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by shivang on 09/08/17.
 */

public interface APIInterface {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movies.MovieResult> cb);

    @GET("/discover/movie")
    void getUpcomingMovies(@Query("primary_release_date.gte") String date1, Callback<Movies.MovieResult> cb);

}