package com.oculus.config.duc;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pp;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.BindConstant;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.google.inject.name.Named;
import com.oculus.config.ConfigController;
import com.oculus.config.annotations.GatekeeperSet;
import javax.inject.Provider;

@InjectorModule
public abstract class DUCModule extends AnonymousClass0J5 {
    @Named(ENABLE_CACHE_GATING)
    @BindConstant
    public static final String ENABLE_CACHE_GATING = "oculus_duc_local_cache_gating_mobile";

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_config_duc_DUCHelper_GkWrapper_ULSEP_BINDING_ID = 233;
        public static final int _UL__ULSEP_com_oculus_config_duc_DUCHelper_ULSEP_BINDING_ID = 239;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_BINDING_ID = 551;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_BINDING_ID = 526;
    }

    @GatekeeperSet
    @AddToMultiBind
    public abstract String addEnableCacheGatingGk(@Named("oculus_duc_local_cache_gating_mobile") String str);

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(551, r2);
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_lang_String_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(526, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Boolean _UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (Boolean) AnonymousClass117.A00(551, r1);
    }

    @AutoGeneratedAccessMethod
    public static final String _UL__ULSEP_java_lang_String_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (String) AnonymousClass117.A00(526, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(551, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_lang_String_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(526, r2);
    }

    @Named(ENABLE_CACHE_GATING)
    @ProviderMethod
    public static Boolean provideEnableCacheGatingGk(ConfigController configController) {
        return configController.getGatekeeper(ENABLE_CACHE_GATING, false);
    }

    @AutoGeneratedFactoryMethod
    public static final Boolean _UL__ULSEP_java_lang_Boolean_ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_duc_ULUNDERSCORE_local_ULUNDERSCORE_cache_ULUNDERSCORE_gating_ULUNDERSCORE_mobile_ULSEP_FACTORY_METHOD(AbstractC06640p5 r2) {
        return ConfigController._UL__ULSEP_com_oculus_config_ConfigController_ULSEP_ACCESS_METHOD(r2).getGatekeeper(ENABLE_CACHE_GATING, false);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForDUCModule {
        public static void bind(AnonymousClass0Pp r0) {
        }
    }
}
