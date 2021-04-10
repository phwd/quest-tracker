package com.oculus.os;

import android.content.Context;

public final class MolokiniParams {

    public enum ChargingStatus {
        UNKNOWN,
        CHARGING,
        NOT_CHARGING
    }

    public MolokiniParams(Context context) {
        throw new RuntimeException("Stub!");
    }

    public final int getBatteryLevel() {
        throw new RuntimeException("Stub!");
    }

    public final ChargingStatus getChargingStatus() {
        throw new RuntimeException("Stub!");
    }

    public final int getCombinedBatteryLevel() {
        throw new RuntimeException("Stub!");
    }

    public final boolean getConnectionStatus() {
        throw new RuntimeException("Stub!");
    }

    public final boolean isChargerPlugged() {
        throw new RuntimeException("Stub!");
    }
}
