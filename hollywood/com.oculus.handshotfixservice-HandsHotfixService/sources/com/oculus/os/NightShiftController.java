package com.oculus.os;

import android.content.Context;
import oculus.internal.NightShiftAdapter;

public final class NightShiftController {
    public static final int AUTO_MODE_CUSTOM = 1;
    public static final int AUTO_MODE_DISABLED = 0;
    public static final int AUTO_MODE_TWILIGHT = 2;
    private static final String TAG = "NightShiftController";
    private NightShiftAdapter mNightShiftAdapter;

    public NightShiftController(Context context) {
        this.mNightShiftAdapter = new NightShiftAdapter(context);
    }

    public boolean isActivated() {
        return this.mNightShiftAdapter.isActivated();
    }

    public boolean setActivated(boolean activated) {
        return this.mNightShiftAdapter.setActivated(activated);
    }

    public int getAutoMode() {
        return this.mNightShiftAdapter.getAutoMode();
    }

    public boolean setAutoMode(int autoMode) {
        return this.mNightShiftAdapter.setAutoMode(autoMode);
    }

    public int getCustomStartTime() {
        return this.mNightShiftAdapter.getCustomStartTime();
    }

    public boolean setCustomStartTime(int minuteOfDay) {
        return this.mNightShiftAdapter.setCustomStartTime(minuteOfDay);
    }

    public int getCustomEndTime() {
        return this.mNightShiftAdapter.getCustomEndTime();
    }

    public boolean setCustomEndTime(int minuteOfDay) {
        return this.mNightShiftAdapter.setCustomEndTime(minuteOfDay);
    }

    public int getMinimumColorTemperature() {
        return this.mNightShiftAdapter.getMinimumColorTemperature();
    }

    public int getMaximumColorTemperature() {
        return this.mNightShiftAdapter.getMaximumColorTemperature();
    }

    public int getColorTemperature() {
        return this.mNightShiftAdapter.getColorTemperature();
    }

    public boolean setColorTemperature(int minuteOfDay) {
        return this.mNightShiftAdapter.setColorTemperature(minuteOfDay);
    }
}
