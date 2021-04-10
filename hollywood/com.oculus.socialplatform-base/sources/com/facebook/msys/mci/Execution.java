package com.facebook.msys.mci;

import X.AnonymousClass006;
import X.AnonymousClass1Kd;
import X.AnonymousClass1Nh;
import X.AnonymousClass1uK;
import X.AnonymousClass1uL;
import X.AnonymousClass1uM;
import android.annotation.SuppressLint;
import android.os.Trace;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public class Execution {
    public static volatile boolean sInitialized;
    public static final ThreadLocal<Integer> sThreadLocalExecutionContext = new AnonymousClass1uK();

    public static void executePossiblySync(AnonymousClass1Kd r2, int i) {
        assertInitialized();
        if (r2 != null) {
            try {
                if (getExecutionContext() == 1) {
                    r2.run();
                    return;
                }
            } catch (RuntimeException unused) {
            }
            executeAsyncWithPriority(r2, 1, 0);
            return;
        }
        throw null;
    }

    public static native int nativeGetExecutionContext();

    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    public static native void nativeInitializeExecutors(int[] iArr);

    @DoNotStrip
    public static native boolean nativeScheduleTask(Runnable runnable, int i, int i2, double d, @Nullable String str);

    @DoNotStrip
    public static native void nativeStartExecutor(int i);

    public static void assertInitialized() {
        if (!sInitialized) {
            throw new RuntimeException("This class has to be initialized before it can be used");
        }
    }

    @DoNotStrip
    public static int getExecutionContext() {
        return sThreadLocalExecutionContext.get().intValue();
    }

    @SuppressLint({"BadMethodUse-java.lang.Thread.start"})
    public static synchronized boolean initialize() {
        synchronized (Execution.class) {
            Trace.beginSection("Execution.initialize");
            try {
                int i = 0;
                if (sInitialized) {
                    return false;
                }
                int[] iArr = {1, 2, 3, 4, 5};
                String[] strArr = {"Main", "Disk", "Network", "Decoding", "Crypto"};
                nativeInitializeExecutors(iArr);
                do {
                    new Thread(new AnonymousClass1uL(iArr[i]), AnonymousClass006.A07(strArr[i], "Context")).start();
                    i++;
                } while (i < 5);
                nativeInitialize();
                synchronized (ExecutionIdle.class) {
                    if (!ExecutionIdle.sInitialized) {
                        Trace.beginSection("ExecutionIdle.initialize");
                        try {
                            ExecutionIdle.nativeInitialize();
                            new Thread(new AnonymousClass1uM()).start();
                            ExecutionIdle.sInitialized = true;
                        } finally {
                            Trace.endSection();
                        }
                    }
                }
                synchronized (TaskTracker.class) {
                    int i2 = 0;
                    if (!TaskTracker.sInitialized) {
                        TaskTracker[] taskTrackerArr = {TaskTracker.TRACKER_MAIN, TaskTracker.TRACKER_DISK_IO, TaskTracker.TRACKER_NETWORK, TaskTracker.TRACKER_DECODING, TaskTracker.TRACKER_CRYPTO};
                        do {
                            TaskTracker taskTracker = taskTrackerArr[i2];
                            taskTracker.mNativeHolder = TaskTracker.initNativeHolder(taskTracker.mExecutionContext, taskTracker.mQueueName);
                            i2++;
                        } while (i2 < 5);
                        TaskTracker.sInitialized = true;
                    }
                }
                sInitialized = true;
                Trace.endSection();
                return true;
            } finally {
                Trace.endSection();
            }
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }

    public static void executeAsyncWithPriority(AnonymousClass1Kd r7, int i, int i2) {
        assertInitialized();
        if (r7 != null) {
            assertInitialized();
            if (!nativeScheduleTask(r7, i, i2, ((double) 0) / 1000.0d, r7.toString())) {
                throw new RuntimeException(AnonymousClass006.A03("UNKNOWN execution context ", i));
            }
            return;
        }
        throw null;
    }
}
