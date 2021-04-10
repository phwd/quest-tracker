package com.oculus.executors;

import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

@InjectorModule
public class ExecutorsModule extends AbstractLibraryModule {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Handler.class, (Class<? extends Annotation>) ForUiThread.class)));
        public static final int _UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Looper.class, (Class<? extends Annotation>) ForUiThread.class)));
        public static final int _UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(OculusThreadExecutor.class)));
    }

    @AutoGeneratedFactoryMethod
    public static final Looper _UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideUiThreadLooper();
    }

    @AutoGeneratedFactoryMethod
    public static final OculusThreadExecutor _UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideOculusThreadExecutor();
    }

    @ForUiThread
    @ProviderMethod
    static Looper provideUiThreadLooper() {
        return Looper.getMainLooper();
    }

    @ProviderMethod
    static OculusThreadExecutor provideOculusThreadExecutor() {
        return OculusThreadExecutor.getInstance();
    }
}