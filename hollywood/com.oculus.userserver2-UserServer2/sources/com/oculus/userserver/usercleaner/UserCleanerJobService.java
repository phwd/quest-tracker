package com.oculus.userserver.usercleaner;

import X.BZ;
import X.Om;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.oculus.userserver.managerservice.OculusUserManagerImpl;

public class UserCleanerJobService extends JobService {
    public static final String TAG = "UserCleanerJobService";
    public Om _UL_mInjectionContext;

    public final boolean onStartJob(JobParameters jobParameters) {
        ((OculusUserManagerImpl) BZ.A02(0, 44, this._UL_mInjectionContext)).A05();
        return false;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new Om(1, BZ.get(this));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
