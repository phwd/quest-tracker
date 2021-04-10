package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import java.util.Map;
import java.util.WeakHashMap;

public final class SystraceMetadata {
    private static volatile WeakHashMap<Thread, Integer> sThreadTidMap;

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

    /* access modifiers changed from: private */
    public static void onTraceStarted() {
        traceCurrentThreadMetadata();
        traceTagsEnabledMetadata();
        traceProcessMetadata();
        if (trackingTids()) {
            traceExistingThreadsMetadata();
        }
    }

    /* access modifiers changed from: private */
    public static void onTraceStopped() {
    }

    public static void init() {
    }

    private static boolean trackingTids() {
        return sThreadTidMap != null;
    }

    private static void traceTagsEnabledMetadata() {
        if (TraceConfig.isTracing(1)) {
            StringBuilder taglist = new StringBuilder(127);
            taglist.append("Android trace tags: ");
            taglist.append(TraceConfig.getAndroidTagsEnabled());
            taglist.append(", Facebook trace tags: ");
            taglist.append(TraceConfig.getFacebookTagsEnabled());
            Systrace.traceMetadata(64, "process_labels", taglist.toString(), 0);
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
        String heapgrowthlimit = SystemPropertiesInternal.get("dalvik.vm.heapgrowthlimit");
        String heapminfree = SystemPropertiesInternal.get("dalvik.vm.heapmaxfree");
        String heapmaxfree = SystemPropertiesInternal.get("dalvik.vm.heapminfree");
        return String.format("device=%s,heapgrowthlimit=%s,heapstartsize=%s,heapminfree=%s,heapmaxfree=%s,heaptargetutilization=%s", Build.MODEL, heapgrowthlimit, SystemPropertiesInternal.get("dalvik.vm.heapstartsize"), heapminfree, heapmaxfree, SystemPropertiesInternal.get("dalvik.vm.heaptargetutilization"));
    }

    public static void traceCurrentThreadMetadata() {
        if (TraceConfig.isTracing(64)) {
            int tid = Process.myTid();
            Thread currentThread = Thread.currentThread();
            Systrace.traceMetadata(64, "thread_name", currentThread.getName(), tid);
            if (sThreadTidMap != null) {
                sThreadTidMap.put(currentThread, Integer.valueOf(tid));
            }
        }
    }

    private static void traceExistingThreadsMetadata() {
        if (TraceConfig.isTracing(64)) {
            Systrace.beginSection(64, "TraceExistingThreadsMetadata");
            try {
                for (Map.Entry<Thread, Integer> threadAndTid : sThreadTidMap.entrySet()) {
                    Systrace.traceMetadata(64, "thread_name", threadAndTid.getKey().getName(), threadAndTid.getValue().intValue());
                }
            } finally {
                Systrace.endSection(64);
            }
        }
    }
}
