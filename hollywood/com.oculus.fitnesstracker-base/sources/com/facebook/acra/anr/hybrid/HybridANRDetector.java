package com.facebook.acra.anr.hybrid;

import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.base.AbstractANRDetector;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class HybridANRDetector implements IANRDetector {
    private static final String LOG_TAG = "HybridANRDetector";
    private static HybridANRDetector sInstance;
    private long mDetectorStartTime;
    private AbstractANRDetector mInitialDetector;
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

    @Override // com.facebook.acra.anr.IANRDetector
    public final synchronized void start() {
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

    private HybridANRDetector(ANRDetectorConfig aNRDetectorConfig, int i) {
        this.mInitialDetector = ProcessErrorMonitorANRDetector.getInstance(aNRDetectorConfig, i);
        this.mSigquitBasedDetector = SigquitBasedANRDetector.getInstance(aNRDetectorConfig);
    }
}
