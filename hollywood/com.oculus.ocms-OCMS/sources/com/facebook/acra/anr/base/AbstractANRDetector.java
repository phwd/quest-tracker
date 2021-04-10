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
    private static final String LOG_TAG = "AbstractANRDetector";
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

    @VisibleForTesting
    public abstract void notifyStateListeners(AnrState anrState);

    @Override // com.facebook.acra.anr.IANRDetector
    public void setCheckIntervalMs(long j) {
    }

    public abstract void start(long j);

    @Override // com.facebook.acra.anr.IANRDetector
    public abstract void stop(IANRDetector.ANRDetectorStopListener aNRDetectorStopListener);

    @VisibleForTesting
    public AbstractANRDetector(ANRDetectorConfig aNRDetectorConfig) {
        this.mANRConfig = aNRDetectorConfig;
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
        ANRReportProvider aNRReportProvider = this.mANRReportProvider;
        if (aNRReportProvider != null) {
            return aNRReportProvider.shouldCollectAndUploadANRReports();
        }
        return this.mANRConfig.getCachedShouldUploadANRReports();
    }

    /* access modifiers changed from: protected */
    public boolean shouldCollectAndUploadANRReportsNow() {
        return shouldCollectAndUploadANRReports() && (this.mInForegroundV2 || this.mInForegroundV1);
    }

    /* access modifiers changed from: protected */
    public void reportSoftError(String str, Throwable th) {
        ANRReportProvider aNRReportProvider = this.mANRReportProvider;
        if (aNRReportProvider != null) {
            aNRReportProvider.reportSoftError(str, th);
        }
    }

    /* access modifiers changed from: protected */
    public void logMainThreadUnblocked(long j) {
        this.mANRConfig.getANRReport().logMainThreadUnblocked(j);
    }

    /* access modifiers changed from: protected */
    public void startReport(@Nullable String str, @Nullable String str2, @Nullable Long l, boolean z) throws IOException {
        ANRDetectorListener aNRDetectorListener;
        String str3;
        String str4;
        synchronized (this) {
            aNRDetectorListener = this.mAnrDetectorListener;
        }
        if (aNRDetectorListener != null) {
            String blackBoxTraceId = aNRDetectorListener.getBlackBoxTraceId();
            String longStallTraceId = aNRDetectorListener.getLongStallTraceId();
            aNRDetectorListener.onStartANRDataCapture();
            str4 = blackBoxTraceId;
            str3 = longStallTraceId;
        } else {
            str4 = null;
            str3 = null;
        }
        collectStackTrace();
        this.mANRReportTime = SystemClock.uptimeMillis();
        this.mANRConfig.getANRReport().startReport(this.mANRReportProvider == null, str4, str3, this.mANRConfig.getDetectorId(), this.mInForegroundV1, this.mInForegroundV2, this.mANRReportTime, this.mDetectorStartTime, getReadyTime(), getSwitchTime(), str, str2, z, this.mANRConfig.shouldRecordSignalTime(), l);
        LooperHistoryHolder.dumpToLogCat(Looper.getMainLooper());
        if (aNRDetectorListener != null) {
            aNRDetectorListener.onEndANRDataCapture();
        }
    }

    /* access modifiers changed from: protected */
    public void anrHasEnded(boolean z) {
        if (z) {
            this.mANRConfig.getANRReport().finalizeAndTryToSendReport(SystemClock.uptimeMillis() - this.mANRReportTime);
        }
    }

    /* access modifiers changed from: protected */
    public void setInAnrStateOnAppStateUpdater() {
        this.mANRConfig.getAppStateUpdater();
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public void setANRReportProvider(ANRReportProvider aNRReportProvider) {
        this.mANRReportProvider = aNRReportProvider;
    }

    @Override // com.facebook.acra.anr.IANRDetector
    public synchronized void setListener(ANRDetectorListener aNRDetectorListener) {
        this.mAnrDetectorListener = aNRDetectorListener;
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
    public void setInAnrState(boolean z) {
        synchronized (this.mStateLock) {
            this.mInAnr = z;
        }
    }

    /* access modifiers changed from: protected */
    public void processMonitorStarted() {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.getANRReport().logProcessMonitorStart(SystemClock.uptimeMillis());
        }
    }

    /* access modifiers changed from: protected */
    public void processMonitorStopped(int i) {
        if (shouldCollectAndUploadANRReportsNow()) {
            this.mANRConfig.getANRReport().logProcessMonitorFailure(SystemClock.uptimeMillis(), i);
        }
    }
}
