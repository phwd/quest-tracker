package com.oculus.mobileconfig.init;

import X.AbstractC02890az;
import X.AbstractC02980bI;
import X.AbstractC02990bJ;
import X.AbstractC07240oz;
import X.AnonymousClass006;
import X.AnonymousClass0Hv;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.AnonymousClass0ST;
import X.AnonymousClass0kj;
import X.AnonymousClass12o;
import X.AnonymousClass12p;
import X.AnonymousClass12r;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.mobileconfig.MobileConfigManagerParamsHolder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.credentials.Credentials;
import com.oculus.common.init.INeedInit;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID"})
@ApplicationScoped(enableScopeValidation = false)
public class MobileConfigInit implements INeedInit {
    public static String TAG;
    public static volatile MobileConfigInit _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final AbstractC02980bI<Context> mContextLazy;
    public final AbstractC07240oz<Credentials> mCredentialsProvider;
    public final AbstractC02980bI<IErrorReporter> mErrorReporterLazy;
    public final AbstractC07240oz<Locale> mLocaleProvider;
    public final AbstractC07240oz<AbstractC02890az> mMobileConfigFactoryProvider;
    public final AbstractC07240oz<PackageInfo> mPackageInfoProvider;
    public final AbstractC02980bI<AnonymousClass0kj> mXAnalyticsProvider;

    public static synchronized MobileConfigManagerHolderImpl A00(MobileConfigInit mobileConfigInit, Credentials credentials) {
        MobileConfigManagerHolderImpl createManager;
        synchronized (mobileConfigInit) {
            MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder = new MobileConfigManagerParamsHolder();
            mobileConfigManagerParamsHolder.setConsistencyLoggingEnabled(true);
            mobileConfigManagerParamsHolder.setConsistencyLoggingEveryNSec(2592000);
            mobileConfigManagerParamsHolder.setUniverseType(((MobileConfigInitOptions) AnonymousClass0Lh.A03(0, 126, mobileConfigInit._UL_mInjectionContext)).mUniverseType.getValue());
            mobileConfigManagerParamsHolder.setResponseCompressionEnabled(false);
            mobileConfigManagerParamsHolder.setQueryHashOptimization(false);
            ImmutableMap.Builder builder = new ImmutableMap.Builder();
            builder.put("locale", mobileConfigInit.mLocaleProvider.get().toString());
            ImmutableMap build = builder.build();
            MobileConfigDependenciesInFBApps mobileConfigDependenciesInFBApps = new MobileConfigDependenciesInFBApps(null, null, false, mobileConfigInit.mXAnalyticsProvider.get().A4z());
            File filesDir = mobileConfigInit.mContextLazy.get().getFilesDir();
            String str = mobileConfigInit.mPackageInfoProvider.get().versionName;
            String string = Settings.Secure.getString(mobileConfigInit.mContextLazy.get().getContentResolver(), "android_id");
            if (string == null) {
                string = "";
            }
            int length = string.length();
            if (length < 16) {
                StringBuilder sb = new StringBuilder(16);
                while (length < 16) {
                    sb.append('0');
                    length++;
                }
                sb.append(string);
                string = sb.toString();
            }
            createManager = mobileConfigDependenciesInFBApps.createManager(filesDir, str, string, credentials.mUserId, "", mobileConfigInit.mContextLazy.get().getAssets(), false, mobileConfigManagerParamsHolder, build);
        }
        return createManager;
    }

    public final synchronized void A01(Credentials credentials, AnonymousClass12o r4) {
        AnonymousClass0ST r1 = r4.A06;
        if (r1 instanceof AnonymousClass12r) {
            AnonymousClass12r r12 = (AnonymousClass12r) r1;
            if (r12.A00() instanceof AnonymousClass12p) {
                r12.A01(A00(this, credentials), r4);
            }
        }
    }

    @Override // com.oculus.common.init.INeedInit
    public final void A5C() {
        synchronized (this) {
            try {
                AnonymousClass12o r3 = (AnonymousClass12o) this.mMobileConfigFactoryProvider.get();
                final Credentials credentials = this.mCredentialsProvider.get();
                if (credentials != null) {
                    r3.A04();
                    if (r3.A0A.get()) {
                        AnonymousClass0Hv.A00(new Callable<Void>() {
                            /* class com.oculus.mobileconfig.init.MobileConfigInit.AnonymousClass1 */

                            /* Return type fixed from 'java.lang.Object' to match base method */
                            @Override // java.util.concurrent.Callable
                            public final Void call() throws Exception {
                                MobileConfigInit mobileConfigInit = MobileConfigInit.this;
                                mobileConfigInit.A01(credentials, (AnonymousClass12o) mobileConfigInit.mMobileConfigFactoryProvider.get());
                                return null;
                            }
                        }, AnonymousClass0Hv.A0C);
                    } else {
                        ((AnonymousClass12r) r3.A06).A01(A00(this, credentials), r3);
                        AnonymousClass12o.A01(r3);
                    }
                }
                r3.A06.isValid();
            } catch (Exception e) {
                if (!(e instanceof IOException)) {
                    this.mErrorReporterLazy.get();
                    e.getMessage();
                }
            }
        }
    }

    @Inject
    public MobileConfigInit(AbstractC02990bJ r4, @ForAppContext AbstractC02980bI<Context> r5, AbstractC02980bI<AnonymousClass0kj> r6, AbstractC02980bI<PackageInfo> r7, AbstractC07240oz<AbstractC02890az> r8, AbstractC07240oz<Credentials> r9, AbstractC07240oz<Locale> r10, AbstractC02980bI<IErrorReporter> r11) {
        AnonymousClass0R7 r2 = new AnonymousClass0R7(1, r4);
        this._UL_mInjectionContext = r2;
        TAG = AnonymousClass006.A05(((MobileConfigInitOptions) AnonymousClass0Lh.A03(0, 126, r2)).mTagPrefix, "MobileConfigInit");
        this.mContextLazy = r5;
        this.mXAnalyticsProvider = r6;
        this.mPackageInfoProvider = r7;
        this.mMobileConfigFactoryProvider = r8;
        this.mCredentialsProvider = r9;
        this.mLocaleProvider = r10;
        this.mErrorReporterLazy = r11;
    }
}
