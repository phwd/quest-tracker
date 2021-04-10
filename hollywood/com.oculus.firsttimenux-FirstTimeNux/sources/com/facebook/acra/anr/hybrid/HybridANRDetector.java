package com.facebook.acra.anr.hybrid;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HybridANRDetector implements SigquitBasedANRDetector.NativeInitListener, IANRDetector, IANRDetector.ANRDetectorStopListener {
    private static final String LOG_TAG = HybridANRDetector.class.getSimpleName();
    @GuardedBy("HybridANRDetector.class")
    @Nullable
    private static HybridANRDetector sInstance;
    @GuardedBy("this")
    private long mDetectorStartTime;
    @GuardedBy("this")
    @Nullable
    private AbstractANRDetector mInitialDetector;
    @GuardedBy("this")
    private boolean mNativeLibLoadedCalled;
    @GuardedBy("this")
    private boolean mRunning;
    private SigquitBasedANRDetector mSigquitBasedDetector;

    public static synchronized HybridANRDetector getInstance(ANRDetectorConfig anrConfig, int errorMonitorCheckInterval) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            if (sInstance == null) {
                sInstance = new HybridANRDetector(anrConfig, errorMonitorCheckInterval);
            }
            hybridANRDetector = sInstance;
        }
        return hybridANRDetector;
    }

    @VisibleForTesting
    static synchronized HybridANRDetector getTestInstance(ProcessErrorMonitorANRDetector initialDetector, SigquitBasedANRDetector sigquitBasedDetector) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            sInstance = new HybridANRDetector(initialDetector, sigquitBasedDetector);
            hybridANRDetector = sInstance;
        }
        return hybridANRDetector;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setANRReportProvider(ANRReportProvider ANRReportProvider) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setANRReportProvider(ANRReportProvider);
        }
        this.mSigquitBasedDetector.setANRReportProvider(ANRReportProvider);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void start() {
        BLog.d(LOG_TAG, "Start called.");
        this.mDetectorStartTime = SystemClock.uptimeMillis();
        if (this.mInitialDetector != null) {
            BLog.d(LOG_TAG, "Starting initial detector");
            this.mInitialDetector.start(this.mDetectorStartTime);
        } else {
            BLog.d(LOG_TAG, "Starting sigquit detector");
            this.mSigquitBasedDetector.start(this.mDetectorStartTime);
        }
        this.mRunning = true;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void resume() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void nativeLibraryLoaded(boolean shouldStart) {
        boolean z = true;
        synchronized (this) {
            if (!this.mNativeLibLoadedCalled) {
                this.mNativeLibLoadedCalled = true;
                if (this.mInitialDetector != null) {
                    BLog.d(LOG_TAG, "About to switch detectors");
                    SigquitBasedANRDetector sigquitBasedANRDetector = this.mSigquitBasedDetector;
                    if (!shouldStart && !this.mRunning) {
                        z = false;
                    }
                    sigquitBasedANRDetector.nativeLibraryLoaded(this, z);
                }
            }
        }
    }

    @Override // com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector.NativeInitListener
    public synchronized void onNativeInit() {
        BLog.d(LOG_TAG, "SigquitBasedANRDetector native init done");
        if (this.mInitialDetector != null) {
            this.mInitialDetector.stop(this);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector.ANRDetectorStopListener
    public void onStop() {
        this.mSigquitBasedDetector.setSwitchTime(SystemClock.uptimeMillis());
        synchronized (this) {
            this.mInitialDetector = null;
            this.mSigquitBasedDetector.setReadyTime(SystemClock.uptimeMillis());
            if (this.mRunning) {
                BLog.d(LOG_TAG, "About to start new detector");
                this.mSigquitBasedDetector.start(this.mDetectorStartTime);
            } else {
                BLog.d(LOG_TAG, "New detector was activated but won't be started right now");
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void stop(IANRDetector.ANRDetectorStopListener stopListener) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.stop(stopListener);
        } else {
            this.mSigquitBasedDetector.stop(stopListener);
        }
        this.mRunning = false;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener listener) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setListener(listener);
        }
        this.mSigquitBasedDetector.setListener(listener);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setCheckIntervalMs(long checkIntervalMs) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setCheckIntervalMs(checkIntervalMs);
        }
    }

    private HybridANRDetector(ANRDetectorConfig anrConfig, int errorMonitorCheckInterval) {
        this.mInitialDetector = ProcessErrorMonitorANRDetector.getInstance(anrConfig, errorMonitorCheckInterval);
        this.mSigquitBasedDetector = SigquitBasedANRDetector.getInstance(anrConfig);
    }

    @VisibleForTesting
    private HybridANRDetector(ProcessErrorMonitorANRDetector initialDetector, SigquitBasedANRDetector sigquitBasedDetector) {
        this.mInitialDetector = initialDetector;
        this.mSigquitBasedDetector = sigquitBasedDetector;
    }
}
