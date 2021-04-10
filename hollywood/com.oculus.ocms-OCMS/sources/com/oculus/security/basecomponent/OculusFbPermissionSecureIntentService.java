package com.oculus.security.basecomponent;

import android.content.Context;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.service.FbPermissionsIntentService;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;

public abstract class OculusFbPermissionSecureIntentService extends FbPermissionsIntentService {
    @Inject
    @Eager
    private OculusIntentLogger mOculusIntentLogger;

    private static final void _UL_injectMe(Context context, OculusFbPermissionSecureIntentService oculusFbPermissionSecureIntentService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), oculusFbPermissionSecureIntentService);
        } else {
            FbInjector.injectMe(OculusFbPermissionSecureIntentService.class, oculusFbPermissionSecureIntentService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, OculusFbPermissionSecureIntentService oculusFbPermissionSecureIntentService) {
        oculusFbPermissionSecureIntentService.mOculusIntentLogger = OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public OculusFbPermissionSecureIntentService(String str) {
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
