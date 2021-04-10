package com.facebook.acra.anr.sigquit.detector;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.sigquit.SigquitDetector;
import com.facebook.acra.anr.sigquit.SigquitDetectorAcra;
import com.facebook.acra.anr.sigquit.SigquitDetectorListener;
import com.facebook.debug.log.BLog;
import com.facebook.reliability.anr.AnrState;

public class SigquitBasedANRDetector extends AbstractANRDetector implements SigquitDetectorListener {
    private static final String LOG_TAG = SigquitBasedANRDetector.class.getSimpleName();
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
    private long mDetectorReadyTime;
    private boolean mHookInPlace;
    private ProcessAnrErrorMonitor mProcessAnrErrorMonitor = null;
    private ProcessAnrErrorMonitorListener mProcessAnrErrorMonitorListener;
    private HandlerThread mProcessingThread;
    private Handler mProcessingThreadHandler;
    private final Object mProcessingThreadLock = new Object();
    private volatile boolean mRunning;
    private final SigquitDetector mSigquitDetector = SigquitDetectorAcra.getInstance(this);
    private final boolean mStartProcessErrorMonitorAfterANRDetection = true;
    private final Object mStateLock = new Object();
    private long mSwitchTime;
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

    /* access modifiers changed from: package-private */
    public class ProcessAnrErrorMonitorListener extends DefaultProcessErrorStateListener {
        ProcessAnrErrorMonitorListener() {
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onStart() {
            SigquitBasedANRDetector.this.processMonitorStarted();
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorDetected(String errorMsg, String tag) {
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
        public boolean onErrorDetectOnOtherProcess(String processName, String msg, String tag) {
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
