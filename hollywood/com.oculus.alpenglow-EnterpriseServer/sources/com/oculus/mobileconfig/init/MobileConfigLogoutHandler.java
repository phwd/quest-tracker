package com.oculus.mobileconfig.init;

import X.AbstractC02890az;
import X.AbstractC02980bI;
import X.AbstractC07240oz;
import X.AnonymousClass0ST;
import X.AnonymousClass12o;
import X.AnonymousClass12r;
import X.C02900b2;
import com.facebook.ultralight.Dependencies;
import com.oculus.auth.handler.LogoutHandler;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID"})
public class MobileConfigLogoutHandler implements LogoutHandler {
    public static final int NUM_USERS_DATA_TO_SAVE = 5;
    public final AbstractC07240oz<AbstractC02890az> mMobileConfigFactoryProvider;
    public final AbstractC02980bI<MobileConfigInit> mMobileConfigInitLazy;
    public final AbstractC07240oz<AnonymousClass12r> mMobileConfigManagerHolderProvider;

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        MobileConfigInit mobileConfigInit = this.mMobileConfigInitLazy.get();
        this.mMobileConfigManagerHolderProvider.get().deleteOldUserData(5);
        synchronized (mobileConfigInit) {
            AnonymousClass12o r2 = (AnonymousClass12o) mobileConfigInit.mMobileConfigFactoryProvider.get();
            AnonymousClass0ST r1 = r2.A06;
            if (r1 instanceof AnonymousClass12r) {
                ((AnonymousClass12r) r1).A01(new C02900b2(), r2);
            }
        }
        AnonymousClass12o.A01((AnonymousClass12o) this.mMobileConfigFactoryProvider.get());
    }

    @Inject
    public MobileConfigLogoutHandler(AbstractC07240oz<AnonymousClass12r> r1, AbstractC07240oz<AbstractC02890az> r2, AbstractC02980bI<MobileConfigInit> r3) {
        this.mMobileConfigManagerHolderProvider = r1;
        this.mMobileConfigFactoryProvider = r2;
        this.mMobileConfigInitLazy = r3;
    }
}
