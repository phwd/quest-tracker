package com.oculus.security.basecomponent;

import X.AnonymousClass0J2;
import X.AnonymousClass117;
import X.AnonymousClass1U8;
import android.annotation.SuppressLint;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;

@SuppressLint({"EndpointWithoutSwitchOff"})
public abstract class OculusPublicIntentService extends AnonymousClass1U8 {
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;

    @Override // X.AnonymousClass1U8
    public void onCreate() {
        super.onCreate();
        this.mOculusIntentLogger = (OculusIntentLogger) AnonymousClass117.A00(172, AnonymousClass0J2.get(this));
    }

    public OculusPublicIntentService(String str) {
        super(str);
    }
}
