package com.facebook.acra.anr.processmonitor.detector;

import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRException;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.processmonitor.ProcessErrorStateListener;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.reliability.anr.AnrState;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessErrorMonitorANRDetector extends AbstractANRDetector implements ProcessErrorStateListener {
    private static final String LOG_TAG = "ProcessErrorMonitorANRDetector";
    private static final int START_DELAY_MS = 4000;
    @Nullable
    private static ProcessErrorMonitorANRDetector sInstance;
    private final ProcessAnrErrorMonitor mAnrErrorMonitor;
    private long mDetectorStartTime;
    @GuardedBy("this")
    private boolean mErrorCleared;
    @GuardedBy("this")
    private boolean mFirstStart = true;
    @GuardedBy("this")
    private boolean mInAnr;
    private final Object mReportLock = new Object();
    @GuardedBy("this")
    private boolean mShouldReport;

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onCheckPerformed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public boolean onErrorDetectOnOtherProcess(String str, @Nullable String str2, @Nullable String str3) {
        return true;
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onStart() {
    }

    public static synchronized ProcessErrorMonitorANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig, int i) {
        ProcessErrorMonitorANRDetector processErrorMonitorANRDetector;
        synchronized (ProcessErrorMonitorANRDetector.class) {
            if (sInstance == null) {
                sInstance = new ProcessErrorMonitorANRDetector(aNRDetectorConfig, i);
            }
            processErrorMonitorANRDetector = sInstance;
        }
        return processErrorMonitorANRDetector;
    }

    private ProcessErrorMonitorANRDetector(ANRDetectorConfig aNRDetectorConfig, int i) {
        super(aNRDetectorConfig);
        this.mAnrErrorMonitor = new ProcessAnrErrorMonitor(aNRDetectorConfig.getContext(), aNRDetectorConfig.getProcessName(), false, i, true, 0, 0);
    }

    @VisibleForTesting
    static synchronized void resetInstance() {
        synchronized (ProcessErrorMonitorANRDetector.class) {
            sInstance = null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class ThreadProvider {
        ThreadProvider() {
        }

        static void runOnNewThread(Runnable runnable) {
            new Thread(runnable, "ProcessErrorMonitorANRDetectorThread").start();
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public synchronized void start(long j) {
        if (this.mDetectorStartTime <= 0) {
            this.mDetectorStartTime = j;
        }
        if (this.mFirstStart) {
            this.mFirstStart = false;
            this.mAnrErrorMonitor.startMonitoringAfterDelay(this, 4000);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void pause() {
        this.mAnrErrorMonitor.pause();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void resume() {
        this.mAnrErrorMonitor.resume();
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener) {
        synchronized (this) {
            this.mAnrErrorMonitor.stopMonitoring();
        }
        if (aNRDetectorStopListener != null) {
            aNRDetectorStopListener.onStop();
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x006c */
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @com.facebook.infer.annotation.ThreadConfined("ProcessAnrErrorMonitorThread")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onErrorDetected(@javax.annotation.Nullable java.lang.String r5, @javax.annotation.Nullable java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.onErrorDetected(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void endAndProcessANRDataCapture() {
        BLog.d(LOG_TAG, "Recovered from ANR");
        if (this.mInAnr) {
            this.mInAnr = false;
            ThreadProvider.runOnNewThread(new Runnable() {
                /* class com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.AnonymousClass2 */

                public void run() {
                    synchronized (ProcessErrorMonitorANRDetector.this) {
                        if (!ProcessErrorMonitorANRDetector.this.mErrorCleared) {
                            ProcessErrorMonitorANRDetector.this.notifyStateListeners(AnrState.ANR_RECOVERED);
                        }
                    }
                }
            });
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public void onErrorCleared() {
        boolean z;
        synchronized (this) {
            z = this.mShouldReport;
            if (!this.mErrorCleared) {
                this.mErrorCleared = true;
                notifyStateListeners(AnrState.NO_ANR_DETECTED);
            }
        }
        synchronized (this.mReportLock) {
            anrHasEnded(z);
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    @VisibleForTesting
    public void notifyStateListeners(AnrState anrState) {
        AppStateUpdater appStateUpdater = this.mANRConfig.getAppStateUpdater();
        if (anrState == AnrState.DURING_ANR) {
            if (appStateUpdater != null) {
                boolean isAppInForegroundV2 = appStateUpdater.isAppInForegroundV2();
                boolean isAppInForegroundV1 = appStateUpdater.isAppInForegroundV1();
                appStateUpdater.updateAnrState(AnrState.DURING_ANR, null, isAppInForegroundV2 || isAppInForegroundV1);
                this.mInForegroundV1 = isAppInForegroundV1;
                this.mInForegroundV2 = isAppInForegroundV2;
            }
        } else if (appStateUpdater == null) {
        } else {
            if (anrState != AnrState.ANR_RECOVERED) {
                appStateUpdater.updateAnrState(AnrState.NO_ANR_DETECTED, null);
            } else {
                appStateUpdater.updateAnrState(AnrState.ANR_RECOVERED, null);
            }
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void collectStackTrace() {
        StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
        ANRException aNRException = new ANRException("ANR detected by ProcessErrorMonitorAnrDetector");
        aNRException.setStackTrace(stackTrace);
        BLog.e(LOG_TAG, aNRException, "Generating ANR Report");
    }
}
