package com.facebook.acra.anr.processmonitor.detector;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRException;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.StackTraceDumper;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.processmonitor.ProcessErrorStateListener;
import com.facebook.debug.log.BLog;
import com.facebook.reliability.anr.AnrState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProcessErrorMonitorANRDetector extends AbstractANRDetector implements ProcessErrorStateListener {
    private static final String LOG_TAG = ProcessErrorMonitorANRDetector.class.getSimpleName();
    private static ProcessErrorMonitorANRDetector sInstance;
    private final ProcessAnrErrorMonitor mAnrErrorMonitor;
    private long mDetectorStartTime;
    private boolean mErrorCleared;
    private boolean mFirstStart = true;
    private boolean mInAnr;
    private final Object mReportLock = new Object();
    private boolean mShouldReport;

    public static synchronized ProcessErrorMonitorANRDetector getInstance(ANRDetectorConfig anrConfig, int errorMonitorCheckIntervalTimeMs) {
        ProcessErrorMonitorANRDetector processErrorMonitorANRDetector;
        synchronized (ProcessErrorMonitorANRDetector.class) {
            if (sInstance == null) {
                sInstance = new ProcessErrorMonitorANRDetector(anrConfig, errorMonitorCheckIntervalTimeMs);
            }
            processErrorMonitorANRDetector = sInstance;
        }
        return processErrorMonitorANRDetector;
    }

    private ProcessErrorMonitorANRDetector(ANRDetectorConfig anrConfig, int errorMonitorCheckIntervalTimeMs) {
        super(anrConfig);
        this.mAnrErrorMonitor = new ProcessAnrErrorMonitor(anrConfig.getContext(), anrConfig.getProcessName(), false, errorMonitorCheckIntervalTimeMs, true, 0, 0);
    }

    /* access modifiers changed from: package-private */
    public static class ThreadProvider {
        static void runOnNewThread(Runnable r) {
            new Thread(r, "ProcessErrorMonitorANRDetectorThread").start();
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public synchronized void start(long startTimeToReport) {
        if (this.mDetectorStartTime <= 0) {
            this.mDetectorStartTime = startTimeToReport;
        }
        if (this.mFirstStart) {
            this.mFirstStart = false;
            this.mAnrErrorMonitor.startMonitoringAfterDelay(this, 4000);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void stop(IANRDetector.ANRDetectorStopListener stopListener) {
        synchronized (this) {
            this.mAnrErrorMonitor.stopMonitoring();
        }
        if (stopListener != null) {
            stopListener.onStop();
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorDetected(String msg, String tag) {
        synchronized (this) {
            this.mInAnr = true;
            this.mErrorCleared = false;
            notifyStateListeners(AnrState.DURING_ANR);
            this.mShouldReport = shouldCollectAndUploadANRReportsNow();
            if (this.mShouldReport) {
                this.mANRConfig.getANRReport().logSystemInfo(msg, tag, SystemClock.uptimeMillis());
            }
            BLog.d(LOG_TAG, "Should report is %b inForegroundV1 is %b inForegroundV2 is %b", Boolean.valueOf(this.mShouldReport), Boolean.valueOf(this.mInForegroundV1), Boolean.valueOf(this.mInForegroundV2));
        }
        BLog.d(LOG_TAG, "ANR detected");
        this.mANRConfig.getMainThreadHandler().post(new Runnable() {
            /* class com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector.AnonymousClass1 */

            public void run() {
                ProcessErrorMonitorANRDetector.this.endAndProcessANRDataCapture();
            }
        });
        if (this.mShouldReport) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StackTraceDumper.dumpStackTraces(bos);
            String stacks = bos.toString();
            synchronized (this.mReportLock) {
                try {
                    startReport(stacks, null, null, false);
                } catch (IOException e) {
                }
            }
        }
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
    public void onStart() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onErrorCleared() {
        boolean shouldReport;
        synchronized (this) {
            shouldReport = this.mShouldReport;
            if (!this.mErrorCleared) {
                this.mErrorCleared = true;
                notifyStateListeners(AnrState.NO_ANR_DETECTED);
            }
        }
        synchronized (this.mReportLock) {
            anrHasEnded(shouldReport);
        }
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedBeforeError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onMaxChecksReachedAfterError() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckFailed() {
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public boolean onErrorDetectOnOtherProcess(String processName, String msg, String tag) {
        return true;
    }

    @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener
    public void onCheckPerformed() {
    }

    public void notifyStateListeners(AnrState anrState) {
        AppStateUpdater appStateUpdater = this.mANRConfig.getAppStateUpdater();
        if (anrState == AnrState.DURING_ANR) {
            if (appStateUpdater != null) {
                boolean inForegroundV2 = appStateUpdater.isAppInForegroundV2();
                boolean inForegroundV1 = appStateUpdater.isAppInForegroundV1();
                appStateUpdater.updateAnrState(AnrState.DURING_ANR, null, inForegroundV2 || inForegroundV1);
                this.mInForegroundV1 = inForegroundV1;
                this.mInForegroundV2 = inForegroundV2;
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
        ANRException exception = new ANRException("ANR detected by ProcessErrorMonitorAnrDetector");
        exception.setStackTrace(stackTrace);
        BLog.e(LOG_TAG, exception, "Generating ANR Report");
    }
}
