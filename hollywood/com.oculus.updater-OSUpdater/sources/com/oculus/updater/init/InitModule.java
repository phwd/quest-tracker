package com.oculus.updater.init;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;

@InjectorModule
public class InitModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AppInitializer.class)));
    }
}
