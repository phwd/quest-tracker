package com.oculus.horizon.vr_lifecycle;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.platform.OVRServiceSynchronous;
import com.oculus.util.constants.JobSchedulerIds;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
@TargetApi(21)
public class VRLifecycleJobScheduler {
    public static final int SESSION_END_MINIMUM_WAIT_TIME_MS = 600000;
    public static final String TAG = "VRLifecycleJobScheduler";
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Context mContext;

    public final boolean A01() {
        for (JobInfo jobInfo : ((JobScheduler) this.mContext.getSystemService("jobscheduler")).getAllPendingJobs()) {
            if (jobInfo.getId() == 1828726824) {
                return true;
            }
        }
        return false;
    }

    @Inject
    public VRLifecycleJobScheduler(AbstractC06640p5 r3, @ForAppContext Context context) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mContext = context;
    }

    public final void A00() {
        int schedule;
        if (!A01() && (schedule = ((JobScheduler) this.mContext.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(JobSchedulerIds.LIFECYCLE_SESSION_END, new ComponentName(this.mContext, VRLifecycleJobService.class.getName())).setMinimumLatency(600000).setRequiredNetworkType(1).setPersisted(true).setBackoffCriteria(OVRServiceSynchronous.SERVICE_CONNECTION_TIMEOUT_MILLIS, 1).build())) != 1) {
            ((IErrorReporter) AnonymousClass0J2.A03(0, 428, this._UL_mInjectionContext)).A96(TAG, AnonymousClass006.A02("VRLifecycle job schedule failed (error code: ", schedule, ")"));
        }
    }
}
