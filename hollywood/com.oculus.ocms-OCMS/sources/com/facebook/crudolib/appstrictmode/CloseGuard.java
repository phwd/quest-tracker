package com.facebook.crudolib.appstrictmode;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CloseGuard {
    private static volatile boolean sEnabled = false;
    @Nullable
    private volatile Throwable allocationSite;

    private CloseGuard() {
    }

    static void setEnabled(boolean z) {
        sEnabled = z;
    }

    public static boolean getEnabled() {
        return sEnabled;
    }

    @Nullable
    public static CloseGuard open(@Nullable CloseGuard closeGuard, String str) {
        if (str == null) {
            throw new NullPointerException("closer == null");
        } else if (!sEnabled) {
            return null;
        } else {
            if (closeGuard == null) {
                closeGuard = new CloseGuard();
            } else if (closeGuard.allocationSite != null) {
                throw new IllegalArgumentException("closeGuard was never released before calling open.", closeGuard.allocationSite);
            }
            closeGuard.allocationSite = new Throwable("Explicit termination method '" + str + "' not called");
            return closeGuard;
        }
    }

    @Nullable
    public static CloseGuard open(String str) {
        return open(null, str);
    }

    public static void close(@Nullable CloseGuard closeGuard) {
        if (closeGuard != null) {
            closeGuard.allocationSite = null;
        }
    }

    public static void warnIfOpen(@Nullable CloseGuard closeGuard) {
        if (closeGuard != null && closeGuard.allocationSite != null) {
            AppStrictMode.reportViolation(1, "A resource was acquired and never released.", closeGuard.allocationSite);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            warnIfOpen(this);
        } finally {
            super.finalize();
        }
    }
}
