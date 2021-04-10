package com.oculus.alpenglow.state;

import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.lifecycle.StartupListener;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_state_DeviceStateManager_ULSEP_BINDING_ID"})
public class DeviceStateStartupListener implements StartupListener {
    public static final int REPORT_STATE_JOB_BACKOFF_INTERVAL = 30;
    public static final int REPORT_STATE_JOB_INTERVAL = 1;
    public static final int REPORT_STATE_JOB_WINDOW = 1;
    public static final String TAG = "EnterpriseServer.DeviceStateStartupListener";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        DeviceStateManager deviceStateManager = (DeviceStateManager) AnonymousClass0Lh.A03(0, 27, this._UL_mInjectionContext);
        long millis = TimeUnit.DAYS.toMillis(1);
        long millis2 = TimeUnit.HOURS.toMillis(1);
        long millis3 = TimeUnit.MINUTES.toMillis(30);
        JobInfo.Builder builder = new JobInfo.Builder(1000, new ComponentName((Context) AnonymousClass0Lh.A03(0, 4, deviceStateManager._UL_mInjectionContext), DeviceStateReporterJobService.class));
        builder.setRequiredNetworkType(1).setPeriodic(millis, millis2).setBackoffCriteria(millis3, 0);
        JobScheduler jobScheduler = (JobScheduler) ((Context) AnonymousClass0Lh.A03(0, 4, deviceStateManager._UL_mInjectionContext)).getSystemService("jobscheduler");
        if (jobScheduler == null) {
            AnonymousClass0NK.A01(DeviceStateManager.TAG, "Job scheduler service not found");
        } else {
            jobScheduler.schedule(builder.build());
        }
    }

    @Inject
    public DeviceStateStartupListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
