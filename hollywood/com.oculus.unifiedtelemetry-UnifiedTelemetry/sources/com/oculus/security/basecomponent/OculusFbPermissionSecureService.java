package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
import X.XP;
import X.XT;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureService extends XP {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureService() {
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractServiceC0390gr
    public final void A02() {
        super.A02();
        this.mOculusIntentLogger = OculusIntentLogger.A00(AbstractC0096Hu.get(this));
    }

    @Override // X.AbstractServiceC0390gr
    public final XT A01() {
        return this.mOculusIntentLogger;
    }
}
