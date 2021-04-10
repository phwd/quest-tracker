package com.oculus.security.basecomponent;

import X.AnonymousClass0Lh;
import X.AnonymousClass133;
import X.AnonymousClass13m;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicIntentService extends AnonymousClass133 {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    @Override // X.AnonymousClass133
    public void onCreate() {
        super.onCreate();
        this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass13m.A00(23, AnonymousClass0Lh.get(this));
    }
}
