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

    public static AsyncTracer startAsyncTracer(String comment, Object... args) {
        return startAsyncTracerInternal(comment, args);
    }

    public static AsyncTracer startAsyncTracer(String comment) {
        return startAsyncTracerInternal(comment, null);
    }

    public static AsyncTracer startAsyncTracerInternal(String comment, Object[] args) {
        ThreadTrace threadTrace = ThreadTrace.sThreadLocalState.get();
        int id = threadTrace.startTracer(comment, args, true);
        String formattedComment = threadTrace.getEvent(id).getFormattedComment();
        AsyncTracer trace = new AsyncTracer(threadTrace, id, TracerClock.nanoTime(), formattedComment);
        Systrace.beginAsyncSection(32, formattedComment, trace.mId);
        return trace;
    }

    private AsyncTracer(ThreadTrace threadTrace, int id, long startNanos, String comment) {
        this.mThreadTrace = threadTrace;
        this.mId = id;
        this.mStartNanos = startNanos;
        this.mComment = comment;
    }

    public void adjustStartTime(long timeOffsetMs) {
        this.mThreadTrace.adjustStartTime(this.mId, timeOffsetMs);
    }

    public void stop() {
        stopAsyncTrace(0, false);
    }

    public void stop(long silenceThresholdParam) {
        stopAsyncTrace(silenceThresholdParam, true);
    }

    public long getElapsedTimeMs() {
        if (this.mTotalNanos == -1) {
            return (TracerClock.nanoTime() - this.mStartNanos) / 1000000;
        }
        return (this.mTotalNanos - this.mStartNanos) / 1000000;
    }

    public long stopAndReturnElapsedMs() {
        return stopAsyncTrace(0, false) / 1000000;
    }

    public long stopAndReturnElapsedMs(long silenceThresholdParam) {
        return stopAsyncTrace(silenceThresholdParam, true) / 1000000;
    }

    public long getId() {
        return (long) this.mId;
    }

    private long stopAsyncTrace(long silenceThresholdParam, boolean useThreshold) {
        Systrace.endAsyncSection(32, this.mComment, this.mId);
        long elapsedNanos = this.mThreadTrace.stopTracer(this.mId, silenceThresholdParam, useThreshold);
        if (elapsedNanos == -1) {
            elapsedNanos = TracerClock.nanoTime() - this.mStartNanos;
        }
        this.mTotalNanos = elapsedNanos;
        return elapsedNanos;
    }

    public void logTrace(Class<?> tag) {
        logTrace(3, LogPrefixer.renderClass(tag));
    }

    public void logTrace(String tag) {
        logTrace(3, tag);
    }

    public void logTrace(int logLevel, String tag) {
        this.mThreadTrace.logTrace(this.mId, logLevel, tag);
    }

    public List<TraceEvent> getTraceEvents() {
        return this.mThreadTrace.getEvents(this.mId);
    }
}
