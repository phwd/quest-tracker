package com.oculus.security.basecomponent;

import com.facebook.secure.trustedapp.checker.FbPermissionCallerChecker;

public abstract class OculusFbPermissionSecureBroadcastReceiver extends OculusPublicBroadcastReceiver {
    public abstract String getFbPermission();

    public OculusFbPermissionSecureBroadcastReceiver(String... strArr) {
        super(strArr);
        addAdditionalPermissionChecker(new FbPermissionCallerChecker(getFbPermission()));
    }
}
