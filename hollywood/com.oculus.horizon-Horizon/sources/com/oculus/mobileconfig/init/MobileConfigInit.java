package com.oculus.mobileconfig.init;

import X.AbstractC06600ny;
import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0RX;
import X.AnonymousClass0il;
import X.AnonymousClass0p1;
import X.AnonymousClass1ar;
import X.C09311at;
import X.C09321au;
import android.content.Context;
import android.content.pm.PackageInfo;
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
import com.oculus.util.device.DeviceUtils;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID"})
@ApplicationScoped(enableScopeValidation = false)
public class MobileConfigInit implements INeedInit {
    public static String TAG;
    public static volatile MobileConfigInit _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final AnonymousClass0p1<Context> mContextLazy;
    public final Provider<Credentials> mCredentialsProvider;
    public final AnonymousClass0p1<IErrorReporter> mErrorReporterLazy;
    public final Provider<Locale> mLocaleProvider;
    public final Provider<AbstractC06600ny> mMobileConfigFactoryProvider;
    public final Provider<PackageInfo> mPackageInfoProvider;
    public final AnonymousClass0p1<AnonymousClass0il> mXAnalyticsProvider;

    public static synchronized MobileConfigManagerHolderImpl A00(MobileConfigInit mobileConfigInit, Credentials credentials) {
        MobileConfigManagerHolderImpl createManager;
        synchronized (mobileConfigInit) {
            MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder = new MobileConfigManagerParamsHolder();
            mobileConfigManagerParamsHolder.setConsistencyLoggingEnabled(true);
            mobileConfigManagerParamsHolder.setConsistencyLoggingEveryNSec(2592000);
            mobileConfigManagerParamsHolder.setUniverseType(((MobileConfigInitOptions) AnonymousClass0J2.A03(0, 589, mobileConfigInit._UL_mInjectionContext)).mUniverseType.getValue());
            mobileConfigManagerParamsHolder.setResponseCompressionEnabled(false);
            mobileConfigManagerParamsHolder.setQueryHashOptimization(false);
            ImmutableMap.Builder A01 = ImmutableMap.A01();
            A01.put("locale", mobileConfigInit.mLocaleProvider.get().toString());
            createManager = new MobileConfigDependenciesInFBApps(null, null, false, mobileConfigInit.mXAnalyticsProvider.get().A4h()).createManager(mobileConfigInit.mContextLazy.get().getFilesDir(), mobileConfigInit.mPackageInfoProvider.get().versionName, DeviceUtils.A03(mobileConfigInit.mContextLazy.get()), credentials.mUserId, "", mobileConfigInit.mContextLazy.get().getAssets(), false, mobileConfigManagerParamsHolder, A01.build());
        }
        return createManager;
    }

    public final synchronized void A01(Credentials credentials, AnonymousClass1ar r4) {
        AnonymousClass0RX r1 = r4.A06;
        if (r1 instanceof C09311at) {
            C09311at r12 = (C09311at) r1;
            if (r12.A00() instanceof C09321au) {
                r12.A01(A00(this, credentials), r4);
            }
        }
    }

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        synchronized (this) {
            try {
                AnonymousClass1ar r3 = (AnonymousClass1ar) this.mMobileConfigFactoryProvider.get();
                final Credentials credentials = this.mCredentialsProvider.get();
                if (credentials != null) {
                    r3.A07();
                    if (r3.A0A.get()) {
                        AnonymousClass0DC.A07(new Callable<Void>() {
                            /* class com.oculus.mobileconfig.init.MobileConfigInit.AnonymousClass1 */

                            /* Return type fixed from 'java.lang.Object' to match base method */
                            @Override // java.util.concurrent.Callable
                            public final Void call() throws Exception {
                                MobileConfigInit mobileConfigInit = MobileConfigInit.this;
                                mobileConfigInit.A01(credentials, (AnonymousClass1ar) mobileConfigInit.mMobileConfigFactoryProvider.get());
                                return null;
                            }
                        }, AnonymousClass0DC.A0C, null);
                    } else {
                        ((C09311at) r3.A06).A01(A00(this, credentials), r3);
                        AnonymousClass1ar.A03(r3);
                    }
                }
                r3.A06.isValid();
            } catch (Exception e) {
                if (!(e instanceof IOException)) {
                    this.mErrorReporterLazy.get().A97(TAG, e.getMessage(), e);
                }
            }
        }
    }

    @Inject
    public MobileConfigInit(AbstractC06640p5 r4, @ForAppContext AnonymousClass0p1<Context> r5, AnonymousClass0p1<AnonymousClass0il> r6, AnonymousClass0p1<PackageInfo> r7, Provider<AbstractC06600ny> provider, Provider<Credentials> provider2, Provider<Locale> provider3, AnonymousClass0p1<IErrorReporter> r11) {
        AnonymousClass0QC r2 = new AnonymousClass0QC(1, r4);
        this._UL_mInjectionContext = r2;
        TAG = AnonymousClass006.A05(((MobileConfigInitOptions) AnonymousClass0J2.A03(0, 589, r2)).mTagPrefix, "MobileConfigInit");
        this.mContextLazy = r5;
        this.mXAnalyticsProvider = r6;
        this.mPackageInfoProvider = r7;
        this.mMobileConfigFactoryProvider = provider;
        this.mCredentialsProvider = provider2;
        this.mLocaleProvider = provider3;
        this.mErrorReporterLazy = r11;
    }
}
