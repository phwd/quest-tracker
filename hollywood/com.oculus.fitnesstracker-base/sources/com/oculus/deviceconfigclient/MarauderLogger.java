package com.oculus.deviceconfigclient;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfigservice.client_base.MobileConfigMarauderLogger;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class MarauderLogger implements MobileConfigMarauderLogger {
    private Context mContext;

    public MarauderLogger(Context context) {
        this.mContext = context;
    }

    @Override // com.facebook.mobileconfigservice.client_base.MobileConfigMarauderLogger
    public final void logEvent(String str, Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            analyticsEvent.setExtra(entry.getKey(), entry.getValue());
        }
        UnifiedTelemetryLogger.getInstance(this.mContext).reportEvent(analyticsEvent, false);
    }
}
