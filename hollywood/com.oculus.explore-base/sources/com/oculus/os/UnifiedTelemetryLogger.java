package com.oculus.os;

import android.content.Context;
import android.os.IBinder;

public class UnifiedTelemetryLogger implements IBinder.DeathRecipient {
    public static UnifiedTelemetryLogger getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void binderDied() {
        throw new RuntimeException("Stub!");
    }

    public void reportEvent(AnalyticsEvent analyticsEvent, boolean lowLatency) {
        throw new RuntimeException("Stub!");
    }
}
