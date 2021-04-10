package com.oculus.mobileconfig.init;

import X.AbstractC06600ny;
import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0RX;
import X.AnonymousClass1ar;
import X.C01010Iv;
import X.C01020Iw;
import X.C09311at;
import com.facebook.mobileconfig.MobileConfigDependenciesInFBApps;
import com.facebook.mobileconfig.MobileConfigManagerHolderImpl;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.config.interfaces.Configuration;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import java.util.concurrent.Callable;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigConfiguration implements Configuration {
    public static final String EVENT_MOBILE_CONFIG_FETCH_RESULT = "oculus_mobile_config_fetch";
    public static final String EXTRA_FETCH_RESULT = "fetch_result";
    public static String TAG;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final Provider<Credentials> mCredentialsProvider;
    @Inject
    public final Provider<AbstractC06600ny> mMobileConfigFactoryProvider;
    @Inject
    public final Provider<C09311at> mMobileConfigManagerHolderProvider;
    @Inject
    public final Provider<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    @Override // com.oculus.config.interfaces.Configuration
    public final AnonymousClass0DC<Void> fetchAsync() {
        final C09311at r0 = this.mMobileConfigManagerHolderProvider.get();
        if (r0 == null) {
            return AnonymousClass0DC.A04(null);
        }
        return AnonymousClass0DC.A07(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigConfiguration.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                MobileConfigConfiguration mobileConfigConfiguration = MobileConfigConfiguration.this;
                AnonymousClass0RX r5 = r0;
                AnonymousClass0RX r3 = r5;
                ((MobileConfigInit) AnonymousClass0J2.A03(0, 487, mobileConfigConfiguration._UL_mInjectionContext)).A01(mobileConfigConfiguration.mCredentialsProvider.get(), (AnonymousClass1ar) mobileConfigConfiguration.mMobileConfigFactoryProvider.get());
                if (r5 instanceof C09311at) {
                    r3 = ((C09311at) r3).A00();
                }
                if (r3 instanceof MobileConfigManagerHolderImpl) {
                    MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl = (MobileConfigManagerHolderImpl) r3;
                    if (!mobileConfigManagerHolderImpl.isNetworkServiceSet()) {
                        MobileConfigDependenciesInFBApps.setNetworkService(mobileConfigManagerHolderImpl, mobileConfigConfiguration.mOkTigonServiceHolderProvider.get(), true);
                    }
                }
                boolean updateConfigs = r5.updateConfigs();
                Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, mobileConfigConfiguration._UL_mInjectionContext)).A22(MobileConfigConfiguration.EVENT_MOBILE_CONFIG_FETCH_RESULT);
                A22.A16(MobileConfigConfiguration.EXTRA_FETCH_RESULT, updateConfigs);
                A22.A5L();
                return null;
            }
        }, AnonymousClass0DC.A0C, null);
    }

    @Inject
    public MobileConfigConfiguration(AbstractC06640p5 r3, MobileConfigInitOptions mobileConfigInitOptions) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mMobileConfigManagerHolderProvider = new C01020Iw(357, r3);
        this.mMobileConfigFactoryProvider = new C01020Iw(399, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mOkTigonServiceHolderProvider = new C01010Iv(107, r3);
        TAG = AnonymousClass006.A05(mobileConfigInitOptions.mTagPrefix, "MobileConfigConfiguration");
    }
}
