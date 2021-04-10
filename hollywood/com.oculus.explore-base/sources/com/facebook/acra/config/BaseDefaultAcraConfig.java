package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.hybrid.HybridANRDetector;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector;
import java.lang.Thread;

public abstract class BaseDefaultAcraConfig implements AcraReportingConfig {
    private final Context mApplicationContext;
    private final String mCrashReportUrl;
    private final String mDefaultCrashReportUrl;
    private final Thread.UncaughtExceptionHandler mExceptionHandler;
    private final boolean mIsInternalBuild;
    private final boolean mIsZeroCrashlogBlocked;
    private final String mSessionId;
    private final boolean mShouldStartANRDetector;

    public BaseDefaultAcraConfig(Context applicationContext, String crashReportUrl, boolean isInternalBuild) {
        this(applicationContext, crashReportUrl, isInternalBuild, false, false);
    }

    public BaseDefaultAcraConfig(Context applicationContext, String crashReportUrl, boolean isInternalBuild, boolean shouldStartANRDetector, boolean isZeroCrashlogBlocked, String sessionId) {
        if (applicationContext == null) {
            throw new IllegalArgumentException("Application context cannot be null.");
        } else if (crashReportUrl == null) {
            throw new IllegalArgumentException("Crash report url cannot be null.");
        } else {
            this.mApplicationContext = applicationContext;
            this.mCrashReportUrl = crashReportUrl;
            this.mDefaultCrashReportUrl = crashReportUrl;
            this.mIsInternalBuild = isInternalBuild;
            this.mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            this.mShouldStartANRDetector = shouldStartANRDetector;
            this.mIsZeroCrashlogBlocked = isZeroCrashlogBlocked;
            this.mSessionId = sessionId;
        }
    }

    public BaseDefaultAcraConfig(Context applicationContext, String crashReportUrl, boolean isInternalBuild, boolean shouldStartANRDetector, boolean isZeroCrashlogBlocked) {
        this(applicationContext, crashReportUrl, isInternalBuild, shouldStartANRDetector, isZeroCrashlogBlocked, null);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return this.mExceptionHandler;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean isInternalBuild() {
        return this.mIsInternalBuild;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String[] logcatArguments(boolean maxNumLines) {
        String numLines;
        if (maxNumLines) {
            numLines = "10000";
        } else {
            numLines = getLogcatNumberOfLinesToCapture();
        }
        return new String[]{"-t", numLines, "-v", "threadtime"};
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String crashReportUrl() {
        return this.mCrashReportUrl;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldReportField(String fieldName) {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean allowProxy() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean allowUnsafeConnectionsForDebugging() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int socketTimeout() {
        return 3000;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String getUserAgent() {
        return "Android";
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldStartANRDetector() {
        return this.mShouldStartANRDetector;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean isZeroCrashlogBlocked() {
        return this.mIsZeroCrashlogBlocked;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public long getPreallocatedFileSizeOverride() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getOomReservationOverride() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingJavaCrashReports(int currentPendingReports) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingMiniDumpReports(int currentPendingReports) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingAnrReports(int currentPendingReports) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldImmediatelyUpload() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldInstallPeriodicReporter() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public StartupBlockingConfig getStartupBlockingConfig() {
        return null;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String getSessionId() {
        return this.mSessionId;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean allowCollectionOfMaxNumberOfLinesInLogcat() {
        return false;
    }

    /* access modifiers changed from: protected */
    public String getLogcatNumberOfLinesToCapture() {
        return "200";
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldStopAnrDetectorOnErrorReporting() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldSkipReportOnSocketTimeout() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldUseUploadService() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldOnlyWriteReport() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldSkipSoftErrorReport() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldLazyFieldsOverwriteExistingValues() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public IANRDetector createANRDetector(int anrDetectorId, ANRDetectorConfig anrDetectorConfig, int errorMonitorCheckInterval) {
        if (anrDetectorId == 5) {
            return ProcessErrorMonitorANRDetector.getInstance(anrDetectorConfig, errorMonitorCheckInterval);
        }
        if (anrDetectorId == 3) {
            return SigquitBasedANRDetector.getInstance(anrDetectorConfig);
        }
        if (anrDetectorId == 4) {
            return HybridANRDetector.getInstance(anrDetectorConfig, errorMonitorCheckInterval);
        }
        return null;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int reportSoftErrorsImmediately() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean strictEnforceAcraDumpFileMax() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldInitReportSource() {
        return true;
    }
}
