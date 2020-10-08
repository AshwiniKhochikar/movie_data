package com.example.movieshow.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movieshow.model.SearchShow;


@Database(entities = {SearchShow.class}, version = 1, exportSchema = false)
public abstract class BookMarkDatabase extends RoomDatabase
{
    public abstract BookmarkDao getDao();
}
