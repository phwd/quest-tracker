package com.facebook.systrace;

import android.os.Build;
import android.os.Process;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SystraceMetadata {
    private static volatile WeakHashMap<Thread, Integer> sThreadTidMap;

    static /* synthetic */ void access$100() {
    }

    public static void init() {
    }

    static class JustInTimeTracer implements TraceListener {
        private JustInTimeTracer() {
        }

        /* synthetic */ JustInTimeTracer(byte b) {
            this();
        }

        @Override // com.facebook.systrace.TraceListener
        public final void onTraceStarted() {
            SystraceMetadata.access$000();
        }

        @Override // com.facebook.systrace.TraceListener
        public final void onTraceStopped() {
            SystraceMetadata.access$100();
        }
    }

    static {
        TraceConfig.addListener(new JustInTimeTracer((byte) 0));
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

    static /* synthetic */ void access$000() {
        if (TraceConfig.isTracing(64)) {
            int myTid = Process.myTid();
            Thread currentThread = Thread.currentThread();
            Systrace.traceMetadata(64, "thread_name", currentThread.getName(), myTid);
            if (sThreadTidMap != null) {
                sThreadTidMap.put(currentThread, Integer.valueOf(myTid));
            }
        }
        boolean z = false;
        if (TraceConfig.isTracing(1)) {
            StringBuilder sb = new StringBuilder(127);
            sb.append("Android trace tags: ");
            sb.append(TraceConfig.getAndroidTagsEnabled());
            sb.append(", Facebook trace tags: ");
            sb.append(TraceConfig.getFacebookTagsEnabled());
            Systrace.traceMetadata(64, "process_labels", sb.toString(), 0);
        }
        if (TraceConfig.isTracing(64)) {
            Systrace.traceMetadata(64, "process_name", SystraceEnabledDetector.getMyCommandLine(), 0);
            String str = SystemPropertiesInternal.get("dalvik.vm.heapgrowthlimit");
            String str2 = SystemPropertiesInternal.get("dalvik.vm.heapmaxfree");
            String str3 = SystemPropertiesInternal.get("dalvik.vm.heapminfree");
            Systrace.traceMetadata(64, "process_labels", String.format("device=%s,heapgrowthlimit=%s,heapstartsize=%s,heapminfree=%s,heapmaxfree=%s,heaptargetutilization=%s", Build.MODEL, str, SystemPropertiesInternal.get("dalvik.vm.heapstartsize"), str2, str3, SystemPropertiesInternal.get("dalvik.vm.heaptargetutilization")), 0);
        }
        if (sThreadTidMap != null) {
            z = true;
        }
        if (z) {
            traceExistingThreadsMetadata();
        }
    }
}
