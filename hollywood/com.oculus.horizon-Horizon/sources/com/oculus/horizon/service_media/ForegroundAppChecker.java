package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.C003108z;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.service_media.AppSwitchManager;
import com.oculus.os.ActivityManagerUtils;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ForegroundAppChecker {
    public static final String TAG = "ForegroundAppChecker";
    public static volatile ForegroundAppChecker _UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_ForegroundAppChecker_ULSEP_INSTANCE;
    @Nullable
    public AppSwitchManager.AppSwitchListener mAppSwitchListener = null;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Nullable
    public Integer mForegroundAppUid = null;
    public Boolean mIsOverlayInputFocused = false;
    @Nullable
    public TopWindowManager mTopWindowManager = null;

    public class TopWindowManager {
        @Nullable
        public ActivityManagerUtils mActivityManagerUtils = null;

        public class TopWindowObserver implements ActivityManagerUtils.WindowLayoutObserver {
            public void onFocusedWindowChanged(IBinder iBinder, int i) {
                ForegroundAppChecker foregroundAppChecker = ForegroundAppChecker.this;
                foregroundAppChecker.mForegroundAppUid = Integer.valueOf(i);
                AppSwitchManager.AppSwitchListener appSwitchListener = foregroundAppChecker.mAppSwitchListener;
                if (appSwitchListener != null) {
                    appSwitchListener.onForegroundAppUpdate();
                }
            }

            public TopWindowObserver() {
            }
        }

        public TopWindowManager() {
        }

        public final void A00() {
            if (this.mActivityManagerUtils == null) {
                ActivityManagerUtils activityManagerUtils = new ActivityManagerUtils();
                this.mActivityManagerUtils = activityManagerUtils;
                activityManagerUtils.setWindowLayoutObserver(new TopWindowObserver(), new Handler(Looper.getMainLooper()));
            }
        }

        public final void A01() {
            ActivityManagerUtils activityManagerUtils = this.mActivityManagerUtils;
            if (activityManagerUtils != null) {
                activityManagerUtils.clearWindowLayoutObserver();
                this.mActivityManagerUtils = null;
            }
        }
    }

    @Nullable
    public static Integer A00(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            return Integer.valueOf(context.getPackageManager().getApplicationInfo(str, 0).uid);
        } catch (PackageManager.NameNotFoundException e) {
            AnonymousClass0NO.A0E(TAG, "unable to get uid for package %s, error:", str, e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
        if ("com.oculus.vrshell".equals(r1) == false) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A01() {
        /*
            r2 = this;
            java.lang.Boolean r0 = r2.mIsOverlayInputFocused
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002d
            java.lang.Integer r0 = r2.mForegroundAppUid
            if (r0 == 0) goto L_0x0033
            android.content.Context r0 = r2.mContext
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            if (r0 == 0) goto L_0x0033
            android.content.Context r0 = r2.mContext
            android.content.pm.PackageManager r1 = r0.getPackageManager()
            java.lang.Integer r0 = r2.mForegroundAppUid
            int r0 = r0.intValue()
            java.lang.String r1 = r1.getNameForUid(r0)
        L_0x0024:
            java.lang.String r0 = "com.oculus.vrshell"
            boolean r1 = r0.equals(r1)
            r0 = 1
            if (r1 != 0) goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x0033:
            r1 = 0
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.ForegroundAppChecker.A01():java.lang.Boolean");
    }

    public final boolean A02(int i) {
        String nameForUid = this.mContext.getPackageManager().getNameForUid(i);
        if ("com.oculus.vrshell".equals(nameForUid) || "com.oculus.shellenv".equals(nameForUid)) {
            return true;
        }
        return false;
    }

    public final boolean A03(String str) {
        if (this.mTopWindowManager == null) {
            TopWindowManager topWindowManager = new TopWindowManager();
            this.mTopWindowManager = topWindowManager;
            topWindowManager.A00();
        }
        if (this.mForegroundAppUid == null) {
            AnonymousClass0NO.A08(TAG, "mForegroundAppUid is null.");
        } else {
            Integer A00 = A00(this.mContext, str);
            if (A00 != null && A00.equals(this.mForegroundAppUid)) {
                return true;
            }
            if (!A02(this.mForegroundAppUid.intValue()) || !A02(A00.intValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Inject
    public ForegroundAppChecker(AbstractC06640p5 r2) {
        this.mContext = C003108z.A02(r2);
    }
}
