package com.example.omdbsearchapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDbApiService {

    @GET("/")
    Call<SearchResponse> searchMovies(@Query("s") String query, @Query("apikey") String apiKey);

    @GET("/")
    Call<Movie> getMovie(@Query("t") String title, @Query("apikey") String apiKey);
}