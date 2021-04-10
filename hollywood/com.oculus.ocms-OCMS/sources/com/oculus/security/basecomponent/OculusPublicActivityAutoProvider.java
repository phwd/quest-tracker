package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusPublicActivityAutoProvider extends AbstractComponentProvider<OculusPublicActivity> {
    public void inject(OculusPublicActivity oculusPublicActivity) {
        OculusPublicActivity._UL_staticInjectMe(this, oculusPublicActivity);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusPublicActivityAutoProvider;
    }
}
