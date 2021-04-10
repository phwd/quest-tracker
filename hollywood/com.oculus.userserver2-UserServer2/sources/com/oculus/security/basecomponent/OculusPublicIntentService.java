package com.oculus.security.basecomponent;

import X.BZ;
import X.IX;
import X.fB;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicIntentService extends fB {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    @Override // X.fB
    public void onCreate() {
        super.onCreate();
        this.mOculusIntentLogger = (OculusIntentLogger) IX.A00(36, BZ.get(this));
    }
}
