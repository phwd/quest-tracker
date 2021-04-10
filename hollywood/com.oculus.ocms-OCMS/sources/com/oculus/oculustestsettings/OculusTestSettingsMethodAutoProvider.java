package com.oculus.oculustestsettings;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusTestSettingsMethodAutoProvider extends AbstractProvider<OculusTestSettings> {
    @Override // javax.inject.Provider
    public OculusTestSettings get() {
        return OculusTestSettingsModule.provideOculusTestSettings(OculusTestSettingsDefaultImpl._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_ACCESS_METHOD(this));
    }
}
