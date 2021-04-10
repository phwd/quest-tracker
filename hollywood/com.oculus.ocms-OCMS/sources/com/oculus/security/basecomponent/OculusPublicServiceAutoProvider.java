package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusPublicServiceAutoProvider extends AbstractComponentProvider<OculusPublicService> {
    public void inject(OculusPublicService oculusPublicService) {
        OculusPublicService._UL_staticInjectMe(this, oculusPublicService);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusPublicServiceAutoProvider;
    }
}
