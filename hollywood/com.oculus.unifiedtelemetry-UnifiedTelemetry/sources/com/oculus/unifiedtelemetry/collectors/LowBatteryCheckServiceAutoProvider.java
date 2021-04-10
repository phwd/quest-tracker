package com.oculus.unifiedtelemetry.collectors;

import X.I2;

public class LowBatteryCheckServiceAutoProvider extends I2<LowBatteryCheckService> {
    public final boolean equals(Object obj) {
        return obj instanceof LowBatteryCheckServiceAutoProvider;
    }
}
