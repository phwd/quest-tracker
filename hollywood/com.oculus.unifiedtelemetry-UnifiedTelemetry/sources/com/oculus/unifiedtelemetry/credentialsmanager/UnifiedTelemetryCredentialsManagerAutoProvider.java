package com.oculus.unifiedtelemetry.credentialsmanager;

import X.AbstractC0097Hv;
import com.facebook.annotations.Generated;
import com.oculus.authapi.inject.OVRAuthModule;

@Generated({"By: InjectorProcessor"})
public class UnifiedTelemetryCredentialsManagerAutoProvider extends AbstractC0097Hv<UnifiedTelemetryCredentialsManager> {
    public final Object get() {
        return new UnifiedTelemetryCredentialsManager(this, OVRAuthModule.A00(this));
    }
}
