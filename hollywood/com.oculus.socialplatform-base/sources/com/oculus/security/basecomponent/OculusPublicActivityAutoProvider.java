package com.oculus.security.basecomponent;

import X.AnonymousClass0VK;

public class OculusPublicActivityAutoProvider extends AnonymousClass0VK<OculusPublicActivity> {
    public boolean equals(Object obj) {
        return obj instanceof OculusPublicActivityAutoProvider;
    }

    public void inject(OculusPublicActivity oculusPublicActivity) {
        OculusPublicActivity._UL_staticInjectMe(this, oculusPublicActivity);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        OculusPublicActivity._UL_staticInjectMe(this, (OculusPublicActivity) obj);
    }
}
