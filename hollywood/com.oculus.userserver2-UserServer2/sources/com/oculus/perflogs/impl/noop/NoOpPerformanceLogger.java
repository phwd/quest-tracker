package com.oculus.perflogs.impl.noop;

import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.perflogs.OculusPerformanceLogger;
import javax.annotation.concurrent.ThreadSafe;

@Dependencies({})
@ThreadSafe
@ApplicationScoped
public final class NoOpPerformanceLogger implements OculusPerformanceLogger {
    public static volatile NoOpPerformanceLogger _UL__ULSEP_com_oculus_perflogs_impl_noop_NoOpPerformanceLogger_ULSEP_INSTANCE;
}
