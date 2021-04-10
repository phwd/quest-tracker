package com.oculus.horizon.platformsdk.dumper;

import X.AnonymousClass0Qr;
import X.AnonymousClass0VI;
import com.facebook.inject.AddToMultiBind;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public abstract class DumperPluginModule extends AnonymousClass0VI {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_horizon_platformsdk_dumper_DeeplinkDumperPlugin_ULSEP_BINDING_ID = 77;
    }

    @AddToMultiBind
    public abstract DeeplinkDumperPlugin addEndpointDumperPlugin(DeeplinkDumperPlugin deeplinkDumperPlugin);

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForDumperPluginModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }
}
