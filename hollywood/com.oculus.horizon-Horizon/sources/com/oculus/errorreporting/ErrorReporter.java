package com.oculus.errorreporting;

import X.AbstractC01060Jr;
import X.AnonymousClass0JP;
import X.AnonymousClass0Jy;
import X.AnonymousClass0Jz;
import X.AnonymousClass0NO;
import X.AnonymousClass0UM;
import android.annotation.SuppressLint;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.common.util.TriState;
import com.oculus.errorreporting.ErrorReportingExecutorFactory;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter implements IErrorReporter {
    public static final String CRASH_ENDPOINT = "https://www.facebook.com/mobile/generic_android_crash_logs";
    public static final boolean DEFAULT_FAIL_HARDER = false;
    public static final int DEFAULT_HORIZON_SAMPLE_RATE = 1;
    @Deprecated
    public static ErrorReporter sErrorReporter;
    public final AbstractC01060Jr mErrorReporter;

    public static final class EmployeeProvider implements Provider<TriState> {
        public Provider<Boolean> mIsTrustedUserProvider;

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // javax.inject.Provider
        public final TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }

        public EmployeeProvider(Provider<Boolean> provider) {
            this.mIsTrustedUserProvider = provider;
        }
    }

    @Deprecated
    public static void A02(String str, String str2) {
        ErrorReporter A01 = A01(null);
        A01.mErrorReporter.A9A(new AnonymousClass0Jy(A00(str, str2, null, false)));
    }

    @Deprecated
    public static synchronized ErrorReporter A01(Provider<Boolean> provider) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            errorReporter = sErrorReporter;
            if (errorReporter == null) {
                Provider r11 = new AnonymousClass0UM(TriState.UNSET);
                if (provider != null) {
                    r11 = new EmployeeProvider(provider);
                }
                AnonymousClass0UM r12 = new AnonymousClass0UM(false);
                synchronized (ErrorReportingExecutorFactory.class) {
                    if (ErrorReportingExecutorFactory.sSingleThreadExecutorService == null) {
                        ErrorReportingExecutorFactory.sSingleThreadExecutorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ErrorReportingExecutorFactory.NamedThreadFactory());
                    }
                }
                errorReporter = new ErrorReporter(new AnonymousClass0JP(r11, r12, ErrorReportingExecutorFactory.sSingleThreadExecutorService, AwakeTimeSinceBootClock.INSTANCE, new Random()));
                sErrorReporter = errorReporter;
            }
        }
        return errorReporter;
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A7i(String str) {
        this.mErrorReporter.A7i(str);
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A96(String str, String str2) {
        this.mErrorReporter.A9A(new AnonymousClass0Jy(A00(str, str2, null, false)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A97(String str, String str2, Throwable th) {
        this.mErrorReporter.A9A(new AnonymousClass0Jy(A00(str, str2, th, false)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A98(String str, String str2) {
        this.mErrorReporter.A9A(new AnonymousClass0Jy(A00(str, str2, null, true)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public final void A99(String str, String str2, Throwable th) {
        this.mErrorReporter.A9A(new AnonymousClass0Jy(A00(str, str2, th, true)));
    }

    public ErrorReporter(AbstractC01060Jr r1) {
        this.mErrorReporter = r1;
    }

    public static AnonymousClass0Jz A00(String str, String str2, Throwable th, boolean z) {
        AnonymousClass0NO.A08(str, str2);
        AnonymousClass0Jz r1 = new AnonymousClass0Jz();
        r1.A01 = str;
        r1.A02 = str2;
        r1.A03 = th;
        r1.A04 = z;
        r1.A00 = 1;
        return r1;
    }
}
