package com.facebook.systrace;

import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.androidinternals.android.os.TraceInternal;
import com.facebook.infer.annotation.Nullsafe;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TraceConfig {
    private static volatile long sEnabledTags = 0;
    private static final TraceListenerNotifier sListeners = new TraceListenerNotifier();

    static {
        updateTraceConfig(false);
        SystemPropertiesInternal.addChangeCallback(new Runnable() {
            /* class com.facebook.systrace.TraceConfig.AnonymousClass1 */

            public final void run() {
                TraceConfig.updateTraceConfig(true);
            }
        });
    }

    public static void addListener(TraceListener traceListener) {
        TraceListenerNotifier traceListenerNotifier = sListeners;
        synchronized (traceListenerNotifier.mLock) {
            traceListenerNotifier.mListeners.add(traceListener);
            if (traceListenerNotifier.mIsTracing) {
                traceListener.onTraceStarted();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateTraceConfig(boolean z) {
        boolean computeIsTracing = SystraceEnabledDetector.computeIsTracing();
        long j = SystemPropertiesInternal.getLong("debug.fbsystrace.tags", 0);
        long j2 = (!computeIsTracing || j == 0) ? 0 : j | 1;
        boolean z2 = (sEnabledTags == 0 && j2 != 0) || (j2 == 0 && sEnabledTags != 0);
        sEnabledTags = j2;
        if (z2) {
            TraceDirect.setEnabledTags(j2);
            asyncNotifyListeners(j2, z);
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
        TraceInternal.setAppTracingAllowed(true);
        updateTraceConfig(false);
    }

    static long getAndroidTagsEnabled() {
        return SystemPropertiesInternal.getLong("debug.atrace.tags.enableflags", 0);
    }

    static long getFacebookTagsEnabled() {
        return sEnabledTags;
    }
}
