package com.example.omdbsearchapp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {

    @SerializedName("Search")
    private List<SearchResult> search;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    public List<SearchResult> getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}