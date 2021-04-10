package com.facebook.common.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.mobilesofterror.intf.HasSoftErrorSkipList;
import com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.systrace.Systrace;
import com.facebook.testenv.TestEnvironment;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
@ThreadSafe
public class FbErrorReporterImpl extends AbstractFbErrorReporter implements HasSoftErrorSkipList {
    static volatile boolean DISABLE_OVERRIDE = TestEnvironment.isTest();
    private static final String FILE_NAME = "soft_errors_pref";
    private static final String TAG = FbErrorReporterImpl.class.getSimpleName();
    private static final int UPLOAD_INTERVAL_IN_SECS = 120;
    private static final int UPLOAD_TRIGGER_THRESHOLD = 100;
    public static final Provider<ErrorReporter> sErrorReporterProvider = new ErrorReporterProvider();
    private final MonotonicClock mClock;
    @Nullable
    private final Context mContext;
    private final Provider<ErrorReporter> mErrorReporterProvider;
    @Nullable
    private final ErrorReportingGkReader mErrorReportingGkReader;
    private final ExecutorService mExecutorService;
    private final Provider<Boolean> mIsInternalBuildProvider;
    private final Provider<TriState> mIsMeUserFbEmployeeProvider;
    private Boolean mIsNonEmployeeModeEnabled;
    private final Map<String, ArrayList<Long>> mObservedSoftErrorsMap;
    @Nullable
    private volatile ErrorReporterQplBridge mQplBridge;
    private final Random mRandom;
    @Nullable
    private volatile SoftErrorCategoryBlacklist mSoftErrorCategoryBlacklist;
    @Nullable
    private volatile SoftErrorReportingFrequency mSoftErrorReportingFrequency;
    private final boolean mTestingMode;

    private static class ErrorReporterProvider implements Provider<ErrorReporter> {
        private ErrorReporterProvider() {
        }

        @Override // javax.inject.Provider
        public ErrorReporter get() {
            return ErrorReporter.getInstance();
        }
    }

    public FbErrorReporterImpl(Provider<TriState> isMeUserFbEmployeeProvider, Provider<Boolean> isInternalBuildProvider, ExecutorService executorService, MonotonicClock monotonicClock, Random random) {
        this(isMeUserFbEmployeeProvider, isInternalBuildProvider, executorService, monotonicClock, random, null, null, sErrorReporterProvider, false);
    }

    FbErrorReporterImpl(Provider<TriState> isMeUserFbEmployeeProvider, Provider<Boolean> isInternalBuildProvider, ExecutorService executorService, MonotonicClock monotonicClock, Random random, @Nullable Context context, @Nullable ErrorReportingGkReader gkReader) {
        this(isMeUserFbEmployeeProvider, isInternalBuildProvider, executorService, monotonicClock, random, context, gkReader, sErrorReporterProvider, false);
    }

    FbErrorReporterImpl(Provider<TriState> isMeUserFbEmployeeProvider, Provider<Boolean> isInternalBuildProvider, ExecutorService executorService, MonotonicClock monotonicClock, Random random, @Nullable Context context, @Nullable ErrorReportingGkReader gkReader, Provider<ErrorReporter> errorReporterProvider, boolean testingMode) {
        this.mIsNonEmployeeModeEnabled = null;
        this.mIsMeUserFbEmployeeProvider = isMeUserFbEmployeeProvider;
        this.mIsInternalBuildProvider = isInternalBuildProvider;
        this.mExecutorService = executorService;
        this.mClock = monotonicClock;
        this.mRandom = random;
        this.mContext = context;
        this.mErrorReporterProvider = errorReporterProvider;
        this.mErrorReportingGkReader = gkReader;
        this.mTestingMode = testingMode;
        this.mObservedSoftErrorsMap = new HashMap();
        this.mSoftErrorCategoryBlacklist = null;
        this.mSoftErrorReportingFrequency = null;
    }

    @Override // com.facebook.common.mobilesofterror.intf.HasSoftErrorSkipList
    public void setSoftErrorCategorySkipList(SoftErrorCategoryBlacklist list) {
        this.mSoftErrorCategoryBlacklist = list;
    }

