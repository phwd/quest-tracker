package com.facebook.acra.anr;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.ipc.activity.ActivityConstants;
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
    public ANRDetectorConfig(Context context, String str, IANRReport iANRReport, @Nullable AppStateUpdater appStateUpdater, Handler handler, int i, boolean z) {
        this(context, str, iANRReport, appStateUpdater, handler, i, z, false, false, false, -1, false, false, ActivityConstants.Extras.WATCH_FEED_INJECTION, ActivityConstants.Extras.WATCH_FEED_INJECTION, ".", "anr", 0);
    }

    public ANRDetectorConfig(Context context, String str, IANRReport iANRReport, @Nullable AppStateUpdater appStateUpdater, Handler handler, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, boolean z6, String str2, String str3, @Nullable String str4, @Nullable String str5, int i3) {
        this(context, str, iANRReport, appStateUpdater, handler, i, z, z2, z3, z4, i2, z5, z6, str2, str3, str4, str5, i3, false);
    }

    public ANRDetectorConfig(Context context, String str, IANRReport iANRReport, @Nullable AppStateUpdater appStateUpdater, Handler handler, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, boolean z6, String str2, String str3, @Nullable String str4, @Nullable String str5, int i3, boolean z7) {
        this.mContext = context;
        this.mProcessName = str;
        this.mANRReport = iANRReport;
        this.mAppStateUpdater = appStateUpdater;
        this.mMainThreadHandler = handler;
        this.mDetectorId = i;
        this.mIsInternalBuild = z;
        this.mShouldReportSoftErrors = z2;
        this.mShouldLogOnSignalHandler = z3;
        this.mShouldAvoidMutexOnSignalHandler = z4;
        this.mRecoveryTimeout = i2;
        this.mShouldRecordSignalTime = z5;
        this.mCachedShouldUploadANRReports = z6;
        this.mAppVersionCode = str2;
        this.mAppVersionName = str3;
        this.mTracesPath = str4;
        this.mTracesExtension = str5;
        this.mForegroundCheckPeriod = i3;
        this.mIsLacrima = z7;
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

    public void setAppStateUpdater(AppStateUpdater appStateUpdater) {
        this.mAppStateUpdater = appStateUpdater;
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
        } catch (IOException unused) {
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
