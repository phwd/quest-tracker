package com.facebook.common.exceptionhandler;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.errorreporting.appstate.GlobalAppState;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class ExceptionHandlerManager implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "ExceptionHandlerManager";
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
    @VisibleForTesting
    boolean mWouldHaveShutDownIfNotInAUnitTest = false;

    /* access modifiers changed from: private */
    public static class PrioritizedExceptionHandler implements Comparable<PrioritizedExceptionHandler> {
        ManagedExceptionHandler handler;
        int priority;

        private PrioritizedExceptionHandler() {
        }

        public int compareTo(PrioritizedExceptionHandler prioritizedExceptionHandler) {
            int i = this.priority;
            int i2 = prioritizedExceptionHandler.priority;
            if (i == i2) {
                return 0;
            }
            return i > i2 ? 1 : -1;
        }
    }

    public static synchronized ExceptionHandlerManager getInitializedInstance() {
        synchronized (ExceptionHandlerManager.class) {
            if (sInstance == null) {
                Log.d(TAG, "ExceptionHandlerManager not explicitly initialized, using default mode");
                return init(true);
            }
            return sInstance;
        }
    }

    public static synchronized ExceptionHandlerManager init(boolean z) {
        ExceptionHandlerManager exceptionHandlerManager;
        synchronized (ExceptionHandlerManager.class) {
            if (sInstance == null) {
                String str = TAG;
                Log.d(str, "initializing exception handler manager, prioritized=" + z);
                sInstance = new ExceptionHandlerManager(Thread.getDefaultUncaughtExceptionHandler(), z);
                Thread.setDefaultUncaughtExceptionHandler(sInstance);
                exceptionHandlerManager = sInstance;
            } else {
                throw new IllegalStateException("Already initialized!");
            }
        }
        return exceptionHandlerManager;
    }

    public static synchronized void addExceptionHandler(ManagedExceptionHandler managedExceptionHandler, int i) {
        synchronized (ExceptionHandlerManager.class) {
            getInitializedInstance().addHandler(managedExceptionHandler, i);
        }
    }

    public static synchronized void handleThrowableAndTerminate(Thread thread, Throwable th) {
        synchronized (ExceptionHandlerManager.class) {
            getInitializedInstance().uncaughtException(thread, th);
        }
    }

    public ExceptionHandlerManager(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        this.mPrioritized = z;
        this.mPreviousHandler = uncaughtExceptionHandler;
        this.mOomReservation = new byte[4096];
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void addHandler(ManagedExceptionHandler managedExceptionHandler, int i) {
        ArrayList arrayList = new ArrayList(this.mHandlerList);
        PrioritizedExceptionHandler prioritizedExceptionHandler = new PrioritizedExceptionHandler();
        prioritizedExceptionHandler.handler = managedExceptionHandler;
        prioritizedExceptionHandler.priority = i;
        arrayList.add(prioritizedExceptionHandler);
        if (this.mPrioritized) {
            Collections.sort(arrayList);
        }
        this.mHandlerList = Collections.unmodifiableList(arrayList);
    }

    private static void logErrorDuringHandling(Throwable th) {
        Log.e(TAG, "Error during exception handling", th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00d3, code lost:
        killThisProcess();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00e3, code lost:
        throw r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00cf  */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.exceptionhandler.ExceptionHandlerManager.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed compute block dominance frontier
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominanceFrontier(BlockProcessor.java:300)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:77)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:46)
        Caused by: java.lang.IndexOutOfBoundsException: Index: 14, Size: 12
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeBlockDF(BlockProcessor.java:325)
        	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominanceFrontier(BlockProcessor.java:298)
        	... 2 more
        */
    private static void killThisProcess() {
        /*
            java.lang.Runnable r0 = com.facebook.common.exceptionhandler.ExceptionHandlerManager.sKillProcessSilentlyRunnable     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x0007
            r0.run()     // Catch:{ all -> 0x000f }
        L_0x0007:
            int r0 = android.os.Process.myPid()     // Catch:{ all -> 0x000f }
            android.os.Process.killProcess(r0)     // Catch:{ all -> 0x000f }
            goto L_0x0013
        L_0x000f:
            r0 = move-exception
            logErrorDuringHandling(r0)
        L_0x0013:
            r0 = 10
            java.lang.System.exit(r0)     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            logErrorDuringHandling(r0)
        L_0x001d:
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.exceptionhandler.ExceptionHandlerManager.killThisProcess():void");
    }

    private static void modifyStackTrace(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length + 1)];
        System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, stackTrace.length);
        stackTraceElementArr[stackTraceElementArr.length - 1] = new StackTraceElement("Z", "init", GlobalAppState.getSessionId(), -1);
        th.setStackTrace(stackTraceElementArr);
    }
}
