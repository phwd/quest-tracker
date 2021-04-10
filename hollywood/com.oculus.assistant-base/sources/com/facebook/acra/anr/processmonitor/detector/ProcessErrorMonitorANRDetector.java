package com.facebook.acra.anr.processmonitor.detector;

import X.C0139Dd;
import X.EnumC0186Iy;
import android.os.Looper;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRException;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.processmonitor.ProcessErrorStateListener;

public class ProcessErrorMonitorANRDetector extends AbstractANRDetector implements ProcessErrorStateListener {
    public static final String LOG_TAG = "ProcessErrorMonitorANRDetector";
    public static final int START_DELAY_MS = 4000;
    public static ProcessErrorMonitorANRDetector sInstance;
    public final ProcessAnrErrorMonitor mAnrErrorMonitor;
    public long mDetectorStartTime;
    public boolean mErrorCleared;
    public boolean mFirstStart = true;
    public boolean mInAnr;
    public final Object mReportLock = new Object();
    public boolean mShouldReport;

    public class ThreadProvider {
        public static void runOnNewThread(Runnable runnable) {
            new Thread(runnable, "ProcessErrorMonitorANRDetectorThread").start();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void endAndProcessANRDataCapture() {
        C0139Dd.A09(LOG_TAG, "Recovered from ANR");
        if (this.mInAnr) {
            this.mInAnr = false;
            ThreadProvider.runOnNewThread(new Runnable() {
                /* class com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.AnonymousClass2 */

                public void run() {
                    synchronized (ProcessErrorMonitorANRDetector.this) {
                        ProcessErrorMonitorANRDetector processErrorMonitorANRDetector = ProcessErrorMonitorANRDetector.this;
                        if (!processErrorMonitorANRDetector.mErrorCleared) {
                            processErrorMonitorANRDetector.notifyStateListeners(EnumC0186Iy.ANR_RECOVERED);
                        }
                    }
                }
            });
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckPerformed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorCleared() {
        boolean z;
        synchronized (this) {
            z = this.mShouldReport;
            if (!this.mErrorCleared) {
                this.mErrorCleared = true;
                notifyStateListeners(EnumC0186Iy.NO_ANR_DETECTED);
            }
        }
        synchronized (this.mReportLock) {
            anrHasEnded(z);
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public boolean onErrorDetectOnOtherProcess(String str, String str2, String str3) {
        return true;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0061 */
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onErrorDetected(java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.onErrorDetected(java.lang.String, java.lang.String):void");
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onStart() {
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

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener) {
        synchronized (this) {
            this.mAnrErrorMonitor.stopMonitoring();
        }
        if (aNRDetectorStopListener != null) {
            aNRDetectorStopListener.onStop();
        }
    }

    public static synchronized ProcessErrorMonitorANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig, int i) {
        ProcessErrorMonitorANRDetector processErrorMonitorANRDetector;
        synchronized (ProcessErrorMonitorANRDetector.class) {
            processErrorMonitorANRDetector = sInstance;
            if (processErrorMonitorANRDetector == null) {
                processErrorMonitorANRDetector = new ProcessErrorMonitorANRDetector(aNRDetectorConfig, i);
                sInstance = processErrorMonitorANRDetector;
            }
        }
        return processErrorMonitorANRDetector;
    }

    public static synchronized void resetInstance() {
        synchronized (ProcessErrorMonitorANRDetector.class) {
            sInstance = null;
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void notifyStateListeners(EnumC0186Iy iy) {
        AppStateUpdater appStateUpdater = this.mANRConfig.mAppStateUpdater;
        EnumC0186Iy iy2 = EnumC0186Iy.DURING_ANR;
        if (iy == iy2) {
            if (appStateUpdater != null) {
                appStateUpdater.updateAnrState(iy2, null);
                this.mInForegroundV1 = false;
                this.mInForegroundV2 = false;
            }
        } else if (appStateUpdater != null) {
            EnumC0186Iy iy3 = EnumC0186Iy.ANR_RECOVERED;
            if (iy != iy3) {
                iy3 = EnumC0186Iy.NO_ANR_DETECTED;
            }
            appStateUpdater.updateAnrState(iy3, null);
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

    public ProcessErrorMonitorANRDetector(ANRDetectorConfig aNRDetectorConfig, int i) {
        super(aNRDetectorConfig);
        this.mAnrErrorMonitor = new ProcessAnrErrorMonitor(aNRDetectorConfig.mContext, aNRDetectorConfig.mProcessName, false, i, true, 0, 0);
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void collectStackTrace() {
        StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
        ANRException aNRException = new ANRException("ANR detected by ProcessErrorMonitorAnrDetector");
        aNRException.setStackTrace(stackTrace);
        C0139Dd.A0S(LOG_TAG, aNRException, "Generating ANR Report");
    }
}
