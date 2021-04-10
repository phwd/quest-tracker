package com.oculus.alpenglow.config;

import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.logging.ConfigLogger;
import com.oculus.alpenglow.logging.LoggerConstants;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigSyncService extends JobService {
    public AnonymousClass0R7 _UL_mInjectionContext;

    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        ((ConfigurationStore) AnonymousClass0Lh.A03(0, 97, this._UL_mInjectionContext)).A03(((ConfigLogger) AnonymousClass0Lh.A03(1, 45, this._UL_mInjectionContext)).A01(LoggerConstants.CONFIGURATION_FETCH_REASON_JOB_SERVICE, LoggerConstants.CONFIGURATION_FETCH_TYPE_NORMAL));
        jobFinished(jobParameters, false);
        return false;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(2, AnonymousClass0Lh.get(this));
    }
}
