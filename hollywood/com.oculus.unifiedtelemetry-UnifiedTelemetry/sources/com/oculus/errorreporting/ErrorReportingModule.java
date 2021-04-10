package com.oculus.errorreporting;

import X.AbstractC0247Xu;
import X.I0;
import X.Pj;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.oculus.errorreporting.interfaces.IErrorReporter;

@InjectorModule
public class ErrorReportingModule extends I0 {
    public static volatile IErrorReporter _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE;

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForErrorReportingModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID = 135;
    }

    @AutoGeneratedFactoryMethod
    public static final IErrorReporter A00(AbstractC0247Xu xu) {
        if (_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE == null) {
            synchronized (IErrorReporter.class) {
                Pj A00 = Pj.A00(_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE, xu);
                if (A00 != null) {
                    try {
                        xu.getApplicationInjector();
                        _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE = ErrorReporter.A00(null);
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_INSTANCE;
    }
}
