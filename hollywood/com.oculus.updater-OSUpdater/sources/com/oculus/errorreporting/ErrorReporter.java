package com.oculus.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.errorreporting.FbErrorReporterImpl;
import com.facebook.common.errorreporting.SoftError;
import com.facebook.common.errorreporting.SoftErrorBuilder;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
import com.google.inject.util.Providers;
import com.oculus.common.build.BuildConstants;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.util.Random;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter implements IErrorReporter {
    @Deprecated
    private static ErrorReporter sErrorReporter;
    private final FbErrorReporter mErrorReporter;

    /* access modifiers changed from: private */
    public static final class EmployeeProvider implements Provider<TriState> {
        private Provider<Boolean> mIsTrustedUserProvider;

        private EmployeeProvider(Provider<Boolean> provider) {
            this.mIsTrustedUserProvider = provider;
        }

        @Override // javax.inject.Provider
        public TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }
    }

    @Deprecated
    static ErrorReporter getInstance() {
        return getInstance(null);
    }

    @Deprecated
    private static synchronized ErrorReporter getInstance(Provider<Boolean> provider) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            if (sErrorReporter == null) {
                sErrorReporter = new ErrorReporter(newFbErrorReporter(provider));
            }
            errorReporter = sErrorReporter;
        }
        return errorReporter;
    }

    private static FbErrorReporter newFbErrorReporter(Provider<Boolean> provider) {
        Provider of = Providers.of(TriState.UNSET);
        if (provider != null) {
            of = new EmployeeProvider(provider);
        }
        return new FbErrorReporterImpl(of, Providers.of(Boolean.valueOf(BuildConstants.DEBUG)), ErrorReportingExecutorFactory.getSingleThreadExecutorService(), ErrorReportingExecutorFactory.getMonotonicClock(), new Random());
    }

    public static String getDefaultEndpoint(String str) {
        return "https://www.facebook.com/mobile/generic_android_crash_logs/" + str;
    }

    private static SoftErrorBuilder getBuilder(String str, String str2, boolean z) {
        return getBuilder(str, str2, null, z);
    }

    private static SoftErrorBuilder getBuilder(String str, String str2, Throwable th, boolean z) {
        BLog.e(str, str2);
        return SoftError.newBuilder(str, str2).setCause(th).setFailHarder(z).setSamplingFrequency(1);
    }

    ErrorReporter(FbErrorReporter fbErrorReporter) {
        this.mErrorReporter = fbErrorReporter;
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String str, String str2) {
        this.mErrorReporter.softReport(getBuilder(str, str2, false).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String str, String str2, Throwable th) {
        this.mErrorReporter.softReport(getBuilder(str, str2, th, false).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String str, String str2) {
        this.mErrorReporter.softReport(getBuilder(str, str2, true).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String str, String str2, Throwable th) {
        this.mErrorReporter.softReport(getBuilder(str, str2, th, true).build());
    }

    @Deprecated
    public static void initCrashReporting(Context context, String str, String str2) {
        initCrashReportingInternal(context, str, str2, getDefaultEndpoint(str));
    }

    private static com.facebook.acra.ErrorReporter initCrashReportingInternal(Context context, String str, String str2, String str3) {
        com.facebook.acra.ErrorReporter init = ACRA.init(new DefaultAcraConfig(context, str3, false));
        com.facebook.acra.ErrorReporter.putCustomData(ErrorReportingConstants.APP_NAME_KEY, str2);
        com.facebook.acra.ErrorReporter.putCustomData(ErrorReportingConstants.FACEBOOK_APP_ID_KEY, str);
        return init;
    }
}
