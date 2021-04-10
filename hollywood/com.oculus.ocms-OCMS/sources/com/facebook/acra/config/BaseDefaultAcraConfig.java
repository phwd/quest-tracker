package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.anr.hybrid.HybridANRDetector;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import com.facebook.acra.anr.sigquit.detector.SigquitBasedANRDetector;
import com.facebook.acra.sender.FlexibleReportSender;
import java.lang.Thread;
import javax.annotation.Nullable;

public abstract class BaseDefaultAcraConfig implements AcraReportingConfig {
    private final Context mApplicationContext;
    private final String mCrashReportUrl;
    private final String mDefaultCrashReportUrl;
    private final Thread.UncaughtExceptionHandler mExceptionHandler;
    private final boolean mIsInternalBuild;
    private final boolean mIsZeroCrashlogBlocked;
    @Nullable
    private final String mSessionId;
    private final boolean mShouldStartANRDetector;

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean allowCollectionOfMaxNumberOfLinesInLogcat() {
        return false;
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
    public abstract FlexibleReportSender createReportSender();

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean enableLeanCrashReporting() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public long getAppBuildTimestamp() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getLogcatNumberOfLinesToCapture() {
        return "200";
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingAnrReports(int i) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingJavaCrashReports(int i) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getMaxPendingMiniDumpReports(int i) {
        return Integer.MAX_VALUE;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int getOomReservationOverride() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public long getPreallocatedFileSizeOverride() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    @Nullable
    public StartupBlockingConfig getStartupBlockingConfig() {
        return null;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String getUserAgent() {
        return "Android";
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int reportSoftErrorsImmediately() {
        return 0;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldImmediatelyUpload() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldInitReportSource() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldInstallPeriodicReporter() {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldLazyFieldsOverwriteExistingValues() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldOnlyWriteReport() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldReportField(String str) {
        return true;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldSkipReportOnSocketTimeout() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldSkipSoftErrorReport() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldStopAnrDetectorOnErrorReporting() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean shouldUseUploadService() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public int socketTimeout() {
        return 3000;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean strictEnforceAcraDumpFileMax() {
        return false;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public boolean usePinningSSLProvider() {
        return false;
    }

    public BaseDefaultAcraConfig(Context context, String str, boolean z) {
        this(context, str, z, false, false);
    }

    public BaseDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3, @Nullable String str2) {
        if (context == null) {
            throw new IllegalArgumentException("Application context cannot be null.");
        } else if (str != null) {
            this.mApplicationContext = context;
            this.mCrashReportUrl = str;
            this.mDefaultCrashReportUrl = str;
            this.mIsInternalBuild = z;
            this.mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            this.mShouldStartANRDetector = z2;
            this.mIsZeroCrashlogBlocked = z3;
            this.mSessionId = str2;
        } else {
            throw new IllegalArgumentException("Crash report url cannot be null.");
        }
    }

    public BaseDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3) {
        this(context, str, z, z2, z3, null);
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
    public String[] logcatArguments(boolean z) {
        return new String[]{"-t", z ? "10000" : getLogcatNumberOfLinesToCapture(), "-v", "threadtime"};
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public String crashReportUrl() {
        return this.mCrashReportUrl;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public Context getApplicationContext() {
        return this.mApplicationContext;
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
    public String getDefaultCrashLogUrl() {
        return this.mDefaultCrashReportUrl;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    @Nullable
    public String getSessionId() {
        return this.mSessionId;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    @Nullable
    public IANRDetector createANRDetector(int i, ANRDetectorConfig aNRDetectorConfig, int i2) {
        if (i == 5) {
            return ProcessErrorMonitorANRDetector.getInstance(aNRDetectorConfig, i2);
        }
        if (i == 3) {
            return SigquitBasedANRDetector.getInstance(aNRDetectorConfig);
        }
        if (i == 4) {
            return HybridANRDetector.getInstance(aNRDetectorConfig, i2);
        }
        return null;
    }
}
