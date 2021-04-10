package com.oculus.panelapp.anytimeui.v2.bar.status;

public class ControllerBatteryState {
    private static final int CONTROLLER_DISCONNECTED_BATTERY_PERCENTAGE = -1;
    private final float charge;
    private final boolean connected;
    private final ControllerBatteryHandedness handedness;

    public ControllerBatteryState(String str, int i) {
        this.handedness = ControllerBatteryHandedness.findByIpcIdentifier(str);
        this.connected = i != -1;
        if (!this.connected) {
            this.charge = 0.0f;
        } else {
            this.charge = ((float) Math.max(0, Math.min(100, i))) / 100.0f;
        }
    }

    public ControllerBatteryHandedness getHandedness() {
        return this.handedness;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public float getCharge() {
        return this.charge;
    }
}
