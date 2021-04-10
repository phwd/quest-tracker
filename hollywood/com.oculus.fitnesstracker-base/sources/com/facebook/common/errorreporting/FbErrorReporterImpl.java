package com.facebook.common.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;
import com.facebook.acra.ErrorReporter;
import com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
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
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class FbErrorReporterImpl extends AbstractFbErrorReporter {
    static volatile boolean DISABLE_OVERRIDE = TestEnvironment.isTest();
    private static final String TAG = "FbErrorReporterImpl";
    public static final Provider<ErrorReporter> sErrorReporterProvider = new ErrorReporterProvider((byte) 0);
    private final MonotonicClock mClock;
    private final Context mContext;
    private final Provider<ErrorReporter> mErrorReporterProvider;
    private final ErrorReportingGkReader mErrorReportingGkReader;
    private final ExecutorService mExecutorService;
    private final Provider<Boolean> mIsInternalBuildProvider;
    private final Provider<TriState> mIsMeUserFbEmployeeProvider;
    private Boolean mIsNonEmployeeModeEnabled;
    private final Map<String, ArrayList<Long>> mObservedSoftErrorsMap;
    private volatile ErrorReporterQplBridge mQplBridge;
    private final Random mRandom;
    private volatile SoftErrorCategoryBlacklist mSoftErrorCategoryBlacklist;
    private volatile SoftErrorReportingFrequency mSoftErrorReportingFrequency;
    private final boolean mTestingMode;

    static class ErrorReporterProvider implements Provider<ErrorReporter> {
        private ErrorReporterProvider() {
        }

        /* synthetic */ ErrorReporterProvider(byte b) {
            this();
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // javax.inject.Provider
        public final /* bridge */ /* synthetic */ ErrorReporter get() {
            return ErrorReporter.getInstance();
        }
    }

    public FbErrorReporterImpl(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, MonotonicClock monotonicClock, Random random) {
        this(provider, provider2, executorService, monotonicClock, random, null, null, sErrorReporterProvider, false);
    }

    private FbErrorReporterImpl(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, MonotonicClock monotonicClock, Random random, Context context, ErrorReportingGkReader errorReportingGkReader, Provider<ErrorReporter> provider3, boolean z) {
        this.mIsNonEmployeeModeEnabled = null;
        this.mIsMeUserFbEmployeeProvider = provider;
        this.mIsInternalBuildProvider = provider2;
        this.mExecutorService = executorService;
        this.mClock = monotonicClock;
        this.mRandom = random;
        this.mContext = null;
        this.mErrorReporterProvider = provider3;
        this.mErrorReportingGkReader = null;
        this.mTestingMode = false;
        this.mObservedSoftErrorsMap = new HashMap();
        this.mSoftErrorCategoryBlacklist = null;
        this.mSoftErrorReportingFrequency = null;
    }

    private Integer countIfShouldUploadSoftError(SoftError softError) {
        int i;
        long now = this.mClock.now() / 1000;
        String str = softError.mCategory;
        synchronized (this.mObservedSoftErrorsMap) {
            ArrayList<Long> arrayList = this.mObservedSoftErrorsMap.get(softError.mCategory);
            if (arrayList != null) {
                int intValue = arrayList.get(1).intValue();
                if (now - arrayList.get(0).longValue() >= 120 || intValue >= 100) {
                    i = intValue + 1;
                } else {
                    arrayList.set(1, Long.valueOf(((long) intValue) + 1));
                    BLog.i(TAG, "Soft error: category '%s' message '%s' fired, but not uploading", str, softError.mMessage);
                    return null;
                }
            } else {
                i = 1;
            }
            this.mObservedSoftErrorsMap.put(str, new ArrayList<>(Arrays.asList(Long.valueOf(now), 0L)));
            return Integer.valueOf(i);
        }
    }

    private void initIsNonEmployeeModeEnabled() {
        if (this.mIsNonEmployeeModeEnabled == null) {
            Context context = this.mContext;
            if (context == null) {
                this.mIsNonEmployeeModeEnabled = Boolean.FALSE;
                return;
            }
            FileInputStream fileInputStream = null;
            try {
                FileInputStream openFileInput = context.openFileInput("soft_errors_pref");
                boolean z = true;
                if (openFileInput.read() != 1) {
                    z = false;
                }
                this.mIsNonEmployeeModeEnabled = Boolean.valueOf(z);
                try {
                    openFileInput.close();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
    }

    static class ShowToast implements Runnable {
        private WeakReference<Context> mContext;
        private final SoftError mSoftError;

        public ShowToast(SoftError softError, Context context) {
            this.mSoftError = softError;
            this.mContext = new WeakReference<>(context);
        }

        public final void run() {
            Context context = this.mContext.get();
            if (context != null) {
                Toast.makeText(context, String.format("[FB-Only] SoftErrorFailHarder: %s\n%s", this.mSoftError.mMessage, this.mSoftError.mCause), 1).show();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    @Override // com.facebook.common.errorreporting.FbErrorReporter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void softReport(com.facebook.common.errorreporting.SoftError r11) {
        /*
        // Method dump skipped, instructions count: 447
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.errorreporting.FbErrorReporterImpl.softReport(com.facebook.common.errorreporting.SoftError):void");
    }
}
