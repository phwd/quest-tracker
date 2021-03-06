package com.oculus.executors;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass117;
import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;

@InjectorModule
public class ExecutorsModule extends AnonymousClass0J5 {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForExecutorsModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 530;
        public static final int _UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 65;
        public static final int _UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID = 333;
    }

    @AutoGeneratedAccessMethod
    public static final Handler A00(AbstractC06640p5 r1) {
        return (Handler) AnonymousClass117.A00(530, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Looper A03(AbstractC06640p5 r1) {
        return (Looper) AnonymousClass117.A00(65, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final Handler A01(AbstractC06640p5 r1) {
        return new Handler(A03(r1));
    }

    @AutoGeneratedFactoryMethod
    public static final Looper A02() {
        return Looper.getMainLooper();
    }

    @AutoGeneratedFactoryMethod
    public static final OculusThreadExecutor A04() {
        return OculusThreadExecutor.A00();
    }
}
