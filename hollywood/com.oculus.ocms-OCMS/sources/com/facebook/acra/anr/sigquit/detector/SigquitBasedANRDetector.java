package com.facebook.acra.anr.sigquit.detector;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
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
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadConfined;
import com.facebook.reliability.anr.AnrState;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitBasedANRDetector extends AbstractANRDetector implements SigquitDetectorListener {
    private static final String LOG_TAG = "SigquitBasedANRDetector";
    @GuardedBy("SigquitBasedANRDetector.class")
    @Nullable
    private static SigquitBasedANRDetector sInstance;
    @GuardedBy("mStateLock")
    private static boolean sNativeInited;
    private final Runnable mClearAnrStateRunnable;
    @GuardedBy("mStateLock")
    private long mDetectorReadyTime;
    @GuardedBy("mStateLock")
    private boolean mHookInPlace;
    @GuardedBy("mStateLock")
    @Nullable
    private NativeInitListener mNativeInitListener;
    @Nullable
    private ProcessAnrErrorMonitor mProcessAnrErrorMonitor;
    @Nullable
    private ProcessAnrErrorMonitorListener mProcessAnrErrorMonitorListener;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    private HandlerThread mProcessingThread;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    private Handler mProcessingThreadHandler;
    private final Object mProcessingThreadLock;
    private volatile boolean mRunning;
    private final SigquitDetector mSigquitDetector;
    private final boolean mStartProcessErrorMonitorAfterANRDetection;
    private final Object mStateLock;
    @GuardedBy("mStateLock")
    private long mSwitchTime;
    @GuardedBy("mWaitingToClearANRLock")
    private boolean mWaitingForANRClearTimeout;
    private final Object mWaitingToClearANRLock;

    public interface NativeInitListener {
        void onNativeInit();
    }

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

    @VisibleForTesting
    static synchronized SigquitBasedANRDetector getTestInstance(ANRDetectorConfig aNRDetectorConfig, ProcessAnrErrorMonitor processAnrErrorMonitor) {
        SigquitBasedANRDetector sigquitBasedANRDetector;
        synchronized (SigquitBasedANRDetector.class) {
            if (isTest()) {
                sInstance = new SigquitBasedANRDetector(aNRDetectorConfig, processAnrErrorMonitor);
                sigquitBasedANRDetector = sInstance;
            } else {
                throw new AssertionError();
            }
        }
        return sigquitBasedANRDetector;
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void start(long j) {
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

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void startForTesting(HandlerThread handlerThread, long j) {
        if (isTest()) {
            sNativeInited = true;
            this.mRunning = true;
            this.mProcessingThread = handlerThread;
            this.mProcessingThread.start();
            this.mProcessingThreadHandler = new Handler((Looper) Assertions.assumeNotNull(this.mProcessingThread.getLooper()));
            if (j == -1) {
                j = SystemClock.uptimeMillis();
            }
            this.mDetectorStartTime = j;
            return;
        }
        throw new AssertionError();
    }

    @VisibleForTesting
    public void nativeLibraryLoaded(NativeInitListener nativeInitListener, boolean z) {
        synchronized (this.mStateLock) {
            this.mNativeInitListener = nativeInitListener;
            nativeLibraryLoaded(z);
        }
    }

    @VisibleForTesting
    public void setSwitchTime(long j) {
        synchronized (this.mStateLock) {
            this.mSwitchTime = j;
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

    @VisibleForTesting
    public void setReadyTime(long j) {
        synchronized (this.mStateLock) {
            this.mDetectorReadyTime = j;
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

    @Override // com.facebook.acra.anr.IANRDetector
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void resume() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void nativeLibraryLoaded(final boolean z) {
        BLog.d(LOG_TAG, "NativeLibLoaded shouldStart = %b", Boolean.valueOf(z));
        synchronized (this.mStateLock) {
            if (!this.mHookInPlace) {
                synchronized (this.mProcessingThreadLock) {
                    if (this.mProcessingThread == null) {
                        this.mProcessingThread = new HandlerThread("SigquitBasedANRDetectorThread");
                        this.mProcessingThread.start();
                    }
                    if (this.mProcessingThreadHandler == null) {
                        this.mProcessingThreadHandler = new Handler((Looper) Assertions.assumeNotNull(this.mProcessingThread.getLooper()));
                    }
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass2 */

                        public void run() {
                            SigquitBasedANRDetector.this.installSignalHandlerAndMaybeStart(z);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void installSignalHandlerAndMaybeStart(boolean z) {
        synchronized (this.mStateLock) {
            if (!sNativeInited) {
                final AppStateUpdater appStateUpdater = this.mANRConfig.getAppStateUpdater();
                this.mANRConfig.setAppStateUpdater(new AppStateUpdater() {
                    /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass3 */

                    @Override // com.facebook.acra.anr.AppStateUpdater
                    public void updateAnrState(AnrState anrState, @Nullable Runnable runnable) {
                        AnonymousClass1 r3;
                        if (anrState != AnrState.DURING_ANR) {
                            r3 = new Runnable() {
                                /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass3.AnonymousClass1 */

                                public void run() {
                                    SigquitBasedANRDetector.this.mSigquitDetector.cleanupAppStateFile();
                                }
                            };
                        } else {
                            if (anrState == AnrState.NO_ANR_DETECTED) {
                                SigquitBasedANRDetector.this.mSigquitDetector.cleanupAppStateFile();
                            }
                            r3 = null;
                        }
                        AppStateUpdater appStateUpdater = appStateUpdater;
                        if (appStateUpdater != null) {
                            appStateUpdater.updateAnrState(anrState, r3);
                        }
                    }
                });
                this.mSigquitDetector.init(this.mANRConfig, shouldCollectAndUploadANRReports());
                sNativeInited = true;
            }
            if (this.mProcessingThreadHandler == null) {
                BLog.w(LOG_TAG, "nativeLibraryLoaded has not been called yet");
            } else {
                this.mSigquitDetector.installSignalHandler(this.mProcessingThreadHandler, z);
            }
        }
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetectorListener
    public void onHookedMethods(boolean z) {
        if (z) {
            synchronized (this.mStateLock) {
                this.mHookInPlace = true;
                if (this.mNativeInitListener != null) {
                    this.mNativeInitListener.onNativeInit();
                }
                this.mNativeInitListener = null;
            }
            return;
        }
        stopHandlerThread();
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener) {
        synchronized (this.mStateLock) {
            if (this.mHookInPlace) {
                this.mRunning = false;
                this.mSigquitDetector.stopDetector();
                stopHandlerThread();
            }
        }
        if (aNRDetectorStopListener != null) {
            aNRDetectorStopListener.onStop();
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
    @ThreadConfined(ThreadConfined.UI)
    private void mainThreadUnblocked() {
        BLog.i(LOG_TAG, "Recovered from ANR");
        if (inAnrState()) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (this.mProcessingThreadLock) {
                if (this.mProcessingThreadHandler != null) {
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass4 */

                        public void run() {
                            if (SigquitBasedANRDetector.this.inAnrState()) {
                                SigquitBasedANRDetector.this.logMainThreadUnblocked(uptimeMillis);
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
    public void processMonitorStopped(int i) {
        super.processMonitorStopped(i);
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
    public void sigquitDetected(final String str, final String str2, final boolean z, final boolean z2) {
        final boolean z3;
        BLog.w(LOG_TAG, "On sigquitDetected call");
        final long uptimeMillis = SystemClock.uptimeMillis();
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
                z3 = true;
            } else {
                z3 = false;
            }
            setInAnrState(true);
            synchronized (this.mProcessingThreadLock) {
                if (this.mProcessingThreadHandler != null) {
                    this.mProcessingThreadHandler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5 */

                        public void run() {
                            if (z3) {
                                BLog.w(SigquitBasedANRDetector.LOG_TAG, "Clearing current ANR");
                                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
                            }
                            BLog.w(SigquitBasedANRDetector.LOG_TAG, "On processing thread handling ANR start");
                            SigquitBasedANRDetector.this.updateForegroundState(z, z2);
                            SigquitBasedANRDetector.this.notifyStateListeners(AnrState.DURING_ANR);
                            SigquitBasedANRDetector.this.mANRConfig.getMainThreadHandler().post(new Runnable() {
                                /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5.AnonymousClass1 */

                                public void run() {
                                    SigquitBasedANRDetector.this.mainThreadUnblocked();
                                }
                            });
                            SigquitRecord.updateRecords(uptimeMillis, SigquitBasedANRDetector.this.mANRConfig.getSigquitTimeFilePath());
                            SigquitBasedANRDetector.this.maybeStartACRAReport(str, str2, uptimeMillis);
                        }
                    });
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateForegroundState(boolean z, boolean z2) {
        this.mInForegroundV1 = z;
        this.mInForegroundV2 = z2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeStartACRAReport(String str, String str2, long j) {
        boolean z;
        if (this.mInForegroundV1 || this.mInForegroundV2) {
            z = shouldCollectAndUploadANRReports();
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
                startReport(str, str2, Long.valueOf(j), z2);
            } catch (IOException e) {
                BLog.e(LOG_TAG, e, "Error saving ANR report");
            }
        } else if (str2 != null) {
            new File(str2).delete();
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isRunning() {
        if (isTest()) {
            return this.mRunning;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public class ProcessAnrErrorMonitorListener extends DefaultProcessErrorStateListener {
        ProcessAnrErrorMonitorListener() {
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onStart() {
            SigquitBasedANRDetector.this.processMonitorStarted();
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorDetected(@Nullable String str, @Nullable String str2) {
            if (SigquitBasedANRDetector.this.inAnrState() && SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                SigquitBasedANRDetector.this.mANRConfig.getANRReport().logSystemInfo(str, str2, SystemClock.uptimeMillis());
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
        public boolean onErrorDetectOnOtherProcess(String str, @Nullable String str2, @Nullable String str3) {
            if (!SigquitBasedANRDetector.this.inAnrState() || !SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                return false;
            }
            SigquitBasedANRDetector.this.mANRConfig.getANRReport().logOtherProcessAnr(str, str2, str3, SystemClock.uptimeMillis());
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public ProcessAnrErrorMonitorListener getProcessAnrErrorMonitorListener() {
        if (isTest()) {
            return this.mProcessAnrErrorMonitorListener;
        }
        throw new AssertionError();
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
                ProcessAnrErrorMonitorListener processAnrErrorMonitorListener = new ProcessAnrErrorMonitorListener();
                this.mProcessAnrErrorMonitor.startMonitoring(processAnrErrorMonitorListener);
                this.mProcessAnrErrorMonitorListener = processAnrErrorMonitorListener;
            }
        }
    }

    private boolean isProcessErrorMonitorMonitoring() {
        ProcessAnrErrorMonitor processAnrErrorMonitor = this.mProcessAnrErrorMonitor;
        return (processAnrErrorMonitor == null || processAnrErrorMonitor.getState() == ProcessAnrErrorMonitor.State.NOT_MONITORING) ? false : true;
    }

    private void notifyStateListeners(AnrState anrState, boolean z) {
        AppStateUpdater appStateUpdater = this.mANRConfig.getAppStateUpdater();
        if (anrState == AnrState.DURING_ANR) {
            if (appStateUpdater != null) {
                appStateUpdater.updateAnrState(AnrState.DURING_ANR, null, z);
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

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    @VisibleForTesting
    public void notifyStateListeners(AnrState anrState) {
        notifyStateListeners(anrState, this.mInForegroundV2 || this.mInForegroundV1);
    }

    private SigquitBasedANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        super(aNRDetectorConfig);
        this.mStateLock = new Object();
        this.mProcessingThreadLock = new Object();
        this.mWaitingToClearANRLock = new Object();
        this.mClearAnrStateRunnable = new Runnable() {
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
        this.mSigquitDetector = SigquitDetectorAcra.getInstance(this);
        this.mProcessAnrErrorMonitor = null;
        this.mStartProcessErrorMonitorAfterANRDetection = true;
    }

    @VisibleForTesting
    private SigquitBasedANRDetector(ANRDetectorConfig aNRDetectorConfig, ProcessAnrErrorMonitor processAnrErrorMonitor) {
        super(aNRDetectorConfig);
        this.mStateLock = new Object();
        this.mProcessingThreadLock = new Object();
        this.mWaitingToClearANRLock = new Object();
        this.mClearAnrStateRunnable = new Runnable() {
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
        this.mSigquitDetector = SigquitDetectorAcra.getInstance(this);
        this.mProcessAnrErrorMonitor = processAnrErrorMonitor;
        this.mStartProcessErrorMonitorAfterANRDetection = true;
    }
}
