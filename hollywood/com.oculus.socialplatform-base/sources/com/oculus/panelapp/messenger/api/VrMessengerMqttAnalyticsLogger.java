package com.oculus.panelapp.messenger.api;

import X.AnonymousClass006;
import X.AnonymousClass1Kt;
import X.AnonymousClass1Kw;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.util.Map;

public class VrMessengerMqttAnalyticsLogger implements AnonymousClass1Kw {
    public static final String ANALYTICS_EVENT_NAME = "vr_messenger_mqtt_event";
    public final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    @Override // X.AnonymousClass1Kw
    public void reportEvent(AnonymousClass1Kt r7) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(ANALYTICS_EVENT_NAME);
        for (Map.Entry<String, String> entry : r7.A03.entrySet()) {
            analyticsEvent.setExtra(AnonymousClass006.A07("mqtt_event_", entry.getKey()), entry.getValue());
        }
        analyticsEvent.setExtra("mqtt_event_name", r7.A02);
        analyticsEvent.setExtra("mqtt_event_time", Long.valueOf(r7.A00));
        analyticsEvent.setExtra("mqtt_event_module_name", r7.A01);
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, true);
    }

    public VrMessengerMqttAnalyticsLogger(UnifiedTelemetryLogger unifiedTelemetryLogger) {
        this.mUnifiedTelemetryLogger = unifiedTelemetryLogger;
    }
}
