package com.oculus.security.basecomponent;

import X.AbstractC04590iB;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass117;
import X.AnonymousClass1U9;
import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicService extends AnonymousClass1U9 {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r1, OculusPublicService oculusPublicService) {
        oculusPublicService.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, r1);
    }

    public static final void _UL_injectMe(Context context, OculusPublicService oculusPublicService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), oculusPublicService);
    }

    @Override // X.AnonymousClass1U9
    public void doCreate() {
        super.doCreate();
        _UL_injectMe(this, this);
    }

    @Override // X.AnonymousClass1U9
    public AbstractC04590iB getIntentLogger() {
        return this.mOculusIntentLogger;
    }
}
