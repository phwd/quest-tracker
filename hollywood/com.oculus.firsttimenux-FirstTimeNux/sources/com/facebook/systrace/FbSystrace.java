package com.facebook.systrace;

import com.facebook.common.errorreporting.SoftError;
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
            Systrace.traceCounter(1, "fburl.com/fbsystrace", SoftError.DEFAULT_SOFT_ERROR_REPORTING_FREQUENCY);
            Systrace.traceCounter(1, "USE fbsystrace", SoftError.DEFAULT_SOFT_ERROR_REPORTING_FREQUENCY);
            Systrace.traceCounter(1, "DO NOT USE systrace", SoftError.DEFAULT_SOFT_ERROR_REPORTING_FREQUENCY);
        } else if (sIssuedSystraceWarning && !TraceConfig.isTracing(1)) {
            sIssuedSystraceWarning = false;
        }
    }
}
