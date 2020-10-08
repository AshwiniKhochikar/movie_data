package com.example.movieshow.utils;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.movieshow.api.ShowApiService;
import com.example.movieshow.db.BookMarkDatabase;
import com.example.movieshow.model.SearchResponse;
import com.example.movieshow.model.SearchShow;

import java.util.List;

import retrofit2.Call;

/**
 * class to handle bookmark db
 */
public class ShowRepository
{
    private static final String TAG = ShowRepository.class.getSimpleName();
    private String DB_NAME = "showdb";
    private BookMarkDatabase bookMarkDatabase;
    LiveData<List<SearchShow>> showDetailList;
    private volatile static ShowRepository instance;
    private ShowApiService mService;


    private ShowRepository(Context application)
    {
        bookMarkDatabase = Room.databaseBuilder(application, BookMarkDatabase.class,
            DB_NAME).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();
        mService = ShowApiService.getInstance();
        showDetailList = getAllBookMark();
    }

    public static ShowRepository getInstance(Context application)
    {
        if (instance == null) {
            synchronized (ShowRepository.class) {
                if ((instance == null)) {
                    instance = new ShowRepository(application);
                }
            }

        }
        return instance;
    }

    /**
     * insert data in bookmark table
     */
    public boolean insertBookMark(SearchShow showSearchDetails)
    {
        boolean result = true;
        try {
            bookMarkDatabase.getDao().insertBookmark(showSearchDetails);
        }
        catch (Exception e) {
            result = false;
            Log.i(TAG, "Exception while inserting bookmark " + e);
        }
        return result;
    }


    /**
     * fetch all bookmarks from DB
     *
     * @return
     */
    public LiveData<List<SearchShow>> getAllBookMark()
    {
        return bookMarkDatabase.getDao().getAllBookMarks();
    }

    /**
     * Get show search result
     *
     * @param key  key to search
     * @param page page to get in result // by default 10 results are received in one call
     */
    public Call<SearchResponse> getSearchResult(String key, int page)
    {
        return mService.getApi().getSearchResults(key, page, Constants.API_KEY);
    }


}
