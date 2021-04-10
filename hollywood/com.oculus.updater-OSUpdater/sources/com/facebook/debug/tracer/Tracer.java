package com.facebook.debug.tracer;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public class Tracer {
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

    @IgnoreAllocations
    public static void startTracer(String str) {
        startTracerInternal(str, 0, null, null, null, null, null);
    }

    @IgnoreAllocations
    public static void startTracer(String str, @Nullable Object obj) {
        startTracerInternal(str, 1, obj, null, null, null, null);
    }

    private static void startTracerInternal(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (Systrace.isTracing(32)) {
            Systrace.beginSection(32, formatStrLocaleSafe(str, i, obj, obj2, obj3, obj4, objArr));
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
                long lastEventTimeNanos = threadTrace.getLastEventTimeNanos();
                threadState.tracerStack.push((long) threadTrace.startTracer(str, combineArgs(i, obj, obj2, obj3, obj4, objArr), false));
                threadState.tracerStack.push(lastEventTimeNanos);
            }
        }
    }

    private static String formatStrLocaleSafe(String str, int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (i == -1 && (objArr == null || objArr.length == 0)) {
            return str;
        }
        if (i == 0) {
            return StringFormatUtil.formatStrLocaleSafe(str);
        }
        if (i == 1) {
            return StringFormatUtil.formatStrLocaleSafe(str, obj);
        }
        if (i == 2) {
            return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2);
        }
        if (i == 3) {
            return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3);
        }
        if (i == 4) {
            return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3, obj4);
        }
        try {
            return StringFormatUtil.formatStrLocaleSafe(str, objArr);
        } catch (IllegalFormatException e) {
            BLog.wtf("Tracer", "Bad format string", e);
            return str;
        }
    }

    @Nullable
    private static Object[] combineArgs(int i, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4, @Nullable Object[] objArr) {
        if (i == -1) {
            return objArr;
        }
        if (i == 0) {
            return null;
        }
        Object[] objArr2 = new Object[i];
        if (i == 4) {
            objArr2[3] = obj4;
        }
        if (i >= 3) {
            objArr2[2] = obj3;
        }
        if (i >= 2) {
            objArr2[1] = obj2;
        }
        if (i >= 1) {
            objArr2[0] = obj;
        }
        return objArr2;
    }

    public static void stopTracer() {
        stopTracer(0, false);
    }

    private static long stopTracer(long j, boolean z) {
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
        long pop = threadState.tracerStack.pop();
        int pop2 = (int) threadState.tracerStack.pop();
        long stopTracer = pop2 != ThreadTrace.INVALID_TRACE_ID ? ThreadTrace.sThreadLocalState.get().stopTracer(pop2, j, z) : -1;
        return stopTracer == -1 ? TracerClock.nanoTime() - pop : stopTracer;
    }
}
