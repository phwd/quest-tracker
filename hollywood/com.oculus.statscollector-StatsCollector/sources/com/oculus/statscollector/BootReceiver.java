package com.oculus.statscollector;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.TimeUnit;

public class BootReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final int STATS_COLLECTOR_JOB_ID = 57471571;
    private static final long STATS_COLLECTOR_JOB_REFRESH_FLEX = TimeUnit.MINUTES.toMillis(10);
    private static final long STATS_COLLECTOR_JOB_REFRESH_PERIOD = TimeUnit.HOURS.toMillis(1);
    private static final String TAG = StatsCollectorJobService.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction()) || "android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if (DEBUG) {
                Log.d(TAG, "Configuring stats service");
            }
            StatsdManager.configureStatsd(context);
            if (DEBUG) {
                Log.d(TAG, "Scheduling StatsCollector job");
            }
            scheduleStatsCollectorJobService(context);
        }
    }

    private void scheduleStatsCollectorJobService(Context context) {
        int result = ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(STATS_COLLECTOR_JOB_ID, new ComponentName(context, StatsCollectorJobService.class)).setPeriodic(STATS_COLLECTOR_JOB_REFRESH_PERIOD, STATS_COLLECTOR_JOB_REFRESH_FLEX).setRequiresDeviceIdle(true).build());
        if (result != 1) {
            String str = TAG;
            Log.e(str, "Failed to schedule StatsCollectorJobService: " + result);
        }
    }
}
