package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.horizon.service_media.FileCapture;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.FunnelData;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class CaptureAnalytics {
    public static final String ACTION_CAPTURE_APP_SWITCH = "capture_app_switch";
    public static final String ACTION_CAPTURE_ERROR = "capture_error";
    public static final String ACTION_START_CAPTURE = "start_capture";
    public static final String ACTION_STOP_CAPTURE = "stop_capture";
    public static final String KEY_CAPTURE_ERROR = "error";
    public static final String KEY_CAPTURE_PACKAGE = "package";
    public static final String KEY_CAPTURE_TYPE = "capture_type";
    public AnonymousClass0QC _UL_mInjectionContext;

    public final void A00(@Nullable Exception exc, @Nullable FileCapture.Mode mode) {
        String message;
        FunnelData A24 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A24();
        String str = "";
        if (exc == null) {
            message = str;
        } else {
            message = exc.getMessage();
        }
        A24.A18("error", message);
        if (mode != null) {
            str = mode.toString();
        }
        A24.A18(KEY_CAPTURE_TYPE, str);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(FunnelContract.CAPTURE_FUNNEL_NAME, ACTION_CAPTURE_ERROR, null, A24);
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A2Q(FunnelContract.CAPTURE_FUNNEL_NAME);
    }

    @Inject
    public CaptureAnalytics(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
