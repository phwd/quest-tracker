package com.oculus.userserver.managerservice;

import X.BZ;
import X.Mi;
import X.Om;
import X.SZ;
import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_userserver_managerservice_ForDeviceProtectedStorage_ULSEP_BINDING_ID"})
@TargetApi(MinidumpReader.MODULE_LIST_OFFSET)
public class UserSyncScheduler {
    public static final int JOB_ID = 1828726831;
    public static final long PERIOD_MILLIS = TimeUnit.DAYS.toMillis(1);
    public static final String TAG = "UserSyncScheduler";
    public Om _UL_mInjectionContext;

    public final void A00() {
        if (((JobScheduler) ((Context) BZ.A02(0, 85, this._UL_mInjectionContext)).getSystemService("jobscheduler")).schedule(new JobInfo.Builder(1828726831, new ComponentName((Context) BZ.A02(0, 85, this._UL_mInjectionContext), UserSyncJobService.class)).setRequiredNetworkType(1).setPeriodic(PERIOD_MILLIS).setPersisted(true).build()) != 1) {
            Mi.A00(TAG, "Failed to schedule user sync job");
        }
    }

    @Inject
    public UserSyncScheduler(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
