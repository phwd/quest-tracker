package com.facebook.acra.anr.base;

import X.C0141Df;
import X.EnumC0186Iy;
import android.os.Debug;
import android.os.Looper;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.IANRReport;

public abstract class AbstractANRDetector implements IANRDetector {
    public static final String LOG_TAG = "AbstractANRDetector";
    public final ANRDetectorConfig mANRConfig;
    public ANRReportProvider mANRReportProvider;
    public long mANRReportTime;
    public ANRDetectorListener mAnrDetectorListener;
    public long mDetectorStartTime;
    public boolean mInAnr;
    public volatile boolean mInForegroundV1;
    public volatile boolean mInForegroundV2;
    public final Object mStateLock = new Object();

    public void collectStackTrace() {
    }

    public long getReadyTime() {
        return 0;
    }

    public long getSwitchTime() {
        return 0;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void nativeLibraryLoaded(boolean z) {
    }

    public abstract void notifyStateListeners(EnumC0186Iy iy);

    @Override // com.facebook.acra.anr.IANRDetector
    public void setCheckIntervalMs(long j) {
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener aNRDetectorListener) {
        this.mAnrDetectorListener = aNRDetectorListener;
    }

    public abstract void start(long j);

    @Override // com.facebook.acra.anr.IANRDetector
    public abstract void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener);

    public void anrHasEnded(boolean z) {
        if (z) {
            this.mANRConfig.mANRReport.finalizeAndTryToSendReport(SystemClock.uptimeMillis() - this.mANRReportTime);
        }
    }

    public boolean inAnrState() {
        boolean z;
        synchronized (this.mStateLock) {
            z = this.mInAnr;
        }
        return z;
    }

    public boolean isDebuggerConnected() {
        if (!this.mANRConfig.mIsInternalBuild || !Debug.isDebuggerConnected()) {
            return false;
        }
        return true;
    }

    public void logMainThreadUnblocked(long j) {
        this.mANRConfig.mANRReport.logMainThreadUnblocked(j);
    }

    public void reportSoftError(String str, Throwable th) {
        ANRReportProvider aNRReportProvider = this.mANRReportProvider;
        if (aNRReportProvider != null) {
            aNRReportProvider.reportSoftError(str, th);
        }
    }

    public void setInAnrState(boolean z) {
        synchronized (this.mStateLock) {
            this.mInAnr = z;
        }
    }

    public boolean shouldCollectAndUploadANRReports() {
        ANRReportProvider aNRReportProvider = this.mANRReportProvider;
        if (aNRReportProvider != null) {
            return aNRReportProvider.shouldCollectAndUploadANRReports();
        }
        return this.mANRConfig.mCachedShouldUploadANRReports;
    }

    public void startReport(String str, String str2, Long l, boolean z) {
        ANRDetectorListener aNRDetectorListener;
        String str3;
        synchronized (this) {
            aNRDetectorListener = this.mAnrDetectorListener;
        }
        String str4 = null;
        if (aNRDetectorListener != null) {
            str4 = aNRDetectorListener.getBlackBoxTraceId();
            str3 = aNRDetectorListener.getLongStallTraceId();
            aNRDetectorListener.onStartANRDataCapture();
        } else {
            str3 = null;
        }
        collectStackTrace();
        this.mANRReportTime = SystemClock.uptimeMillis();
        ANRDetectorConfig aNRDetectorConfig = this.mANRConfig;
        IANRReport iANRReport = aNRDetectorConfig.mANRReport;
        boolean z2 = false;
        if (this.mANRReportProvider == null) {
            z2 = true;
        }
        iANRReport.startReport(z2, str4, str3, aNRDetectorConfig.mDetectorId, this.mInForegroundV1, this.mInForegroundV2, this.mANRReportTime, this.mDetectorStartTime, getReadyTime(), getSwitchTime(), str, str2, z, this.mANRConfig.mShouldRecordSignalTime, l);
        Looper mainLooper = Looper.getMainLooper();
        synchronized (C0141Df.class) {
            C0141Df.A00.get(mainLooper);
        }
        if (aNRDetectorListener != null) {
            aNRDetectorListener.onEndANRDataCapture();
        }
    }

    public AbstractANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        this.mANRConfig = aNRDetectorConfig;
    }

    public static boolean isTest() {
        return false;
    }

    public ANRDetectorConfig getANRConfig() {
        return this.mANRConfig;
    }

    public ANRReportProvider getANRReportProvider() {
        return this.mANRReportProvider;
    }

    public long getDetectorStartTime() {
        return this.mDetectorStartTime;
    }

    public boolean isInForegroundV1() {
        return this.mInForegroundV1;
    }

    public void processMonitorStarted() {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.mANRReport.logProcessMonitorStart(SystemClock.uptimeMillis());
        }
    }

    public void processMonitorStopped(int i) {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.mANRReport.logProcessMonitorFailure(SystemClock.uptimeMillis(), i);
        }
    }

    public void setInAnrStateOnAppStateUpdater() {
    }

    public boolean shouldCollectAndUploadANRReportsNow() {
        if (!shouldCollectAndUploadANRReports()) {
            return false;
        }
        if (this.mInForegroundV2 || this.mInForegroundV1) {
            return true;
        }
        return false;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void setANRReportProvider(ANRReportProvider aNRReportProvider) {
        this.mANRReportProvider = aNRReportProvider;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void start() {
        start(-1);
    }
}
