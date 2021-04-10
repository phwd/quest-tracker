package com.oculus.userserver.usercleaner;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.concurrent.TimeUnit;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class UserCleanerScheduler {
    public static final String TAG = "UserCleanerScheduler";
    public final long REMOVAL_INTERVAL_MS = TimeUnit.DAYS.toMillis(1);
    public Om _UL_mInjectionContext;

    @Inject
    public UserCleanerScheduler(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
