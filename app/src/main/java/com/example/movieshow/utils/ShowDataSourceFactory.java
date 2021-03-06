package com.example.movieshow.utils;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.movieshow.model.SearchShow;

/**
 * Data source factory class
 */
public class ShowDataSourceFactory extends DataSource.Factory<Integer, SearchShow>
{
    private MutableLiveData<ShowDataSource> mMutableLiveData;
    private ShowRepository mRepository;
    private Context context;
    private String mSearchKey="";

    public ShowDataSourceFactory( ShowRepository mRepository, Context context)
    {
        this.mMutableLiveData = new MutableLiveData<>();
        this.mRepository = mRepository;
        this.context = context;
    }

    public MutableLiveData<ShowDataSource> getMutableLiveData()
    {
        return mMutableLiveData;
    }

    @Override
    public DataSource<Integer, SearchShow> create()
    {
        ShowDataSource dataSourceClass = new ShowDataSource(getSearchKey(), context);
        mMutableLiveData.postValue(dataSourceClass);
        return dataSourceClass;
    }

    public String getSearchKey()
    {
        return mSearchKey;
    }

    public void setSearchKey(String mSearchKey)
    {
        this.mSearchKey = mSearchKey;
    }
}
