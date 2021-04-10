package com.oculus.security.basecomponent;

import X.AnonymousClass0VK;

public class OculusPublicServiceAutoProvider extends AnonymousClass0VK<OculusPublicService> {
    public boolean equals(Object obj) {
        return obj instanceof OculusPublicServiceAutoProvider;
    }

    public void inject(OculusPublicService oculusPublicService) {
        OculusPublicService._UL_staticInjectMe(this, oculusPublicService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        OculusPublicService._UL_staticInjectMe(this, (OculusPublicService) obj);
    }
}
