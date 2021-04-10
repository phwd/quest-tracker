package com.oculus.errorreporting;

import X.AbstractC0192Xx;
import X.C2;
import X.JH;
import X.JP;
import X.JQ;
import X.K4;
import X.Mi;
import android.annotation.SuppressLint;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.common.util.TriState;
import com.oculus.errorreporting.ErrorReportingExecutorFactory;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter implements IErrorReporter {
    public static final String CRASH_ENDPOINT = "https://www.facebook.com/mobile/generic_android_crash_logs";
    public static final boolean DEFAULT_FAIL_HARDER = false;
    public static final int DEFAULT_HORIZON_SAMPLE_RATE = 1;
    @Deprecated
    public static ErrorReporter sErrorReporter;
    public final JH mErrorReporter;

    public static final class EmployeeProvider implements AbstractC0192Xx<TriState> {
        public final AbstractC0192Xx<Boolean> mIsTrustedUserProvider;

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // X.AbstractC0192Xx
        public final TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }
    }

    @Deprecated
    public static ErrorReporter A00() {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            errorReporter = sErrorReporter;
            if (errorReporter == null) {
                K4 k4 = new K4(TriState.UNSET);
                K4 k42 = new K4(false);
                synchronized (ErrorReportingExecutorFactory.class) {
                    if (ErrorReportingExecutorFactory.sSingleThreadExecutorService == null) {
                        ErrorReportingExecutorFactory.sSingleThreadExecutorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ErrorReportingExecutorFactory.NamedThreadFactory());
                    }
                }
                errorReporter = new ErrorReporter(new C2(k4, k42, ErrorReportingExecutorFactory.sSingleThreadExecutorService, AwakeTimeSinceBootClock.INSTANCE, new Random()));
                sErrorReporter = errorReporter;
            }
        }
        return errorReporter;
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A3k(String str, String str2) {
        JH jh = this.mErrorReporter;
        Mi.A00(str, str2);
        JQ jq = new JQ();
        jq.A01 = str;
        jq.A02 = str2;
        jq.A03 = null;
        jq.A00 = 1;
        jh.A3m(new JP(jq));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A3l(String str, String str2, Throwable th) {
        JH jh = this.mErrorReporter;
        Mi.A00(str, str2);
        JQ jq = new JQ();
        jq.A01 = str;
        jq.A02 = str2;
        jq.A03 = th;
        jq.A00 = 1;
        jh.A3m(new JP(jq));
    }

    public ErrorReporter(JH jh) {
        this.mErrorReporter = jh;
    }
}
