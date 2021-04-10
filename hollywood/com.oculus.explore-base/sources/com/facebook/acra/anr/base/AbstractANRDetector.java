package com.facebook.acra.anr.base;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.debug.looperhistory.common.LooperHistoryHolder;
import java.io.IOException;

public abstract class AbstractANRDetector implements IANRDetector {
    private static final String LOG_TAG = AbstractANRDetector.class.getSimpleName();
    protected final ANRDetectorConfig mANRConfig;
    private ANRReportProvider mANRReportProvider;
    private long mANRReportTime;
    protected ANRDetectorListener mAnrDetectorListener;
    protected long mDetectorStartTime;
    private boolean mInAnr;
    protected volatile boolean mInForegroundV1;
    protected volatile boolean mInForegroundV2;
    private final Object mStateLock = new Object();

    public abstract void start(long j);

    @Override // com.facebook.acra.anr.IANRDetector
    public abstract void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener);

    public AbstractANRDetector(ANRDetectorConfig anrConfig) {
        this.mANRConfig = anrConfig;
    }

    /* access modifiers changed from: protected */
    public boolean shouldCollectAndUploadANRReports() {
        if (this.mANRReportProvider != null) {
            return this.mANRReportProvider.shouldCollectAndUploadANRReports();
        }
        return this.mANRConfig.getCachedShouldUploadANRReports();
    }

    /* access modifiers changed from: protected */
    public boolean shouldCollectAndUploadANRReportsNow() {
        return shouldCollectAndUploadANRReports() && (this.mInForegroundV2 || this.mInForegroundV1);
    }

    /* access modifiers changed from: protected */
    public void startReport(String logData, String tracesFileName, Long javaCallbackUptime, boolean tracesAreSigquitProduced) throws IOException {
        ANRDetectorListener listener;
        synchronized (this) {
            listener = this.mAnrDetectorListener;
        }
        String blackBoxTraceId = null;
        String longStallTraceId = null;
        if (listener != null) {
            blackBoxTraceId = listener.getBlackBoxTraceId();
            longStallTraceId = listener.getLongStallTraceId();
            listener.onStartANRDataCapture();
        }
        collectStackTrace();
        this.mANRReportTime = SystemClock.uptimeMillis();
        this.mANRConfig.getANRReport().startReport(this.mANRReportProvider == null, blackBoxTraceId, longStallTraceId, this.mANRConfig.getDetectorId(), this.mInForegroundV1, this.mInForegroundV2, this.mANRReportTime, this.mDetectorStartTime, getReadyTime(), getSwitchTime(), logData, tracesFileName, tracesAreSigquitProduced, this.mANRConfig.shouldRecordSignalTime(), javaCallbackUptime);
        LooperHistoryHolder.dumpToLogCat(Looper.getMainLooper());
        if (listener != null) {
            listener.onEndANRDataCapture();
        }
    }

    /* access modifiers changed from: protected */
    public void anrHasEnded(boolean shouldReport) {
        if (shouldReport) {
            this.mANRConfig.getANRReport().finalizeAndTryToSendReport(SystemClock.uptimeMillis() - this.mANRReportTime);
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void start() {
        start(-1);
    }

    /* access modifiers changed from: protected */
    public boolean inAnrState() {
        boolean z;
        synchronized (this.mStateLock) {
            z = this.mInAnr;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void setInAnrState(boolean state) {
        synchronized (this.mStateLock) {
            this.mInAnr = state;
        }
    }

    /* access modifiers changed from: protected */
    public void processMonitorStarted() {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.getANRReport().logProcessMonitorStart(SystemClock.uptimeMillis());
        }
    }

    /* access modifiers changed from: protected */
    public void processMonitorStopped(int cause) {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.getANRReport().logProcessMonitorFailure(SystemClock.uptimeMillis(), cause);
        }
    }

    public void collectStackTrace() {
    }

    public long getSwitchTime() {
        return 0;
    }

    public long getReadyTime() {
        return 0;
    }
}
