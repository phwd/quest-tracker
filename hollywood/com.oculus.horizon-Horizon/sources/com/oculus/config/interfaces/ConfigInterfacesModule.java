package com.oculus.config.interfaces;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pp;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.DeclareMultiBinding;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import java.util.Set;
import javax.inject.Provider;

@InjectorModule
public abstract class ConfigInterfacesModule extends AnonymousClass0J5 {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID = 541;
    }

    @DeclareMultiBinding
    public abstract Set<Configuration> getConfigurationComponents();

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(541, r2);
    }

    @AutoGeneratedAccessMethod
    public static final Set _UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (Set) AnonymousClass117.A00(541, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(541, r2);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForConfigInterfacesModule {
        public static void bind(AnonymousClass0Pp r0) {
        }
    }
}
