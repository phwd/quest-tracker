package com.oculus.errorreporting;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.common.errorreporting.FbErrorReporter;
import com.facebook.common.errorreporting.FbErrorReporterImpl;
import com.facebook.common.util.TriState;
import com.google.inject.util.Providers;
import com.oculus.common.build.BuildConstants;
import java.util.Map;
import java.util.Random;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter {
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

    ErrorReporter(FbErrorReporter fbErrorReporter) {
        this.mErrorReporter = fbErrorReporter;
    }

    public static void initCrashReporting(Context context, String appId, String appName, String crashReportUrl, String userId, Provider<Boolean> isTrustedUserProvider, Map<String, String> customData, Map<String, CustomReportDataSupplier> lazyCustomData) {
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
