package com.oculus.security.basecomponent;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicIntentService extends PublicBaseIntentServiceWithSwitchOff {
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;

    private static final void _UL_injectMe(Context context, OculusPublicIntentService oculusPublicIntentService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusPublicIntentService);
        } else {
            FbInjector.injectMe(OculusPublicIntentService.class, oculusPublicIntentService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusPublicIntentService oculusPublicIntentService) {
        oculusPublicIntentService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public OculusPublicIntentService(String str) {
        super(str);
    }

    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff
    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    @Override // com.facebook.secure.service.PublicBaseIntentServiceWithSwitchOff
    public IntentLogger getIntentLogger() {
        return this.mOculusIntentLogger;
    }
}
