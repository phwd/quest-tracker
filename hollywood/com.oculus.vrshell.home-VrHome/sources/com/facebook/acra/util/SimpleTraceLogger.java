package com.facebook.acra.util;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class SimpleTraceLogger {
    public static int NO_LIMIT = 0;
    private Queue<TraceLogLine> mTrace;
    protected final int mTraceCountLimit;

    /* access modifiers changed from: protected */
    public static class TraceLogLine {
        public final long time;
        public final String trace;

        public String toString() {
            return String.format(Locale.US, "[%d] %s", Long.valueOf(this.time), this.trace);
        }
    }

    public SimpleTraceLogger(int traceCountLimit) {
        this.mTraceCountLimit = traceCountLimit;
        clear();
    }

    public synchronized String toString() {
        return toString(NO_LIMIT);
    }

    public synchronized String toString(int limit) {
        StringBuilder retval;
        int startFrom;
        retval = new StringBuilder();
        if (limit <= NO_LIMIT) {
            startFrom = 0;
        } else {
            startFrom = Math.max(this.mTrace.size() - limit, 0);
        }
        int index = 0;
        for (TraceLogLine line : this.mTrace) {
            if (index >= startFrom) {
                retval.append(line.toString()).append('\n');
            }
            index++;
        }
        return retval.toString();
    }

    public synchronized void clear() {
        this.mTrace = new LinkedList();
    }
}
