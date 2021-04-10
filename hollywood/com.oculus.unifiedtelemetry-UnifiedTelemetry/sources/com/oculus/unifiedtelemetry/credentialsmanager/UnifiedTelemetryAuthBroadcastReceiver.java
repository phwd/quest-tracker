package com.oculus.unifiedtelemetry.credentialsmanager;

import X.Fa;

public class UnifiedTelemetryAuthBroadcastReceiver extends Fa {
    public UnifiedTelemetryAuthBroadcastReceiver() {
        super(new UnifiedTelemetryAuthAction(), new UnifiedTelemetryAuthAction());
    }
}
