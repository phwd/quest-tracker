package com.facebook.acra.anr.sigquit.detector;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.SigquitRecord;
import com.facebook.acra.anr.StackTraceDumper;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.sigquit.SigquitDetector;
import com.facebook.acra.anr.sigquit.SigquitDetectorAcra;
import com.facebook.acra.anr.sigquit.SigquitDetectorListener;
import com.facebook.debug.log.BLog;
import com.facebook.reliability.anr.AnrState;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class SigquitBasedANRDetector extends AbstractANRDetector implements SigquitDetectorListener {
    private static final String LOG_TAG = SigquitBasedANRDetector.class.getSimpleName();
    @GuardedBy("SigquitBasedANRDetector.class")
    @Nullable
    private static SigquitBasedANRDetector sInstance;
    private final Runnable mClearAnrStateRunnable = new Runnable() {
        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass1 */

        public void run() {
            synchronized (SigquitBasedANRDetector.this.mWaitingToClearANRLock) {
                if (SigquitBasedANRDetector.this.mWaitingForANRClearTimeout) {
                    BLog.d(SigquitBasedANRDetector.LOG_TAG, "Clearing ANR state by timeout");
                    SigquitBasedANRDetector.this.notifyStateListeners(AnrState.NO_ANR_DETECTED);
                    if (SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                        SigquitBasedANRDetector.this.anrHasEnded(true);
                    }
                    SigquitBasedANRDetector.this.mWaitingForANRClearTimeout = false;
                }
            }
        }
    };
    @GuardedBy("mStateLock")
    private long mDetectorReadyTime;
    @GuardedBy("mStateLock")
    private boolean mHookInPlace;
    @Nullable
    private ProcessAnrErrorMonitor mProcessAnrErrorMonitor = null;
    @Nullable
    private ProcessAnrErrorMonitorListener mProcessAnrErrorMonitorListener;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    private HandlerThread mProcessingThread;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    private Handler mProcessingThreadHandler;
    private final Object mProcessingThreadLock = new Object();
    private volatile boolean mRunning;
    private final SigquitDetector mSigquitDetector = SigquitDetectorAcra.getInstance(this);
    private final boolean mStartProcessErrorMonitorAfterANRDetection = true;
    private final Object mStateLock = new Object();
    @GuardedBy("mStateLock")
    private long mSwitchTime;
    @GuardedBy("mWaitingToClearANRLock")
    private boolean mWaitingForANRClearTimeout;
    private final Object mWaitingToClearANRLock = new Object();

    public static synchronized SigquitBasedANRDetector getInstance(ANRDetectorConfig anrConfig) {
        SigquitBasedANRDetector sigquitBasedANRDetector;
        synchronized (SigquitBasedANRDetector.class) {
            if (sInstance == null) {
                sInstance = new SigquitBasedANRDetector(anrConfig);
            }
            sigquitBasedANRDetector = sInstance;
        }
        return sigquitBasedANRDetector;
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void start(long startTimeToReport) {
        BLog.d(LOG_TAG, "Started");
        synchronized (this.mStateLock) {
            if (this.mDetectorStartTime <= 0) {
                this.mDetectorStartTime = startTimeToReport;
            }
            if (this.mHookInPlace && !this.mRunning) {
                if (this.mDetectorStartTime == -1) {
                    this.mDetectorStartTime = SystemClock.uptimeMillis();
                }
                this.mRunning = true;
            }
        }
    }

    public void setSwitchTime(long switchTime) {
        synchronized (this.mStateLock) {
            this.mSwitchTime = switchTime;
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public long getSwitchTime() {
        long j;
        synchronized (this.mStateLock) {
            j = this.mSwitchTime;
        }
        return j;
    }

    public void setReadyTime(long readyTime) {
        synchronized (this.mStateLock) {
            this.mDetectorReadyTime = readyTime;
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public long getReadyTime() {
        long j;
        synchronized (this.mStateLock) {
            j = this.mDetectorReadyTime;
        }
        return j;
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void stop(IANRDetector.ANRDetectorStopListener stopListener) {
        synchronized (this.mStateLock) {
            if (this.mHookInPlace) {
                this.mRunning = false;
                this.mSigquitDetector.stopDetector();
                stopHandlerThread();
            }
        }
        if (stopListener != null) {
            stopListener.onStop();
        }
    }

    private void stopHandlerThread() {
        synchronized (this.mProcessingThreadLock) {
            this.mProcessingThreadHandler = null;
            if (this.mProcessingThread != null) {
                this.mProcessingThread.quit();
                this.mProcessingThread = null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void mainThreadUnblocked() {
        BLog.i(LOG_TAG, "Recovered from ANR");
        if (inAnrState()) {
            final long uptimeMillisWhenSomethingCouldRunOnMainThread = SystemClock.uptimeMillis();
            synchronized (this.mProcessingThreadLock) {
                if (this.mProcessingThreadHandler != null) {
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass4 */

                        public void run() {
                            if (SigquitBasedANRDetector.this.inAnrState()) {
                                SigquitBasedANRDetector.this.logMainThreadUnblocked(uptimeMillisWhenSomethingCouldRunOnMainThread);
                                SigquitBasedANRDetector.this.notifyStateListeners(AnrState.ANR_RECOVERED);
                            }
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void processMonitorStopped(int cause) {
        super.processMonitorStopped(cause);
        if (this.mANRConfig.getRecoveryTimeout() > 0) {
            synchronized (this.mWaitingToClearANRLock) {
                synchronized (this.mProcessingThreadLock) {
                    if (this.mProcessingThreadHandler != null) {
                        BLog.d(LOG_TAG, "Scheduling cleanup ANR task");
                        this.mWaitingForANRClearTimeout = true;
                        this.mProcessingThreadHandler.postDelayed(this.mClearAnrStateRunnable, (long) this.mANRConfig.getRecoveryTimeout());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void anrErrorClearedOnProcessMonitor() {
        BLog.d(LOG_TAG, "On anrErrorClearedOnProcessMonitor");
        setInAnrState(false);
        notifyStateListeners(AnrState.NO_ANR_DETECTED);
        if (shouldCollectAndUploadANRReportsNow()) {
            anrHasEnded(true);
        }
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetectorListener
    public void sigquitDetected(final String sigquitData, final String sigquitFileName, final boolean inForegroundV1, final boolean inForegroundV2) {
        final boolean clearFirst;
        BLog.w(LOG_TAG, "On sigquitDetected call");
        final long callbackUptime = SystemClock.uptimeMillis();
        BLog.d(LOG_TAG, "handleAnrDetected called");
        if (!isDebuggerConnected() && this.mRunning) {
            if (this.mANRConfig.getRecoveryTimeout() > 0) {
                synchronized (this.mWaitingToClearANRLock) {
                    synchronized (this.mProcessingThreadLock) {
                        if (this.mProcessingThreadHandler != null) {
                            this.mWaitingForANRClearTimeout = false;
                            this.mProcessingThreadHandler.removeCallbacks(this.mClearAnrStateRunnable);
                        }
                    }
                }
            }
            if (inAnrState()) {
                BLog.w(LOG_TAG, "Detected a new ANR before the end of the previous one");
                clearFirst = true;
            } else {
                clearFirst = false;
            }
            setInAnrState(true);
            synchronized (this.mProcessingThreadLock) {
                if (this.mProcessingThreadHandler != null) {
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5 */

                        public void run() {
                            if (clearFirst) {
                                BLog.w(SigquitBasedANRDetector.LOG_TAG, "Clearing current ANR");
                                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
                            }
                            BLog.w(SigquitBasedANRDetector.LOG_TAG, "On processing thread handling ANR start");
                            SigquitBasedANRDetector.this.updateForegroundState(inForegroundV1, inForegroundV2);
                            SigquitBasedANRDetector.this.notifyStateListeners(AnrState.DURING_ANR);
                            SigquitBasedANRDetector.this.mANRConfig.getMainThreadHandler().post(new Runnable() {
                                /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5.AnonymousClass1 */

                                public void run() {
                                    SigquitBasedANRDetector.this.mainThreadUnblocked();
                                }
                            });
                            SigquitRecord.updateRecords(callbackUptime, SigquitBasedANRDetector.this.mANRConfig.getSigquitTimeFilePath());
                            SigquitBasedANRDetector.this.maybeStartACRAReport(sigquitData, sigquitFileName, callbackUptime);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateForegroundState(boolean inForegroundV1, boolean inForegroundV2) {
        this.mInForegroundV1 = inForegroundV1;
        this.mInForegroundV2 = inForegroundV2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeStartACRAReport(String logData, String tracesFileName, long callbackUptime) {
        boolean shouldReport;
        if (this.mInForegroundV1 || this.mInForegroundV2) {
            shouldReport = shouldCollectAndUploadANRReports();
        } else {
            shouldReport = false;
        }
        if (shouldReport) {
            BLog.i(LOG_TAG, "Reporting ANR start");
            boolean tracesAreSigquitProduced = true;
            if (logData == null && tracesFileName == null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                StackTraceDumper.dumpStackTraces(bos);
                logData = bos.toString();
                tracesAreSigquitProduced = false;
            }
            try {
                startReport(logData, tracesFileName, Long.valueOf(callbackUptime), tracesAreSigquitProduced);
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error saving ANR report");
            }
        } else if (tracesFileName != null) {
            new File(tracesFileName).delete();
        }
    }

    /* access modifiers changed from: package-private */
    public class ProcessAnrErrorMonitorListener extends DefaultProcessErrorStateListener {
        ProcessAnrErrorMonitorListener() {
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onStart() {
            SigquitBasedANRDetector.this.processMonitorStarted();
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorDetected(@Nullable String errorMsg, @Nullable String tag) {
            if (SigquitBasedANRDetector.this.inAnrState() && SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                SigquitBasedANRDetector.this.mANRConfig.getANRReport().logSystemInfo(errorMsg, tag, SystemClock.uptimeMillis());
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorCleared() {
            if (SigquitBasedANRDetector.this.inAnrState()) {
                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onMaxChecksReachedBeforeError() {
            BLog.w(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks before error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(1);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onMaxChecksReachedAfterError() {
            BLog.w(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks after error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(2);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onCheckFailed() {
            BLog.e(SigquitBasedANRDetector.LOG_TAG, "Failed when checking process error state");
            SigquitBasedANRDetector.this.processMonitorStopped(3);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public boolean onErrorDetectOnOtherProcess(String processName, @Nullable String msg, @Nullable String tag) {
            if (!SigquitBasedANRDetector.this.inAnrState() || !SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                return false;
            }
            SigquitBasedANRDetector.this.mANRConfig.getANRReport().logOtherProcessAnr(processName, msg, tag, SystemClock.uptimeMillis());
            return true;
        }
    }

    private void maybeStartProcessErrorMonitor() {
        synchronized (this.mStateLock) {
            if (this.mStartProcessErrorMonitorAfterANRDetection) {
                if (this.mProcessAnrErrorMonitor == null) {
                    this.mProcessAnrErrorMonitor = new ProcessAnrErrorMonitor(this.mANRConfig.getContext(), this.mANRConfig.getProcessName(), 20, 100);
                }
                if (this.mProcessAnrErrorMonitor.getState() != ProcessAnrErrorMonitor.State.NOT_MONITORING) {
                    this.mProcessAnrErrorMonitor.stopMonitoring();
                }
                ProcessAnrErrorMonitorListener errorMonitorListener = new ProcessAnrErrorMonitorListener();
                this.mProcessAnrErrorMonitor.startMonitoring(errorMonitorListener);
                this.mProcessAnrErrorMonitorListener = errorMonitorListener;
            }
        }
    }

    private boolean isProcessErrorMonitorMonitoring() {
        return (this.mProcessAnrErrorMonitor == null || this.mProcessAnrErrorMonitor.getState() == ProcessAnrErrorMonitor.State.NOT_MONITORING) ? false : true;
    }

    private void notifyStateListeners(AnrState anrState, boolean inForeground) {
        AppStateUpdater appStateUpdater = this.mANRConfig.getAppStateUpdater();
        if (anrState == AnrState.DURING_ANR) {
            if (appStateUpdater != null) {
                appStateUpdater.updateAnrState(AnrState.DURING_ANR, null, inForeground);
            }
            maybeStartProcessErrorMonitor();
        } else if (appStateUpdater == null) {
        } else {
            if (isProcessErrorMonitorMonitoring() || anrState == AnrState.ANR_RECOVERED) {
                appStateUpdater.updateAnrState(AnrState.ANR_RECOVERED, null);
            } else {
                appStateUpdater.updateAnrState(AnrState.NO_ANR_DETECTED, null);
            }
        }
    }

    public void notifyStateListeners(AnrState anrState) {
        notifyStateListeners(anrState, this.mInForegroundV2 || this.mInForegroundV1);
    }

    private SigquitBasedANRDetector(ANRDetectorConfig anrConfig) {
        super(anrConfig);
    }
}
