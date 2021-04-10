package com.oculus.security.basecomponent;

import X.fB;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureIntentService extends fB {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
}
