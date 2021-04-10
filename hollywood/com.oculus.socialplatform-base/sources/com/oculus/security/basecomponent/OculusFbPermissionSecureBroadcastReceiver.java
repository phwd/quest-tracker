package com.oculus.security.basecomponent;

public abstract class OculusFbPermissionSecureBroadcastReceiver extends OculusPublicBroadcastReceiver {
    public abstract String getFbPermission();

    public OculusFbPermissionSecureBroadcastReceiver(String... strArr) {
        super(strArr);
        throw new NullPointerException("getFbPermission");
    }
}
