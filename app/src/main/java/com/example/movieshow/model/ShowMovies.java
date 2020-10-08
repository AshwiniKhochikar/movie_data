package com.example.movieshow.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.movieshow.utils.ShowDataSourceFactory;
import com.example.movieshow.utils.ShowRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class ShowMovies extends AndroidViewModel
{
    private ShowRepository mShowRepository;
    private ShowDataSourceFactory mShowDataSourceFactory;
    private LiveData<List<SearchShow>> mBookmarkList;
    private LiveData<PagedList<SearchShow>> mShowSearchLiveData;

    public ShowMovies(@NonNull Application application)
    {
        super(application);
        mShowRepository = ShowRepository.getInstance(application);
        mShowDataSourceFactory = new ShowDataSourceFactory(mShowRepository, application);
        initializePaging();
    }

    public LiveData<List<SearchShow>> getBookmarkList()
    {
        return mBookmarkList;
    }


    private void initializePaging() {

        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10).build();

        mShowSearchLiveData = new LivePagedListBuilder<>(mShowDataSourceFactory, pagedListConfig)
                .build();

    }


    public void searchShow(String mSearchKey, Executor executor)
    {
        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10).build();
        mShowDataSourceFactory.setSearchKey(mSearchKey);

        mShowSearchLiveData = new LivePagedListBuilder<>(mShowDataSourceFactory, pagedListConfig)
                .build();
        // Build PagedList
    }

    public LiveData<PagedList<SearchShow>> getmShowSearchLiveData()
    {
        return mShowSearchLiveData;
    }

}
