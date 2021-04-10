package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.vrshell.util.ThreadExecutor;

public class LibraryAppFetch {
    private static final long APP_FETCH_TIMEOUT_MILLIS = 5000;
    private static final String TAG = LoggingUtil.tag(LibraryFetcher.class);
    private long mCurrentFetchStartTime;
    private ThreadExecutor.ConcurrentListenableScheduledFuture<App> mFuture;
    private boolean mHasPendingFetch;
    private String mPackageName;

    public LibraryAppFetch(String str) {
        this.mPackageName = str;
    }

    public void startFetch(ThreadExecutor.ConcurrentListenableScheduledFuture concurrentListenableScheduledFuture) {
        stopFetchIfRunning();
        Log.d(TAG, String.format("Starting app fetch: %s", this.mPackageName));
        this.mCurrentFetchStartTime = System.currentTimeMillis();
        this.mFuture = concurrentListenableScheduledFuture;
    }

    public boolean isAppFetchTimedOut() {
        if (!isFetching() || System.currentTimeMillis() - this.mCurrentFetchStartTime <= 5000) {
            return false;
        }
        Log.d(TAG, String.format("Fetch for app has TIMED OUT: %s", this.mPackageName));
        return true;
    }

    public void addPendingFetch() {
        this.mHasPendingFetch = true;
    }

    public boolean hasPendingFetch() {
        return this.mHasPendingFetch;
    }

    public boolean isFetching() {
        ThreadExecutor.ConcurrentListenableScheduledFuture<App> concurrentListenableScheduledFuture = this.mFuture;
        return concurrentListenableScheduledFuture != null && !concurrentListenableScheduledFuture.isDone();
    }

    public void resetPendingFetches() {
        this.mHasPendingFetch = false;
    }

    public void stopFetchIfRunning() {
        if (isFetching()) {
            Log.d(TAG, String.format("Stopping fetch for app: %s", this.mPackageName));
            this.mFuture.cancel(true);
        }
    }

    public String getPackageName() {
        return this.mPackageName;
    }
}
