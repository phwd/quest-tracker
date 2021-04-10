package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusPublicIntentServiceAutoProvider extends AbstractComponentProvider<OculusPublicIntentService> {
    public void inject(OculusPublicIntentService oculusPublicIntentService) {
        OculusPublicIntentService._UL_staticInjectMe(this, oculusPublicIntentService);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusPublicIntentServiceAutoProvider;
    }
}
