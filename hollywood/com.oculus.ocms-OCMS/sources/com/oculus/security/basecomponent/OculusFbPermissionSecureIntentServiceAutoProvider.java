package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusFbPermissionSecureIntentServiceAutoProvider extends AbstractComponentProvider<OculusFbPermissionSecureIntentService> {
    public void inject(OculusFbPermissionSecureIntentService oculusFbPermissionSecureIntentService) {
        OculusFbPermissionSecureIntentService._UL_staticInjectMe(this, oculusFbPermissionSecureIntentService);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusFbPermissionSecureIntentServiceAutoProvider;
    }
}