    public void setSoftErrorDefaultFrequency(SoftErrorReportingFrequency reportingFrequency) {
        BLog.i(TAG, "Setting soft error default reporting frequency %s as defined by the client app module", Integer.valueOf(reportingFrequency.getDefaultReportingFrequency()));
        this.mSoftErrorReportingFrequency = reportingFrequency;
    }

    public void setQplBridge(ErrorReporterQplBridge qplBridge) {
        this.mQplBridge = qplBridge;
    }

    public static void setDisableOverride(boolean value) {
        DISABLE_OVERRIDE = value;
    }

    public boolean setSoftErrorsPref(boolean isEnabled) {
        int i;
        if (this.mContext == null) {
            return false;
        }
        FileOutputStream outputStream = null;
        try {
            FileOutputStream outputStream2 = this.mContext.openFileOutput(FILE_NAME, 0);
            if (isEnabled) {
                i = 1;
            } else {
                i = 0;
            }
            outputStream2.write(i);
            if (outputStream2 != null) {
                try {
                    outputStream2.close();
                } catch (IOException e) {
                }
            }
            return true;
        } catch (IOException e2) {
            if (0 == 0) {
                return false;
            }
            try {
                outputStream.close();
                return false;
            } catch (IOException e3) {
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    outputStream.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError) {
        softReport(softError, (ExternalProcessInfo) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        r2 = samplingFrequencyCategoryModifier(r9);
     */
    @Override // com.facebook.common.errorreporting.FbErrorReporter, com.facebook.common.errorreporting.AbstractFbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReport(com.facebook.common.errorreporting.SoftError r9, @javax.annotation.Nullable final com.facebook.acra.info.ExternalProcessInfo r10) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReport(com.facebook.common.errorreporting.SoftError, com.facebook.acra.info.ExternalProcessInfo):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        r2 = samplingFrequencyCategoryModifier(r8);
     */
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReportDelayed(com.facebook.common.errorreporting.SoftError r8) {
        /*
            r7 = this;
            r7.initIsNonEmployeeModeEnabled()
            com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist r4 = r7.mSoftErrorCategoryBlacklist
            if (r4 == 0) goto L_0x001f
            com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist r4 = r7.mSoftErrorCategoryBlacklist
            java.lang.String r5 = r8.getCategory()
            boolean r4 = r4.isCategoryBlacklisted(r5)
            if (r4 == 0) goto L_0x001f
            java.lang.String r4 = com.facebook.common.errorreporting.FbErrorReporterImpl.TAG
            java.lang.String r5 = "Not logging because Soft Error category %s is blacklisted"
            java.lang.String r6 = r8.getCategory()
            com.facebook.debug.log.BLog.i(r4, r5, r6)
        L_0x001e:
            return
        L_0x001f:
            com.facebook.common.errorreporting.SoftError r8 = r7.applySamplingFreqOverride(r8)
            com.facebook.common.errorreporting.ErrorReporterQplBridge r4 = r7.mQplBridge
            if (r4 == 0) goto L_0x0034
            com.facebook.common.errorreporting.ErrorReporterQplBridge r4 = r7.mQplBridge
            java.lang.String r5 = r8.getCategory()
            java.lang.String r6 = r8.getMessage()
            r4.logSoftError(r5, r6)
        L_0x0034:
            java.lang.Integer r1 = r7.prepSoftReport(r8)
            if (r1 == 0) goto L_0x001e
            java.lang.String r2 = r7.samplingFrequencyCategoryModifier(r8)
            if (r2 == 0) goto L_0x001e
            java.lang.String r0 = r8.getMessage()
            com.facebook.common.errorreporting.SoftErrorException r3 = new com.facebook.common.errorreporting.SoftErrorException
            java.lang.Throwable r4 = r8.getCause()
            r3.<init>(r0, r4)
            java.util.concurrent.ExecutorService r4 = r7.mExecutorService
            com.facebook.common.errorreporting.FbErrorReporterImpl$2 r5 = new com.facebook.common.errorreporting.FbErrorReporterImpl$2
            r5.<init>(r2, r0, r3)
            r4.execute(r5)
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReportDelayed(com.facebook.common.errorreporting.SoftError):void");
    }

    private SoftError applySamplingFreqOverride(SoftError softError) {
        if (softError.getSamplingFrequency() != 1000 || this.mSoftErrorReportingFrequency == null) {
            return softError;
        }
        return SoftError.newError(softError, this.mSoftErrorReportingFrequency.getDefaultReportingFrequency());
    }

    @Nullable
    private Integer prepSoftReport(SoftError softError) {
        if (DISABLE_OVERRIDE) {
            return null;
        }
        boolean always = this.mIsMeUserFbEmployeeProvider.get() == TriState.YES || this.mIsInternalBuildProvider.get().booleanValue();
        if (softError.shouldFailHarder() && always) {
            if (!isFailHarderFatalDisabled()) {
                failHarder(softError);
                return null;
            } else if (this.mContext != null) {
                new Handler(Looper.getMainLooper()).post(new ShowToast(softError, this.mContext));
            }
        }
        Integer occurrenceCount = countIfShouldUploadSoftError(softError);
        if (always && occurrenceCount == null) {
            return null;
        }
        if (!always) {
            occurrenceCount = Integer.valueOf(softError.getSamplingFrequency());
        }
        if (!Systrace.isTracing(256)) {
            return occurrenceCount;
        }
        Systrace.traceInstant(256, "softReport category: " + softError.getCategory() + " message: " + softError.getMessage(), Systrace.EventScope.THREAD);
        return occurrenceCount;
    }

    private boolean isFailHarderFatalDisabled() {
        return this.mErrorReportingGkReader != null && this.mErrorReportingGkReader.isFailHarderFatalDisabled() == TriState.YES;
    }

    private void failHarder(SoftError softError) {
        ErrorReporter.putCustomData(ErrorReportingConstants.SOFT_ERROR_MESSAGE, softError.getMessage());
        BLog.e(TAG, "category: %s message: %s", softError.getCategory(), softError.getMessage());
        this.mErrorReporterProvider.get().reportErrorAndTerminate(Thread.currentThread(), new RuntimeException("Soft error FAILING HARDER: " + softError.getCategory() + ", " + softError.getMessage(), softError.getCause()));
    }

    @Nullable
    private Integer countIfShouldUploadSoftError(SoftError softError) {
        int count;
        Integer valueOf;
        long nowInSecs = this.mClock.now() / 1000;
        String category = softError.getCategory();
        synchronized (this.mObservedSoftErrorsMap) {
            List<Long> data = this.mObservedSoftErrorsMap.get(softError.getCategory());
            if (data != null) {
                int previousCount = data.get(1).intValue();
                if (nowInSecs - data.get(0).longValue() >= 120 || previousCount >= 100) {
                    count = previousCount + 1;
                    this.mObservedSoftErrorsMap.put(category, new ArrayList<>(Arrays.asList(Long.valueOf(nowInSecs), 0L)));
                    valueOf = Integer.valueOf(count);
                } else {
                    data.set(1, Long.valueOf(((long) previousCount) + 1));
                    BLog.i(TAG, "Soft error: category '%s' message '%s' fired, but not uploading", category, softError.getMessage());
                    valueOf = null;
                }
            } else {
                count = 1;
                this.mObservedSoftErrorsMap.put(category, new ArrayList<>(Arrays.asList(Long.valueOf(nowInSecs), 0L)));
                valueOf = Integer.valueOf(count);
            }
        }
        return valueOf;
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void strictModeReport(final String category, final String message, final String stackTrace) {
        if (!DISABLE_OVERRIDE) {
            if (Systrace.isTracing(256)) {
                Systrace.traceInstant(256, "StrictModeReport category: " + category + " message: " + message, Systrace.EventScope.THREAD);
            }
            this.mExecutorService.execute(new Runnable() {
                /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass3 */

                public void run() {
                    String reportedCategory = FbErrorReporterImpl.this.samplingFrequencyCategoryModifier(category, 1, false);
                    if (reportedCategory != null) {
                        try {
                            CrashReportData draft = new CrashReportData();
                            draft.put(ErrorReportingConstants.STRICT_MODE_MESSAGE, message);
                            draft.put(ErrorReportingConstants.STRICT_MODE_CATEGORY, reportedCategory);
                            ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException(new StrictModeException(message), stackTrace, draft);
                        } catch (Throwable th) {
                        }
                    }
                }
            });
        }
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportStall(final long duration) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass4 */

            public void run() {
                try {
                    CrashReportData draft = new CrashReportData();
                    draft.put(ErrorReportingConstants.STALL_DURATION_KEY, Long.toString(duration));
                    ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException((Throwable) new StallException(), draft);
                } catch (Throwable th) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportJavascriptException(final Throwable cause) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass5 */

            public void run() {
                try {
                    ErrorReporter.getInstance().handleException(cause);
                } catch (Throwable th) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void updateCrashReportProxy(Proxy proxy) {
        this.mErrorReporterProvider.get().setReportProxy(proxy);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putCustomData(String key, @Nullable String value) {
        this.mErrorReporterProvider.get();
        ErrorReporter.putCustomData(key, value);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void removeCustomData(String key) {
        this.mErrorReporterProvider.get();
        ErrorReporter.removeCustomData(key);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putLazyCustomData(String key, final FbCustomReportDataSupplier valueSupplier) {
        this.mErrorReporterProvider.get().putLazyCustomData(key, new CustomReportDataSupplier() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass6 */

            @Override // com.facebook.acra.CustomReportDataSupplier
            public String getCustomData(Throwable throwable) {
                return valueSupplier.getCustomData(throwable);
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void removeLazyCustomData(String key) {
        this.mErrorReporterProvider.get().removeLazyCustomData(key);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportLastActivity(String activityName) {
        this.mErrorReporterProvider.get().registerActivity(activityName);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportANRs() {
        this.mErrorReporterProvider.get().prepareCachedANRReports(10);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportRNException(String category, String message) {
        reportRNException(category, message, null);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportRNException(String category, Throwable cause) {
        reportRNException(category, cause.getMessage(), cause);
    }

    private void reportRNException(final String category, final String message, @Nullable final Throwable cause) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass7 */

            public void run() {
                try {
                    CrashReportData draft = new CrashReportData();
                    draft.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, category);
                    draft.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, message);
                    ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException((Throwable) new ReactNativeException(message, cause), draft);
                } catch (Throwable th) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putCurrentUserId(String userId) {
        this.mErrorReporterProvider.get().setUserId(userId);
    }

    private String samplingFrequencyCategoryModifier(SoftError softError) {
        return samplingFrequencyCategoryModifier(softError.getCategory(), softError.getSamplingFrequency(), softError.getOnlyIfEmployeeOrBetaBuild());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private String samplingFrequencyCategoryModifier(String category, int samplingFrequency, boolean onlyIfEmployeeBuild) {
        if ((this.mIsInternalBuildProvider.get().booleanValue() || this.mIsMeUserFbEmployeeProvider.get() == TriState.YES) && (this.mIsNonEmployeeModeEnabled == null || !this.mIsNonEmployeeModeEnabled.booleanValue())) {
            return category;
        }
        if (onlyIfEmployeeBuild) {
            return null;
        }
        if (this.mRandom.nextInt() % samplingFrequency != 0) {
            return null;
        }
        if (samplingFrequency != 1) {
            return category + " [freq=" + samplingFrequency + "]";
        }
        return category;
    }

    @ThreadSafe(enableChecks = false)
    private void initIsNonEmployeeModeEnabled() {
        boolean z = true;
        if (this.mIsNonEmployeeModeEnabled == null) {
            if (this.mContext == null) {
                this.mIsNonEmployeeModeEnabled = false;
                return;
            }
            FileInputStream inputStream = null;
            try {
                FileInputStream inputStream2 = this.mContext.openFileInput(FILE_NAME);
                if (inputStream2.read() != 1) {
                    z = false;
                }
                this.mIsNonEmployeeModeEnabled = Boolean.valueOf(z);
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class ShowToast implements Runnable {
        private WeakReference<Context> mContext;
        private final SoftError mSoftError;

        public ShowToast(SoftError softError, Context context) {
            this.mSoftError = softError;
            this.mContext = new WeakReference<>(context);
        }

        public void run() {
            Context c = this.mContext.get();
            if (c != null) {
                Toast.makeText(c, String.format("[FB-Only] SoftErrorFailHarder: %s\n%s", this.mSoftError.getMessage(), this.mSoftError.getCause()), 1).show();
            }
        }
    }
}
