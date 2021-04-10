package com.facebook.location.battery;

import com.facebook.annotations.OkToExtend;
import com.facebook.infer.annotation.Nullsafe;

@OkToExtend
@Nullsafe(Nullsafe.Mode.LOCAL)
public class LocationBatteryExperiments {
    public long getMaxTimeoutMs() {
        return 0;
    }

    public boolean shouldForceMaxTimeout() {
        return false;
    }

    public boolean shouldRemoveLocationUpdatesOnStop() {
        return false;
    }
}
