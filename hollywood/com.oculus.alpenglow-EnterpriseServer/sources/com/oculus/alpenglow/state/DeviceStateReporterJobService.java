package com.oculus.alpenglow.state;

import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import com.oculus.alpenglow.logging.DeviceStateLogger;
import com.oculus.executors.OculusThreadExecutor;

public class DeviceStateReporterJobService extends JobService {
    public AnonymousClass0R7 _UL_mInjectionContext;
    public AsyncTask<JobService, Void, Void> mReportStateAsyncTask;

    public final boolean onStartJob(JobParameters jobParameters) {
        AsyncTask<JobService, Void, Void> A00 = ((DeviceStateManager) AnonymousClass0Lh.A03(0, 27, this._UL_mInjectionContext)).A00(jobParameters, ((DeviceStateLogger) AnonymousClass0Lh.A03(1, 130, this._UL_mInjectionContext)).A01("job triggered"));
        this.mReportStateAsyncTask = A00;
        A00.executeOnExecutor(OculusThreadExecutor.A00(), this);
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        AsyncTask<JobService, Void, Void> asyncTask = this.mReportStateAsyncTask;
        if (asyncTask == null) {
            return true;
        }
        asyncTask.cancel(false);
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(2, AnonymousClass0Lh.get(this));
    }
}
