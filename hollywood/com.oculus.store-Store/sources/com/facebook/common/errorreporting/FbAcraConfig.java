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

    public FbAcraConfig(Application application, String crashReportUrl, boolean isInternalBuild) {
        super(application, crashReportUrl, isInternalBuild);
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

    public FbAcraConfig(Application application, String crashReportUrl, boolean isInternalBuild, boolean shouldStartANRDetector) {
        this(application, crashReportUrl, 0, isInternalBuild, shouldStartANRDetector, false, false, false, false, null, null, 200, false, false, false, false, false, false);
    }

    public FbAcraConfig(Application application, String crashReportUrl, boolean isInternalBuild, boolean shouldStartANRDetector, boolean useMultipartPost, boolean allowAndroidLogCatNativeCrashes, boolean useZstd, boolean isZrCrashlogBlocked, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String sessionId, boolean shouldUseUploadService) {
        this(application, crashReportUrl, 0, isInternalBuild, shouldStartANRDetector, useMultipartPost, allowAndroidLogCatNativeCrashes, useZstd, isZrCrashlogBlocked, startupBlockingConfig, sessionId, 200, false, false, false, shouldUseUploadService, false, false);
    }

    public FbAcraConfig(Application application, String crashReportUrl, boolean isInternalBuild, boolean shouldStartANRDetector, boolean useMultipartPost, boolean allowAndroidLogCatNativeCrashes, boolean useZstd, boolean isZrCrashlogBlocked, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String sessionId, int logcatNumberOfLines, boolean allowMaxNumberOfLinesCollectedFromLogcat, boolean shouldStopAnrDetectorOnErrorReporting, boolean shouldSkipReportOnSocketTimeout, boolean shouldUseUploadService) {
        this(application, crashReportUrl, 0, isInternalBuild, shouldStartANRDetector, useMultipartPost, allowAndroidLogCatNativeCrashes, useZstd, isZrCrashlogBlocked, startupBlockingConfig, sessionId, logcatNumberOfLines, allowMaxNumberOfLinesCollectedFromLogcat, shouldStopAnrDetectorOnErrorReporting, shouldSkipReportOnSocketTimeout, shouldUseUploadService, false, false);
    }

    public FbAcraConfig(Application application, String crashReportUrl, long appBuildTimestamp, boolean isInternalBuild, boolean shouldStartANRDetector, boolean useMultipartPost, boolean allowAndroidLogCatNativeCrashes, boolean useZstd, boolean isZrCrashlogBlocked, @Nullable StartupBlockingConfig startupBlockingConfig, @Nullable String sessionId, int logcatNumberOfLines, boolean allowMaxNumberOfLinesCollectedFromLogcat, boolean shouldStopAnrDetectorOnErrorReporting, boolean shouldSkipReportOnSocketTimeout, boolean shouldUseUploadService, boolean shouldOnlyWriteReport, boolean shouldUsePinnedSSLProvider) {
        super(application, crashReportUrl, isInternalBuild, shouldStartANRDetector, isZrCrashlogBlocked, sessionId);
        this.mUseMultipartPost = false;
        this.mUseZstd = false;
        this.mAllowAndroidLogCatNativeCrashes = false;
        this.mIsCallingExternalProcsDuringErrorReportingDisabled = false;
        this.mStartupBlockingConfig = null;
        this.mUseMultipartPost = useMultipartPost;
        this.mAllowAndroidLogCatNativeCrashes = allowAndroidLogCatNativeCrashes;
        this.mUseZstd = useZstd;
        this.mStartupBlockingConfig = startupBlockingConfig;
        this.mLogcatNumberOfLines = logcatNumberOfLines;
        this.mAllowCollectionOfMaxNumberOfLinesInLogcat = allowMaxNumberOfLinesCollectedFromLogcat;
        this.mShouldStopAnrDetectorOnErrorReporting = shouldStopAnrDetectorOnErrorReporting;
        this.mShouldSkipReportOnSocketTimeout = shouldSkipReportOnSocketTimeout;
        this.mShouldUseUploadService = shouldUseUploadService;
        this.mShouldOnlyWriteReport = shouldOnlyWriteReport;
        this.mAppBuildTimestamp = appBuildTimestamp;
        this.mShouldUsePinnedSSLProvider = shouldUsePinnedSSLProvider;
    }

    public static Uri getErrorUploadURI(String appID) {
        return Uri.parse(URI_UPLOAD_CRASH).buildUpon().appendPath(appID).build();
    }

    public void setIsCallingExternalProcessesForCrashReportsDisabled(boolean isDisabled) {
        this.mIsCallingExternalProcsDuringErrorReportingDisabled = isDisabled;
    }

    public void setShouldLazyFieldsOverwriteExistingValues(boolean value) {
        this.mShouldLazyFieldsOverwriteExistingValues = value;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public FlexibleReportSender createReportSender() {
        HttpPostSender sender;
        if (this.mShouldUsePinnedSSLProvider) {
            sender = new HttpPinnedCertPostSender(this);
        } else {
            sender = new HttpPostSender(this);
        }
        sender.setUseMultipartPost(this.mUseMultipartPost);
        sender.setUseZstd(this.mUseZstd);
        return sender;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldReportField(String field) {
        if (this.mIsCallingExternalProcsDuringErrorReportingDisabled && field.equals(ReportField.DATA_FILE_LS_LR)) {
            return false;
        }
        if (this.mIsCallingExternalProcsDuringErrorReportingDisabled && field.equals(ReportField.OPEN_FILE_DESCRIPTORS)) {
            return false;
        }
        if (field.equals(ReportField.LOGCAT_NATIVE)) {
            return this.mAllowAndroidLogCatNativeCrashes;
        }
        if (field.equals(ReportField.COMPONENTS_TOTAL) || field.equals(ReportField.COMPONENTS_DEFAULT) || field.equals(ReportField.COMPONENTS_DISABLED) || field.equals(ReportField.COMPONENTS_ENABLED) || field.equals(ReportField.COMPONENTS_DEFAULT_NAMES) || field.equals(ReportField.COMPONENTS_DISABLED_NAMES) || field.equals(ReportField.COMPONENTS_FLAG_STATE)) {
            return AppComponentConstants.isPreTosBuild();
        }
        return super.shouldReportField(field);
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    @Nullable
    public StartupBlockingConfig getStartupBlockingConfig() {
        return this.mStartupBlockingConfig;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.acra.config.DefaultAcraConfig
    public String getLogcatNumberOfLinesToCapture() {
        return String.valueOf(this.mLogcatNumberOfLines);
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean allowCollectionOfMaxNumberOfLinesInLogcat() {
        return this.mAllowCollectionOfMaxNumberOfLinesInLogcat;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldStopAnrDetectorOnErrorReporting() {
        return this.mShouldStopAnrDetectorOnErrorReporting;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldSkipReportOnSocketTimeout() {
        return this.mShouldSkipReportOnSocketTimeout;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldUseUploadService() {
        return this.mShouldUseUploadService;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldOnlyWriteReport() {
        return this.mShouldOnlyWriteReport;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean shouldLazyFieldsOverwriteExistingValues() {
        return this.mShouldLazyFieldsOverwriteExistingValues;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public long getAppBuildTimestamp() {
        return this.mAppBuildTimestamp;
    }

    @Override // com.facebook.acra.config.DefaultAcraConfig, com.facebook.acra.config.AcraReportingConfig
    public boolean usePinningSSLProvider() {
        return this.mShouldUsePinnedSSLProvider;
    }
}
