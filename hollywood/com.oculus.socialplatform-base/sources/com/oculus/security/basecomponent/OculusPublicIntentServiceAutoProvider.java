package com.oculus.security.basecomponent;

import X.AnonymousClass0VK;

public class OculusPublicIntentServiceAutoProvider extends AnonymousClass0VK<OculusPublicIntentService> {
    public boolean equals(Object obj) {
        return obj instanceof OculusPublicIntentServiceAutoProvider;
    }

    public void inject(OculusPublicIntentService oculusPublicIntentService) {
        OculusPublicIntentService._UL_staticInjectMe(this, oculusPublicIntentService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        OculusPublicIntentService._UL_staticInjectMe(this, (OculusPublicIntentService) obj);
    }
}
