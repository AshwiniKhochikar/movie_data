package com.example.movieshow.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class ThreadExecutor implements Executor
{
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void execute(Runnable command)
    {
        mHandler.post(command);
    }
}
