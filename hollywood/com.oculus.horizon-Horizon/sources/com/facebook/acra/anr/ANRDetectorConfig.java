package com.facebook.acra.anr;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ANRDetectorConfig {
    public final IANRReport mANRReport;
    @Nullable
    public AppStateUpdater mAppStateUpdater;
    public final String mAppVersionCode;
    public final String mAppVersionName;
    public final boolean mCachedShouldUploadANRReports;
    public final Context mContext;
    public final int mDetectorId;
    public final int mForegroundCheckPeriod;
    public final boolean mIsInternalBuild;
    public final boolean mIsLacrima;
    public final Handler mMainThreadHandler;
    public final String mProcessName;
    public final int mRecoveryTimeout;
    public final boolean mShouldAvoidMutexOnSignalHandler;
    public final boolean mShouldLogOnSignalHandler;
    public final boolean mShouldRecordSignalTime;
    public final boolean mShouldReportSoftErrors;
    @Nullable
    public final String mTracesExtension;
    @Nullable
    public final String mTracesPath;

    public File getSigquitTimeDir() {
        return this.mContext.getDir("sigquit", 0);
    }

    public boolean processShouldSendAnrReports() {
        if (!this.mProcessName.contains(":") || this.mProcessName.endsWith(":browser")) {
            return true;
        }
        return false;
    }

    public IANRReport getANRReport() {
        return this.mANRReport;
    }

    @Nullable
    public AppStateUpdater getAppStateUpdater() {
        return this.mAppStateUpdater;
    }

    public String getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public boolean getCachedShouldUploadANRReports() {
        return this.mCachedShouldUploadANRReports;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getDetectorId() {
        return this.mDetectorId;
    }

    public int getForegroundCheckPeriod() {
        return this.mForegroundCheckPeriod;
    }

    public Handler getMainThreadHandler() {
        return this.mMainThreadHandler;
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    public int getRecoveryTimeout() {
        return this.mRecoveryTimeout;
    }

    @Nullable
    public String getSigquitTimeFilePath() {
        try {
            return new File(getSigquitTimeDir(), this.mProcessName.replace('.', '_').replace(':', '_')).getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    @Nullable
    public String getTracesExtension() {
        return this.mTracesExtension;
    }

    @Nullable
    public String getTracesPath() {
        return this.mTracesPath;
    }

    public boolean isInternalBuild() {
        return this.mIsInternalBuild;
    }

    public boolean isLacrima() {
        return this.mIsLacrima;
    }

    public boolean shouldAvoidMutexOnSignalHandler() {
        return this.mShouldAvoidMutexOnSignalHandler;
    }

    public boolean shouldLogOnSignalHandler() {
        return this.mShouldLogOnSignalHandler;
    }

    public boolean shouldRecordSignalTime() {
        return this.mShouldRecordSignalTime;
    }

    public boolean shouldReportSoftErrors() {
        return this.mShouldReportSoftErrors;
    }

    public void setAppStateUpdater(AppStateUpdater appStateUpdater) {
        this.mAppStateUpdater = appStateUpdater;
    }

    @VisibleForTesting
    public ANRDetectorConfig(Context context, String str, IANRReport iANRReport, @Nullable AppStateUpdater appStateUpdater, Handler handler, int i, boolean z) {
        this(context, str, iANRReport, appStateUpdater, handler, i, z, false, false, false, -1, false, false, DiskLruCache.VERSION_1, DiskLruCache.VERSION_1, ".", "anr", 0);
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
}
