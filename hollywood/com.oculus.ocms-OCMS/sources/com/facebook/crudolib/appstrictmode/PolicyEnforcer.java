package com.facebook.crudolib.appstrictmode;

import android.util.Log;
import com.facebook.crudolib.appstrictmode.AppStrictMode;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
class PolicyEnforcer {
    private static AppStrictMode.AppPolicy sAppPolicy = AppStrictMode.AppPolicy.LAX;

    PolicyEnforcer() {
    }

    public static void setAppPolicy(AppStrictMode.AppPolicy appPolicy) {
        sAppPolicy = appPolicy;
        boolean z = true;
        if ((sAppPolicy.mMask & 1) == 0) {
            z = false;
        }
        CloseGuard.setEnabled(z);
    }

    public static void reportViolation(int i, String str) {
        if ((sAppPolicy.mMask & i) != 0) {
            reportViolation(i, str, new ReportingRuntimeException());
        }
    }

    public static void reportViolation(int i, String str, @Nullable Throwable th) {
        if ((i & sAppPolicy.mMask) != 0 && (sAppPolicy.mMask & 65536) != 0) {
            Log.e(AppStrictModeModule.TAG, str, th);
        }
    }

    private static class ReportingRuntimeException extends RuntimeException {
        private ReportingRuntimeException() {
        }
    }
}
