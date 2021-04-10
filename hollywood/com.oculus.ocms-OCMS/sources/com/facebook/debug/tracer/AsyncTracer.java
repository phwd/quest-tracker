package com.facebook.debug.tracer;

import com.facebook.debug.log.LogPrefixer;
import com.facebook.systrace.Systrace;
import java.util.List;

public class AsyncTracer {
    private final String mComment;
    private final int mId;
    private final long mStartNanos;
    private final ThreadTrace mThreadTrace;
    private long mTotalNanos = -1;

    public static AsyncTracer startAsyncTracer(String str, Object... objArr) {
        return startAsyncTracerInternal(str, objArr);
    }

    public static AsyncTracer startAsyncTracer(String str) {
        return startAsyncTracerInternal(str, null);
    }

    public static AsyncTracer startAsyncTracerInternal(String str, Object[] objArr) {
        ThreadTrace threadTrace = ThreadTrace.sThreadLocalState.get();
        int startTracer = threadTrace.startTracer(str, objArr, true);
        String formattedComment = threadTrace.getEvent(startTracer).getFormattedComment();
        AsyncTracer asyncTracer = new AsyncTracer(threadTrace, startTracer, TracerClock.nanoTime(), formattedComment);
        Systrace.beginAsyncSection(32, formattedComment, asyncTracer.mId);
        return asyncTracer;
    }

    private AsyncTracer(ThreadTrace threadTrace, int i, long j, String str) {
        this.mThreadTrace = threadTrace;
        this.mId = i;
        this.mStartNanos = j;
        this.mComment = str;
    }

    public void adjustStartTime(long j) {
        this.mThreadTrace.adjustStartTime(this.mId, j);
    }

    public void stop() {
        stopAsyncTrace(0, false);
    }

    public void stop(long j) {
        stopAsyncTrace(j, true);
    }

    public long getElapsedTimeMs() {
        long j = this.mTotalNanos;
        if (j == -1) {
            return (TracerClock.nanoTime() - this.mStartNanos) / 1000000;
        }
        return (j - this.mStartNanos) / 1000000;
    }

    public long stopAndReturnElapsedMs() {
        return stopAsyncTrace(0, false) / 1000000;
    }

    public long stopAndReturnElapsedMs(long j) {
        return stopAsyncTrace(j, true) / 1000000;
    }

    public long getId() {
        return (long) this.mId;
    }

    private long stopAsyncTrace(long j, boolean z) {
        Systrace.endAsyncSection(32, this.mComment, this.mId);
        long stopTracer = this.mThreadTrace.stopTracer(this.mId, j, z);
        if (stopTracer == -1) {
            stopTracer = TracerClock.nanoTime() - this.mStartNanos;
        }
        this.mTotalNanos = stopTracer;
        return stopTracer;
    }

    public void logTrace(Class<?> cls) {
        logTrace(3, LogPrefixer.renderClass(cls));
    }

    public void logTrace(String str) {
        logTrace(3, str);
    }

    public void logTrace(int i, String str) {
        this.mThreadTrace.logTrace(this.mId, i, str);
    }

    public List<TraceEvent> getTraceEvents() {
        return this.mThreadTrace.getEvents(this.mId);
    }
}
