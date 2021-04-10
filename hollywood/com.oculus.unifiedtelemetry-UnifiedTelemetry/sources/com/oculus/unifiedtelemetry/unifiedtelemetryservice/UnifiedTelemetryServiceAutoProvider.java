package com.oculus.unifiedtelemetry.unifiedtelemetryservice;

import X.I2;

public class UnifiedTelemetryServiceAutoProvider extends I2<UnifiedTelemetryService> {
    public final boolean equals(Object obj) {
        return obj instanceof UnifiedTelemetryServiceAutoProvider;
    }
}
