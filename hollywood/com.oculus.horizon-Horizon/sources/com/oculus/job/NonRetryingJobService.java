package com.oculus.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

public abstract class NonRetryingJobService extends JobService {
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 2;
    }
}
