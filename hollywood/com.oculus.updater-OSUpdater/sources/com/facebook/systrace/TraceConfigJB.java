package com.facebook.systrace;

import com.facebook.androidinternals.android.os.SystemPropertiesInternal;

/* access modifiers changed from: package-private */
public final class TraceConfigJB {
    private static volatile Boolean sIsTracing = null;

    static {
        SystemPropertiesInternal.addChangeCallback(new Runnable() {
            /* class com.facebook.systrace.TraceConfigJB.AnonymousClass1 */

            public void run() {
                TraceConfigJB.cacheEnabledTags();
            }
        });
    }

    private TraceConfigJB() {
    }

    public static boolean isTracing() {
        if (sIsTracing == null) {
            cacheEnabledTags();
        }
        return sIsTracing.booleanValue();
    }

    /* access modifiers changed from: private */
    public static void cacheEnabledTags() {
        String str = SystemPropertiesInternal.get("debug.atrace.app_cmdlines");
        if (str.length() == 0) {
            sIsTracing = false;
            return;
        }
        String[] split = str.split(",");
        String myCommandLine = SystraceEnabledDetector.getMyCommandLine();
        for (String str2 : split) {
            if (myCommandLine.equals(str2)) {
                sIsTracing = true;
                return;
            }
        }
        sIsTracing = false;
    }
}
