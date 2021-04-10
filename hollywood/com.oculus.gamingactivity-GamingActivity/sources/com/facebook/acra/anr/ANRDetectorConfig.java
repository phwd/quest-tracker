package com.facebook.acra.anr;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ANRDetectorConfig {
    private final IANRReport mANRReport;
    @Nullable
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
    @Nullable
    private final String mTracesExtension;
    @Nullable
    private final String mTracesPath;

    @VisibleForTesting
    public ANRDetectorConfig(Context context, String processName, IANRReport anrReport, @Nullable AppStateUpdater appStateUpdater, Handler mainThreadHandler, int detectorId, boolean isInternalBuild) {
        this(context, processName, anrReport, appStateUpdater, mainThreadHandler, detectorId, isInternalBuild, false, false, false, -1, false, false, "1", "1", ".", "anr", 0);
    }

    public ANRDetectorConfig(Context context, String processName, IANRReport anrReport, @Nullable AppStateUpdater appStateUpdater, Handler mainThreadHandler, int detectorId, boolean isInternalBuild, boolean shouldReportSoftErrors, boolean shouldLogOnSignalHandler, boolean avoidMutexOnSignalHandler, int recoveryTimeout, boolean shouldRecordSignalTime, boolean cachedShouldUploadANRReports, String appVersionCode, String appVersionName, @Nullable String tracesPath, @Nullable String tracesExtension, int foregroundCheckPeriod) {
        this(context, processName, anrReport, appStateUpdater, mainThreadHandler, detectorId, isInternalBuild, shouldReportSoftErrors, shouldLogOnSignalHandler, avoidMutexOnSignalHandler, recoveryTimeout, shouldRecordSignalTime, cachedShouldUploadANRReports, appVersionCode, appVersionName, tracesPath, tracesExtension, foregroundCheckPeriod, false);
    }

    public ANRDetectorConfig(Context context, String processName, IANRReport anrReport, @Nullable AppStateUpdater appStateUpdater, Handler mainThreadHandler, int detectorId, boolean isInternalBuild, boolean shouldReportSoftErrors, boolean shouldLogOnSignalHandler, boolean avoidMutexOnSignalHandler, int recoveryTimeout, boolean shouldRecordSignalTime, boolean cachedShouldUploadANRReports, String appVersionCode, String appVersionName, @Nullable String tracesPath, @Nullable String tracesExtension, int foregroundCheckPeriod, boolean isLacrima) {
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

    @Nullable
    public AppStateUpdater getAppStateUpdater() {
        return this.mAppStateUpdater;
    }

    public Handler getMainThreadHandler() {
        return this.mMainThreadHandler;
    }

    public int getDetectorId() {
        return this.mDetectorId;
    }

    public boolean isInternalBuild() {
        return this.mIsInternalBuild;
    }

    public void setAppStateUpdater(AppStateUpdater updater) {
        this.mAppStateUpdater = updater;
    }

    public boolean shouldReportSoftErrors() {
        return this.mShouldReportSoftErrors;
    }

    public boolean shouldLogOnSignalHandler() {
        return this.mShouldLogOnSignalHandler;
    }

    public boolean shouldAvoidMutexOnSignalHandler() {
        return this.mShouldAvoidMutexOnSignalHandler;
    }

    public int getRecoveryTimeout() {
        return this.mRecoveryTimeout;
    }

    public boolean shouldRecordSignalTime() {
        return this.mShouldRecordSignalTime;
    }

    public File getSigquitTimeDir() {
        return this.mContext.getDir("sigquit", 0);
    }

    public boolean processShouldSendAnrReports() {
        return !this.mProcessName.contains(":") || this.mProcessName.endsWith(":browser");
    }

    @Nullable
    public String getSigquitTimeFilePath() {
        try {
            return new File(getSigquitTimeDir(), this.mProcessName.replace('.', '_').replace(':', '_')).getCanonicalPath();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean getCachedShouldUploadANRReports() {
        return this.mCachedShouldUploadANRReports;
    }

    public String getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    @Nullable
    public String getTracesPath() {
        return this.mTracesPath;
    }

    @Nullable
    public String getTracesExtension() {
        return this.mTracesExtension;
    }

    public int getForegroundCheckPeriod() {
        return this.mForegroundCheckPeriod;
    }

    public boolean isLacrima() {
        return this.mIsLacrima;
    }
}
