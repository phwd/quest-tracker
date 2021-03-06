package com.oculus.logging.utils;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;

@InjectorModule
public class UtilsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(EventManagerImpl.class)));
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(EventManager.class)));
        public static final int _UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(StorageLoggingUtils.class)));
    }

    @ProviderMethod
    static EventManager provideEventManager(EventManagerImpl eventManagerImpl) {
        return eventManagerImpl;
    }

    @AutoGeneratedFactoryMethod
    public static final EventManager _UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideEventManager(EventManagerImpl._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_ACCESS_METHOD(injectorLike));
    }
}
