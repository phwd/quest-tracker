package com.oculus.alpenglow.remotewipe;

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
import com.oculus.alpenglow.constants.Constants;
import com.oculus.alpenglow.lifecycle.StartupListener;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_remotewipe_RemoteWipeManager_ULSEP_BINDING_ID"})
public class RemoteWipeListener implements StartupListener {
    public static final int REMOTE_WIPE_POLLING_JOB_BACKOFF_INTERVAL = 5;
    public static final int REMOTE_WIPE_POLLING_JOB_INTERVAL = 2;
    public static final String TAG = "RemoteWipeListener";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        RemoteWipeManager remoteWipeManager = (RemoteWipeManager) AnonymousClass0Lh.A03(0, 14, this._UL_mInjectionContext);
        long millis = TimeUnit.HOURS.toMillis(2);
        long millis2 = TimeUnit.MINUTES.toMillis(5);
        JobInfo.Builder builder = new JobInfo.Builder(Constants.REMOTE_WIPE_POLLING_JOB_ID, new ComponentName((Context) AnonymousClass0Lh.A03(0, 4, remoteWipeManager._UL_mInjectionContext), RemoteWipePollingJobService.class));
        builder.setRequiredNetworkType(1).setPeriodic(millis).setBackoffCriteria(millis2, 0);
        JobScheduler jobScheduler = (JobScheduler) ((Context) AnonymousClass0Lh.A03(0, 4, remoteWipeManager._UL_mInjectionContext)).getSystemService("jobscheduler");
        if (jobScheduler == null) {
            AnonymousClass0NK.A01(RemoteWipeManager.TAG, "Job scheduler service not found");
        } else {
            jobScheduler.schedule(builder.build());
        }
    }

    @Inject
    public RemoteWipeListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
