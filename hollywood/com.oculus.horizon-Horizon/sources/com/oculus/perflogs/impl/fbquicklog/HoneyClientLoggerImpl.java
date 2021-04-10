package com.oculus.perflogs.impl.fbquicklog;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0TF;
import X.AnonymousClass1Yh;
import X.AnonymousClass1n9;
import com.facebook.ultralight.Dependencies;
import com.oculus.horizon.capture.CaptureLogger;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_quicklog_QuicklogNameProvider_ULSEP_BINDING_ID"})
public class HoneyClientLoggerImpl implements AnonymousClass1n9 {
    public static final String MARKER_NAME = "marker_name";
    public static final String PERF = "moonlight_perf";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // X.AnonymousClass1n9
    public final void A5I(AnonymousClass1Yh r5) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(PERF);
        A22.A8q(r5.A4Y());
        A22.A15(MARKER_NAME, ((AnonymousClass0TF) AnonymousClass0J2.A03(1, 56, this._UL_mInjectionContext)).A3o(r5.getMarkerId()));
        A22.A13(CaptureLogger.EXTRA_MARKER_ID, r5.getMarkerId());
        A22.A13("instance_id", r5.A4a());
        A22.A14("time_since_boot_ms", r5.A3u());
        A22.A13("duration_ms", r5.A3L());
        A22.A13("action_id", r5.A2v());
        if (r5.A4u() && r5.A52()) {
            A22.A16("app_started_in_bg", r5.A3S());
        }
        A22.A13("value", r5.A3L());
        A22.A5L();
    }

    @Inject
    public HoneyClientLoggerImpl(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
