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
    private static final String LOG_TAG = "HybridANRDetector";
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

    public static synchronized HybridANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig, int i) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            if (sInstance == null) {
                sInstance = new HybridANRDetector(aNRDetectorConfig, i);
            }
            hybridANRDetector = sInstance;
        }
        return hybridANRDetector;
    }

    @VisibleForTesting
    static synchronized HybridANRDetector getTestInstance(ProcessErrorMonitorANRDetector processErrorMonitorANRDetector, SigquitBasedANRDetector sigquitBasedANRDetector) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            sInstance = new HybridANRDetector(processErrorMonitorANRDetector, sigquitBasedANRDetector);
            hybridANRDetector = sInstance;
        }
        return hybridANRDetector;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setANRReportProvider(ANRReportProvider aNRReportProvider) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setANRReportProvider(aNRReportProvider);
        }
        this.mSigquitBasedDetector.setANRReportProvider(aNRReportProvider);
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
    public synchronized void nativeLibraryLoaded(boolean z) {
        if (!this.mNativeLibLoadedCalled) {
            boolean z2 = true;
            this.mNativeLibLoadedCalled = true;
            if (this.mInitialDetector != null) {
                BLog.d(LOG_TAG, "About to switch detectors");
                SigquitBasedANRDetector sigquitBasedANRDetector = this.mSigquitBasedDetector;
                if (!z && !this.mRunning) {
                    z2 = false;
                }
                sigquitBasedANRDetector.nativeLibraryLoaded(this, z2);
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
    public synchronized void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.stop(aNRDetectorStopListener);
        } else {
            this.mSigquitBasedDetector.stop(aNRDetectorStopListener);
        }
        this.mRunning = false;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener aNRDetectorListener) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setListener(aNRDetectorListener);
        }
        this.mSigquitBasedDetector.setListener(aNRDetectorListener);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setCheckIntervalMs(long j) {
        if (this.mInitialDetector != null) {
            this.mInitialDetector.setCheckIntervalMs(j);
        }
    }

    private HybridANRDetector(ANRDetectorConfig aNRDetectorConfig, int i) {
        this.mInitialDetector = ProcessErrorMonitorANRDetector.getInstance(aNRDetectorConfig, i);
        this.mSigquitBasedDetector = SigquitBasedANRDetector.getInstance(aNRDetectorConfig);
    }

    @VisibleForTesting
    private HybridANRDetector(ProcessErrorMonitorANRDetector processErrorMonitorANRDetector, SigquitBasedANRDetector sigquitBasedANRDetector) {
        this.mInitialDetector = processErrorMonitorANRDetector;
        this.mSigquitBasedDetector = sigquitBasedANRDetector;
    }
}
