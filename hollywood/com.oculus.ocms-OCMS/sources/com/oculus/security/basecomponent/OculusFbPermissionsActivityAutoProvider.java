package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusFbPermissionsActivityAutoProvider extends AbstractComponentProvider<OculusFbPermissionsActivity> {
    public void inject(OculusFbPermissionsActivity oculusFbPermissionsActivity) {
        OculusFbPermissionsActivity._UL_staticInjectMe(this, oculusFbPermissionsActivity);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusFbPermissionsActivityAutoProvider;
    }
}
