package X;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

/* renamed from: X.zE  reason: case insensitive filesystem */
public final class C1422zE {
    public Context A00;

    public final void A00(Map map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("mobileconfig_service_client_data_logger");
        for (Map.Entry entry : map.entrySet()) {
            analyticsEvent.setExtra((String) entry.getKey(), entry.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.A00).reportEvent(analyticsEvent, false);
    }

    public C1422zE(Context context) {
        this.A00 = context;
    }
}
