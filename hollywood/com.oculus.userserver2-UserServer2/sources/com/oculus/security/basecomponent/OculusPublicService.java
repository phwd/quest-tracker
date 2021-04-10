package com.oculus.security.basecomponent;

import X.BZ;
import X.IX;
import X.fC;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends fC {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    @Override // X.fC
    public final void A02() {
        super.A02();
        this.mOculusIntentLogger = (OculusIntentLogger) IX.A00(36, BZ.get(this));
    }
}
