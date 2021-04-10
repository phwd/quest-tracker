package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MarauderLogger {
    public Context mContext;

    public void logEvent(String str, Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            analyticsEvent.setExtra(entry.getKey(), entry.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }

    public MarauderLogger(Context context) {
        this.mContext = context;
    }
}
