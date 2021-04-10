package com.facebook.acra.anr.base;

import android.os.Debug;
import android.os.Looper;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.debug.looperhistory.common.LooperHistoryHolder;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AbstractANRDetector implements IANRDetector {
    private static final String LOG_TAG = "AbstractANRDetector";
    public final ANRDetectorConfig mANRConfig;
    private ANRReportProvider mANRReportProvider;
    private long mANRReportTime;
    protected ANRDetectorListener mAnrDetectorListener;
    protected long mDetectorStartTime;
    private boolean mInAnr;
    public volatile boolean mInForegroundV1;
    public volatile boolean mInForegroundV2;
    private final Object mStateLock = new Object();

    public void collectStackTrace() {
    }

    public long getReadyTime() {
        return 0;
    }

    public long getSwitchTime() {
        return 0;
    }

    public abstract void start(long j);

    public AbstractANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        this.mANRConfig = aNRDetectorConfig;
    }

    /* access modifiers changed from: protected */
    public final boolean isDebuggerConnected() {
        return this.mANRConfig.mIsInternalBuild && Debug.isDebuggerConnected();
    }

    public final boolean shouldCollectAndUploadANRReports() {
        ANRReportProvider aNRReportProvider = this.mANRReportProvider;
        if (aNRReportProvider != null) {
            return aNRReportProvider.shouldCollectAndUploadANRReports();
        }
        return this.mANRConfig.mCachedShouldUploadANRReports;
    }

    public final boolean shouldCollectAndUploadANRReportsNow() {
        if (shouldCollectAndUploadANRReports()) {
            return this.mInForegroundV2 || this.mInForegroundV1;
        }
        return false;
    }

    public final void logMainThreadUnblocked(long j) {
        this.mANRConfig.mANRReport.logMainThreadUnblocked(j);
    }

    public final void startReport(String str, String str2, Long l, boolean z) throws IOException {
        ANRDetectorListener aNRDetectorListener;
        String str3;
        String str4;
        synchronized (this) {
            aNRDetectorListener = this.mAnrDetectorListener;
        }
        if (aNRDetectorListener != null) {
            String blackBoxTraceId = aNRDetectorListener.getBlackBoxTraceId();
            str3 = aNRDetectorListener.getLongStallTraceId();
            str4 = blackBoxTraceId;
        } else {
            str4 = null;
            str3 = null;
        }
        collectStackTrace();
        this.mANRReportTime = SystemClock.uptimeMillis();
        this.mANRConfig.mANRReport.startReport(this.mANRReportProvider == null, str4, str3, this.mANRConfig.mDetectorId, this.mInForegroundV1, this.mInForegroundV2, this.mANRReportTime, this.mDetectorStartTime, getReadyTime(), getSwitchTime(), str, str2, z, this.mANRConfig.mShouldRecordSignalTime, l);
        LooperHistoryHolder.dumpToLogCat(Looper.getMainLooper());
    }

    public final void anrHasEnded(boolean z) {
        if (z) {
            this.mANRConfig.mANRReport.finalizeAndTryToSendReport(SystemClock.uptimeMillis() - this.mANRReportTime);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public final void start() {
        start(-1);
    }

    public final boolean inAnrState() {
        boolean z;
        synchronized (this.mStateLock) {
            z = this.mInAnr;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public final void setInAnrState(boolean z) {
        synchronized (this.mStateLock) {
            this.mInAnr = z;
        }
    }

    public final void processMonitorStarted() {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.mANRReport.logProcessMonitorStart(SystemClock.uptimeMillis());
        }
    }

    public void processMonitorStopped(int i) {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.mANRReport.logProcessMonitorFailure(SystemClock.uptimeMillis(), i);
        }
    }
}
