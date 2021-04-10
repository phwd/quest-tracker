package com.oculus.horizon.usermanagerupdater;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C02600ao;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.profile.UserProfileUpdateListener;
import com.oculus.userserver.api.OculusUserManager;

@Dependencies({"_UL__ULSEP_com_oculus_userserver_api_inject_OculusUserManagerProvider_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class UserManagerUpdaterServiceStarter implements UserProfileUpdateListener {
    public static final String TAG = "UserManagerUpdaterServiceStarter";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public UserManagerUpdaterServiceStarter(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }

    @Override // com.oculus.horizon.profile.UserProfileUpdateListener
    public final void A79() {
        if (OculusUserManager.A01()) {
            Intent intent = new Intent((Context) AnonymousClass0J2.A03(1, 294, this._UL_mInjectionContext), UserManagerUpdaterService.class);
            intent.setAction(UserManagerUpdaterService.ACTION_UPDATE_USER_MANAGER);
            C02600ao.A00().A06().A00(intent, (Context) AnonymousClass0J2.A03(1, 294, this._UL_mInjectionContext));
        }
    }
}
