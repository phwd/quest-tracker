package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import java.util.Map;
import java.util.WeakHashMap;

public final class SystraceMetadata {
    private static volatile WeakHashMap<Thread, Integer> sThreadTidMap;

    static /* synthetic */ void access$100() {
    }

    public static void init() {
    }

    private static class JustInTimeTracer implements TraceListener {
        private JustInTimeTracer() {
        }

        @Override // com.facebook.systrace.TraceListener
        public void onTraceStarted() {
            SystraceMetadata.onTraceStarted();
        }

        @Override // com.facebook.systrace.TraceListener
        public void onTraceStopped() {
            SystraceMetadata.access$100();
        }
    }

    static {
        TraceConfig.addListener(new JustInTimeTracer());
    }

    /* access modifiers changed from: private */
    public static void onTraceStarted() {
        traceCurrentThreadMetadata();
        traceTagsEnabledMetadata();
        traceProcessMetadata();
        if (trackingTids()) {
            traceExistingThreadsMetadata();
        }
    }

    private static boolean trackingTids() {
        return sThreadTidMap != null;
    }

    private static void traceTagsEnabledMetadata() {
        if (TraceConfig.isTracing(1)) {
            StringBuilder sb = new StringBuilder(127);
            sb.append("Android trace tags: ");
            sb.append(TraceConfig.getAndroidTagsEnabled());
            sb.append(", Facebook trace tags: ");
            sb.append(TraceConfig.getFacebookTagsEnabled());
            Systrace.traceMetadata(64, "process_labels", sb.toString(), 0);
        }
    }

    private static void traceProcessMetadata() {
        if (TraceConfig.isTracing(64)) {
            Systrace.traceMetadata(64, "process_name", SystraceEnabledDetector.getMyCommandLine(), 0);
            Systrace.traceMetadata(64, "process_labels", getProcessLabels(), 0);
        }
    }

    @SuppressLint({"StringFormatUse"})
    private static String getProcessLabels() {
        String str = SystemPropertiesInternal.get("dalvik.vm.heapgrowthlimit");
        String str2 = SystemPropertiesInternal.get("dalvik.vm.heapmaxfree");
        String str3 = SystemPropertiesInternal.get("dalvik.vm.heapminfree");
        return String.format("device=%s,heapgrowthlimit=%s,heapstartsize=%s,heapminfree=%s,heapmaxfree=%s,heaptargetutilization=%s", Build.MODEL, str, SystemPropertiesInternal.get("dalvik.vm.heapstartsize"), str2, str3, SystemPropertiesInternal.get("dalvik.vm.heaptargetutilization"));
    }

    public static void traceCurrentThreadMetadata() {
        if (TraceConfig.isTracing(64)) {
            int myTid = Process.myTid();
            Thread currentThread = Thread.currentThread();
            Systrace.traceMetadata(64, "thread_name", currentThread.getName(), myTid);
            if (sThreadTidMap != null) {
                sThreadTidMap.put(currentThread, Integer.valueOf(myTid));
            }
        }
    }

    private static void traceExistingThreadsMetadata() {
        if (TraceConfig.isTracing(64)) {
            Systrace.beginSection(64, "TraceExistingThreadsMetadata");
            try {
                for (Map.Entry<Thread, Integer> entry : sThreadTidMap.entrySet()) {
                    Systrace.traceMetadata(64, "thread_name", entry.getKey().getName(), entry.getValue().intValue());
                }
            } finally {
                Systrace.endSection(64);
            }
        }
    }
}
