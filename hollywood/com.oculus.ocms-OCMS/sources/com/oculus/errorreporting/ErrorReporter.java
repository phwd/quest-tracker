package com.oculus.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.common.errorreporting.FbAcraConfig;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.errorreporting.FbErrorReporterImpl;
import com.facebook.common.errorreporting.SoftError;
import com.facebook.common.errorreporting.SoftErrorBuilder;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
import com.facebook.debug.log.LoggingUtil;
import com.google.inject.util.Providers;
import com.oculus.common.build.BuildConstants;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter implements IErrorReporter {
    private static final String CRASH_ENDPOINT = "https://www.facebook.com/mobile/generic_android_crash_logs";
    private static final boolean DEFAULT_FAIL_HARDER = false;
    private static final int DEFAULT_HORIZON_SAMPLE_RATE = 1;
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
        return FbAcraConfig.URI_UPLOAD_CRASH + str;
    }

    @Deprecated
    public static void softReportWithCrash(String str, String str2) {
        getInstance().softReport(getBuilder(str, str2, true).build());
    }

    @Deprecated
    public static void softReportWithCrash(String str, Throwable th) {
        getInstance().softReport(getBuilder(str, th.getMessage(), th, true).build());
    }

    @Deprecated
    public static void softReportWithCrash(String str, String str2, Throwable th) {
        getInstance().softReport(getBuilder(str, str2, th, true).build());
    }

    @Deprecated
    public static void softReport(String str, String str2) {
        getInstance().softReport(getBuilder(str, str2, false).build());
    }

    @Deprecated
    public static void softReport(String str, String str2, Throwable th) {
        getInstance().softReport(getBuilder(str, str2, th, false).build());
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

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void putCurrentUserId(String str) {
        this.mErrorReporter.putCurrentUserId(str);
    }

    private void softReport(SoftError softError) {
        this.mErrorReporter.softReport(softError);
    }

    @Deprecated
    public static void initCrashReporting(Context context, String str, String str2) {
        initCrashReportingInternal(context, str, str2, getDefaultEndpoint(str));
    }

    public static void initCrashReporting(Context context, String str, String str2, @Nullable String str3, Provider<Boolean> provider, Map<String, String> map) {
        initCrashReporting(context, str, str2, getDefaultEndpoint(str), str3, provider, map);
    }

    public static void initCrashReporting(Context context, String str, String str2, String str3, @Nullable String str4, Provider<Boolean> provider, Map<String, String> map) {
        initCrashReporting(context, str, str2, str3, str4, provider, map, new HashMap());
    }

    public static void initCrashReporting(Context context, String str, String str2, String str3, @Nullable String str4, Provider<Boolean> provider, Map<String, String> map, Map<String, CustomReportDataSupplier> map2) {
        com.facebook.acra.ErrorReporter initCrashReportingInternal = initCrashReportingInternal(context, str, str2, str3);
        if (str4 != null) {
            initCrashReportingInternal.setUserId(str4);
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            com.facebook.acra.ErrorReporter.putCustomData(entry.getKey(), entry.getValue() == null ? LoggingUtil.NO_HASHCODE : entry.getValue());
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : map2.entrySet()) {
            initCrashReportingInternal.putLazyCustomData(entry2.getKey(), entry2.getValue());
        }
        getInstance(provider);
    }

    private static com.facebook.acra.ErrorReporter initCrashReportingInternal(Context context, String str, String str2, String str3) {
        com.facebook.acra.ErrorReporter init = ACRA.init(new DefaultAcraConfig(context, str3, false));
        com.facebook.acra.ErrorReporter.putCustomData(ErrorReportingConstants.APP_NAME_KEY, str2);
        com.facebook.acra.ErrorReporter.putCustomData("fb_app_id", str);
        return init;
    }
}
