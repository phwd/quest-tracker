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
        String appCommandLinesProperty = SystemPropertiesInternal.get("debug.atrace.app_cmdlines");
        if (appCommandLinesProperty.length() == 0) {
            sIsTracing = false;
            return;
        }
        String[] enabledCommandLines = appCommandLinesProperty.split(",");
        String myCommandLine = SystraceEnabledDetector.getMyCommandLine();
        for (String str : enabledCommandLines) {
            if (myCommandLine.equals(str)) {
                sIsTracing = true;
                return;
            }
        }
        sIsTracing = false;
    }
}
