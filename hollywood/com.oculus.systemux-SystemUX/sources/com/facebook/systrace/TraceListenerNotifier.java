package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceListenerNotifier {
    private static final File TRACE_DATA_FILE = new File("/sys/kernel/debug/tracing/trace");
    private boolean mIsTracing;
    @GuardedBy("mLock")
    @SuppressLint({"BadMethodUse-java.util.ArrayList._Constructor"})
    private final List<TraceListener> mListeners = new ArrayList();
    private final Object mLock = new Object[0];

    TraceListenerNotifier() {
    }

    public void addListener(TraceListener traceListener) {
        synchronized (this.mLock) {
            this.mListeners.add(traceListener);
            if (this.mIsTracing) {
                traceListener.onTraceStarted();
            }
        }
    }

    public void removeListener(TraceListener traceListener) {
        synchronized (this.mLock) {
            this.mListeners.remove(traceListener);
            if (this.mIsTracing) {
                traceListener.onTraceStopped();
            }
        }
    }

    public void asyncNotifyListenersForTraceStart() {
        synchronized (this.mLock) {
            final long lastModified = TRACE_DATA_FILE.lastModified();
            Thread thread = new Thread(new Runnable() {
                /* class com.facebook.systrace.TraceListenerNotifier.AnonymousClass1 */

                public void run() {
                    synchronized (TraceListenerNotifier.this.mLock) {
                        waitForTraceFileToBeCleared();
                        TraceListenerNotifier.this.notifyListenersForTraceStart();
                    }
                }

                @GuardedBy("mLock")
                private void waitForTraceFileToBeCleared() {
                    if (Build.VERSION.SDK_INT < 23) {
                        do {
                        } while (TraceListenerNotifier.TRACE_DATA_FILE.lastModified() == lastModified);
                        return;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "fbsystrace notification thread");
            thread.setPriority(10);
            thread.start();
        }
    }

    public void notifyListenersForTraceStart() {
        synchronized (this.mLock) {
            Systrace.beginSection(1, "Run Trace Listeners");
            try {
                issueNotifications(true);
            } finally {
                Systrace.endSection(1);
            }
        }
    }

    public void notifyListenersForTraceStop() {
        synchronized (this.mLock) {
            issueNotifications(false);
        }
    }

    @GuardedBy("mLock")
    private void issueNotifications(boolean z) {
        this.mIsTracing = z;
        for (int i = 0; i < this.mListeners.size(); i++) {
            TraceListener traceListener = this.mListeners.get(i);
            if (z) {
                traceListener.onTraceStarted();
            } else {
                traceListener.onTraceStopped();
            }
        }
    }
}
