package com.facebook.debug.tracer;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLog;
import com.facebook.debug.log.LogPrefixer;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;
import javax.annotation.Nullable;

public class Tracer {
    private static final int EXPECTED_TRACE_DEPTH = 10;
    private static final boolean LOGGING_FOR_TEST_GEN_ENABLED = false;
    private static final String LOGGING_FOR_TEST_GEN_TAG = "tracer500";
    private static final int MAX_TRACE_DEPTH = 100;
    private static final String TAG = "Tracer";
    private static final ThreadLocal<ThreadState> sThreadLocalState = new ThreadLocal<ThreadState>() {
        /* class com.facebook.debug.tracer.Tracer.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ThreadState initialValue() {
            return new ThreadState();
        }
    };
    private static TracerConfigCallback sTracerConfigCallback = new DefaultTracerConfigCallback();

    /* access modifiers changed from: private */
    public static class ThreadState {
        public boolean stackOverflowDetected;
        public final LongStack tracerStack;

        private ThreadState() {
            this.tracerStack = new LongStack(20);
        }
    }

    private Tracer() {
    }

    public static void setTracerConfigCallback(TracerConfigCallback callback) {
        if (callback == null) {
            throw new NullPointerException();
        }
        sTracerConfigCallback = callback;
    }

    public static boolean isTracing() {
        return Systrace.isTracing(32);
    }

    public static void setWarnOnUnclosedEnabled(boolean enabled) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            Systrace.setWarnOnUnclosedEnabled(enabled);
        }
    }

    @IgnoreAllocations
    public static void startTracer(String comment) {
        startTracerInternal(comment, 0, null, null, null, null, null);
    }

    @IgnoreAllocations
    public static void startTracer(String comment, @Nullable Object arg0) {
        startTracerInternal(comment, 1, arg0, null, null, null, null);
    }

    @IgnoreAllocations
    public static void startTracer(String comment, @Nullable Object arg0, @Nullable Object arg1) {
        startTracerInternal(comment, 2, arg0, arg1, null, null, null);
    }

    @IgnoreAllocations
    public static void startTracer(String comment, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2) {
        startTracerInternal(comment, 3, arg0, arg1, arg2, null, null);
    }

    @IgnoreAllocations
    public static void startTracer(String comment, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        startTracerInternal(comment, 4, arg0, arg1, arg2, arg3, null);
    }

    private static void startTracerInternal(String comment, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        if (Systrace.isTracing(32)) {
            Systrace.beginSection(32, formatStrLocaleSafe(comment, argCount, arg0, arg1, arg2, arg3, argsList));
        }
        if (sTracerConfigCallback.shouldCollectTracer()) {
            ThreadState threadState = sThreadLocalState.get();
            if (!threadState.stackOverflowDetected) {
                if (threadState.tracerStack.size() >= 100) {
                    BLog.d(Tracer.class, "Tracer stack overflow. There is probably a missing stopTracer somewhere.");
                    threadState.tracerStack.clear();
                    threadState.stackOverflowDetected = true;
                    return;
                }
                ThreadTrace threadTrace = ThreadTrace.sThreadLocalState.get();
                long id = (long) threadTrace.startTracer(comment, combineArgs(argCount, arg0, arg1, arg2, arg3, argsList), false);
                long startTime = threadTrace.getLastEventTimeNanos();
                threadState.tracerStack.push(id);
                threadState.tracerStack.push(startTime);
            }
        }
    }

    private static String formatStrLocaleSafe(String comment, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        if (argCount == -1 && (argsList == null || argsList.length == 0)) {
            return comment;
        }
        switch (argCount) {
            case 0:
                return StringFormatUtil.formatStrLocaleSafe(comment);
            case 1:
                return StringFormatUtil.formatStrLocaleSafe(comment, arg0);
            case 2:
                return StringFormatUtil.formatStrLocaleSafe(comment, arg0, arg1);
            case 3:
                return StringFormatUtil.formatStrLocaleSafe(comment, arg0, arg1, arg2);
            case 4:
                return StringFormatUtil.formatStrLocaleSafe(comment, arg0, arg1, arg2, arg3);
            default:
                try {
                    return StringFormatUtil.formatStrLocaleSafe(comment, argsList);
                } catch (IllegalFormatException e) {
                    BLog.wtf(TAG, "Bad format string", e);
                    return comment;
                }
        }
    }

    @Nullable
    private static Object[] combineArgs(int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        if (argCount == -1) {
            return argsList;
        }
        if (argCount == 0) {
            return null;
        }
        Object[] args = new Object[argCount];
        if (argCount == 4) {
            args[3] = arg3;
        }
        if (argCount >= 3) {
            args[2] = arg2;
        }
        if (argCount >= 2) {
            args[1] = arg1;
        }
        if (argCount >= 1) {
            args[0] = arg0;
        }
        return args;
    }

    public static void stopTracer() {
        stopTracer(0, false);
    }

    public static void stopTracer(long silenceThresholdParam) {
        stopTracer(silenceThresholdParam, true);
    }

    public static long stopTracerAndReturnElapsedMs() {
        return stopTracer(0, false) / 1000000;
    }

    public static long stopTracerAndReturnElapsedMs(long silenceThresholdParam) {
        return stopTracer(silenceThresholdParam, true) / 1000000;
    }

    private static long stopTracer(long silenceThresholdParam, boolean useThreshold) {
        Systrace.endSection(32);
        if (!sTracerConfigCallback.shouldCollectTracer()) {
            return 0;
        }
        ThreadState threadState = sThreadLocalState.get();
        if (threadState.stackOverflowDetected) {
            return -1;
        }
        if (threadState.tracerStack.size() < 2) {
            BLog.d(Tracer.class, "Tracer stack underflow. There's an extra stopTracer somewhere.");
            threadState.stackOverflowDetected = true;
            return -1;
        }
        long startTime = threadState.tracerStack.pop();
        int id = (int) threadState.tracerStack.pop();
        long elapsedTime = -1;
        if (id != ThreadTrace.INVALID_TRACE_ID) {
            elapsedTime = ThreadTrace.sThreadLocalState.get().stopTracer(id, silenceThresholdParam, useThreshold);
        }
        if (elapsedTime == -1) {
            return TracerClock.nanoTime() - startTime;
        }
        return elapsedTime;
    }

    public static void addComment(String comment) {
        addCommentInternal(comment, null);
    }

    public static void addComment(String comment, Object... args) {
        addCommentInternal(comment, args);
    }

    private static void addCommentInternal(String comment, Object[] args) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            ThreadTrace.sThreadLocalState.get().addComment(comment, args);
        }
    }

    public static void addSpawn(String comment) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            ThreadTrace.sThreadLocalState.get().addSpawn(comment);
        }
    }

    public static void logTrace(Class<?> tag) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            logTrace(3, LogPrefixer.renderClass(tag));
        }
    }

    public static void logTrace(String tag) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            logTrace(3, tag);
        }
    }

    public static void logTrace(int logLevel, String tag) {
        if (sTracerConfigCallback.shouldCollectTracer()) {
            ThreadTrace threadTrace = ThreadTrace.sThreadLocalState.get();
            threadTrace.logTrace(threadTrace.getLastTraceId(), logLevel, tag);
        }
    }

    public static List<TraceEvent> getTraceEvents() {
        if (!sTracerConfigCallback.shouldCollectTracer()) {
            return Collections.emptyList();
        }
        ThreadTrace threadTrace = ThreadTrace.sThreadLocalState.get();
        return threadTrace.getEvents(threadTrace.getLastTraceId());
    }
}
