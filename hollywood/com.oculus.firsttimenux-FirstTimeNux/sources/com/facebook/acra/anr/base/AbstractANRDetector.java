package com.facebook.acra.anr.base;

import android.os.Debug;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.ANRDetectorListener;
import com.facebook.acra.anr.ANRReportProvider;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.common.build.BuildConstants;
import com.facebook.debug.looperhistory.common.LooperHistoryHolder;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.reliability.anr.AnrState;
import com.facebook.testenv.TestEnvironment;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AbstractANRDetector implements IANRDetector {
    private static final String LOG_TAG = AbstractANRDetector.class.getSimpleName();
    protected final ANRDetectorConfig mANRConfig;
    @Nullable
    private ANRReportProvider mANRReportProvider;
    private long mANRReportTime;
    @GuardedBy("this")
    @Nullable
    protected ANRDetectorListener mAnrDetectorListener;
    protected long mDetectorStartTime;
    @GuardedBy("mStateLock")
    private boolean mInAnr;
    protected volatile boolean mInForegroundV1;
    protected volatile boolean mInForegroundV2;
    private final Object mStateLock = new Object();

    @VisibleForTesting
    public abstract void notifyStateListeners(AnrState anrState);

    public abstract void start(long j);

    @Override // com.facebook.acra.anr.IANRDetector
    public abstract void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener);

    @VisibleForTesting
    public AbstractANRDetector(ANRDetectorConfig anrConfig) {
        this.mANRConfig = anrConfig;
    }

    protected static boolean isTest() {
        return BuildConstants.isInternalBuild() && TestEnvironment.isTest();
    }

    @Nullable
    public ANRReportProvider getANRReportProvider() {
        return this.mANRReportProvider;
    }

    public long getDetectorStartTime() {
        return this.mDetectorStartTime;
    }

    public boolean isInForegroundV1() {
        return this.mInForegroundV1;
    }

    public ANRDetectorConfig getANRConfig() {
        return this.mANRConfig;
    }

    /* access modifiers changed from: protected */
    public boolean isDebuggerConnected() {
        return this.mANRConfig.isInternalBuild() && Debug.isDebuggerConnected();
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
    public void reportSoftError(String category, Throwable t) {
        if (this.mANRReportProvider != null) {
            this.mANRReportProvider.reportSoftError(category, t);
        }
    }

    /* access modifiers changed from: protected */
    public void logMainThreadUnblocked(long timestamp) {
        this.mANRConfig.getANRReport().logMainThreadUnblocked(timestamp);
    }

    /* access modifiers changed from: protected */
    public void startReport(@Nullable String logData, @Nullable String tracesFileName, @Nullable Long javaCallbackUptime, boolean tracesAreSigquitProduced) throws IOException {
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

    /* access modifiers changed from: protected */
    public void setInAnrStateOnAppStateUpdater() {
        if (this.mANRConfig.getAppStateUpdater() != null) {
        }
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void setANRReportProvider(ANRReportProvider ANRReportProvider) {
        this.mANRReportProvider = ANRReportProvider;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener listener) {
        this.mAnrDetectorListener = listener;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void start() {
        start(-1);
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void nativeLibraryLoaded(boolean shouldStart) {
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void setCheckIntervalMs(long checkIntervalMs) {
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
