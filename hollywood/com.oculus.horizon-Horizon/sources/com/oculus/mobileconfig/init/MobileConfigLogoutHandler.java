package com.oculus.mobileconfig.init;

import X.AbstractC06600ny;
import X.AnonymousClass0RX;
import X.AnonymousClass0o1;
import X.AnonymousClass0p1;
import X.AnonymousClass1ar;
import X.C09311at;
import com.facebook.ultralight.Dependencies;
import com.oculus.auth.handler.LogoutHandler;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID"})
public class MobileConfigLogoutHandler implements LogoutHandler {
    public static final int NUM_USERS_DATA_TO_SAVE = 5;
    public final Provider<AbstractC06600ny> mMobileConfigFactoryProvider;
    public final AnonymousClass0p1<MobileConfigInit> mMobileConfigInitLazy;
    public final Provider<C09311at> mMobileConfigManagerHolderProvider;

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        MobileConfigInit mobileConfigInit = this.mMobileConfigInitLazy.get();
        this.mMobileConfigManagerHolderProvider.get().deleteOldUserData(5);
        synchronized (mobileConfigInit) {
            AnonymousClass1ar r2 = (AnonymousClass1ar) mobileConfigInit.mMobileConfigFactoryProvider.get();
            AnonymousClass0RX r1 = r2.A06;
            if (r1 instanceof C09311at) {
                ((C09311at) r1).A01(new AnonymousClass0o1(), r2);
            }
        }
        AnonymousClass1ar.A03((AnonymousClass1ar) this.mMobileConfigFactoryProvider.get());
    }

    @Inject
    public MobileConfigLogoutHandler(Provider<C09311at> provider, Provider<AbstractC06600ny> provider2, AnonymousClass0p1<MobileConfigInit> r3) {
        this.mMobileConfigManagerHolderProvider = provider;
        this.mMobileConfigFactoryProvider = provider2;
        this.mMobileConfigInitLazy = r3;
    }
}
