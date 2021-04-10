package com.oculus.http.socketconfig;

import X.AbstractC00970Od;
import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C01150Rm;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import javax.inject.Provider;

@InjectorModule
public class SocketConfigModule extends AnonymousClass0VI {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_BINDING_ID = 79;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC00970Od _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (AbstractC00970Od) AnonymousClass1TK.A00(79, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final AbstractC00970Od _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        OculusSocketConfig oculusSocketConfig = new OculusSocketConfig();
        C01150Rm.A00(oculusSocketConfig, r1);
        return oculusSocketConfig;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_http_config_SocketConfig_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(79, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_http_config_SocketConfig_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(79, r1);
    }

    @ProviderMethod(asDefault = true)
    public static AbstractC00970Od provideSocketConfig() {
        return new OculusSocketConfig();
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForSocketConfigModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
