package com.oculus.assistant.learning;

import X.C0139Dd;
import X.WC;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import java.util.concurrent.TimeUnit;

public class DataDeletionJobService extends JobService {
    public static final long A00 = TimeUnit.HOURS.toMillis(2);
    public static final long A01 = TimeUnit.DAYS.toMillis(1);

    public static void A00(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && jobScheduler.getPendingJob(2020) == null) {
            JobInfo.Builder builder = new JobInfo.Builder(2020, new ComponentName(context.getApplicationContext(), DataDeletionJobService.class));
            long j = A01;
            jobScheduler.schedule(builder.setMinimumLatency(j).setOverrideDeadline(j).setPersisted(true).setBackoffCriteria(A00, 0).build());
        }
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        C0139Dd.A09("com.oculus.assistant.learning.DataDeletionJobService", "Started");
        AsyncTask.execute(new WC(this, jobParameters));
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        C0139Dd.A09("com.oculus.assistant.learning.DataDeletionJobService", "Deletion job stopped before completion");
        A00(this);
        return false;
    }
}
