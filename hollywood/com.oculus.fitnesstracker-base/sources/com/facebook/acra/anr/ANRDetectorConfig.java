package com.facebook.acra.anr;

import android.content.Context;
import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ANRDetectorConfig {
    public final IANRReport mANRReport;
    public AppStateUpdater mAppStateUpdater;
    private final String mAppVersionCode;
    private final String mAppVersionName;
    public final boolean mCachedShouldUploadANRReports;
    public final Context mContext;
    public final int mDetectorId;
    private final int mForegroundCheckPeriod;
    public final boolean mIsInternalBuild;
    private final boolean mIsLacrima;
    public final Handler mMainThreadHandler;
    public final String mProcessName;
    public final int mRecoveryTimeout;
    private final boolean mShouldAvoidMutexOnSignalHandler;
    private final boolean mShouldLogOnSignalHandler;
    public final boolean mShouldRecordSignalTime;
    private final boolean mShouldReportSoftErrors;
    private final String mTracesExtension;
    private final String mTracesPath;

    public ANRDetectorConfig(Context context, String str, IANRReport iANRReport, AppStateUpdater appStateUpdater, Handler handler, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, boolean z6, String str2, String str3, String str4, String str5, int i3) {
        this(context, str, iANRReport, appStateUpdater, handler, i, z, z2, z3, z4, i2, z5, z6, str2, str3, str4, str5, i3, false);
    }

    private ANRDetectorConfig(Context context, String str, IANRReport iANRReport, AppStateUpdater appStateUpdater, Handler handler, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, boolean z5, boolean z6, String str2, String str3, String str4, String str5, int i3, boolean z7) {
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
        this.mIsLacrima = false;
    }

    public final String getSigquitTimeFilePath() {
        try {
            return new File(this.mContext.getDir("sigquit", 0), this.mProcessName.replace('.', '_').replace(':', '_')).getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }
}
