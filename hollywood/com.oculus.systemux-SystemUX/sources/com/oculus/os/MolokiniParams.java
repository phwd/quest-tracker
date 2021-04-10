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

    public boolean isChargerPlugged() {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getCombinedBatteryLevel() {
        throw new RuntimeException("Stub!");
    }

    public ChargingStatus getChargingStatus() {
        throw new RuntimeException("Stub!");
    }

    public boolean getConnectionStatus() {
        throw new RuntimeException("Stub!");
    }
}
