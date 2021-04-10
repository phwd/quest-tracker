package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.C003108z;
import X.C003809k;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID"})
public class OVRLivestreamingErrorUtility {
    public static final String LIVESTREAM_FATAL_ERROR = "livestream_fatal_error";
    public static final int MIN_SA_VERSION_FOR_ERROR_HANDLING = 57149588;
    public static final int MIN_SHELL_HOME_VERSION_FOR_ERROR_HANDLING = 68334058;
    public static final String OCULUS_NOTIFICATION_TYPE = "oculus_notification_type";
    public static final String SA_PACKAGE_NAME = "com.oculus.systemactivities";
    public static final String SA_SOCIAL_NOTIFICATION_ACTION = "com.oculus.SEE_SOCIAL_NOTIFICATIONS";
    public static final String SHELL_HOME_PACKAGE_NAME = "com.oculus.vrshell.home";
    public static final String SOCIAL_PLATFORM_PACKAGE_NAME = "com.oculus.socialplatform";
    public static final String TAG = "OVRLivestreamingErrorUtility";
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    @Eager
    public final PackageManager mPackageManager;

    public static boolean A01(OVRLivestreamingErrorUtility oVRLivestreamingErrorUtility, String str, int i) {
        try {
            if (oVRLivestreamingErrorUtility.mPackageManager.getPackageInfo("com.oculus.systemactivities", 0).versionCode >= i) {
                return true;
            }
            AnonymousClass0NO.A0E(TAG, "Old version of %s detected, halting error broadcast", str);
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            AnonymousClass0NO.A0E(TAG, "%s package not found.  This is super bad. ", str, e);
            return false;
        }
    }

    public static void A00(OVRLivestreamingErrorUtility oVRLivestreamingErrorUtility, String str) {
        Intent intent = new Intent("com.oculus.SEE_SOCIAL_NOTIFICATIONS");
        intent.setPackage(str);
        intent.putExtra("oculus_notification_type", LIVESTREAM_FATAL_ERROR);
        oVRLivestreamingErrorUtility.mContext.sendBroadcast(intent);
    }

    @Inject
    public OVRLivestreamingErrorUtility(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
        this.mPackageManager = C003809k.A03(r2);
    }
}
