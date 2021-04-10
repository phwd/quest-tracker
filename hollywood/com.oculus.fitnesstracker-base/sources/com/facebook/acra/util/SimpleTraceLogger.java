package com.facebook.acra.util;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public final class SimpleTraceLogger {
    public static int NO_LIMIT;
    private Queue<TraceLogLine> mTrace;
    protected final int mTraceCountLimit = 20;

    /* access modifiers changed from: protected */
    public static class TraceLogLine {
        public final String toString() {
            Locale locale = Locale.US;
            throw null;
        }
    }

    public SimpleTraceLogger(int i) {
        clear();
    }

    public final synchronized String toString() {
        return toString(NO_LIMIT);
    }

    public final synchronized String toString(int i) {
        StringBuilder sb;
        int i2;
        sb = new StringBuilder();
        int i3 = 0;
        if (i <= NO_LIMIT) {
            i2 = 0;
        } else {
            i2 = Math.max(this.mTrace.size() - i, 0);
        }
        for (TraceLogLine traceLogLine : this.mTrace) {
            if (i3 < i2) {
                i3++;
            } else {
                throw null;
            }
        }
        return sb.toString();
    }

    private synchronized void clear() {
        this.mTrace = new LinkedList();
    }
}
