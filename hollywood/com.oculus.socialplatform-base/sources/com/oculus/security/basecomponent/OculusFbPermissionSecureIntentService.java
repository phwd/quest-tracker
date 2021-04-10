package com.oculus.security.basecomponent;

import X.AbstractC03010kk;
import X.AbstractIntentServiceC02960kd;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.content.Context;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureIntentService extends AbstractIntentServiceC02960kd {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureIntentService(String str) {
        super(str);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static final void _UL_injectMe(Context context, OculusFbPermissionSecureIntentService oculusFbPermissionSecureIntentService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusFbPermissionSecureIntentService);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, OculusFbPermissionSecureIntentService oculusFbPermissionSecureIntentService) {
        oculusFbPermissionSecureIntentService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r0);
    }

    @Override // X.AbstractIntentServiceC02740jl
    public AbstractC03010kk getIntentLogger() {
        return this.mOculusIntentLogger;
    }

    @Override // X.AbstractIntentServiceC02740jl
    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }
}
