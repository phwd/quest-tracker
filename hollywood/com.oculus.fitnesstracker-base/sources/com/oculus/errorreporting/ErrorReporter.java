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
public final class ErrorReporter {
    @Deprecated
    private static ErrorReporter sErrorReporter;
    private final FbErrorReporter mErrorReporter;

    /* access modifiers changed from: package-private */
    public static final class EmployeeProvider implements Provider<TriState> {
        private Provider<Boolean> mIsTrustedUserProvider;

        /* synthetic */ EmployeeProvider(Provider provider, byte b) {
            this(provider);
        }

        private EmployeeProvider(Provider<Boolean> provider) {
            this.mIsTrustedUserProvider = provider;
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        @Override // javax.inject.Provider
        public final /* bridge */ /* synthetic */ TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }
    }

    @Deprecated
    private static synchronized ErrorReporter getInstance(Provider<Boolean> provider) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            if (sErrorReporter == null) {
                Provider of = Providers.of(TriState.UNSET);
                if (provider != null) {
                    of = new EmployeeProvider(provider, (byte) 0);
                }
                sErrorReporter = new ErrorReporter(new FbErrorReporterImpl(of, Providers.of(Boolean.valueOf(BuildConstants.DEBUG)), ErrorReportingExecutorFactory.getSingleThreadExecutorService(), ErrorReportingExecutorFactory.getMonotonicClock(), new Random()));
            }
            errorReporter = sErrorReporter;
        }
        return errorReporter;
    }

    private ErrorReporter(FbErrorReporter fbErrorReporter) {
        this.mErrorReporter = fbErrorReporter;
    }

    public static void initCrashReporting(Context context, String str, String str2, String str3, String str4, Provider<Boolean> provider, Map<String, String> map, Map<String, CustomReportDataSupplier> map2) {
        com.facebook.acra.ErrorReporter init = ACRA.init(new DefaultAcraConfig(context, str3, false));
        com.facebook.acra.ErrorReporter.putCustomData("app", str2);
        com.facebook.acra.ErrorReporter.putCustomData("fb_app_id", str);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            com.facebook.acra.ErrorReporter.putCustomData(entry.getKey(), entry.getValue() == null ? "null" : entry.getValue());
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : map2.entrySet()) {
            String key = entry2.getKey();
            CustomReportDataSupplier value = entry2.getValue();
            synchronized (init.mInstanceLazyCustomParameters) {
                init.mInstanceLazyCustomParameters.put(key, value);
            }
        }
        getInstance(provider);
    }
}
