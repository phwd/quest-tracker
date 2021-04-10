package com.facebook.acra.anr.processmonitor.detector;

import X.Mu;
import X.gL;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRException;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.processmonitor.ProcessErrorStateListener;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadConfined;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcessErrorMonitorANRDetector extends AbstractANRDetector implements ProcessErrorStateListener {
    public static final String LOG_TAG = "ProcessErrorMonitorANRDetector";
    public static final int START_DELAY_MS = 4000;
    @Nullable
    public static ProcessErrorMonitorANRDetector sInstance;
    public final ProcessAnrErrorMonitor mAnrErrorMonitor;
    public long mDetectorStartTime;
    @GuardedBy("this")
    public boolean mErrorCleared;
    @GuardedBy("this")
    public boolean mFirstStart = true;
    @GuardedBy("this")
    public boolean mInAnr;
    public final Object mReportLock = new Object();
    @GuardedBy("this")
    public boolean mShouldReport;

    @VisibleForTesting
    public static class ThreadProvider {
        public static void runOnNewThread(Runnable runnable) {
            new Thread(runnable, "ProcessErrorMonitorANRDetectorThread").start();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void endAndProcessANRDataCapture() {
        if (this.mInAnr) {
            this.mInAnr = false;
            ThreadProvider.runOnNewThread(new Runnable() {
                /* class com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.AnonymousClass2 */

                public void run() {
                    synchronized (ProcessErrorMonitorANRDetector.this) {
                        ProcessErrorMonitorANRDetector processErrorMonitorANRDetector = ProcessErrorMonitorANRDetector.this;
                        if (!processErrorMonitorANRDetector.mErrorCleared) {
                            processErrorMonitorANRDetector.notifyStateListeners(gL.ANR_RECOVERED);
                        }
                    }
                }
            });
        }
    }

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
    public void onErrorCleared() {
        boolean z;
        synchronized (this) {
            z = this.mShouldReport;
            if (!this.mErrorCleared) {
                this.mErrorCleared = true;
                notifyStateListeners(gL.NO_ANR_DETECTED);
            }
        }
        synchronized (this.mReportLock) {
            anrHasEnded(z);
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @ThreadConfined("ProcessAnrErrorMonitorThread")
    public boolean onErrorDetectOnOtherProcess(String str, @Nullable String str2, @Nullable String str3) {
        return true;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0043 */
    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    @com.facebook.infer.annotation.ThreadConfined("ProcessAnrErrorMonitorThread")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onErrorDetected(@javax.annotation.Nullable java.lang.String r5, @javax.annotation.Nullable java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 1
            r4.mInAnr = r0     // Catch:{ all -> 0x004a }
            r3 = 0
            r4.mErrorCleared = r3     // Catch:{ all -> 0x004a }
            X.gL r0 = X.gL.DURING_ANR     // Catch:{ all -> 0x004a }
            r4.notifyStateListeners(r0)     // Catch:{ all -> 0x004a }
            boolean r0 = r4.shouldCollectAndUploadANRReportsNow()     // Catch:{ all -> 0x004a }
            r4.mShouldReport = r0     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x001f
            com.facebook.acra.anr.ANRDetectorConfig r0 = r4.mANRConfig     // Catch:{ all -> 0x004a }
            com.facebook.acra.anr.IANRReport r2 = r0.mANRReport     // Catch:{ all -> 0x004a }
            long r0 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x004a }
            r2.logSystemInfo(r5, r6, r0)     // Catch:{ all -> 0x004a }
        L_0x001f:
            monitor-exit(r4)     // Catch:{ all -> 0x004a }
            com.facebook.acra.anr.ANRDetectorConfig r0 = r4.mANRConfig
            android.os.Handler r1 = r0.mMainThreadHandler
            com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector$1 r0 = new com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector$1
            r0.<init>()
            r1.post(r0)
            boolean r0 = r4.mShouldReport
            if (r0 == 0) goto L_0x0049
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r2 = 0
            com.facebook.acra.anr.StackTraceDumper.dumpStackTraces(r0, r2, r2)
            java.lang.String r0 = r0.toString()
            java.lang.Object r1 = r4.mReportLock
            monitor-enter(r1)
            r4.startReport(r0, r2, r2, r3)     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x0045 }
            goto L_0x0048
        L_0x0045:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x0048:
            return
        L_0x0049:
            return
        L_0x004a:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.onErrorDetected(java.lang.String, java.lang.String):void");
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

    @VisibleForTesting
    public static synchronized void resetInstance() {
        synchronized (ProcessErrorMonitorANRDetector.class) {
            sInstance = null;
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    @VisibleForTesting
    public void notifyStateListeners(gL gLVar) {
        AppStateUpdater appStateUpdater = this.mANRConfig.mAppStateUpdater;
        gL gLVar2 = gL.DURING_ANR;
        if (gLVar == gLVar2) {
            if (appStateUpdater != null) {
                appStateUpdater.updateAnrState(gLVar2, null);
                this.mInForegroundV1 = false;
                this.mInForegroundV2 = false;
            }
        } else if (appStateUpdater != null) {
            gL gLVar3 = gL.ANR_RECOVERED;
            if (gLVar != gLVar3) {
                gLVar3 = gL.NO_ANR_DETECTED;
            }
            appStateUpdater.updateAnrState(gLVar3, null);
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
        Mu.A08(LOG_TAG, aNRException, "Generating ANR Report");
    }
}
