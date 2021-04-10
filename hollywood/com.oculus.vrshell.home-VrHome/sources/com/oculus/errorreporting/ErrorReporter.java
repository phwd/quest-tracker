package com.oculus.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.errorreporting.FbErrorReporterImpl;
import com.facebook.common.errorreporting.SoftError;
import com.facebook.common.errorreporting.SoftErrorBuilder;
import com.facebook.common.util.TriState;
import com.facebook.debug.log.BLog;
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

        private EmployeeProvider(Provider<Boolean> isTrustedUserProvider) {
            this.mIsTrustedUserProvider = isTrustedUserProvider;
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
    private static synchronized ErrorReporter getInstance(Provider<Boolean> isTrustedUserProvider) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            if (sErrorReporter == null) {
                sErrorReporter = new ErrorReporter(newFbErrorReporter(isTrustedUserProvider));
            }
            errorReporter = sErrorReporter;
        }
        return errorReporter;
    }

    private static FbErrorReporter newFbErrorReporter(Provider<Boolean> isTrustedUserProvider) {
        Provider<TriState> employeeProvider = Providers.of(TriState.UNSET);
        if (isTrustedUserProvider != null) {
            employeeProvider = new EmployeeProvider(isTrustedUserProvider);
        }
        return new FbErrorReporterImpl(employeeProvider, Providers.of(Boolean.valueOf(BuildConstants.DEBUG)), ErrorReportingExecutorFactory.getSingleThreadExecutorService(), ErrorReportingExecutorFactory.getMonotonicClock(), new Random());
    }

    public static String getDefaultEndpoint(String appId) {
        return "https://www.facebook.com/mobile/generic_android_crash_logs/" + appId;
    }

    @Deprecated
    public static void softReportWithCrash(String category, String message) {
        getInstance().softReport(getBuilder(category, message, true).build());
    }

    @Deprecated
    public static void softReportWithCrash(String category, Throwable cause) {
        getInstance().softReport(getBuilder(category, cause.getMessage(), cause, true).build());
    }

    @Deprecated
    public static void softReportWithCrash(String category, String message, Throwable cause) {
        getInstance().softReport(getBuilder(category, message, cause, true).build());
    }

    @Deprecated
    public static void softReport(String category, String message) {
        getInstance().softReport(getBuilder(category, message, false).build());
    }

    @Deprecated
    public static void softReport(String category, String message, Throwable cause) {
        getInstance().softReport(getBuilder(category, message, cause, false).build());
    }

    private static SoftErrorBuilder getBuilder(String category, String message, boolean failHarder) {
        return getBuilder(category, message, null, failHarder);
    }

    private static SoftErrorBuilder getBuilder(String category, String message, Throwable cause, boolean failHarder) {
        BLog.e(category, message);
        return SoftError.newBuilder(category, message).setCause(cause).setFailHarder(failHarder).setSamplingFrequency(1);
    }

    ErrorReporter(FbErrorReporter fbErrorReporter) {
        this.mErrorReporter = fbErrorReporter;
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String category, String message) {
        this.mErrorReporter.softReport(getBuilder(category, message, false).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String category, String message, Throwable cause) {
        this.mErrorReporter.softReport(getBuilder(category, message, cause, false).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String category, String message) {
        this.mErrorReporter.softReport(getBuilder(category, message, true).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String category, String message, Throwable cause) {
        this.mErrorReporter.softReport(getBuilder(category, message, cause, true).build());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void putCurrentUserId(String userId) {
        this.mErrorReporter.putCurrentUserId(userId);
    }

    private void softReport(SoftError error) {
        this.mErrorReporter.softReport(error);
    }

    @Deprecated
    public static void initCrashReporting(Context context, String appId, String appName) {
        initCrashReportingInternal(context, appId, appName, getDefaultEndpoint(appId));
    }

    public static void initCrashReporting(Context context, String appId, String appName, @Nullable String userId, Provider<Boolean> isTrustedUserProvider, Map<String, String> customData) {
        initCrashReporting(context, appId, appName, getDefaultEndpoint(appId), userId, isTrustedUserProvider, customData);
    }

    public static void initCrashReporting(Context context, String appId, String appName, String crashReportUrl, @Nullable String userId, Provider<Boolean> isTrustedUserProvider, Map<String, String> customData) {
        initCrashReporting(context, appId, appName, crashReportUrl, userId, isTrustedUserProvider, customData, new HashMap<>());
    }

    public static void initCrashReporting(Context context, String appId, String appName, String crashReportUrl, @Nullable String userId, Provider<Boolean> isTrustedUserProvider, Map<String, String> customData, Map<String, CustomReportDataSupplier> lazyCustomData) {
        com.facebook.acra.ErrorReporter reporter = initCrashReportingInternal(context, appId, appName, crashReportUrl);
        if (userId != null) {
            reporter.setUserId(userId);
        }
        for (Map.Entry<String, String> entry : customData.entrySet()) {
            com.facebook.acra.ErrorReporter.putCustomData(entry.getKey(), entry.getValue() == null ? "null" : entry.getValue());
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : lazyCustomData.entrySet()) {
            reporter.putLazyCustomData(entry2.getKey(), entry2.getValue());
        }
        getInstance(isTrustedUserProvider);
    }

    private static com.facebook.acra.ErrorReporter initCrashReportingInternal(Context context, String appId, String appName, String crashReportUrl) {
        com.facebook.acra.ErrorReporter reporter = ACRA.init(new DefaultAcraConfig(context, crashReportUrl, false));
        com.facebook.acra.ErrorReporter.putCustomData("app", appName);
        com.facebook.acra.ErrorReporter.putCustomData("fb_app_id", appId);
        return reporter;
    }
}
