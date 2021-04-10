package com.facebook.common.errorreporting;

import android.app.Application;
import android.net.Uri;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.pinnedcertsender.HttpPinnedCertPostSender;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.HttpPostSender;
import com.facebook.appcomponentmanager.AppComponentConstants;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FbAcraConfig extends DefaultAcraConfig {
    private static final int DEFAULT_NUMBER_OF_LOGCAT_LINES_TO_READ = 200;
    public static final String URI_UPLOAD_CRASH = "https://www.facebook.com/mobile/generic_android_crash_logs/";
    private boolean mAllowAndroidLogCatNativeCrashes;
    private final boolean mAllowCollectionOfMaxNumberOfLinesInLogcat;
    private final long mAppBuildTimestamp;
    private boolean mIsCallingExternalProcsDuringErrorReportingDisabled;
    private final int mLogcatNumberOfLines;
    private boolean mShouldLazyFieldsOverwriteExistingValues;
    private final boolean mShouldOnlyWriteReport;
    private final boolean mShouldSkipReportOnSocketTimeout;
    private final boolean mShouldStopAnrDetectorOnErrorReporting;
    private final boolean mShouldUsePinnedSSLProvider;
    private final boolean mShouldUseUploadService;
    @Nullable
    private StartupBlockingConfig mStartupBlockingConfig;
    private boolean mUseMultipartPost;
    private boolean mUseZstd;

    public FbAcraConfig(Application application, String str, boolean z) {
        super(application, str, z);
        this.mUseMultipartPost = false;
        this.mUseZstd = false;
        this.mAllowAndroidLogCatNativeCrashes = false;
        this.mIsCallingExternalProcsDuringErrorReportingDisabled = false;
        this.mStartupBlockingConfig = null;
        this.mLogcatNumberOfLines = 200;
        this.mAllowCollectionOfMaxNumberOfLinesInLogcat = false;
        this.mShouldStopAnrDetectorOnErrorReporting = false;
        this.mShouldSkipReportOnSocketTimeout = false;
        this.mShouldUseUploadService = false;
        this.mShouldOnlyWriteReport = false;
        this.mAppBuildTimestamp = 0;
        this.mShouldUsePinnedSSLProvider = false;
    }

    public FbAcraConfig(Application application, String str, boolean z, boolean z2) {
        this(application, str, 0, z, z2, false, false, false, false, null, null, 200, false, false, false, false, false, false);
    }

    public FbAcraConfig(Application application, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String str2, boolean z7) {
        this(application, str, 0, z, z2, z3, z4, z5, z6, startupBlockingConfig, str2, 200, false, false, false, z7, false, false);
    }

    public FbAcraConfig(Application application, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String str2, int i, boolean z7, boolean z8, boolean z9, boolean z10) {
        this(application, str, 0, z, z2, z3, z4, z5, z6, startupBlockingConfig, str2, i, z7, z8, z9, z10, false, false);
    }

    public FbAcraConfig(Application application, String str, long j, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String str2, int i, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        super(application, str, z, z2, z6, str2);
        this.mUseMultipartPost = false;
        this.mUseZstd = false;
        this.mAllowAndroidLogCatNativeCrashes = false;
        this.mIsCallingExternalProcsDuringErrorReportingDisabled = false;
        this.mStartupBlockingConfig = null;
        this.mUseMultipartPost = z3;
        this.mAllowAndroidLogCatNativeCrashes = z4;
        this.mUseZstd = z5;
        this.mStartupBlockingConfig = startupBlockingConfig;
        this.mLogcatNumberOfLines = i;
        this.mAllowCollectionOfMaxNumberOfLinesInLogcat = z7;
        this.mShouldStopAnrDetectorOnErrorReporting = z8;
        this.mShouldSkipReportOnSocketTimeout = z9;
        this.mShouldUseUploadService = z10;
        this.mShouldOnlyWriteReport = z11;
        this.mAppBuildTimestamp = j;
        this.mShouldUsePinnedSSLProvider = z12;
    }

    public static Uri getErrorUploadURI(String str) {
        return Uri.parse(URI_UPLOAD_CRASH).buildUpon().appendPath(str).build();
    }

    public void setIsCallingExternalProcessesForCrashReportsDisabled(boolean z) {
        this.mIsCallingExternalProcsDuringErrorReportingDisabled = z;
    }

    public void setShouldLazyFieldsOverwriteExistingValues(boolean z) {
        this.mShouldLazyFieldsOverwriteExistingValues = z;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public FlexibleReportSender createReportSender() {
        HttpPostSender httpPostSender;
        if (this.mShouldUsePinnedSSLProvider) {
            httpPostSender = new HttpPinnedCertPostSender(this);
        } else {
            httpPostSender = new HttpPostSender(this);
        }
        httpPostSender.setUseMultipartPost(this.mUseMultipartPost);
        httpPostSender.setUseZstd(this.mUseZstd);
        return httpPostSender;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldReportField(String str) {
        if (this.mIsCallingExternalProcsDuringErrorReportingDisabled && str.equals(ReportField.DATA_FILE_LS_LR)) {
            return false;
        }
        if (this.mIsCallingExternalProcsDuringErrorReportingDisabled && str.equals(ReportField.OPEN_FILE_DESCRIPTORS)) {
            return false;
        }
        if (str.equals(ReportField.LOGCAT_NATIVE)) {
            return this.mAllowAndroidLogCatNativeCrashes;
        }
        if (str.equals(ReportField.COMPONENTS_TOTAL) || str.equals(ReportField.COMPONENTS_DEFAULT) || str.equals(ReportField.COMPONENTS_DISABLED) || str.equals(ReportField.COMPONENTS_ENABLED) || str.equals(ReportField.COMPONENTS_DEFAULT_NAMES) || str.equals(ReportField.COMPONENTS_DISABLED_NAMES) || str.equals(ReportField.COMPONENTS_FLAG_STATE)) {
            return AppComponentConstants.isPreTosBuild();
        }
        return super.shouldReportField(str);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    @Nullable
    public StartupBlockingConfig getStartupBlockingConfig() {
        return this.mStartupBlockingConfig;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.config.BaseDefaultAcraConfig
    public String getLogcatNumberOfLinesToCapture() {
        return String.valueOf(this.mLogcatNumberOfLines);
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean allowCollectionOfMaxNumberOfLinesInLogcat() {
        return this.mAllowCollectionOfMaxNumberOfLinesInLogcat;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldStopAnrDetectorOnErrorReporting() {
        return this.mShouldStopAnrDetectorOnErrorReporting;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldSkipReportOnSocketTimeout() {
        return this.mShouldSkipReportOnSocketTimeout;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldUseUploadService() {
        return this.mShouldUseUploadService;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldOnlyWriteReport() {
        return this.mShouldOnlyWriteReport;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean shouldLazyFieldsOverwriteExistingValues() {
        return this.mShouldLazyFieldsOverwriteExistingValues;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public long getAppBuildTimestamp() {
        return this.mAppBuildTimestamp;
    }

    @Override // com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.config.BaseDefaultAcraConfig
    public boolean usePinningSSLProvider() {
        return this.mShouldUsePinnedSSLProvider;
    }
}
