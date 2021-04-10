package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfigservice.client_base.MobileConfigMarauderLogger;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MarauderLogger implements MobileConfigMarauderLogger {
    private Context mContext;

    public MarauderLogger(Context context) {
        this.mContext = context;
    }

    @Override // com.facebook.mobileconfigservice.client_base.MobileConfigMarauderLogger
    public void logEvent(String eventName, Map<String, String> eventData) {
        AnalyticsEvent event = new AnalyticsEvent(eventName);
        for (Map.Entry<String, String> data : eventData.entrySet()) {
            event.setExtra(data.getKey(), data.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(event, false);
    }
}
