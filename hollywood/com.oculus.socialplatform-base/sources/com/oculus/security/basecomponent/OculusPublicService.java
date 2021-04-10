package com.oculus.security.basecomponent;

import X.AbstractC03010kk;
import X.AbstractServiceC02750jm;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends AbstractServiceC02750jm {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public static final void _UL_injectMe(Context context, OculusPublicService oculusPublicService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), oculusPublicService);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, OculusPublicService oculusPublicService) {
        oculusPublicService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(r0);
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
