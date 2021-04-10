package com.oculus.handshotfixservice;

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
    private static final int HANDS_HOTFIX_JOB_ID = 64745;
    private static final long HANDS_HOTFIX_JOB_REFRESH_FLEX = TimeUnit.MINUTES.toMillis(10);
    private static final long HANDS_HOTFIX_JOB_REFRESH_PERIOD = TimeUnit.HOURS.toMillis(1);
    private static final String TAG = "HandsHotfixService";

    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if (DEBUG) {
                Log.d(TAG, "Scheduling HandsHotfixJob");
            }
            scheduleHandsHotfixJob(context);
        }
    }

    private void scheduleHandsHotfixJob(Context context) {
        if (((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(HANDS_HOTFIX_JOB_ID, new ComponentName(context, HandsHotfixJobService.class)).setPeriodic(HANDS_HOTFIX_JOB_REFRESH_PERIOD, HANDS_HOTFIX_JOB_REFRESH_FLEX).setRequiresDeviceIdle(true).build()) != 1) {
            Log.e(TAG, "Failed to schedule HandsHotfixJobService");
        }
    }
}
