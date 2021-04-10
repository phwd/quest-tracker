package com.oculus.os;

import android.content.Context;

public final class NightShiftController {
    public static final int AUTO_MODE_CUSTOM = 1;
    public static final int AUTO_MODE_DISABLED = 0;
    public static final int AUTO_MODE_TWILIGHT = 2;

    public NightShiftController(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean isActivated() {
        throw new RuntimeException("Stub!");
    }

    public boolean setActivated(boolean activated) {
        throw new RuntimeException("Stub!");
    }

    public int getAutoMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean setAutoMode(int autoMode) {
        throw new RuntimeException("Stub!");
    }

    public int getCustomStartTime() {
        throw new RuntimeException("Stub!");
    }

    public boolean setCustomStartTime(int minuteOfDay) {
        throw new RuntimeException("Stub!");
    }

    public int getCustomEndTime() {
        throw new RuntimeException("Stub!");
    }

    public boolean setCustomEndTime(int minuteOfDay) {
        throw new RuntimeException("Stub!");
    }

    public int getMinimumColorTemperature() {
        throw new RuntimeException("Stub!");
    }

    public int getMaximumColorTemperature() {
        throw new RuntimeException("Stub!");
    }

    public int getColorTemperature() {
        throw new RuntimeException("Stub!");
    }

    public boolean setColorTemperature(int minuteOfDay) {
        throw new RuntimeException("Stub!");
    }
}
