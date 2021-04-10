package com.facebook.acra.util;

import android.os.SystemClock;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class SimpleTraceLogger {
    public static final int NO_LIMIT = 0;
    public static final String TAG = "SimpleTraceLogger";
    public Queue mTrace;
    public final int mTraceCountLimit;

    public class TraceLogLine {
        public final long time;
        public final String trace;

        public String toString() {
            return String.format(Locale.US, "[%d] %s", Long.valueOf(this.time), this.trace);
        }

        public TraceLogLine(String str, long j) {
            this.trace = str;
            this.time = j;
        }
    }

    public synchronized void clear() {
        this.mTrace = new LinkedList();
    }

    public SimpleTraceLogger(int i) {
        this.mTraceCountLimit = i;
        clear();
    }

    public void append(String str) {
        synchronized (this) {
            if (this.mTraceCountLimit > 0 && this.mTrace.size() == this.mTraceCountLimit) {
                this.mTrace.remove();
            }
            this.mTrace.offer(new TraceLogLine(str, SystemClock.uptimeMillis()));
        }
    }

    public void append(String str, Object... objArr) {
        append(String.format(str, objArr));
    }

    public synchronized String toString() {
        return toString(0);
    }

    public synchronized String toString(int i) {
        StringBuilder sb;
        int i2;
        sb = new StringBuilder();
        int i3 = 0;
        if (i <= 0) {
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
}
