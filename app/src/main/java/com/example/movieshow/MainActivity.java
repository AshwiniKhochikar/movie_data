package com.example.movieshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.movieshow.adapter.IShowClickListner;
import com.example.movieshow.adapter.ShowListAdapter;
import com.example.movieshow.model.SearchShow;
import com.example.movieshow.model.ShowMovies;
import com.example.movieshow.utils.Constants;
import com.example.movieshow.utils.ThreadExecutor;


import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    private ShowListAdapter mAdapter;

    String mSearchKey = Constants.DEFAULT_SEARCH;
    private ShowMovies mShowViewModel;
    private Executor mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExecutor = new ThreadExecutor();
        initResultRecylerView();
        mShowViewModel = ViewModelProviders.of(this).get(ShowMovies.class);
        observe();
        loadDefaultSearch();
    }

    private void observe()
    {
        mShowViewModel.getmShowSearchLiveData().observe(this, (Observer<? super PagedList<SearchShow>>) new Observer<PagedList<SearchShow>>()
        {
            @Override
            public void onChanged(PagedList<SearchShow> showSearchDetails)
            {
                Log.i(TAG, "On Changed  list size is " + (showSearchDetails!=null?showSearchDetails.size():0));
                mAdapter.submitList(showSearchDetails);
            }
        });
    }

    private void loadDefaultSearch()
    {
        refreshData(mSearchKey);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
            {

                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    Log.i(TAG, "Text Submitted " + query);
                    mSearchKey = query;
                    refreshData(mSearchKey);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query)
                {

                    // DO Nothing
                    return false;

                }

            });

        }

        return true;

    }

    private void initResultRecylerView()
    {
        mRecyclerView = findViewById(R.id.showListRecylerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ShowListAdapter(this, showClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * RecyclerItem click event listener
     */
    private IShowClickListner showClickListener = new IShowClickListner()
    {
        @Override
        public void onShowClick(SearchShow showSearchDetails)
        {

            Log.i(TAG, "Show clicked " + showSearchDetails.getTitle());
            //startDetailActivity(showSearchDetails.getImdbID());

        }

        @Override
        public void onSaveBookMark(SearchShow showDetails) {

        }

        @Override
        public void onDeleteBookMark(SearchShow showDetails) {

        }

    };


    public void refreshData(String mSearchKey)
    {
        Log.i(TAG, "Search key is "+mSearchKey);
        mShowViewModel.searchShow(mSearchKey, mExecutor);

    }

}
