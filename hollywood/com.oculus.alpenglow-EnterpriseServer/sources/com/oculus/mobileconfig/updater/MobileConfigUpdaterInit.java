package com.oculus.mobileconfig.updater;

import X.AbstractC02990bJ;
import X.AbstractC07240oz;
import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.C01340Gg;
import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.common.init.INeedInit;
import com.oculus.logging.utils.EventManager;
import com.oculus.mobileconfig.init.MobileConfigInitOptions;
import com.oculus.util.constants.JobSchedulerIds;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@TargetApi(21)
public class MobileConfigUpdaterInit implements INeedInit {
    public static final String ERROR_FAILED_TO_SCHEDULE = "mobileconfig_updater_job_schedule_failed";
    public static final String EVENT_SCHEDULE_FAILED = "oc_mobileconfig_schedule_failed";
    public static final String EVENT_SCHEDULE_INIT = "oc_mobileconfig_schedule_init";
    public static final String EVENT_SCHEDULE_SUCCESSFUL = "oc_mobileconfig_schedule_successful";
    public static String TAG;
    public static final long UPDATE_PERIOD_MS = TimeUnit.HOURS.toMillis(6);
    public AnonymousClass0R7 _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    public final AbstractC07240oz<Credentials> mCredentialsProvider;

    @Override // com.oculus.common.init.INeedInit
    public final void A5C() {
        String str;
        ((EventManager) AnonymousClass0Lh.A03(1, 103, this._UL_mInjectionContext)).A1z(EVENT_SCHEDULE_INIT).A5i();
        if (this.mCredentialsProvider.get() != null) {
            for (JobInfo jobInfo : ((JobScheduler) this.mContext.getSystemService("jobscheduler")).getAllPendingJobs()) {
                if (jobInfo.getId() == 1828726827) {
                    return;
                }
            }
            int schedule = ((JobScheduler) this.mContext.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(JobSchedulerIds.MOBILE_CONFIG_UPDATE, new ComponentName(this.mContext, MobileConfigUpdaterJobService.class)).setPeriodic(UPDATE_PERIOD_MS).setRequiredNetworkType(1).build());
            EventManager eventManager = (EventManager) AnonymousClass0Lh.A03(1, 103, this._UL_mInjectionContext);
            if (schedule == 1) {
                str = EVENT_SCHEDULE_SUCCESSFUL;
            } else {
                str = EVENT_SCHEDULE_FAILED;
            }
            eventManager.A1z(str).A5i();
        }
    }

    @Inject
    public MobileConfigUpdaterInit(AbstractC02990bJ r3, MobileConfigInitOptions mobileConfigInitOptions) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
        this.mContext = C01340Gg.A02(r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        TAG = AnonymousClass006.A05(mobileConfigInitOptions.mTagPrefix, "MobileConfigUpdaterInit");
    }
}
