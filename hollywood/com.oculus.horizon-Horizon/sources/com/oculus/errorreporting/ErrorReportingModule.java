package com.oculus.errorreporting;

import X.AbstractC06640p5;
import X.AnonymousClass0J5;
import X.AnonymousClass0Pi;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@InjectorModule
public class ErrorReportingModule extends AnonymousClass0J5 {
    public static volatile IErrorReporter _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE;

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForErrorReportingModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID = 428;
    }

    @AutoGeneratedFactoryMethod
    public static final IErrorReporter A00(AbstractC06640p5 r3) {
        if (_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE == null) {
            synchronized (IErrorReporter.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        r3.getApplicationInjector();
                        _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE = ErrorReporter.A01(null);
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE;
    }
}
