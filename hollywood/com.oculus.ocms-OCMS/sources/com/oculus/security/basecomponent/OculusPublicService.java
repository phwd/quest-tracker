package com.oculus.security.basecomponent;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.service.PublicBaseServiceWithSwitchOff;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends PublicBaseServiceWithSwitchOff {
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;

    private static final void _UL_injectMe(Context context, OculusPublicService oculusPublicService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusPublicService);
        } else {
            FbInjector.injectMe(OculusPublicService.class, oculusPublicService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusPublicService oculusPublicService) {
        oculusPublicService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    @Override // com.facebook.secure.service.PublicBaseServiceWithSwitchOff
    public IntentLogger getIntentLogger() {
        return this.mOculusIntentLogger;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.service.PublicBaseServiceWithSwitchOff
    public void doCreate() {
        super.doCreate();
        _UL_injectMe(this, this);
    }
}
