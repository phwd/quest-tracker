package com.oculus.security.basecomponent;

import X.AnonymousClass0Lh;
import X.AnonymousClass13m;
import X.AnonymousClass15J;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

public abstract class OculusFbPermissionSecureService extends AnonymousClass15J {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public OculusFbPermissionSecureService() {
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AnonymousClass132
    public final void A02() {
        super.A02();
        this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, AnonymousClass0Lh.get(this));
    }
}
