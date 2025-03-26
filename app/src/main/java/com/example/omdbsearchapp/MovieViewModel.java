package com.example.omdbsearchapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    private final MutableLiveData<List<SearchResult>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<Movie> movieDetails = new MutableLiveData<>();
    private final OMDbApiService apiService;

    public MovieViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(OMDbApiService.class);
    }

    public LiveData<List<SearchResult>> searchMovies(String query) {
        apiService.searchMovies(query, "b05ea5b6").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                if (response.body() != null && "True".equalsIgnoreCase(response.body().getResponse())) {
                    searchResults.setValue(response.body().getSearch());
                } else {
                    searchResults.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                searchResults.setValue(null);
            }
        });
        return searchResults;
    }

    public LiveData<Movie> getMovieDetails(String title) {
        apiService.getMovie(title, "b05ea5b6").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                movieDetails.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                movieDetails.setValue(null);
            }
        });
        return movieDetails;
    }
}