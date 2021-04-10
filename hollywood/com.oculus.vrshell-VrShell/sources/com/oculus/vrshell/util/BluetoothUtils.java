package com.oculus.vrshell.util;

public final class BluetoothUtils {
    public static String bluetoothBondStateToString(int i) {
        switch (i) {
            case 10:
                return "BOND_NONE";
            case 11:
                return "BOND_BONDING";
            case 12:
                return "BOND_BONDED";
            default:
                return null;
        }
    }

    public static String bluetoothDeviceTypeToString(int i) {
        if (i == 0) {
            return "DEVICE_TYPE_UNKNOWN";
        }
        if (i == 1) {
            return "DEVICE_TYPE_CLASSIC";
        }
        if (i == 2) {
            return "DEVICE_TYPE_LE";
        }
        if (i != 3) {
            return null;
        }
        return "DEVICE_TYPE_DUAL";
    }
}
