package com.facebook.acra.anr.sigquit.detector;

import X.Mu;
import X.gL;
import android.os.Handler;
import android.os.HandlerThread;
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
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ThreadConfined;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitBasedANRDetector extends AbstractANRDetector implements SigquitDetectorListener {
    public static final String LOG_TAG = "SigquitBasedANRDetector";
    @GuardedBy("SigquitBasedANRDetector.class")
    @Nullable
    public static SigquitBasedANRDetector sInstance;
    @GuardedBy("mStateLock")
    public static boolean sNativeInited;
    public final Runnable mClearAnrStateRunnable;
    @GuardedBy("mStateLock")
    public long mDetectorReadyTime;
    @GuardedBy("mStateLock")
    public boolean mHookInPlace;
    @GuardedBy("mStateLock")
    @Nullable
    public NativeInitListener mNativeInitListener;
    @Nullable
    public ProcessAnrErrorMonitor mProcessAnrErrorMonitor;
    @Nullable
    public ProcessAnrErrorMonitorListener mProcessAnrErrorMonitorListener;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    public HandlerThread mProcessingThread;
    @GuardedBy("mProcessingThreadLock")
    @Nullable
    public Handler mProcessingThreadHandler;
    public final Object mProcessingThreadLock;
    public volatile boolean mRunning;
    public final SigquitDetector mSigquitDetector;
    public final boolean mStartProcessErrorMonitorAfterANRDetection;
    public final Object mStateLock;
    @GuardedBy("mStateLock")
    public long mSwitchTime;
    @GuardedBy("mWaitingToClearANRLock")
    public boolean mWaitingForANRClearTimeout;
    public final Object mWaitingToClearANRLock;

    public interface NativeInitListener {
        void onNativeInit();
    }

    @VisibleForTesting
    public class ProcessAnrErrorMonitorListener extends DefaultProcessErrorStateListener {
        public ProcessAnrErrorMonitorListener() {
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onCheckFailed() {
            Mu.A00(SigquitBasedANRDetector.LOG_TAG, "Failed when checking process error state");
            SigquitBasedANRDetector.this.processMonitorStopped(3);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorCleared() {
            if (SigquitBasedANRDetector.this.inAnrState()) {
                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public boolean onErrorDetectOnOtherProcess(String str, @Nullable String str2, @Nullable String str3) {
            if (!SigquitBasedANRDetector.this.inAnrState() || !SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                return false;
            }
            SigquitBasedANRDetector.this.mANRConfig.mANRReport.logOtherProcessAnr(str, str2, str3, SystemClock.uptimeMillis());
            return true;
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onErrorDetected(@Nullable String str, @Nullable String str2) {
            if (SigquitBasedANRDetector.this.inAnrState() && SigquitBasedANRDetector.this.shouldCollectAndUploadANRReportsNow()) {
                SigquitBasedANRDetector.this.mANRConfig.mANRReport.logSystemInfo(str, str2, SystemClock.uptimeMillis());
            }
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onMaxChecksReachedAfterError() {
            Mu.A01(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks after error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(2);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onMaxChecksReachedBeforeError() {
            Mu.A01(SigquitBasedANRDetector.LOG_TAG, "Reached max number of checks before error was detected");
            SigquitBasedANRDetector.this.processMonitorStopped(1);
        }

        @Override // com.facebook.acra.anr.processmonitor.ProcessErrorStateListener, com.facebook.acra.anr.processmonitor.DefaultProcessErrorStateListener
        public void onStart() {
            SigquitBasedANRDetector.this.processMonitorStarted();
        }
    }

    public void anrErrorClearedOnProcessMonitor() {
        setInAnrState(false);
        notifyStateListeners(gL.NO_ANR_DETECTED);
        if (shouldCollectAndUploadANRReportsNow()) {
            anrHasEnded(true);
        }
    }

    public static synchronized SigquitBasedANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig) {
        SigquitBasedANRDetector sigquitBasedANRDetector;
        synchronized (SigquitBasedANRDetector.class) {
            sigquitBasedANRDetector = sInstance;
            if (sigquitBasedANRDetector == null) {
                sigquitBasedANRDetector = new SigquitBasedANRDetector(aNRDetectorConfig);
                sInstance = sigquitBasedANRDetector;
            }
        }
        return sigquitBasedANRDetector;
    }

    @VisibleForTesting
    public static synchronized SigquitBasedANRDetector getTestInstance(ANRDetectorConfig aNRDetectorConfig, ProcessAnrErrorMonitor processAnrErrorMonitor) {
        synchronized (SigquitBasedANRDetector.class) {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void installSignalHandlerAndMaybeStart(boolean z) {
        synchronized (this.mStateLock) {
            if (!sNativeInited) {
                ANRDetectorConfig aNRDetectorConfig = this.mANRConfig;
                final AppStateUpdater appStateUpdater = aNRDetectorConfig.mAppStateUpdater;
                aNRDetectorConfig.mAppStateUpdater = new AppStateUpdater() {
                    /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass3 */

                    @Override // com.facebook.acra.anr.AppStateUpdater
                    public void updateAnrState(gL gLVar, @Nullable Runnable runnable) {
                        AnonymousClass1 r1;
                        if (gLVar != gL.DURING_ANR) {
                            r1 = new Runnable() {
                                /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass3.AnonymousClass1 */

                                public void run() {
                                    SigquitBasedANRDetector.this.mSigquitDetector.cleanupAppStateFile();
                                }
                            };
                        } else {
                            r1 = null;
                        }
                        AppStateUpdater appStateUpdater = appStateUpdater;
                        if (appStateUpdater != null) {
                            appStateUpdater.updateAnrState(gLVar, r1);
                        }
                    }
                };
                this.mSigquitDetector.init(aNRDetectorConfig, shouldCollectAndUploadANRReports());
                sNativeInited = true;
            }
            Handler handler = this.mProcessingThreadHandler;
            if (handler == null) {
                Mu.A01(LOG_TAG, "nativeLibraryLoaded has not been called yet");
            } else {
                this.mSigquitDetector.installSignalHandler(handler, z);
            }
        }
    }

    private boolean isProcessErrorMonitorMonitoring() {
        ProcessAnrErrorMonitor processAnrErrorMonitor = this.mProcessAnrErrorMonitor;
        if (processAnrErrorMonitor == null || processAnrErrorMonitor.getState() == ProcessAnrErrorMonitor.State.NOT_MONITORING) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeStartACRAReport(String str, String str2, long j) {
        if ((this.mInForegroundV1 || this.mInForegroundV2) && shouldCollectAndUploadANRReports()) {
            boolean z = true;
            if (str == null && str2 == null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                StackTraceDumper.dumpStackTraces(byteArrayOutputStream, null, null);
                str = byteArrayOutputStream.toString();
                z = false;
            }
            try {
                startReport(str, str2, Long.valueOf(j), z);
            } catch (IOException e) {
                Mu.A08(LOG_TAG, e, "Error saving ANR report");
            }
        } else if (str2 != null) {
            new File(str2).delete();
        }
    }

    private void maybeStartProcessErrorMonitor() {
        synchronized (this.mStateLock) {
            if (this.mStartProcessErrorMonitorAfterANRDetection) {
                ProcessAnrErrorMonitor processAnrErrorMonitor = this.mProcessAnrErrorMonitor;
                if (processAnrErrorMonitor == null) {
                    ANRDetectorConfig aNRDetectorConfig = this.mANRConfig;
                    processAnrErrorMonitor = new ProcessAnrErrorMonitor(aNRDetectorConfig.mContext, aNRDetectorConfig.mProcessName, 20, 100);
                    this.mProcessAnrErrorMonitor = processAnrErrorMonitor;
                }
                if (processAnrErrorMonitor.getState() != ProcessAnrErrorMonitor.State.NOT_MONITORING) {
                    this.mProcessAnrErrorMonitor.stopMonitoring();
                }
                ProcessAnrErrorMonitorListener processAnrErrorMonitorListener = new ProcessAnrErrorMonitorListener();
                this.mProcessAnrErrorMonitor.startMonitoringAfterDelay(processAnrErrorMonitorListener, 0);
                this.mProcessAnrErrorMonitorListener = processAnrErrorMonitorListener;
            }
        }
    }

    private void stopHandlerThread() {
        synchronized (this.mProcessingThreadLock) {
            this.mProcessingThreadHandler = null;
            HandlerThread handlerThread = this.mProcessingThread;
            if (handlerThread != null) {
                handlerThread.quit();
                this.mProcessingThread = null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateForegroundState(boolean z, boolean z2) {
        this.mInForegroundV1 = z;
        this.mInForegroundV2 = z2;
    }

    @Nullable
    @VisibleForTesting
    public ProcessAnrErrorMonitorListener getProcessAnrErrorMonitorListener() {
        throw new AssertionError();
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public long getReadyTime() {
        long j;
        synchronized (this.mStateLock) {
            j = this.mDetectorReadyTime;
        }
        return j;
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
    public boolean isRunning() {
        throw new AssertionError();
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetectorListener
    public void onHookedMethods(boolean z) {
        if (z) {
            synchronized (this.mStateLock) {
                this.mHookInPlace = true;
                NativeInitListener nativeInitListener = this.mNativeInitListener;
                if (nativeInitListener != null) {
                    nativeInitListener.onNativeInit();
                }
                this.mNativeInitListener = null;
            }
            return;
        }
        stopHandlerThread();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void resume() {
        throw new UnsupportedOperationException();
    }

    @VisibleForTesting
    public void setReadyTime(long j) {
        synchronized (this.mStateLock) {
            this.mDetectorReadyTime = j;
        }
    }

    @VisibleForTesting
    public void setSwitchTime(long j) {
        synchronized (this.mStateLock) {
            this.mSwitchTime = j;
        }
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetectorListener
    public void sigquitDetected(final String str, final String str2, final boolean z, final boolean z2) {
        final boolean z3;
        Mu.A01(LOG_TAG, "On sigquitDetected call");
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (!isDebuggerConnected() && this.mRunning) {
            if (this.mANRConfig.mRecoveryTimeout > 0) {
                synchronized (this.mWaitingToClearANRLock) {
                    synchronized (this.mProcessingThreadLock) {
                        Handler handler = this.mProcessingThreadHandler;
                        if (handler != null) {
                            this.mWaitingForANRClearTimeout = false;
                            handler.removeCallbacks(this.mClearAnrStateRunnable);
                        }
                    }
                }
            }
            if (inAnrState()) {
                Mu.A01(LOG_TAG, "Detected a new ANR before the end of the previous one");
                z3 = true;
            } else {
                z3 = false;
            }
            setInAnrState(true);
            synchronized (this.mProcessingThreadLock) {
                Handler handler2 = this.mProcessingThreadHandler;
                if (handler2 != null) {
                    handler2.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass5 */

                        public void run() {
                            if (z3) {
                                Mu.A01(SigquitBasedANRDetector.LOG_TAG, "Clearing current ANR");
                                SigquitBasedANRDetector.this.anrErrorClearedOnProcessMonitor();
                            }
                            Mu.A01(SigquitBasedANRDetector.LOG_TAG, "On processing thread handling ANR start");
                            SigquitBasedANRDetector.this.updateForegroundState(z, z2);
                            SigquitBasedANRDetector.this.notifyStateListeners(gL.DURING_ANR);
                            SigquitBasedANRDetector.this.mANRConfig.mMainThreadHandler.post(new Runnable() {
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

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void start(long j) {
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

    @VisibleForTesting
    public void startForTesting(HandlerThread handlerThread, long j) {
        throw new AssertionError();
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @ThreadConfined("UI")
    private void mainThreadUnblocked() {
        if (inAnrState()) {
            final long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (this.mProcessingThreadLock) {
                Handler handler = this.mProcessingThreadHandler;
                if (handler != null) {
                    handler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass4 */

                        public void run() {
                            if (SigquitBasedANRDetector.this.inAnrState()) {
                                SigquitBasedANRDetector.this.logMainThreadUnblocked(uptimeMillis);
                                SigquitBasedANRDetector.this.notifyStateListeners(gL.ANR_RECOVERED);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    public void processMonitorStopped(int i) {
        super.processMonitorStopped(i);
        if (this.mANRConfig.mRecoveryTimeout > 0) {
            synchronized (this.mWaitingToClearANRLock) {
                synchronized (this.mProcessingThreadLock) {
                    Handler handler = this.mProcessingThreadHandler;
                    if (handler != null) {
                        this.mWaitingForANRClearTimeout = true;
                        handler.postDelayed(this.mClearAnrStateRunnable, (long) this.mANRConfig.mRecoveryTimeout);
                    }
                }
            }
        }
    }

    public static /* synthetic */ String access$200() {
        return LOG_TAG;
    }

    public SigquitBasedANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        super(aNRDetectorConfig);
        this.mStateLock = new Object();
        this.mProcessingThreadLock = new Object();
        this.mWaitingToClearANRLock = new Object();
        this.mClearAnrStateRunnable = new Runnable() {
            /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass1 */

            public void run() {
                synchronized (SigquitBasedANRDetector.this.mWaitingToClearANRLock) {
                    SigquitBasedANRDetector sigquitBasedANRDetector = SigquitBasedANRDetector.this;
                    if (sigquitBasedANRDetector.mWaitingForANRClearTimeout) {
                        sigquitBasedANRDetector.notifyStateListeners(gL.NO_ANR_DETECTED);
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
    public SigquitBasedANRDetector(ANRDetectorConfig aNRDetectorConfig, ProcessAnrErrorMonitor processAnrErrorMonitor) {
        super(aNRDetectorConfig);
        this.mStateLock = new Object();
        this.mProcessingThreadLock = new Object();
        this.mWaitingToClearANRLock = new Object();
        this.mClearAnrStateRunnable = new Runnable() {
            /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass1 */

            public void run() {
                synchronized (SigquitBasedANRDetector.this.mWaitingToClearANRLock) {
                    SigquitBasedANRDetector sigquitBasedANRDetector = SigquitBasedANRDetector.this;
                    if (sigquitBasedANRDetector.mWaitingForANRClearTimeout) {
                        sigquitBasedANRDetector.notifyStateListeners(gL.NO_ANR_DETECTED);
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

    private void notifyStateListeners(gL gLVar, boolean z) {
        gL gLVar2;
        AppStateUpdater appStateUpdater = this.mANRConfig.mAppStateUpdater;
        gL gLVar3 = gL.DURING_ANR;
        if (gLVar == gLVar3) {
            if (appStateUpdater != null) {
                appStateUpdater.updateAnrState(gLVar3, null);
            }
            maybeStartProcessErrorMonitor();
        } else if (appStateUpdater != null) {
            if (isProcessErrorMonitorMonitoring() || gLVar == gL.ANR_RECOVERED) {
                gLVar2 = gL.ANR_RECOVERED;
            } else {
                gLVar2 = gL.NO_ANR_DETECTED;
            }
            appStateUpdater.updateAnrState(gLVar2, null);
        }
    }

    @VisibleForTesting
    public void nativeLibraryLoaded(NativeInitListener nativeInitListener, boolean z) {
        synchronized (this.mStateLock) {
            this.mNativeInitListener = nativeInitListener;
            nativeLibraryLoaded(z);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector, com.facebook.acra.anr.base.AbstractANRDetector
    public void nativeLibraryLoaded(final boolean z) {
        synchronized (this.mStateLock) {
            if (!this.mHookInPlace) {
                synchronized (this.mProcessingThreadLock) {
                    if (this.mProcessingThread == null) {
                        HandlerThread handlerThread = new HandlerThread("SigquitBasedANRDetectorThread");
                        this.mProcessingThread = handlerThread;
                        handlerThread.start();
                    }
                    Handler handler = this.mProcessingThreadHandler;
                    if (handler == null) {
                        handler = new Handler(this.mProcessingThread.getLooper());
                        this.mProcessingThreadHandler = handler;
                    }
                    handler.post(new Runnable() {
                        /* class com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.AnonymousClass2 */

                        public void run() {
                            SigquitBasedANRDetector.this.installSignalHandlerAndMaybeStart(z);
                        }
                    });
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        if (r2.mInForegroundV1 != false) goto L_0x0009;
     */
    @Override // com.facebook.acra.anr.base.AbstractANRDetector
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyStateListeners(X.gL r3) {
        /*
            r2 = this;
            boolean r0 = r2.mInForegroundV2
            if (r0 != 0) goto L_0x0009
            boolean r1 = r2.mInForegroundV1
            r0 = 0
            if (r1 == 0) goto L_0x000a
        L_0x0009:
            r0 = 1
        L_0x000a:
            r2.notifyStateListeners(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.notifyStateListeners(X.gL):void");
    }
}
