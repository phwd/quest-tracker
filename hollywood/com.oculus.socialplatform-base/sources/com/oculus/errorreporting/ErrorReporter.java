package com.oculus.errorreporting;

import X.AbstractC00650Hy;
import X.AnonymousClass006;
import X.AnonymousClass0I6;
import X.AnonymousClass0I7;
import X.AnonymousClass0MD;
import X.C01250Vj;
import X.C01260cs;
import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.common.util.TriState;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
public class ErrorReporter implements IErrorReporter {
    public static final String CRASH_ENDPOINT = "https://www.facebook.com/mobile/generic_android_crash_logs";
    public static final boolean DEFAULT_FAIL_HARDER = false;
    public static final int DEFAULT_HORIZON_SAMPLE_RATE = 1;
    @Deprecated
    public static ErrorReporter sErrorReporter;
    public final AbstractC00650Hy mErrorReporter;

    public static com.facebook.acra.ErrorReporter initCrashReportingInternal(Context context, String str, String str2, String str3) {
        com.facebook.acra.ErrorReporter init = ACRA.init(new DefaultAcraConfig(context, str3, false));
        com.facebook.acra.ErrorReporter.putCustomData(ErrorReportingConstants.APP_NAME_KEY, str2);
        com.facebook.acra.ErrorReporter.putCustomData("fb_app_id", str);
        return init;
    }

    public static String getDefaultEndpoint(String str) {
        return AnonymousClass006.A07("https://www.facebook.com/mobile/generic_android_crash_logs/", str);
    }

    public static AbstractC00650Hy newFbErrorReporter(Provider<Boolean> provider) {
        Provider r1 = new C01260cs(TriState.UNSET);
        if (provider != null) {
            r1 = new EmployeeProvider(provider);
        }
        return new C01250Vj(r1, new C01260cs(false), ErrorReportingExecutorFactory.getSingleThreadExecutorService(), AwakeTimeSinceBootClock.INSTANCE, new Random());
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void putCurrentUserId(String str) {
        this.mErrorReporter.putCurrentUserId(str);
    }

    public ErrorReporter(AbstractC00650Hy r1) {
        this.mErrorReporter = r1;
    }

    public static AnonymousClass0I7 getBuilder(String str, String str2, Throwable th, boolean z) {
        AnonymousClass0MD.A04(str, str2);
        AnonymousClass0I7 r1 = new AnonymousClass0I7();
        r1.A01 = str;
        r1.A02 = str2;
        r1.A03 = th;
        r1.A04 = z;
        r1.A00 = 1;
        return r1;
    }

    public static AnonymousClass0I7 getBuilder(String str, String str2, boolean z) {
        return getBuilder(str, str2, null, z);
    }

    @Deprecated
    public static ErrorReporter getInstance() {
        return getInstance(null);
    }

    @Deprecated
    public static synchronized ErrorReporter getInstance(Provider<Boolean> provider) {
        ErrorReporter errorReporter;
        synchronized (ErrorReporter.class) {
            errorReporter = sErrorReporter;
            if (errorReporter == null) {
                errorReporter = new ErrorReporter(newFbErrorReporter(provider));
                sErrorReporter = errorReporter;
            }
        }
        return errorReporter;
    }

    @Deprecated
    public static void initCrashReporting(Context context, String str, String str2) {
        initCrashReportingInternal(context, str, str2, AnonymousClass006.A07("https://www.facebook.com/mobile/generic_android_crash_logs/", str));
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
            com.facebook.acra.ErrorReporter.putCustomData(entry.getKey(), entry.getValue() == null ? "null" : entry.getValue());
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : map2.entrySet()) {
            initCrashReportingInternal.putLazyCustomData(entry2.getKey(), entry2.getValue());
        }
        getInstance(provider);
    }

    public static void initCrashReporting(Context context, String str, String str2, @Nullable String str3, Provider<Boolean> provider, Map<String, String> map) {
        initCrashReporting(context, str, str2, AnonymousClass006.A07("https://www.facebook.com/mobile/generic_android_crash_logs/", str), str3, provider, map);
    }

    private void softReport(AnonymousClass0I6 r2) {
        this.mErrorReporter.AAT(r2);
    }

    @Deprecated
    public static void softReport(String str, String str2) {
        getInstance(null).softReport(new AnonymousClass0I6(getBuilder(str, str2, false)));
    }

    @Deprecated
    public static void softReport(String str, String str2, Throwable th) {
        getInstance(null).softReport(new AnonymousClass0I6(getBuilder(str, str2, th, false)));
    }

    @Deprecated
    public static void softReportWithCrash(String str, String str2) {
        getInstance(null).softReport(new AnonymousClass0I6(getBuilder(str, str2, true)));
    }

    @Deprecated
    public static void softReportWithCrash(String str, String str2, Throwable th) {
        getInstance(null).softReport(new AnonymousClass0I6(getBuilder(str, str2, th, true)));
    }

    @Deprecated
    public static void softReportWithCrash(String str, Throwable th) {
        getInstance(null).softReport(new AnonymousClass0I6(getBuilder(str, th.getMessage(), th, true)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String str, String str2) {
        this.mErrorReporter.AAT(new AnonymousClass0I6(getBuilder(str, str2, false)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softError(String str, String str2, Throwable th) {
        this.mErrorReporter.AAT(new AnonymousClass0I6(getBuilder(str, str2, th, false)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String str, String str2) {
        this.mErrorReporter.AAT(new AnonymousClass0I6(getBuilder(str, str2, true)));
    }

    @Override // com.oculus.errorreporting.interfaces.IErrorReporter
    public void softErrorWithCrash(String str, String str2, Throwable th) {
        this.mErrorReporter.AAT(new AnonymousClass0I6(getBuilder(str, str2, th, true)));
    }

    public static final class EmployeeProvider implements Provider<TriState> {
        public Provider<Boolean> mIsTrustedUserProvider;

        public EmployeeProvider(Provider<Boolean> provider) {
            this.mIsTrustedUserProvider = provider;
        }

        @Override // javax.inject.Provider
        public TriState get() {
            return TriState.valueOf(this.mIsTrustedUserProvider.get());
        }
    }
}
