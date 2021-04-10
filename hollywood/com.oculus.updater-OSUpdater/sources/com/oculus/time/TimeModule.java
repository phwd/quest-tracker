package com.oculus.time;

import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.TimeZone;

@InjectorModule
public class TimeModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Clock.class)));
        public static final int _UL__ULSEP_java_util_TimeZone_ULSEP_com_oculus_time_ForOculusTimeZone_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_util_TimeZone_ULSEP_com_oculus_time_ForOculusTimeZone_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(TimeZone.class, (Class<? extends Annotation>) ForOculusTimeZone.class)));
    }
}