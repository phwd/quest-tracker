package com.oculus.nux.ota;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class Telemetry {
    private static UnifiedTelemetryLogger sUnifiedTelemetryLogger;

    public Telemetry(Context context) {
        sUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    public void recordEvent(String str, String str2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_" + str);
        analyticsEvent.setExtra("message", str2);
        sUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public void recordError(String str) {
        recordEvent("nux_ota_error", str);
    }

    public void recordStateChange(NuxOtaState nuxOtaState, NuxOtaState nuxOtaState2) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_nux_ota_state_changed");
        analyticsEvent.setExtra("new_state", nuxOtaState.toString());
        analyticsEvent.setExtra("old_state", nuxOtaState2.toString());
        sUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }
}
