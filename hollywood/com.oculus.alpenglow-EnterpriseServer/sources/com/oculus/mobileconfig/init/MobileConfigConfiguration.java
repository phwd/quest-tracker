package com.oculus.mobileconfig.init;

import X.AbstractC02890az;
import X.AbstractC02990bJ;
import X.AbstractC07240oz;
import X.AnonymousClass006;
import X.AnonymousClass0Hv;
import X.AnonymousClass0Ld;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.AnonymousClass0ST;
import X.AnonymousClass12o;
import X.AnonymousClass12r;
import X.C01730Le;
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

@Dependencies({"_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID"})
public class MobileConfigConfiguration implements Configuration {
    public static final String EVENT_MOBILE_CONFIG_FETCH_RESULT = "oculus_mobile_config_fetch";
    public static final String EXTRA_FETCH_RESULT = "fetch_result";
    public static String TAG;
    public AnonymousClass0R7 _UL_mInjectionContext;
    @Inject
    public final AbstractC07240oz<Credentials> mCredentialsProvider;
    @Inject
    public final AbstractC07240oz<AbstractC02890az> mMobileConfigFactoryProvider;
    @Inject
    public final AbstractC07240oz<AnonymousClass12r> mMobileConfigManagerHolderProvider;
    @Inject
    public final AbstractC07240oz<OkTigonServiceHolder> mOkTigonServiceHolderProvider;

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: X.0Hv<?>, X.0Hv<java.lang.Void> */
    @Override // com.oculus.config.interfaces.Configuration
    public final AnonymousClass0Hv<Void> fetchAsync() {
        final AnonymousClass12r r0 = this.mMobileConfigManagerHolderProvider.get();
        if (r0 == null) {
            return AnonymousClass0Hv.A07;
        }
        return AnonymousClass0Hv.A00(new Callable<Void>() {
            /* class com.oculus.mobileconfig.init.MobileConfigConfiguration.AnonymousClass1 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // java.util.concurrent.Callable
            public final Void call() throws Exception {
                MobileConfigConfiguration mobileConfigConfiguration = MobileConfigConfiguration.this;
                AnonymousClass0ST r5 = r0;
                AnonymousClass0ST r3 = r5;
                ((MobileConfigInit) AnonymousClass0Lh.A03(0, 32, mobileConfigConfiguration._UL_mInjectionContext)).A01(mobileConfigConfiguration.mCredentialsProvider.get(), (AnonymousClass12o) mobileConfigConfiguration.mMobileConfigFactoryProvider.get());
                if (r5 instanceof AnonymousClass12r) {
                    r3 = ((AnonymousClass12r) r3).A00();
                }
                if (r3 instanceof MobileConfigManagerHolderImpl) {
                    MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl = (MobileConfigManagerHolderImpl) r3;
                    if (!mobileConfigManagerHolderImpl.isNetworkServiceSet()) {
                        MobileConfigDependenciesInFBApps.setNetworkService(mobileConfigManagerHolderImpl, mobileConfigConfiguration.mOkTigonServiceHolderProvider.get(), true);
                    }
                }
                boolean updateConfigs = r5.updateConfigs();
                Event A1z = ((EventManager) AnonymousClass0Lh.A03(1, 103, mobileConfigConfiguration._UL_mInjectionContext)).A1z(MobileConfigConfiguration.EVENT_MOBILE_CONFIG_FETCH_RESULT);
                A1z.A10(MobileConfigConfiguration.EXTRA_FETCH_RESULT, updateConfigs);
                A1z.A5i();
                return null;
            }
        }, AnonymousClass0Hv.A0C);
    }

    @Inject
    public MobileConfigConfiguration(AbstractC02990bJ r3, MobileConfigInitOptions mobileConfigInitOptions) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
        this.mMobileConfigManagerHolderProvider = new C01730Le(55, r3);
        this.mMobileConfigFactoryProvider = new C01730Le(116, r3);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r3);
        this.mOkTigonServiceHolderProvider = new AnonymousClass0Ld(114, r3);
        TAG = AnonymousClass006.A05(mobileConfigInitOptions.mTagPrefix, "MobileConfigConfiguration");
    }
}
