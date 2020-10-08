package com.example.movieshow.api;

import com.example.movieshow.model.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowApi
{
    @GET("?")
    Call<SearchResponse> getSearchResults(@Query("s") String title, @Query("page") int pages, @Query("apikey") String apikey);
}
