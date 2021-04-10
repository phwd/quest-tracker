package com.oculus.security.basecomponent;

import X.AnonymousClass0Lh;
import X.AnonymousClass132;
import X.AnonymousClass13m;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends AnonymousClass132 {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    @Override // X.AnonymousClass132
    public final void A02() {
        super.A02();
        this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, AnonymousClass0Lh.get(this));
    }
}
