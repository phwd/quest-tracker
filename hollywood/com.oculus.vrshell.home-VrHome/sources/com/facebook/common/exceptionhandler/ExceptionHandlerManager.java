package com.facebook.common.exceptionhandler;

import android.util.Log;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.errorreporting.appstate.GlobalAppState;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class ExceptionHandlerManager implements Thread.UncaughtExceptionHandler {
    private static final String TAG = ExceptionHandlerManager.class.getSimpleName();
    private static boolean sDontShutDownBecauseWeAreInAUnitTest = false;
    @Nullable
    private static ExceptionHandlerManager sInstance;
    @Nullable
    private static Runnable sKillProcessSilentlyRunnable;
    private volatile List<PrioritizedExceptionHandler> mHandlerList = Collections.unmodifiableList(new ArrayList());
    @DoNotStrip
    @Nullable
    private byte[] mOomReservation = null;
    private final Thread.UncaughtExceptionHandler mPreviousHandler;
    private final boolean mPrioritized;
    @Nullable
    private CustomStackTracerInterface mStackTracer = null;
    private final Object mThereCanOnlyBeOneCrash = new Object();
    boolean mWouldHaveShutDownIfNotInAUnitTest = false;

    /* access modifiers changed from: private */
    public static class PrioritizedExceptionHandler implements Comparable<PrioritizedExceptionHandler> {
        ManagedExceptionHandler handler;
        int priority;

        private PrioritizedExceptionHandler() {
        }

        public int compareTo(PrioritizedExceptionHandler other) {
            if (this.priority == other.priority) {
                return 0;
            }
            return this.priority > other.priority ? 1 : -1;
        }
    }

    public static synchronized ExceptionHandlerManager getInitializedInstance() {
        ExceptionHandlerManager exceptionHandlerManager;
        synchronized (ExceptionHandlerManager.class) {
            if (sInstance == null) {
                Log.d(TAG, "ExceptionHandlerManager not explicitly initialized, using default mode");
                exceptionHandlerManager = init(true);
            } else {
                exceptionHandlerManager = sInstance;
            }
        }
        return exceptionHandlerManager;
    }

    public static synchronized ExceptionHandlerManager init(boolean prioritized) {
        ExceptionHandlerManager exceptionHandlerManager;
        synchronized (ExceptionHandlerManager.class) {
            if (sInstance != null) {
                throw new IllegalStateException("Already initialized!");
            }
            Log.d(TAG, "initializing exception handler manager, prioritized=" + prioritized);
            sInstance = new ExceptionHandlerManager(Thread.getDefaultUncaughtExceptionHandler(), prioritized);
            Thread.setDefaultUncaughtExceptionHandler(sInstance);
            exceptionHandlerManager = sInstance;
        }
        return exceptionHandlerManager;
    }

    public static synchronized void addExceptionHandler(ManagedExceptionHandler instance, int priority) {
        synchronized (ExceptionHandlerManager.class) {
            getInitializedInstance().addHandler(instance, priority);
        }
    }

    public static synchronized void handleThrowableAndTerminate(Thread thread, Throwable throwable) {
        synchronized (ExceptionHandlerManager.class) {
            getInitializedInstance().uncaughtException(thread, throwable);
        }
    }

    public ExceptionHandlerManager(Thread.UncaughtExceptionHandler oldHandler, boolean prioritized) {
        this.mPrioritized = prioritized;
        this.mPreviousHandler = oldHandler;
        this.mOomReservation = new byte[4096];
    }

    /* access modifiers changed from: package-private */
    public synchronized void addHandler(ManagedExceptionHandler handler, int priority) {
        ArrayList<PrioritizedExceptionHandler> newHandlerList = new ArrayList<>(this.mHandlerList);
        PrioritizedExceptionHandler prioritizedHandler = new PrioritizedExceptionHandler();
        prioritizedHandler.handler = handler;
        prioritizedHandler.priority = priority;
        newHandlerList.add(prioritizedHandler);
        if (this.mPrioritized) {
            Collections.sort(newHandlerList);
        }
        this.mHandlerList = Collections.unmodifiableList(newHandlerList);
    }

    private static void logErrorDuringHandling(Throwable thrownDuringHandling) {
        Log.e(TAG, "Error during exception handling", thrownDuringHandling);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00ec, code lost:
        throw r7;
     */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r13, java.lang.Throwable r14) {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.exceptionhandler.ExceptionHandlerManager.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0013 A[REMOVE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void killThisProcess() {
        /*
            java.lang.Runnable r1 = com.facebook.common.exceptionhandler.ExceptionHandlerManager.sKillProcessSilentlyRunnable     // Catch:{ Throwable -> 0x0016 }
            if (r1 == 0) goto L_0x0007
            r1.run()     // Catch:{ Throwable -> 0x0016 }
        L_0x0007:
            int r2 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0016 }
            android.os.Process.killProcess(r2)     // Catch:{ Throwable -> 0x0016 }
        L_0x000e:
            r2 = 10
            java.lang.System.exit(r2)     // Catch:{ Throwable -> 0x001b }
        L_0x0013:
            goto L_0x0013
        L_0x0016:
            r0 = move-exception
            logErrorDuringHandling(r0)
            goto L_0x000e
        L_0x001b:
            r0 = move-exception
            logErrorDuringHandling(r0)
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.exceptionhandler.ExceptionHandlerManager.killThisProcess():void");
    }

    private static void modifyStackTrace(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        StackTraceElement[] newStackTrace = new StackTraceElement[(stackTrace.length + 1)];
        System.arraycopy(stackTrace, 0, newStackTrace, 0, stackTrace.length);
        newStackTrace[newStackTrace.length - 1] = new StackTraceElement("Z", "init", GlobalAppState.getSessionId(), -1);
        throwable.setStackTrace(newStackTrace);
    }
}
