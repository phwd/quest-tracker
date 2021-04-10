package com.oculus.headlesshorizon.social;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import android.content.Context;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.job.OculusJobHelper;
import com.oculus.util.constants.JobSchedulerIds;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
public class SocialJobScheduler extends OculusJobHelper {
    public static final long PERIODIC_REFRESH_TIMER = TimeUnit.MINUTES.toMillis(15);
    public static final String TAG = "SocialJobScheduler";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public SocialJobScheduler(AbstractC06640p5 r3, @ForAppContext Context context) {
        super(context, SocialJobService.class, JobSchedulerIds.USER_PROFILE_UPDATE);
        this._UL_mInjectionContext = new AnonymousClass0QC(0, r3);
    }
}
