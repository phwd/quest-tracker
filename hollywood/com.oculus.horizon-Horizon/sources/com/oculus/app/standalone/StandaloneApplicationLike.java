package com.oculus.app.standalone;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0J2;
import X.AnonymousClass0JF;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.AnonymousClass117;
import X.C003108z;
import X.C03160cK;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.facebook.acra.ACRA;
import com.facebook.acra.CustomReportDataSupplier;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.config.DefaultAcraConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.base.app.AppInfo;
import com.oculus.base.app.AppInfoModule;
import com.oculus.base.app.ApplicationLike;
import com.oculus.common.build.BuildConfig;
import com.oculus.common.init.AppInitLock;
import com.oculus.common.init.INeedInit;
import com.oculus.common.init.impl.AppInitializer;
import com.oculus.debug.DebugMode;
import com.oculus.managed.ManagedMode;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
public class StandaloneApplicationLike implements ApplicationLike {
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AppInfo mAppInfo;
    @Inject
    @Eager
    public final AppInitLock mAppInitLock;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public final DebugMode mDebugMode;

    @Override // com.oculus.base.app.ApplicationLike
    public final void A5v(Application application) {
        String str;
        String str2;
        int i = 4;
        if (this.mDebugMode.A02()) {
            i = 2;
        }
        AnonymousClass0NO.A00(i);
        AppInitializer appInitializer = (AppInitializer) AnonymousClass0J2.A04(206, this._UL_mInjectionContext);
        Context context = this.mContext;
        AppInfo appInfo = this.mAppInfo;
        String str3 = appInfo.appId;
        String str4 = appInfo.appName;
        if (this.mCredentialsProvider.get() != null) {
            str = this.mCredentialsProvider.get().mUserId;
        } else {
            str = null;
        }
        AnonymousClass1 r6 = new Provider<Boolean>() {
            /* class com.oculus.app.standalone.StandaloneApplicationLike.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // javax.inject.Provider
            public final Boolean get() {
                return Boolean.valueOf(((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, StandaloneApplicationLike.this._UL_mInjectionContext)).A36(36310353600053471L));
            }
        };
        HashMap hashMap = new HashMap();
        String A05 = AnonymousClass006.A05("https://www.facebook.com/mobile/generic_android_crash_logs/", str3);
        HashMap hashMap2 = new HashMap();
        ErrorReporter init = ACRA.init(new DefaultAcraConfig(context, A05, false));
        ErrorReporter.putCustomData(ErrorReportingConstants.APP_NAME_KEY, str4);
        ErrorReporter.putCustomData("fb_app_id", str3);
        if (str != null) {
            init.setUserId(str);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str5 = (String) entry.getKey();
            if (entry.getValue() == null) {
                str2 = "null";
            } else {
                str2 = (String) entry.getValue();
            }
            ErrorReporter.putCustomData(str5, str2);
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            init.putLazyCustomData((String) entry2.getKey(), (CustomReportDataSupplier) entry2.getValue());
        }
        com.oculus.errorreporting.ErrorReporter.A01(r6);
        synchronized (BreakpadManager.class) {
            if (BreakpadManager.mNativeLibraryName == null) {
                C03160cK.A05("breakpad", 0);
                BreakpadManager.mNativeLibraryName = "breakpad";
            }
            if (BreakpadManager.mCrashDirectory == null) {
                File dir = application.getDir("minidumps", 0);
                if (dir != null) {
                    BreakpadManager.install(dir.getAbsolutePath(), "", 1536000);
                    BreakpadManager.mCrashDirectory = dir;
                    BreakpadManager.setMinidumpFlags(0 | BreakpadManager.getMinidumpFlags() | 2 | 4);
                    BreakpadManager.setVersionInfo(AnonymousClass0JF.A01(), BuildConfig.VERSION_NAME, Build.FINGERPRINT);
                    BreakpadManager.setCustomData("Fingerprint", Build.FINGERPRINT, new Object[0]);
                } else {
                    throw new RuntimeException("Breakpad init failed to create crash directory: minidumps");
                }
            }
        }
        UnifiedTelemetryLogger.getInstance().init(application.getApplicationContext());
        ((Set) AnonymousClass0J2.A03(0, 298, appInitializer._UL_mInjectionContext)).size();
        for (INeedInit iNeedInit : (Set) AnonymousClass0J2.A03(0, 298, appInitializer._UL_mInjectionContext)) {
            iNeedInit.init();
        }
        AnonymousClass0QC r1 = appInitializer._UL_mInjectionContext;
        if (((ManagedMode) AnonymousClass0J2.A03(3, 380, r1)).isEnterpriseModeEnabled) {
            ((Set) AnonymousClass0J2.A03(1, 342, r1)).size();
            for (INeedInit iNeedInit2 : (Set) AnonymousClass0J2.A03(1, 342, appInitializer._UL_mInjectionContext)) {
                iNeedInit2.init();
            }
        }
        ((Set) AnonymousClass0J2.A03(2, 474, appInitializer._UL_mInjectionContext)).size();
        for (INeedInit iNeedInit3 : (Set) AnonymousClass0J2.A03(2, 474, appInitializer._UL_mInjectionContext)) {
            iNeedInit3.init();
        }
        this.mAppInitLock.A00();
    }

    @Inject
    public StandaloneApplicationLike(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mContext = C003108z.A02(r3);
        this.mAppInfo = AppInfoModule.A00(r3);
        this.mDebugMode = DebugMode.A00(r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mAppInitLock = (AppInitLock) AnonymousClass117.A00(139, r3);
    }
}
