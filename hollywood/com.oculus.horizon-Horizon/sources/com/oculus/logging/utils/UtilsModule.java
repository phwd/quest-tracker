package com.oculus.logging.utils;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public class UtilsModule extends AnonymousClass0J5 {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForUtilsModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID = 46;
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID = 242;
        public static final int _UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID = 204;
    }

    @AutoGeneratedFactoryMethod
    public static final EventManager A00(AbstractC06640p5 r0) {
        return EventManagerImpl.A00(r0);
    }
}
