package com.oculus.security.basecomponent;

import X.AbstractIntentServiceC0389gq;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicIntentService extends AbstractIntentServiceC0389gq {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
}
