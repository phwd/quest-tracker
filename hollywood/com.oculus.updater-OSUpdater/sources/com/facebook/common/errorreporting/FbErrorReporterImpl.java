package com.facebook.common.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.systrace.Systrace;
import com.facebook.testenv.TestEnvironment;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
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
public class FbErrorReporterImpl extends AbstractFbErrorReporter {
    static volatile boolean DISABLE_OVERRIDE = TestEnvironment.isTest();
    private static final String TAG = "FbErrorReporterImpl";
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

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError) {
        softReport(softError, (ExternalProcessInfo) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        r5 = samplingFrequencyCategoryModifier(r11);
     */
    @Override // com.facebook.common.errorreporting.AbstractFbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReport(com.facebook.common.errorreporting.SoftError r11, @javax.annotation.Nullable final com.facebook.acra.info.ExternalProcessInfo r12) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReport(com.facebook.common.errorreporting.SoftError, com.facebook.acra.info.ExternalProcessInfo):void");
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

    private String samplingFrequencyCategoryModifier(SoftError softError) {
        return samplingFrequencyCategoryModifier(softError.getCategory(), softError.getSamplingFrequency(), softError.getOnlyIfEmployeeOrBetaBuild());
    }

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

    @ThreadSafe
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
                fileInputStream = context.openFileInput("soft_errors_pref");
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
