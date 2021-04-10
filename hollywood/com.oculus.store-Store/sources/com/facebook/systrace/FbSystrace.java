package com.facebook.systrace;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
class FbSystrace {
    private static volatile boolean sIssuedSystraceWarning = false;

    FbSystrace() {
    }

    public static long computeDeltaNanos(long newRealtimeNanos) {
        warnIfNotFbSystrace();
        return newRealtimeNanos - System.nanoTime();
    }

    private static void warnIfNotFbSystrace() {
        if (TraceConfig.isTracing(1) && !sIssuedSystraceWarning) {
            sIssuedSystraceWarning = true;
            Systrace.traceCounter(1, "fburl.com/fbsystrace", 1000);
            Systrace.traceCounter(1, "USE fbsystrace", 1000);
            Systrace.traceCounter(1, "DO NOT USE systrace", 1000);
        } else if (sIssuedSystraceWarning && !TraceConfig.isTracing(1)) {
            sIssuedSystraceWarning = false;
        }
    }
}
