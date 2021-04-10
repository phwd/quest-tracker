package com.oculus.security.basecomponent;

import X.AbstractC04590iB;
import X.AnonymousClass0J2;
import X.AnonymousClass117;
import X.AnonymousClass1UL;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureService extends AnonymousClass1UL {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureService() {
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AnonymousClass1U9
    public final void doCreate() {
        super.doCreate();
        this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, AnonymousClass0J2.get(this));
    }

    @Override // X.AnonymousClass1U9
    public final AbstractC04590iB getIntentLogger() {
        return this.mOculusIntentLogger;
    }
}
