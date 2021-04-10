package com.oculus.http.socketconfig;

import X.AbstractC0247Xu;
import X.C0515sp;
import X.I0;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public class SocketConfigModule extends I0 {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForSocketConfigModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_BINDING_ID = 88;
    }

    @AutoGeneratedFactoryMethod
    public static final OculusSocketConfig A00() {
        return new OculusSocketConfig();
    }

    @AutoGeneratedAccessMethod
    public static final OculusSocketConfig A01(AbstractC0247Xu xu) {
        return (OculusSocketConfig) C0515sp.A00(88, xu);
    }
}