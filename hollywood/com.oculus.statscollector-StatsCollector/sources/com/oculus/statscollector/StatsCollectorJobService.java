package com.oculus.statscollector;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;
import com.oculus.statscollectorn.FetchBLEStatsTask;

public class StatsCollectorJobService extends JobService {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = StatsCollectorJobService.class.getSimpleName();
    private FetchBLEStatsTask mFetchBLEStatsTask;
    private FetchStatsTask mFetchStatsTask;

    @MainThread
    public boolean onStartJob(@NonNull JobParameters params) {
        this.mFetchStatsTask = new FetchStatsTask((JobService) this);
        this.mFetchStatsTask.execute(params);
        this.mFetchBLEStatsTask = new FetchBLEStatsTask((JobService) this);
        this.mFetchBLEStatsTask.execute(params);
        return true;
    }

    @MainThread
    public boolean onStopJob(@NonNull JobParameters params) {
        FetchStatsTask fetchStatsTask = this.mFetchStatsTask;
        if (fetchStatsTask != null) {
            fetchStatsTask.cancel(true);
        }
        FetchBLEStatsTask fetchBLEStatsTask = this.mFetchBLEStatsTask;
        if (fetchBLEStatsTask != null) {
            fetchBLEStatsTask.cancel(true);
        }
        return true;
    }
}
