package com.example.movieshow.adapter;


import com.example.movieshow.model.SearchShow;

public interface IShowClickListner
{
    void onShowClick(SearchShow showDetails);
    void onSaveBookMark(SearchShow showDetails);
    void onDeleteBookMark(SearchShow showDetails);
}
