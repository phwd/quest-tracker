package com.oculus.security.basecomponent;

import X.AbstractC03010kk;
import X.AbstractServiceC02950kb;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.content.Context;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureService extends AbstractServiceC02950kb {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureService() {
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static final void _UL_injectMe(Context context, OculusFbPermissionSecureService oculusFbPermissionSecureService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusFbPermissionSecureService);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, OculusFbPermissionSecureService oculusFbPermissionSecureService) {
        oculusFbPermissionSecureService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r0);
    }

    @Override // X.AbstractServiceC02750jm
    public void doCreate() {
        super.doCreate();
        _UL_injectMe(this, this);
    }

    @Override // X.AbstractServiceC02750jm
    public AbstractC03010kk getIntentLogger() {
        return this.mOculusIntentLogger;
    }
}
