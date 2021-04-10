package com.oculus.ocms.defaultapps;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.debug.log.BLog;
import com.oculus.util.constants.JobSchedulerIds;
import java.util.concurrent.TimeUnit;

public abstract class DefaultAppsScheduler {
    private static final int HIGH_PRI_APPS_RESCHEDULE_MIN_LATENCY_SECONDS = 10;
    private static final String TAG = "DefaultAppsScheduler";

    public static void schedule(Context context) {
        schedule(context, createBasicJob(context).build());
    }

    private static JobInfo.Builder createBasicJob(Context context) {
        return new JobInfo.Builder(JobSchedulerIds.STANDALONE_SETUP, new ComponentName(context, DefaultAppsHighPriorityJobService.class)).setRequiredNetworkType(1).setMinimumLatency(TimeUnit.SECONDS.toMillis(10)).setPersisted(false);
    }

    private static void schedule(Context context, JobInfo jobInfo) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            BLog.e(TAG, "Failed to get JobScheduler (null)");
            return;
        }
        int schedule = jobScheduler.schedule(jobInfo);
        if (schedule == 1) {
            BLog.d(TAG, "Default apps setup job scheduled successfully");
            return;
        }
        BLog.e(TAG, "Failed to schedule default apps setup job. Return: %d", Integer.valueOf(schedule));
    }
}
