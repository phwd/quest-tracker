package com.oculus.unifiedtelemetry.collectors;

public class BatterySnapshot {
    public final int health;
    public boolean isCharging;
    public final boolean isPowerSave;
    public boolean isSleeping;
    public final int level;
    public final long realtimeMs;
    public final int temperature;
    public final long uptimeMs;
    public final int voltage;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004e, code lost:
        if (r7.isPowerSaveMode() == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0042, code lost:
        if (r7.isScreenOn() != false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BatterySnapshot(android.content.Context r6, @javax.annotation.Nullable android.os.PowerManager r7) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.BatterySnapshot.<init>(android.content.Context, android.os.PowerManager):void");
    }
}
