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
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HybridANRDetector implements IANRDetector, IANRDetector.ANRDetectorStopListener, SigquitBasedANRDetector.NativeInitListener {
    public static final String LOG_TAG = "HybridANRDetector";
    @GuardedBy("HybridANRDetector.class")
    @Nullable
    public static HybridANRDetector sInstance;
    @GuardedBy("this")
    public long mDetectorStartTime;
    @GuardedBy("this")
    @Nullable
    public AbstractANRDetector mInitialDetector;
    @GuardedBy("this")
    public boolean mNativeLibLoadedCalled;
    @GuardedBy("this")
    public boolean mRunning;
    public SigquitBasedANRDetector mSigquitBasedDetector;

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void nativeLibraryLoaded(boolean z) {
        if (!this.mNativeLibLoadedCalled) {
            boolean z2 = true;
            this.mNativeLibLoadedCalled = true;
            if (this.mInitialDetector != null) {
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
        AbstractANRDetector abstractANRDetector = this.mInitialDetector;
        if (abstractANRDetector != null) {
            abstractANRDetector.stop(this);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setANRReportProvider(ANRReportProvider aNRReportProvider) {
        AbstractANRDetector abstractANRDetector = this.mInitialDetector;
        if (abstractANRDetector != null) {
            abstractANRDetector.setANRReportProvider(aNRReportProvider);
        }
        this.mSigquitBasedDetector.setANRReportProvider(aNRReportProvider);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setCheckIntervalMs(long j) {
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener aNRDetectorListener) {
        AbstractANRDetector abstractANRDetector = this.mInitialDetector;
        if (abstractANRDetector != null) {
            abstractANRDetector.setListener(aNRDetectorListener);
        }
        this.mSigquitBasedDetector.setListener(aNRDetectorListener);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void start() {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.mDetectorStartTime = uptimeMillis;
        AbstractANRDetector abstractANRDetector = this.mInitialDetector;
        if (abstractANRDetector != null) {
            abstractANRDetector.start(uptimeMillis);
        } else {
            this.mSigquitBasedDetector.start(uptimeMillis);
        }
        this.mRunning = true;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener) {
        AbstractANRDetector abstractANRDetector = this.mInitialDetector;
        if (abstractANRDetector != null) {
            abstractANRDetector.stop(aNRDetectorStopListener);
        } else {
            this.mSigquitBasedDetector.stop(aNRDetectorStopListener);
        }
        this.mRunning = false;
    }

    public static synchronized HybridANRDetector getInstance(ANRDetectorConfig aNRDetectorConfig, int i) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            hybridANRDetector = sInstance;
            if (hybridANRDetector == null) {
                hybridANRDetector = new HybridANRDetector(aNRDetectorConfig, i);
                sInstance = hybridANRDetector;
            }
        }
        return hybridANRDetector;
    }

    @VisibleForTesting
    public static synchronized HybridANRDetector getTestInstance(ProcessErrorMonitorANRDetector processErrorMonitorANRDetector, SigquitBasedANRDetector sigquitBasedANRDetector) {
        HybridANRDetector hybridANRDetector;
        synchronized (HybridANRDetector.class) {
            hybridANRDetector = new HybridANRDetector(processErrorMonitorANRDetector, sigquitBasedANRDetector);
            sInstance = hybridANRDetector;
        }
        return hybridANRDetector;
    }

    @Override // com.facebook.acra.anr.IANRDetector.ANRDetectorStopListener
    public void onStop() {
        this.mSigquitBasedDetector.setSwitchTime(SystemClock.uptimeMillis());
        synchronized (this) {
            this.mInitialDetector = null;
            this.mSigquitBasedDetector.setReadyTime(SystemClock.uptimeMillis());
            if (this.mRunning) {
                this.mSigquitBasedDetector.start(this.mDetectorStartTime);
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void pause() {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void resume() {
        throw new UnsupportedOperationException();
    }

    public HybridANRDetector(ANRDetectorConfig aNRDetectorConfig, int i) {
        this.mInitialDetector = ProcessErrorMonitorANRDetector.getInstance(aNRDetectorConfig, i);
        this.mSigquitBasedDetector = SigquitBasedANRDetector.getInstance(aNRDetectorConfig);
    }

    @VisibleForTesting
    public HybridANRDetector(ProcessErrorMonitorANRDetector processErrorMonitorANRDetector, SigquitBasedANRDetector sigquitBasedANRDetector) {
        this.mInitialDetector = processErrorMonitorANRDetector;
        this.mSigquitBasedDetector = sigquitBasedANRDetector;
    }
}
