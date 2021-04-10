package com.oculus.horizon.logging;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class HorizonForegroundListenerAutoProvider extends AbstractProvider<HorizonForegroundListener> {
    @Override // javax.inject.Provider
    public HorizonForegroundListener get() {
        return new HorizonForegroundListener(this, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), OculusLogger._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_ACCESS_METHOD(this));
    }
}
