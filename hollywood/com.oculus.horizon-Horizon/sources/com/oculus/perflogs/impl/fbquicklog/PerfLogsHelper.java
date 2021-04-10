package com.oculus.perflogs.impl.fbquicklog;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_perflogs_impl_fbquicklog_HoneyClientLoggerImpl_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_foreground_ApplicationForegroundTracker_ULSEP_BINDING_ID"})
@ApplicationScoped
public class PerfLogsHelper {
    public static volatile PerfLogsHelper _UL__ULSEP_com_oculus_perflogs_impl_fbquicklog_PerfLogsHelper_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public PerfLogsHelper(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
