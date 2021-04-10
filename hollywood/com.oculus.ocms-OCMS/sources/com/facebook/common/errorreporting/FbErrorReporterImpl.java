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
    private static final String TAG = "FbErrorReporterImpl";
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

    public FbErrorReporterImpl(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, MonotonicClock monotonicClock, Random random) {
        this(provider, provider2, executorService, monotonicClock, random, null, null, sErrorReporterProvider, false);
    }

    FbErrorReporterImpl(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, MonotonicClock monotonicClock, Random random, @Nullable Context context, @Nullable ErrorReportingGkReader errorReportingGkReader) {
        this(provider, provider2, executorService, monotonicClock, random, context, errorReportingGkReader, sErrorReporterProvider, false);
    }

    FbErrorReporterImpl(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, MonotonicClock monotonicClock, Random random, @Nullable Context context, @Nullable ErrorReportingGkReader errorReportingGkReader, Provider<ErrorReporter> provider3, boolean z) {
        this.mIsNonEmployeeModeEnabled = null;
        this.mIsMeUserFbEmployeeProvider = provider;
        this.mIsInternalBuildProvider = provider2;
        this.mExecutorService = executorService;
        this.mClock = monotonicClock;
        this.mRandom = random;
        this.mContext = context;
        this.mErrorReporterProvider = provider3;
        this.mErrorReportingGkReader = errorReportingGkReader;
        this.mTestingMode = z;
        this.mObservedSoftErrorsMap = new HashMap();
        this.mSoftErrorCategoryBlacklist = null;
        this.mSoftErrorReportingFrequency = null;
    }

    @Override // com.facebook.common.mobilesofterror.intf.HasSoftErrorSkipList
    public void setSoftErrorCategorySkipList(SoftErrorCategoryBlacklist softErrorCategoryBlacklist) {
        this.mSoftErrorCategoryBlacklist = softErrorCategoryBlacklist;
    }

    public void setSoftErrorDefaultFrequency(SoftErrorReportingFrequency softErrorReportingFrequency) {
        BLog.i(TAG, "Setting soft error default reporting frequency %s as defined by the client app module", Integer.valueOf(softErrorReportingFrequency.getDefaultReportingFrequency()));
        this.mSoftErrorReportingFrequency = softErrorReportingFrequency;
    }

    public void setQplBridge(ErrorReporterQplBridge errorReporterQplBridge) {
        this.mQplBridge = errorReporterQplBridge;
    }

    public static void setDisableOverride(boolean z) {
        DISABLE_OVERRIDE = z;
    }

    public boolean setSoftErrorsPref(boolean z) {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream openFileOutput = context.openFileOutput(FILE_NAME, 0);
            openFileOutput.write(z ? 1 : 0);
            try {
                openFileOutput.close();
            } catch (IOException unused) {
            }
            return true;
        } catch (IOException unused2) {
            if (0 != 0) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError) {
        softReport(softError, (ExternalProcessInfo) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        r5 = samplingFrequencyCategoryModifier(r11);
     */
    @Override // com.facebook.common.errorreporting.FbErrorReporter, com.facebook.common.errorreporting.AbstractFbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReport(com.facebook.common.errorreporting.SoftError r11, @javax.annotation.Nullable final com.facebook.acra.info.ExternalProcessInfo r12) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReport(com.facebook.common.errorreporting.SoftError, com.facebook.acra.info.ExternalProcessInfo):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        r0 = samplingFrequencyCategoryModifier(r5);
     */
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReportDelayed(com.facebook.common.errorreporting.SoftError r5) {
        /*
            r4 = this;
            r4.initIsNonEmployeeModeEnabled()
            com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist r0 = r4.mSoftErrorCategoryBlacklist
            if (r0 == 0) goto L_0x001f
            com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist r0 = r4.mSoftErrorCategoryBlacklist
            java.lang.String r1 = r5.getCategory()
            boolean r0 = r0.isCategoryBlacklisted(r1)
            if (r0 == 0) goto L_0x001f
            java.lang.String r0 = com.facebook.common.errorreporting.FbErrorReporterImpl.TAG
            java.lang.String r5 = r5.getCategory()
            java.lang.String r1 = "Not logging because Soft Error category %s is blacklisted"
            com.facebook.debug.log.BLog.i(r0, r1, r5)
            return
        L_0x001f:
            com.facebook.common.errorreporting.SoftError r5 = r4.applySamplingFreqOverride(r5)
            com.facebook.common.errorreporting.ErrorReporterQplBridge r0 = r4.mQplBridge
            if (r0 == 0) goto L_0x0034
            com.facebook.common.errorreporting.ErrorReporterQplBridge r0 = r4.mQplBridge
            java.lang.String r1 = r5.getCategory()
            java.lang.String r2 = r5.getMessage()
            r0.logSoftError(r1, r2)
        L_0x0034:
            java.lang.Integer r0 = r4.prepSoftReport(r5)
            if (r0 != 0) goto L_0x003b
            return
        L_0x003b:
            java.lang.String r0 = r4.samplingFrequencyCategoryModifier(r5)
            if (r0 != 0) goto L_0x0042
            return
        L_0x0042:
            java.lang.String r1 = r5.getMessage()
            com.facebook.common.errorreporting.SoftErrorException r2 = new com.facebook.common.errorreporting.SoftErrorException
            java.lang.Throwable r5 = r5.getCause()
            r2.<init>(r1, r5)
            java.util.concurrent.ExecutorService r5 = r4.mExecutorService
            com.facebook.common.errorreporting.FbErrorReporterImpl$2 r3 = new com.facebook.common.errorreporting.FbErrorReporterImpl$2
            r3.<init>(r0, r1, r2)
            r5.execute(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReportDelayed(com.facebook.common.errorreporting.SoftError):void");
    }

    private SoftError applySamplingFreqOverride(SoftError softError) {
        return (softError.getSamplingFrequency() != 1000 || this.mSoftErrorReportingFrequency == null) ? softError : SoftError.newError(softError, this.mSoftErrorReportingFrequency.getDefaultReportingFrequency());
    }

    @Nullable
    private Integer prepSoftReport(SoftError softError) {
        if (DISABLE_OVERRIDE) {
            return null;
        }
        boolean z = this.mIsMeUserFbEmployeeProvider.get() == TriState.YES || this.mIsInternalBuildProvider.get().booleanValue();
        if (softError.shouldFailHarder() && z) {
            if (!isFailHarderFatalDisabled()) {
                failHarder(softError);
                return null;
            } else if (this.mContext != null) {
                new Handler(Looper.getMainLooper()).post(new ShowToast(softError, this.mContext));
            }
        }
        Integer countIfShouldUploadSoftError = countIfShouldUploadSoftError(softError);
        if (z && countIfShouldUploadSoftError == null) {
            return null;
        }
        if (!z) {
            countIfShouldUploadSoftError = Integer.valueOf(softError.getSamplingFrequency());
        }
        if (Systrace.isTracing(256)) {
            Systrace.traceInstant(256, "softReport category: " + softError.getCategory() + " message: " + softError.getMessage(), Systrace.EventScope.THREAD);
        }
        return countIfShouldUploadSoftError;
    }

    private boolean isFailHarderFatalDisabled() {
        ErrorReportingGkReader errorReportingGkReader = this.mErrorReportingGkReader;
        return errorReportingGkReader != null && errorReportingGkReader.isFailHarderFatalDisabled() == TriState.YES;
    }

    private void failHarder(SoftError softError) {
        ErrorReporter.putCustomData(ErrorReportingConstants.SOFT_ERROR_MESSAGE, softError.getMessage());
        BLog.e(TAG, "category: %s message: %s", softError.getCategory(), softError.getMessage());
        this.mErrorReporterProvider.get().reportErrorAndTerminate(Thread.currentThread(), new RuntimeException("Soft error FAILING HARDER: " + softError.getCategory() + ", " + softError.getMessage(), softError.getCause()));
    }

    @Nullable
    private Integer countIfShouldUploadSoftError(SoftError softError) {
        int i;
        long now = this.mClock.now() / 1000;
        String category = softError.getCategory();
        synchronized (this.mObservedSoftErrorsMap) {
            ArrayList<Long> arrayList = this.mObservedSoftErrorsMap.get(softError.getCategory());
            if (arrayList != null) {
                int intValue = arrayList.get(1).intValue();
                if (now - arrayList.get(0).longValue() >= 120 || intValue >= 100) {
                    i = intValue + 1;
                } else {
                    arrayList.set(1, Long.valueOf(((long) intValue) + 1));
                    BLog.i(TAG, "Soft error: category '%s' message '%s' fired, but not uploading", category, softError.getMessage());
                    return null;
                }
            } else {
                i = 1;
            }
            this.mObservedSoftErrorsMap.put(category, new ArrayList<>(Arrays.asList(Long.valueOf(now), 0L)));
            return Integer.valueOf(i);
        }
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void strictModeReport(final String str, final String str2, final String str3) {
        if (!DISABLE_OVERRIDE) {
            if (Systrace.isTracing(256)) {
                Systrace.traceInstant(256, "StrictModeReport category: " + str + " message: " + str2, Systrace.EventScope.THREAD);
            }
            this.mExecutorService.execute(new Runnable() {
                /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass3 */

                public void run() {
                    String samplingFrequencyCategoryModifier = FbErrorReporterImpl.this.samplingFrequencyCategoryModifier(str, 1, false);
                    if (samplingFrequencyCategoryModifier != null) {
                        try {
                            CrashReportData crashReportData = new CrashReportData();
                            crashReportData.put(ErrorReportingConstants.STRICT_MODE_MESSAGE, str2);
                            crashReportData.put(ErrorReportingConstants.STRICT_MODE_CATEGORY, samplingFrequencyCategoryModifier);
                            ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException(new StrictModeException(str2), str3, crashReportData);
                        } catch (Throwable unused) {
                        }
                    }
                }
            });
        }
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportStall(final long j) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass4 */

            public void run() {
                try {
                    CrashReportData crashReportData = new CrashReportData();
                    crashReportData.put(ErrorReportingConstants.STALL_DURATION_KEY, Long.toString(j));
                    ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException((Throwable) new StallException(), crashReportData);
                } catch (Throwable unused) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportJavascriptException(final Throwable th) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass5 */

            public void run() {
                try {
                    ErrorReporter.getInstance().handleException(th);
                } catch (Throwable unused) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void updateCrashReportProxy(Proxy proxy) {
        this.mErrorReporterProvider.get().setReportProxy(proxy);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putCustomData(String str, @Nullable String str2) {
        this.mErrorReporterProvider.get();
        ErrorReporter.putCustomData(str, str2);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void removeCustomData(String str) {
        this.mErrorReporterProvider.get();
        ErrorReporter.removeCustomData(str);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putLazyCustomData(String str, final FbCustomReportDataSupplier fbCustomReportDataSupplier) {
        this.mErrorReporterProvider.get().putLazyCustomData(str, new CustomReportDataSupplier() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass6 */

            @Override // com.facebook.acra.CustomReportDataSupplier
            public String getCustomData(Throwable th) {
                return fbCustomReportDataSupplier.getCustomData(th);
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void removeLazyCustomData(String str) {
        this.mErrorReporterProvider.get().removeLazyCustomData(str);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportLastActivity(String str) {
        this.mErrorReporterProvider.get().registerActivity(str);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportANRs() {
        this.mErrorReporterProvider.get().prepareCachedANRReports(10);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportRNException(String str, String str2) {
        reportRNException(str, str2, null);
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void reportRNException(String str, Throwable th) {
        reportRNException(str, th.getMessage(), th);
    }

    private void reportRNException(final String str, final String str2, @Nullable final Throwable th) {
        this.mExecutorService.execute(new Runnable() {
            /* class com.facebook.common.errorreporting.FbErrorReporterImpl.AnonymousClass7 */

            public void run() {
                try {
                    CrashReportData crashReportData = new CrashReportData();
                    crashReportData.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, str);
                    crashReportData.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, str2);
                    ((ErrorReporter) FbErrorReporterImpl.this.mErrorReporterProvider.get()).handleException((Throwable) new ReactNativeException(str2, th), crashReportData);
                } catch (Throwable unused) {
                }
            }
        });
    }

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void putCurrentUserId(String str) {
        this.mErrorReporterProvider.get().setUserId(str);
    }

    private String samplingFrequencyCategoryModifier(SoftError softError) {
        return samplingFrequencyCategoryModifier(softError.getCategory(), softError.getSamplingFrequency(), softError.getOnlyIfEmployeeOrBetaBuild());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private String samplingFrequencyCategoryModifier(String str, int i, boolean z) {
        Boolean bool;
        if ((this.mIsInternalBuildProvider.get().booleanValue() || this.mIsMeUserFbEmployeeProvider.get() == TriState.YES) && ((bool = this.mIsNonEmployeeModeEnabled) == null || !bool.booleanValue())) {
            return str;
        }
        if (z || this.mRandom.nextInt() % i != 0) {
            return null;
        }
        if (i == 1) {
            return str;
        }
        return str + " [freq=" + i + "]";
    }

    @ThreadSafe(enableChecks = false)
    private void initIsNonEmployeeModeEnabled() {
        if (this.mIsNonEmployeeModeEnabled == null) {
            Context context = this.mContext;
            boolean z = false;
            if (context == null) {
                this.mIsNonEmployeeModeEnabled = false;
                return;
            }
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = context.openFileInput(FILE_NAME);
                if (fileInputStream.read() == 1) {
                    z = true;
                }
                this.mIsNonEmployeeModeEnabled = Boolean.valueOf(z);
            } catch (IOException unused) {
                if (0 == 0) {
                    return;
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
            try {
                fileInputStream.close();
            } catch (IOException unused3) {
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
            Context context = this.mContext.get();
            if (context != null) {
                Toast.makeText(context, String.format("[FB-Only] SoftErrorFailHarder: %s\n%s", this.mSoftError.getMessage(), this.mSoftError.getCause()), 1).show();
            }
        }
    }
}
