package com.example.omdbsearchapp;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String poster;

    @SerializedName("imdbRating")
    private String imdbRating;

    @SerializedName("Response")
    private String response;

    public String getTitle() { return title; }
    public String getYear() { return year; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public String getActors() { return actors; }
    public String getPlot() { return plot; }
    public String getPoster() { return poster; }
    public String getImdbRating() { return imdbRating; }
    public String getResponse() { return response; }
}