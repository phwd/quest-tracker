package com.oculus.companion.gattble.phonenotifications;

import android.content.Context;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;

public class Telemetry {
    private static UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public Telemetry(Context context) {
        mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
    }

    public void recordPhoneNotificationsReceivedEvent() {
        mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_mobile_received_phone_notification"), false);
    }

    public void recordPhoneNotificationsDroppedEvent(PhoneNotificationEvent phoneNotificationEvent) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_mobile_dropped_phone_notification");
        analyticsEvent.setExtra("code", Integer.valueOf(phoneNotificationEvent.getCode()));
        analyticsEvent.setExtra("description", phoneNotificationEvent.getDescription());
        mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }
}
