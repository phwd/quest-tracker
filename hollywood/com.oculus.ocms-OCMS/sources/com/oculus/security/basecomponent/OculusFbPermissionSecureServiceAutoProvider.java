package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusFbPermissionSecureServiceAutoProvider extends AbstractComponentProvider<OculusFbPermissionSecureService> {
    public void inject(OculusFbPermissionSecureService oculusFbPermissionSecureService) {
        OculusFbPermissionSecureService._UL_staticInjectMe(this, oculusFbPermissionSecureService);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusFbPermissionSecureServiceAutoProvider;
    }
}
