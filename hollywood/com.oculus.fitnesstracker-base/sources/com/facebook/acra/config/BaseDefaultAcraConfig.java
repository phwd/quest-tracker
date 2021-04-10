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

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final String getUserAgent() {
        return "Android";
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final String[] logcatArguments(boolean z) {
        return new String[]{"-t", z ? "10000" : "200", "-v", "threadtime"};
    }

    public BaseDefaultAcraConfig(Context context, String str, boolean z) {
        this(context, str, z, false, false);
    }

    private BaseDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3, String str2) {
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
            this.mSessionId = null;
        } else {
            throw new IllegalArgumentException("Crash report url cannot be null.");
        }
    }

    private BaseDefaultAcraConfig(Context context, String str, boolean z, boolean z2, boolean z3) {
        this(context, str, z, false, false, null);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final boolean isInternalBuild() {
        return this.mIsInternalBuild;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final String crashReportUrl() {
        return this.mCrashReportUrl;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final boolean shouldStartANRDetector() {
        return this.mShouldStartANRDetector;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final boolean isZeroCrashlogBlocked() {
        return this.mIsZeroCrashlogBlocked;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final String getSessionId() {
        return this.mSessionId;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig
    public final IANRDetector createANRDetector(int i, ANRDetectorConfig aNRDetectorConfig, int i2) {
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
