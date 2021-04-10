package com.facebook.acra.anr.processmonitor.detector;

import android.os.Looper;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRException;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.processmonitor.ProcessErrorStateListener;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.reliability.anr.AnrState;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessErrorMonitorANRDetector extends AbstractANRDetector implements ProcessErrorStateListener {
    private static final String LOG_TAG = "ProcessErrorMonitorANRDetector";
    private static ProcessErrorMonitorANRDetector sInstance;
    private final ProcessAnrErrorMonitor mAnrErrorMonitor;
    private long mDetectorStartTime;
    private boolean mErrorCleared;
    private boolean mFirstStart = true;
    private boolean mInAnr;
    private final Object mReportLock = new Object();
    private boolean mShouldReport;

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final boolean onErrorDetectOnOtherProcess(String str, String str2, String str3) {
        return true;
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final void onStart() {
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
        this.mAnrErrorMonitor = new ProcessAnrErrorMonitor(aNRDetectorConfig.mContext, aNRDetectorConfig.mProcessName, false, i, true, 0, 0);
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final synchronized void start(long j) {
        if (this.mDetectorStartTime <= 0) {
            this.mDetectorStartTime = j;
        }
        if (this.mFirstStart) {
            this.mFirstStart = false;
            this.mAnrErrorMonitor.startMonitoringAfterDelay(this, 4000);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0068 */
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onErrorDetected(java.lang.String r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.onErrorDetected(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void endAndProcessANRDataCapture() {
        BLog.d(LOG_TAG, "Recovered from ANR");
        if (this.mInAnr) {
            this.mInAnr = false;
            new Thread(new Runnable() {
                /* class com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.AnonymousClass2 */

                public final void run() {
                    synchronized (ProcessErrorMonitorANRDetector.this) {
                        if (!ProcessErrorMonitorANRDetector.this.mErrorCleared) {
                            ProcessErrorMonitorANRDetector.this.notifyStateListeners$6ef55a6(AnrState.ANR_RECOVERED$65befc1);
                        }
                    }
                }
            }, "ProcessErrorMonitorANRDetectorThread").start();
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public final void onErrorCleared() {
        boolean z;
        synchronized (this) {
            z = this.mShouldReport;
            if (!this.mErrorCleared) {
                this.mErrorCleared = true;
                notifyStateListeners$6ef55a6(AnrState.NO_ANR_DETECTED$65befc1);
            }
        }
        synchronized (this.mReportLock) {
            anrHasEnded(z);
        }
    }

    public final void notifyStateListeners$6ef55a6(int i) {
        AppStateUpdater appStateUpdater = this.mANRConfig.mAppStateUpdater;
        if (i == AnrState.DURING_ANR$65befc1) {
            if (appStateUpdater != null) {
                int i2 = AnrState.DURING_ANR$65befc1;
                throw null;
            }
        } else if (appStateUpdater == null) {
        } else {
            if (i != AnrState.ANR_RECOVERED$65befc1) {
                int i3 = AnrState.NO_ANR_DETECTED$65befc1;
                throw null;
            } else {
                int i4 = AnrState.ANR_RECOVERED$65befc1;
                throw null;
            }
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final void collectStackTrace() {
        StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
        ANRException aNRException = new ANRException("ANR detected by ProcessErrorMonitorAnrDetector");
        aNRException.setStackTrace(stackTrace);
        BLog.e(LOG_TAG, aNRException, "Generating ANR Report");
    }
}
