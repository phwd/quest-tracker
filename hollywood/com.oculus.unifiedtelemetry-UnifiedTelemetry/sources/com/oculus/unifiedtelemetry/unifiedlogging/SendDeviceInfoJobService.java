package com.oculus.unifiedtelemetry.unifiedlogging;

import X.AbstractC0096Hu;
import X.QC;
import android.app.job.JobParameters;
import android.app.job.JobService;
import java.util.concurrent.TimeUnit;

public class SendDeviceInfoJobService extends JobService {
    public static final long DAILY_INTERVAL_MS = TimeUnit.DAYS.toMicros(1);
    public static final String DEVICE_INFO_EVENT_NAME = "device_info";
    public static final String TAG = "SendDeviceInfoJobService";
    public QC _UL_mInjectionContext;

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onStartJob(android.app.job.JobParameters r7) {
        /*
        // Method dump skipped, instructions count: 121
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.unifiedlogging.SendDeviceInfoJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new QC(1, AbstractC0096Hu.get(this));
    }
}
