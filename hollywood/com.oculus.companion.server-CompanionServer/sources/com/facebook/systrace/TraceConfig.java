package com.facebook.systrace;

import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.androidinternals.android.os.TraceInternal;

/* access modifiers changed from: package-private */
public final class TraceConfig {
    private static volatile long sEnabledTags = 0;
    private static final TraceListenerNotifier sListeners = new TraceListenerNotifier();

    static {
        staticInit();
    }

    private static void staticInit() {
        updateTraceConfig(false);
        SystemPropertiesInternal.addChangeCallback(new Runnable() {
            /* class com.facebook.systrace.TraceConfig.AnonymousClass1 */

            public void run() {
                TraceConfig.updateTraceConfig(true);
            }
        });
    }

    public static void addListener(TraceListener traceListener) {
        sListeners.addListener(traceListener);
    }

    /* access modifiers changed from: private */
    public static void updateTraceConfig(boolean z) {
        boolean computeIsTracing = SystraceEnabledDetector.computeIsTracing();
        long computeTraceTags = computeTraceTags();
        long j = (!computeIsTracing || computeTraceTags == 0) ? 0 : computeTraceTags | 1;
        boolean z2 = (sEnabledTags == 0 && j != 0) || (j == 0 && sEnabledTags != 0);
        sEnabledTags = j;
        if (z2) {
            TraceDirect.setEnabledTags(j);
            asyncNotifyListeners(j, z);
        }
    }

    static long computeTraceTags() {
        return SystemPropertiesInternal.getLong("debug.fbsystrace.tags", 0);
    }

    private static void asyncNotifyListeners(long j, boolean z) {
        if (!(j > 0)) {
            sListeners.notifyListenersForTraceStop();
        } else if (!z) {
            sListeners.notifyListenersForTraceStart();
        } else {
            sListeners.asyncNotifyListenersForTraceStart();
        }
    }

    public static boolean isTracing(long j) {
        return (j & sEnabledTags) != 0;
    }

    public static void setAppTracingAllowed(boolean z) {
        TraceInternal.setAppTracingAllowed(z);
        updateTraceConfig(false);
    }

    static long getAndroidTagsEnabled() {
        return SystemPropertiesInternal.getLong("debug.atrace.tags.enableflags", 0);
    }

    static long getFacebookTagsEnabled() {
        return sEnabledTags;
    }
}
