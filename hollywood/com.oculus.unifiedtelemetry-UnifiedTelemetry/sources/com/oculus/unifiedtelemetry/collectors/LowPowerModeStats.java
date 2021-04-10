package com.oculus.unifiedtelemetry.collectors;

import X.AnonymousClass06;
import X.Mu;
import javax.annotation.Nullable;

public class LowPowerModeStats {
    public static final String EVENT_NAME_LOW_POWER_MODE = "oculus_mobile_low_power_mode";
    public static final String TAG = "LowPowerModeStats";
    public BatterySnapshot mEndSnapshot;
    public BatterySnapshot mStartSnapshot;

    public static void A00(Event event, BatterySnapshot batterySnapshot, String str) {
        event.A02(AnonymousClass06.A04(str, "_battery_health"), batterySnapshot.health);
        event.A02(AnonymousClass06.A04(str, "_battery_level"), batterySnapshot.level);
        event.A02(AnonymousClass06.A04(str, "_battery_temperature"), batterySnapshot.temperature);
        event.A03(AnonymousClass06.A04(str, "_battery_realtime_ms"), batterySnapshot.realtimeMs);
        event.A03(AnonymousClass06.A04(str, "_battery_uptime_ms"), batterySnapshot.uptimeMs);
    }

    @Nullable
    public final Event A01() {
        BatterySnapshot batterySnapshot;
        Event event = new Event(EVENT_NAME_LOW_POWER_MODE);
        BatterySnapshot batterySnapshot2 = this.mStartSnapshot;
        if (batterySnapshot2 == null || (batterySnapshot = this.mEndSnapshot) == null) {
            Mu.A00(TAG, "trying to create low power mode event in intermediate state (missing start or end snapshot)");
            return null;
        }
        A00(event, batterySnapshot2, "on");
        A00(event, this.mEndSnapshot, "off");
        event.A03("on_millis", batterySnapshot.realtimeMs - batterySnapshot2.realtimeMs);
        return event;
    }
}
