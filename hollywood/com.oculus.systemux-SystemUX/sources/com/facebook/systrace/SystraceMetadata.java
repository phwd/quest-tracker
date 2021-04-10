package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SystraceMetadata {
    public static final String METADATA_PROCESS_LABELS = "process_labels";
    public static final String METADATA_PROCESS_NAME = "process_name";
    public static final String METADATA_THREAD_NAME = "thread_name";
    private static volatile WeakHashMap<Thread, Integer> sThreadTidMap;

    public static void init() {
    }

    /* access modifiers changed from: private */
    public static void onTraceStopped() {
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
            SystraceMetadata.onTraceStopped();
        }
    }

    static {
        TraceConfig.addListener(new JustInTimeTracer());
    }

    private SystraceMetadata() {
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

    public static void enableTidTracking() {
        sThreadTidMap = new WeakHashMap<>();
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
            Systrace.traceMetadata(64, METADATA_PROCESS_LABELS, sb.toString(), 0);
        }
    }

    private static void traceProcessMetadata() {
        if (TraceConfig.isTracing(64)) {
            Systrace.traceMetadata(64, METADATA_PROCESS_NAME, SystraceEnabledDetector.getMyCommandLine(), 0);
            Systrace.traceMetadata(64, METADATA_PROCESS_LABELS, getProcessLabels(), 0);
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
            Systrace.traceMetadata(64, METADATA_THREAD_NAME, currentThread.getName(), myTid);
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
                    Systrace.traceMetadata(64, METADATA_THREAD_NAME, entry.getKey().getName(), entry.getValue().intValue());
                }
            } finally {
                Systrace.endSection(64);
            }
        }
    }
}
