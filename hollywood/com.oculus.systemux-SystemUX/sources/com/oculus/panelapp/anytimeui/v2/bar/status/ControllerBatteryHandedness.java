package com.oculus.panelapp.anytimeui.v2.bar.status;

public enum ControllerBatteryHandedness {
    LEFT("left"),
    RIGHT("right");
    
    private String ipcIdentifier;

    private ControllerBatteryHandedness(String str) {
        this.ipcIdentifier = str;
    }

    public static ControllerBatteryHandedness findByIpcIdentifier(String str) {
        ControllerBatteryHandedness[] values = values();
        for (ControllerBatteryHandedness controllerBatteryHandedness : values) {
            if (controllerBatteryHandedness.ipcIdentifier.equals(str)) {
                return controllerBatteryHandedness;
            }
        }
        throw new IllegalArgumentException("Invalid controller handedness " + str);
    }
}
