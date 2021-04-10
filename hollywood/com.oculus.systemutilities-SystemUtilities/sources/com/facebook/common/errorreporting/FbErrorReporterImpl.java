package com.facebook.common.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.CrashReportData;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.info.ExternalProcessInfo;
import com.facebook.common.mobilesofterror.intf.SoftErrorCategoryBlacklist;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.util.TriState;
import com.facebook.testenv.TestEnvironment;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class FbErrorReporterImpl extends AbstractFbErrorReporter {
    static volatile boolean DISABLE_OVERRIDE = TestEnvironment.isTest();
    private static final String TAG = FbErrorReporterImpl.class.getSimpleName();
    public static final Provider<ErrorReporter> sErrorReporterProvider = new ErrorReporterProvider(null);
    private final MonotonicClock mClock;
    private final Context mContext;
    private final Provider<ErrorReporter> mErrorReporterProvider;
    private final ErrorReportingGkReader mErrorReportingGkReader;
    private final ExecutorService mExecutorService;
    private final Provider<Boolean> mIsInternalBuildProvider;
    private final Provider<TriState> mIsMeUserFbEmployeeProvider;
    private Boolean mIsNonEmployeeModeEnabled;
    private final Map<String, ArrayList<Long>> mObservedSoftErrorsMap;
    private final Random mRandom;
    private volatile SoftErrorCategoryBlacklist mSoftErrorCategoryBlacklist;
    private volatile SoftErrorReportingFrequency mSoftErrorReportingFrequency;
    private final boolean mTestingMode;

    private static class ErrorReporterProvider implements Provider<ErrorReporter> {
        private ErrorReporterProvider() {
        }

        /* synthetic */ ErrorReporterProvider(AnonymousClass1 x0) {
            this();
        }

        @Override // javax.inject.Provider
        public ErrorReporter get() {
            return ErrorReporter.getInstance();
        }
    }

    public FbErrorReporterImpl(Provider<TriState> isMeUserFbEmployeeProvider, Provider<Boolean> isInternalBuildProvider, ExecutorService executorService, MonotonicClock monotonicClock, Random random) {
        this(isMeUserFbEmployeeProvider, isInternalBuildProvider, executorService, monotonicClock, random, null, null, sErrorReporterProvider, false);
    }

    FbErrorReporterImpl(Provider<TriState> isMeUserFbEmployeeProvider, Provider<Boolean> isInternalBuildProvider, ExecutorService executorService, MonotonicClock monotonicClock, Random random, Context context, ErrorReportingGkReader gkReader, Provider<ErrorReporter> errorReporterProvider, boolean testingMode) {
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

    /* renamed from: com.facebook.common.errorreporting.FbErrorReporterImpl$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ FbErrorReporterImpl this$0;
        final /* synthetic */ ExternalProcessInfo val$external;
        final /* synthetic */ String val$message;
        final /* synthetic */ Integer val$occurrenceCount;
        final /* synthetic */ String val$reportedCategory;
        final /* synthetic */ Throwable val$throwable;

        public void run() {
            try {
                CrashReportData draft = new CrashReportData();
                draft.put("soft_error_category", this.val$reportedCategory);
                draft.put("soft_error_message", this.val$message);
                draft.put("sample_rate", Integer.toString(this.val$occurrenceCount.intValue()));
                ErrorReporter reporter = (ErrorReporter) this.this$0.mErrorReporterProvider.get();
                if (this.val$external != null) {
                    String eventsLog = reporter.getEventsLog();
                    if (eventsLog != null) {
                        draft.put("EVENTSLOG", eventsLog);
                    }
                    draft.put("LOGCAT", reporter.getLogcatOutputIfPidFound(true, null));
                    draft.merge(this.val$external.getAcraFields(), true, (Writer) null);
                }
                reporter.handleException(this.val$throwable, draft);
            } catch (Throwable t) {
                if (!this.this$0.mTestingMode) {
                    return;
                }
                if (t instanceof Error) {
                    throw ((Error) t);
                } else if (t instanceof RuntimeException) {
                    throw ((RuntimeException) t);
                } else {
                    throw new RuntimeException(t);
                }
            }
        }
    }
}
