package com.oculus.security.basecomponent;

import X.AbstractC0096Hu;
import X.AbstractServiceC0390gr;
import X.XT;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends AbstractServiceC0390gr {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

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
