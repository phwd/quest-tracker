package com.facebook.systrace;

import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.androidinternals.android.os.TraceInternal;
import com.facebook.infer.annotation.Nullsafe;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TraceConfig {
    private static final String FB_SYSTRACE_TAG_KEY = "debug.fbsystrace.tags";
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

    private TraceConfig() {
    }

    public static void addListener(TraceListener listener) {
        sListeners.addListener(listener);
    }

    public static void removeListener(TraceListener listener) {
        sListeners.removeListener(listener);
    }

    /* access modifiers changed from: private */
    public static void updateTraceConfig(boolean fromSystemPropertyChange) {
        long traceTags;
        boolean isTracing = SystraceEnabledDetector.computeIsTracing();
        long traceTags2 = computeTraceTags();
        if (!isTracing || traceTags2 == 0) {
            traceTags = 0;
        } else {
            traceTags = traceTags2 | 1;
        }
        boolean traceStatusChanged = (sEnabledTags == 0 && traceTags != 0) || (traceTags == 0 && sEnabledTags != 0);
        sEnabledTags = traceTags;
        if (traceStatusChanged) {
            TraceDirect.setEnabledTags(traceTags);
            asyncNotifyListeners(traceTags, fromSystemPropertyChange);
        }
    }

    static void forceEnabledTags(long tags) {
        sEnabledTags = tags;
    }

    static long computeTraceTags() {
        return SystemPropertiesInternal.getLong(FB_SYSTRACE_TAG_KEY, 0);
    }

    private static void asyncNotifyListeners(long traceTags, boolean fromSystemPropertyChange) {
        if (!(traceTags > 0)) {
            sListeners.notifyListenersForTraceStop();
        } else if (!fromSystemPropertyChange) {
            sListeners.notifyListenersForTraceStart();
        } else {
            sListeners.asyncNotifyListenersForTraceStart();
        }
    }

    public static boolean isTracing(long tag) {
        return (sEnabledTags & tag) != 0;
    }

    public static void setAppTracingAllowed(boolean allowed) {
        TraceInternal.setAppTracingAllowed(allowed);
        updateTraceConfig(false);
    }

    static long getAndroidTagsEnabled() {
        return SystemPropertiesInternal.getLong("debug.atrace.tags.enableflags", 0);
    }

    static long getFacebookTagsEnabled() {
        return sEnabledTags;
    }
}
