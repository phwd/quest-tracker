package com.oculus.vrshell;

public class BluetoothDeviceInfo {
    private final String address;
    private final String deviceClass;
    private final String majorDeviceClass;
    private final String name;
    private final String type;

    public BluetoothDeviceInfo(String str, String str2, String str3, String str4, String str5) {
        this.name = str;
        this.address = str2;
        this.deviceClass = str3;
        this.majorDeviceClass = str4;
        this.type = str5;
    }
}
