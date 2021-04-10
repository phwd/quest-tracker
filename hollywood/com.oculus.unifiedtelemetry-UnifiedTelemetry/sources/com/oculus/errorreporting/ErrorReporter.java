package com.oculus.errorreporting;

import X.I8;
import X.Ix;
import X.J5;
import X.J6;
import X.Mu;
import X.RL;
import X.eJ;
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
    public final Ix mErrorReporter;

    public static final class EmployeeProvider implements eJ<TriState> {
        public eJ<Boolean> mIsTrustedUserProvider;

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // X.eJ
        public final TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }

        public EmployeeProvider(eJ<Boolean> eJVar) {
            this.mIsTrustedUserProvider = eJVar;
        }
    }

    @Deprecated
    public static synchronized ErrorReporter A00(eJ<Boolean> eJVar) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            errorReporter = sErrorReporter;
            if (errorReporter == null) {
                eJ rl = new RL(TriState.UNSET);
                if (eJVar != null) {
                    rl = new EmployeeProvider(eJVar);
                }
                RL rl2 = new RL(false);
                synchronized (ErrorReportingExecutorFactory.class) {
                    if (ErrorReportingExecutorFactory.sSingleThreadExecutorService == null) {
                        ErrorReportingExecutorFactory.sSingleThreadExecutorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ErrorReportingExecutorFactory.NamedThreadFactory());
                    }
                }
                errorReporter = new ErrorReporter(new I8(rl, rl2, ErrorReportingExecutorFactory.sSingleThreadExecutorService, AwakeTimeSinceBootClock.INSTANCE, new Random()));
                sErrorReporter = errorReporter;
            }
        }
        return errorReporter;
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A5H(String str, String str2) {
        Ix ix = this.mErrorReporter;
        Mu.A00(str, str2);
        J6 j6 = new J6();
        j6.A01 = str;
        j6.A02 = str2;
        j6.A03 = null;
        j6.A04 = false;
        j6.A00 = 1;
        ix.A5K(new J5(j6));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A5I(String str, String str2, Throwable th) {
        Ix ix = this.mErrorReporter;
        Mu.A00(str, str2);
        J6 j6 = new J6();
        j6.A01 = str;
        j6.A02 = str2;
        j6.A03 = th;
        j6.A04 = false;
        j6.A00 = 1;
        ix.A5K(new J5(j6));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A5J(String str, String str2, Throwable th) {
        Ix ix = this.mErrorReporter;
        Mu.A00(str, str2);
        J6 j6 = new J6();
        j6.A01 = str;
        j6.A02 = str2;
        j6.A03 = th;
        j6.A04 = true;
        j6.A00 = 1;
        ix.A5K(new J5(j6));
    }

    public ErrorReporter(Ix ix) {
        this.mErrorReporter = ix;
    }
}
