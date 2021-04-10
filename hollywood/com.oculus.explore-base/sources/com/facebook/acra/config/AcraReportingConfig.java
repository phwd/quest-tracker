package com.facebook.acra.config;

import android.content.Context;
import com.facebook.acra.anr.ANRDetectorConfig;
import com.facebook.acra.anr.IANRDetector;
import com.facebook.acra.sender.FlexibleReportSender;
import java.lang.Thread;

public interface AcraReportingConfig {
    boolean allowCollectionOfMaxNumberOfLinesInLogcat();

    boolean allowProxy();

    boolean allowUnsafeConnectionsForDebugging();

    String crashReportUrl();

    IANRDetector createANRDetector(int i, ANRDetectorConfig aNRDetectorConfig, int i2);

    FlexibleReportSender createReportSender();

    Context getApplicationContext();

    Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler();

    int getMaxPendingAnrReports(int i);

    int getMaxPendingJavaCrashReports(int i);

    int getMaxPendingMiniDumpReports(int i);

    int getOomReservationOverride();

    long getPreallocatedFileSizeOverride();

    String getSessionId();

    StartupBlockingConfig getStartupBlockingConfig();

    String getUserAgent();

    boolean isInternalBuild();

    boolean isZeroCrashlogBlocked();

    String[] logcatArguments(boolean z);

    int reportSoftErrorsImmediately();

    boolean shouldImmediatelyUpload();

    boolean shouldInitReportSource();

    boolean shouldInstallPeriodicReporter();

    boolean shouldLazyFieldsOverwriteExistingValues();

    boolean shouldOnlyWriteReport();

    boolean shouldReportField(String str);

    boolean shouldSkipReportOnSocketTimeout();

    boolean shouldSkipSoftErrorReport();

    boolean shouldStartANRDetector();

    boolean shouldStopAnrDetectorOnErrorReporting();

    boolean shouldUseUploadService();

    int socketTimeout();

    boolean strictEnforceAcraDumpFileMax();
}
