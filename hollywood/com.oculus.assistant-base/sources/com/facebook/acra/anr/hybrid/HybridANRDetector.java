package com.facebook.acra.anr.hybrid;

import X.C0139Dd;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector;

public class HybridANRDetector implements IANRDetector, IANRDetector.ANRDetectorStopListener, SigquitBasedANRDetector.NativeInitListener {
    public static final String LOG_TAG = "HybridANRDetector";
    public static HybridANRDetector sInstance;
    public long mDetectorStartTime;
    public AbstractANRDetector mInitialDetector;
    public boolean mNativeLibLoadedCalled;
    public boolean mRunning;
    public SigquitBasedANRDetector mSigquitBasedDetector;

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void nativeLibraryLoaded(boolean z) {
        if (!this.mNativeLibLoadedCalled) {
            boolean z2 = true;
            this.mNativeLibLoadedCalled = true;
            if (this.mInitialDetector != null) {
                C0139Dd.A09(LOG_TAG, "About to switch detectors");
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
        C0139Dd.A09(LOG_TAG, "SigquitBasedANRDetector native init done");
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
        C0139Dd.A09(LOG_TAG, "Start called.");
        this.mDetectorStartTime = SystemClock.uptimeMillis();
        if (this.mInitialDetector != null) {
            C0139Dd.A09(LOG_TAG, "Starting initial detector");
            this.mInitialDetector.start(this.mDetectorStartTime);
        } else {
            C0139Dd.A09(LOG_TAG, "Starting sigquit detector");
            this.mSigquitBasedDetector.start(this.mDetectorStartTime);
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
                C0139Dd.A09(LOG_TAG, "About to start new detector");
                this.mSigquitBasedDetector.start(this.mDetectorStartTime);
            } else {
                C0139Dd.A09(LOG_TAG, "New detector was activated but won't be started right now");
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

    public HybridANRDetector(ProcessErrorMonitorANRDetector processErrorMonitorANRDetector, SigquitBasedANRDetector sigquitBasedANRDetector) {
        this.mInitialDetector = processErrorMonitorANRDetector;
        this.mSigquitBasedDetector = sigquitBasedANRDetector;
    }
}
