package com.facebook.acra.anr.sigquit.detector;

import android.os.Handler;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.AppStateUpdater;
import com.facebook.acra.anr.SigquitRecord;
import com.facebook.acra.anr.StackTraceDumper;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.facebook.acra.anr.sigquit.SigquitDetector;
import com.facebook.acra.anr.sigquit.SigquitDetectorAcra;
import com.facebook.acra.anr.sigquit.SigquitDetectorListener;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.reliability.anr.AnrState;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitBasedANRDetector extends AbstractANRDetector implements SigquitDetectorListener {
    private static final String LOG_TAG = "SigquitBasedANRDetector";
    private static SigquitBasedANRDetector sInstance;
    private final Runnable mClearAnrStateRunnable = new Runnable() {
        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass1 */

        public final void run() {
            synchronized (SigquitBasedANRDetector.this.mWaitingToClearANRLock) {
                if (SigquitBasedANRDetector.this.mWaitingForANRClearTimeout) {
                    BLog.d(SigquitBasedANRDetector.LOG_TAG, "Clearing ANR state by timeout");
                    SigquitBasedANRDetector.this.notifyStateListeners$6ef55a6(AnrState.NO_ANR_DETECTED$65befc1);
                    if (SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                        SigquitBasedANRDetector.this.anrHasEnded(true);
                    }
                    SigquitBasedANRDetector.this.mWaitingForANRClearTimeout = false;
                }
            }
        }
    };
    public long mDetectorReadyTime;
    private boolean mHookInPlace;
    private ProcessAnrErrorMonitor mProcessAnrErrorMonitor = null;
    private ProcessAnrErrorMonitorListener mProcessAnrErrorMonitorListener;
    private Handler mProcessingThreadHandler;
    private final Object mProcessingThreadLock = new Object();
    private volatile boolean mRunning;
    private final SigquitDetector mSigquitDetector = SigquitDetectorAcra.getInstance(this);
    private final boolean mStartProcessErrorMonitorAfterANRDetection = true;
    public final Object mStateLock = new Object();
    public long mSwitchTime;
    private boolean mWaitingForANRClearTimeout;
    private final Object mWaitingToClearANRLock = new Object();

    public static synchronized SigquitBasedANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig) {
        SigquitBasedANRDetector sigquitBasedANRDetector;
        synchronized (SigquitBasedANRDetector.class) {
            if (sInstance == null) {
                sInstance = new SigquitBasedANRDetector(aNRDetectorConfig);
            }
            sigquitBasedANRDetector = sInstance;
        }
        return sigquitBasedANRDetector;
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final void start(long j) {
        BLog.d(LOG_TAG, "Started");
        synchronized (this.mStateLock) {
            if (this.mDetectorStartTime <= 0) {
                this.mDetectorStartTime = j;
            }
            if (this.mHookInPlace && !this.mRunning) {
                if (this.mDetectorStartTime == -1) {
                    this.mDetectorStartTime = SystemClock.uptimeMillis();
                }
                this.mRunning = true;
            }
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final long getSwitchTime() {
        long j;
        synchronized (this.mStateLock) {
            j = this.mSwitchTime;
        }
        return j;
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final long getReadyTime() {
        long j;
        synchronized (this.mStateLock) {
            j = this.mDetectorReadyTime;
        }
        return j;
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public final void processMonitorStopped(int i) {
        super.processMonitorStopped(i);
        if (this.mANRConfig.mRecoveryTimeout > 0) {
            synchronized (this.mWaitingToClearANRLock) {
                synchronized (this.mProcessingThreadLock) {
                    if (this.mProcessingThreadHandler != null) {
                        BLog.d(LOG_TAG, "Scheduling cleanup ANR task");
                        this.mWaitingForANRClearTimeout = true;
                        this.mProcessingThreadHandler.postDelayed(this.mClearAnrStateRunnable, (long) this.mANRConfig.mRecoveryTimeout);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void anrErrorClearedOnProcessMonitor() {
        BLog.d(LOG_TAG, "On anrErrorClearedOnProcessMonitor");
        setInAnrState(false);
        notifyStateListeners$6ef55a6(AnrState.NO_ANR_DETECTED$65befc1);
        if (shouldCollectAndUploadANRReportsNow()) {
            anrHasEnded(true);
        }
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetectorListener
    public final void sigquitDetected(final String str, final String str2, final boolean z, final boolean z2) {
        final boolean z3;
        BLog.w(LOG_TAG, "On sigquitDetected call");
        final long uptimeMillis = SystemClock.uptimeMillis();
        BLog.d(LOG_TAG, "handleAnrDetected called");
        if (!isDebuggerConnected() && this.mRunning) {
            if (this.mANRConfig.mRecoveryTimeout > 0) {
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
                z3 = true;
            } else {
                z3 = false;
            }
            setInAnrState(true);
            synchronized (this.mProcessingThreadLock) {
                if (this.mProcessingThreadHandler != null) {
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5 */

                        public final void run() {
                            if (z3) {
                                BLog.w(SigquitBasedANRDetector.LOG_TAG, "Clearing current ANR");
                                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
                            }
                            BLog.w(SigquitBasedANRDetector.LOG_TAG, "On processing thread handling ANR start");
                            SigquitBasedANRDetector.access$900(SigquitBasedANRDetector.this, z, z2);
                            SigquitBasedANRDetector.this.notifyStateListeners$6ef55a6(AnrState.DURING_ANR$65befc1);
                            SigquitBasedANRDetector.this.mANRConfig.mMainThreadHandler.post(new Runnable() {
                                /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5.AnonymousClass1 */

                                public final void run() {
                                    SigquitBasedANRDetector.access$1000(SigquitBasedANRDetector.this);
                                }
                            });
                            SigquitRecord.updateRecords(uptimeMillis, SigquitBasedANRDetector.this.mANRConfig.getSigquitTimeFilePath());
                            SigquitBasedANRDetector.access$1300(SigquitBasedANRDetector.this, str, str2, uptimeMillis);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class ProcessAnrErrorMonitorListener extends DefaultProcessErrorStateListener {
        ProcessAnrErrorMonitorListener() {
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onStart() {
            SigquitBasedANRDetector.this.processMonitorStarted();
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onErrorDetected(String str, String str2) {
            if (SigquitBasedANRDetector.this.inAnrState() && SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                SigquitBasedANRDetector.this.mANRConfig.mANRReport.logSystemInfo(str, str2, SystemClock.uptimeMillis());
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onErrorCleared() {
            if (SigquitBasedANRDetector.this.inAnrState()) {
                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onMaxChecksReachedBeforeError() {
            BLog.w(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks before error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(1);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onMaxChecksReachedAfterError() {
            BLog.w(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks after error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(2);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final void onCheckFailed() {
            BLog.e(SigquitBasedANRDetector.LOG_TAG, "Failed when checking process error state");
            SigquitBasedANRDetector.this.processMonitorStopped(3);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public final boolean onErrorDetectOnOtherProcess(String str, String str2, String str3) {
            if (!SigquitBasedANRDetector.this.inAnrState() || !SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                return false;
            }
            SigquitBasedANRDetector.this.mANRConfig.mANRReport.logOtherProcessAnr(str, str2, str3, SystemClock.uptimeMillis());
            return true;
        }
    }

    private void notifyStateListeners$2903ee12(int i, boolean z) {
        AppStateUpdater appStateUpdater = this.mANRConfig.mAppStateUpdater;
        if (i == AnrState.DURING_ANR$65befc1) {
            if (appStateUpdater == null) {
                synchronized (this.mStateLock) {
                    if (this.mStartProcessErrorMonitorAfterANRDetection) {
                        if (this.mProcessAnrErrorMonitor == null) {
                            this.mProcessAnrErrorMonitor = new ProcessAnrErrorMonitor(this.mANRConfig.mContext, this.mANRConfig.mProcessName, 20, 100);
                        }
                        if (this.mProcessAnrErrorMonitor.getState$5e679686() != ProcessAnrErrorMonitor.State.NOT_MONITORING$20c36de5) {
                            this.mProcessAnrErrorMonitor.stopMonitoring();
                        }
                        ProcessAnrErrorMonitorListener processAnrErrorMonitorListener = new ProcessAnrErrorMonitorListener();
                        this.mProcessAnrErrorMonitor.startMonitoringAfterDelay(processAnrErrorMonitorListener, 0);
                        this.mProcessAnrErrorMonitorListener = processAnrErrorMonitorListener;
                    }
                }
                return;
            }
            int i2 = AnrState.DURING_ANR$65befc1;
            throw null;
        } else if (appStateUpdater != null) {
            ProcessAnrErrorMonitor processAnrErrorMonitor = this.mProcessAnrErrorMonitor;
            if (((processAnrErrorMonitor == null || processAnrErrorMonitor.getState$5e679686() == ProcessAnrErrorMonitor.State.NOT_MONITORING$20c36de5) ? false : true) || i == AnrState.ANR_RECOVERED$65befc1) {
                int i3 = AnrState.ANR_RECOVERED$65befc1;
                throw null;
            } else {
                int i4 = AnrState.NO_ANR_DETECTED$65befc1;
                throw null;
            }
        }
    }

    public final void notifyStateListeners$6ef55a6(int i) {
        notifyStateListeners$2903ee12(i, this.mInForegroundV2 || this.mInForegroundV1);
    }

    private SigquitBasedANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        super(aNRDetectorConfig);
    }

    static /* synthetic */ void access$900(SigquitBasedANRDetector sigquitBasedANRDetector, boolean z, boolean z2) {
        sigquitBasedANRDetector.mInForegroundV1 = z;
        sigquitBasedANRDetector.mInForegroundV2 = z2;
    }

    static /* synthetic */ void access$1000(SigquitBasedANRDetector sigquitBasedANRDetector) {
        BLog.i(LOG_TAG, "Recovered from ANR");
        if (sigquitBasedANRDetector.inAnrState()) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (sigquitBasedANRDetector.mProcessingThreadLock) {
                Handler handler = sigquitBasedANRDetector.mProcessingThreadHandler;
                if (handler != null) {
                    handler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass4 */

                        public final void run() {
                            if (SigquitBasedANRDetector.this.inAnrState()) {
                                SigquitBasedANRDetector.this.logMainThreadUnblocked(uptimeMillis);
                                SigquitBasedANRDetector.this.notifyStateListeners$6ef55a6(AnrState.ANR_RECOVERED$65befc1);
                            }
                        }
                    });
                }
            }
        }
    }

    static /* synthetic */ void access$1300(SigquitBasedANRDetector sigquitBasedANRDetector, String str, String str2, long j) {
        boolean z;
        if (sigquitBasedANRDetector.mInForegroundV1 || sigquitBasedANRDetector.mInForegroundV2) {
            z = sigquitBasedANRDetector.shouldCollectAndUploadANRReports();
        } else {
            z = false;
        }
        if (z) {
            BLog.i(LOG_TAG, "Reporting ANR start");
            boolean z2 = true;
            if (str == null && str2 == null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                StackTraceDumper.dumpStackTraces(byteArrayOutputStream);
                str = byteArrayOutputStream.toString();
                z2 = false;
            }
            try {
                sigquitBasedANRDetector.startReport(str, str2, Long.valueOf(j), z2);
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error saving ANR report");
            }
        } else if (str2 != null) {
            new File(str2).delete();
        }
    }
}
