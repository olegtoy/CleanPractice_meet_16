package com.practice.olegtojgildin.clean_meet_16.threading;

/**
 * Created by olegtojgildin on 16/02/2019.
 */


import android.os.Handler;
import android.os.Looper;

import com.practice.olegtojgildin.clean_meet_16.domain.executor.MainThread;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
