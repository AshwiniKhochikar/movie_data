package com.example.movieshow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse
{
    @SerializedName("Search")
    List<SearchShow> showDetailsList;
    @SerializedName("totalResults")
    String totalResult;

    public SearchResponse(List<SearchShow> showDetailsList, String totalResult)
    {
        this.showDetailsList = showDetailsList;
        this.totalResult = totalResult;
    }

    public List<SearchShow> getShowDetailsList()
    {
        return showDetailsList;
    }

    public void setShowDetailsList(List<SearchShow> showDetailsList)
    {
        this.showDetailsList = showDetailsList;
    }

    public String getTotalResult()
    {
        return totalResult;
    }

    public void setTotalResult(String totalResult)
    {
        this.totalResult = totalResult;
    }
}
