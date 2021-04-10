package com.facebook.acra.anr;

import android.content.Context;
import android.os.Handler;

public class ANRDetectorConfig {
    private final IANRReport mANRReport;
    private AppStateUpdater mAppStateUpdater;
    private final String mAppVersionCode;
    private final String mAppVersionName;
    private final boolean mCachedShouldUploadANRReports;
    private final Context mContext;
    private final int mDetectorId;
    private final int mForegroundCheckPeriod;
    private final boolean mIsInternalBuild;
    private final boolean mIsLacrima;
    private final Handler mMainThreadHandler;
    private final String mProcessName;
    private final int mRecoveryTimeout;
    private final boolean mShouldAvoidMutexOnSignalHandler;
    private final boolean mShouldLogOnSignalHandler;
    private final boolean mShouldRecordSignalTime;
    private final boolean mShouldReportSoftErrors;
    private final String mTracesExtension;
    private final String mTracesPath;

    public ANRDetectorConfig(Context context, String processName, IANRReport anrReport, AppStateUpdater appStateUpdater, Handler mainThreadHandler, int detectorId, boolean isInternalBuild, boolean shouldReportSoftErrors, boolean shouldLogOnSignalHandler, boolean avoidMutexOnSignalHandler, int recoveryTimeout, boolean shouldRecordSignalTime, boolean cachedShouldUploadANRReports, String appVersionCode, String appVersionName, String tracesPath, String tracesExtension, int foregroundCheckPeriod) {
        this(context, processName, anrReport, appStateUpdater, mainThreadHandler, detectorId, isInternalBuild, shouldReportSoftErrors, shouldLogOnSignalHandler, avoidMutexOnSignalHandler, recoveryTimeout, shouldRecordSignalTime, cachedShouldUploadANRReports, appVersionCode, appVersionName, tracesPath, tracesExtension, foregroundCheckPeriod, false);
    }

    public ANRDetectorConfig(Context context, String processName, IANRReport anrReport, AppStateUpdater appStateUpdater, Handler mainThreadHandler, int detectorId, boolean isInternalBuild, boolean shouldReportSoftErrors, boolean shouldLogOnSignalHandler, boolean avoidMutexOnSignalHandler, int recoveryTimeout, boolean shouldRecordSignalTime, boolean cachedShouldUploadANRReports, String appVersionCode, String appVersionName, String tracesPath, String tracesExtension, int foregroundCheckPeriod, boolean isLacrima) {
        this.mContext = context;
        this.mProcessName = processName;
        this.mANRReport = anrReport;
        this.mAppStateUpdater = appStateUpdater;
        this.mMainThreadHandler = mainThreadHandler;
        this.mDetectorId = detectorId;
        this.mIsInternalBuild = isInternalBuild;
        this.mShouldReportSoftErrors = shouldReportSoftErrors;
        this.mShouldLogOnSignalHandler = shouldLogOnSignalHandler;
        this.mShouldAvoidMutexOnSignalHandler = avoidMutexOnSignalHandler;
        this.mRecoveryTimeout = recoveryTimeout;
        this.mShouldRecordSignalTime = shouldRecordSignalTime;
        this.mCachedShouldUploadANRReports = cachedShouldUploadANRReports;
        this.mAppVersionCode = appVersionCode;
        this.mAppVersionName = appVersionName;
        this.mTracesPath = tracesPath;
        this.mTracesExtension = tracesExtension;
        this.mForegroundCheckPeriod = foregroundCheckPeriod;
        this.mIsLacrima = isLacrima;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public IANRReport getANRReport() {
        return this.mANRReport;
    }

    public AppStateUpdater getAppStateUpdater() {
        return this.mAppStateUpdater;
    }

    public Handler getMainThreadHandler() {
        return this.mMainThreadHandler;
    }

    public int getDetectorId() {
        return this.mDetectorId;
    }

    public int getRecoveryTimeout() {
        return this.mRecoveryTimeout;
    }

    public boolean shouldRecordSignalTime() {
        return this.mShouldRecordSignalTime;
    }

    public boolean getCachedShouldUploadANRReports() {
        return this.mCachedShouldUploadANRReports;
    }
}
