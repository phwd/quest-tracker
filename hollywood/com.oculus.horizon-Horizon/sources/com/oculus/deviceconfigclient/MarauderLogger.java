package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MarauderLogger {
    public Context mContext;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)V */
    public final void A00(Map map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("mobileconfig_service_client_data_logger");
        for (Map.Entry entry : map.entrySet()) {
            analyticsEvent.setExtra((String) entry.getKey(), entry.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }

    public MarauderLogger(Context context) {
        this.mContext = context;
    }
}
