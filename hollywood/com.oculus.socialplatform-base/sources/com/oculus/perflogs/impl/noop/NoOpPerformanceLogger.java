package com.oculus.perflogs.impl.noop;

import X.AbstractC03180ld;
import X.AnonymousClass0Qj;
import X.AnonymousClass0VB;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.oculus.perflogs.OculusPerformanceLogger;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;

@Dependencies({})
@ThreadSafe
@ApplicationScoped
public final class NoOpPerformanceLogger implements OculusPerformanceLogger {
    public static volatile NoOpPerformanceLogger _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE;

    @Override // com.oculus.perflogs.OculusPerformanceLogger
    public void markerEnd(int i, short s) {
    }

    @Override // com.oculus.perflogs.OculusPerformanceLogger
    public void markerStart(int i) {
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(91, r2);
    }

    @AutoGeneratedAccessMethod
    public static final NoOpPerformanceLogger _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (NoOpPerformanceLogger) AnonymousClass1TK.A00(91, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final NoOpPerformanceLogger _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_FACTORY_METHOD(AnonymousClass0lg r3, Object obj) {
        if (_UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE == null) {
            synchronized (NoOpPerformanceLogger.class) {
                AnonymousClass0Qj A00 = AnonymousClass0Qj.A00(_UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE, r3);
                if (A00 != null) {
                    try {
                        r3.getApplicationInjector();
                        _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE = new NoOpPerformanceLogger();
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return new AnonymousClass0VB(91, r2);
    }
}
