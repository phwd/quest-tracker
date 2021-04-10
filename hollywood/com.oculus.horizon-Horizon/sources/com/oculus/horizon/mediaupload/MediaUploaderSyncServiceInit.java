package com.oculus.horizon.mediaupload;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.C02600ao;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.horizon.mediaupload.MC;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class MediaUploaderSyncServiceInit implements INeedInit {
    public static final int JOB_ID = MediaUploaderSyncServiceInit.class.hashCode();
    public static final int RUN_INTERVAL_IN_MS = 3600000;
    public static final String TAG = "MediaUploaderSyncServiceInit";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        JobScheduler jobScheduler;
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_mobile_save_to_fb_gaming_profile_horizon.enabled) && (jobScheduler = (JobScheduler) ((Context) AnonymousClass0J2.A03(1, 80, this._UL_mInjectionContext)).getSystemService("jobscheduler")) != null) {
            jobScheduler.schedule(new JobInfo.Builder(JOB_ID, new ComponentName((Context) AnonymousClass0J2.A03(1, 80, this._UL_mInjectionContext), MediaUploaderSyncService.class)).setRequiredNetworkType(2).setRequiresCharging(true).setPeriodic(3600000).build());
        }
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_mobile_save_to_fb_gaming_profile_horizon.enabled)) {
            C02600ao.A00().A06().A00(new Intent((Context) AnonymousClass0J2.A03(1, 80, this._UL_mInjectionContext), MediaChangeObserverService.class), (Context) AnonymousClass0J2.A03(1, 80, this._UL_mInjectionContext));
        }
    }

    @Inject
    public MediaUploaderSyncServiceInit(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
