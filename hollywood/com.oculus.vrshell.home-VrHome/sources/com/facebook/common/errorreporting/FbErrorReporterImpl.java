package com.facebook.common.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.acra.ErrorReporter;
import com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
import com.facebook.systrace.Systrace;
import com.facebook.testenv.TestEnvironment;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
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
public class FbErrorReporterImpl extends AbstractFbErrorReporter {
    static volatile boolean DISABLE_OVERRIDE = TestEnvironment.isTest();
    private static final String TAG = FbErrorReporterImpl.class.getSimpleName();
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

    @Override // com.facebook.common.errorreporting.FbErrorReporter
    public void softReport(SoftError softError) {
        softReport(softError, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        r2 = samplingFrequencyCategoryModifier(r9);
     */
    @Override // com.facebook.common.errorreporting.AbstractFbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void softReport(com.facebook.common.errorreporting.SoftError r9, @javax.annotation.Nullable final com.facebook.acra.info.ExternalProcessInfo r10) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReport(com.facebook.common.errorreporting.SoftError, com.facebook.acra.info.ExternalProcessInfo):void");
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
        ErrorReporter.putCustomData("soft_error_message", softError.getMessage());
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
    public void putCurrentUserId(String userId) {
        this.mErrorReporterProvider.get().setUserId(userId);
    }

    private String samplingFrequencyCategoryModifier(SoftError softError) {
        return samplingFrequencyCategoryModifier(softError.getCategory(), softError.getSamplingFrequency(), softError.getOnlyIfEmployeeOrBetaBuild());
    }

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

    private void initIsNonEmployeeModeEnabled() {
        boolean z = true;
        if (this.mIsNonEmployeeModeEnabled == null) {
            if (this.mContext == null) {
                this.mIsNonEmployeeModeEnabled = false;
                return;
            }
            FileInputStream inputStream = null;
            try {
                FileInputStream inputStream2 = this.mContext.openFileInput("soft_errors_pref");
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
