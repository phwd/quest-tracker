package com.oculus.horizon.auth;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.C003108z;
import android.content.Context;
import android.content.Intent;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.receiver.BaseLoginHandler;
import com.oculus.auth.receiver.LoginHandlersService;
import com.oculus.auth.receiver.LogoutHandlersRunner;
import com.oculus.auth.service.contract.ServiceContract;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_BINDING_ID"})
public class HorizonLoginHandler implements BaseLoginHandler {
    public AnonymousClass0QC _UL_mInjectionContext;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public final void onLogin() {
        Intent intent = new Intent(this.mContext, LoginHandlersService.class);
        intent.setAction(ServiceContract.BROADCAST_LOGIN);
        this.mContext.startService(intent);
    }

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public final void onLogout() {
        ((LogoutHandlersRunner) AnonymousClass0J2.A03(0, 308, this._UL_mInjectionContext)).runLogoutHandlers();
    }

    @Inject
    public HorizonLoginHandler(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mContext = C003108z.A02(r3);
    }

    @Override // com.oculus.auth.receiver.BaseLoginHandler
    public final void onRelogin() {
        onLogin();
    }
}
