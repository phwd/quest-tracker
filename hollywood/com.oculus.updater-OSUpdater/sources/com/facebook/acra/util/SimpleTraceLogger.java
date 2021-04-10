package com.facebook.acra.util;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class SimpleTraceLogger {
    public static int NO_LIMIT = 0;
    public static final String TAG = "SimpleTraceLogger";
    private Queue<TraceLogLine> mTrace;
    protected final int mTraceCountLimit;

    /* access modifiers changed from: protected */
    public static class TraceLogLine {
        public final long time;
        public final String trace;

        TraceLogLine(String str, long j) {
            this.trace = str;
            this.time = j;
        }

        public String toString() {
            return String.format(Locale.US, "[%d] %s", Long.valueOf(this.time), this.trace);
        }
    }

    public SimpleTraceLogger(int i) {
        this.mTraceCountLimit = i;
        clear();
    }

    public void append(String str, Object... objArr) {
        append(String.format(str, objArr));
    }

    public void append(String str) {
        synchronized (this) {
            if (this.mTraceCountLimit > NO_LIMIT && this.mTrace.size() == this.mTraceCountLimit) {
                this.mTrace.remove();
            }
            this.mTrace.offer(new TraceLogLine(str, SystemClock.uptimeMillis()));
        }
    }

    public synchronized String toString() {
        return toString(NO_LIMIT);
    }

    public synchronized String toString(int i) {
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
            if (i3 >= i2) {
                sb.append(traceLogLine.toString());
                sb.append('\n');
            }
            i3++;
        }
        return sb.toString();
    }

    public synchronized void clear() {
        this.mTrace = new LinkedList();
    }
}
