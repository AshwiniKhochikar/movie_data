package com.example.movieshow.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.movieshow.model.SearchShow;

import java.util.List;

@Dao
public interface BookmarkDao
{
    @Insert
    void insertBookmark(SearchShow bookMark);

    @Query("SELECT * FROM bookmarkdata order by _id desc")
    LiveData<List<SearchShow>> getAllBookMarks();

    @Delete
    void deleteBookmark(SearchShow bookMark);
}
