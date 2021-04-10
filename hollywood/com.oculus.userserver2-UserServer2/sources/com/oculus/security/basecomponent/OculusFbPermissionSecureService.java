package com.oculus.security.basecomponent;

import X.BZ;
import X.IX;
import X.S9;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureService extends S9 {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureService() {
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.fC
    public final void A02() {
        super.A02();
        this.mOculusIntentLogger = (OculusIntentLogger) IX.A00(36, BZ.get(this));
    }
}
