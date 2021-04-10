package com.oculus.userserver.managerservice;

import X.BZ;
import X.Om;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import com.oculus.executors.OculusThreadExecutor;

@TargetApi(21)
public class UserSyncJobService extends JobService {
    public static final String TAG = "UserSyncJobService";
    public Om _UL_mInjectionContext;

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new Om(3, BZ.get(this));
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        jobParameters.getJobId();
        ((OculusThreadExecutor) BZ.A02(0, 57, this._UL_mInjectionContext)).execute(new Runnable() {
            /* class com.oculus.userserver.managerservice.UserSyncJobService.AnonymousClass1 */

            public final void run() {
                try {
                    ((OculusUserManagerImpl) BZ.A02(1, 44, UserSyncJobService.this._UL_mInjectionContext)).A06();
                } finally {
                    ((Handler) BZ.A02(2, 23, UserSyncJobService.this._UL_mInjectionContext)).post(new Runnable() {
                        /* class com.oculus.userserver.managerservice.UserSyncJobService.AnonymousClass1.AnonymousClass1 */

                        public final void run() {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            UserSyncJobService.this.jobFinished(jobParameters, false);
                        }
                    });
                }
            }
        });
        return true;
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        jobParameters.getJobId();
        return true;
    }
}
