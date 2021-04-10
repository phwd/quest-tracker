package com.oculus.device;

import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.serial.BuildSerialUtil;

public enum DeviceType {
    Unknown("", "Unknown Device", ""),
    Pacific("PH", "Oculus Go", "PACIFIC"),
    Monterey("SH", "Oculus Quest", "MONTEREY"),
    Hollywood("HH", "Oculus Quest 2", "HOLLYWOOD"),
    Seacliff("", "Oculus Seacliff", "SEACLIFF");
    
    private static final int PART_CODE_OFFSET = 3;
    public final String hmdType;
    public final String partCode;
    public final String publicName;

    private DeviceType(String str, String str2, String str3) {
        this.partCode = str;
        this.publicName = str2;
        this.hmdType = str3;
    }

    public static DeviceType current() {
        DeviceType fromDeviceModel = fromDeviceModel(Build.DEVICE);
        return fromDeviceModel == Unknown ? fromSerialNumber(BuildSerialUtil.getSerial()) : fromDeviceModel;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @VisibleForTesting
    static DeviceType fromDeviceModel(String str) {
        char c;
        switch (str.hashCode()) {
            case -1329022146:
                if (str.equals("monterey_proto0")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1329022145:
                if (str.equals("monterey_proto1")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -807117175:
                if (str.equals("pacific")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -319084279:
                if (str.equals("monterey")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3304:
                if (str.equals("go")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 875062001:
                if (str.equals("seacliff")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1455215167:
                if (str.equals("hollywood")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return Pacific;
            case 2:
            case 3:
            case 4:
                return Monterey;
            case 5:
                return Hollywood;
            case 6:
                return Seacliff;
            default:
                return Unknown;
        }
    }

    public static DeviceType fromSerialNumber(String str) {
        if (str.length() <= 3) {
            return Unknown;
        }
        String substring = str.substring(3);
        if (substring.startsWith(Pacific.partCode) || substring.startsWith("PP")) {
            return Pacific;
        }
        if (substring.startsWith(Monterey.partCode)) {
            return Monterey;
        }
        if (substring.startsWith(Hollywood.partCode)) {
            return Hollywood;
        }
        return Unknown;
    }

    public static boolean is6DOF() {
        DeviceType current = current();
        return (current == Unknown || current == Pacific) ? false : true;
    }
}
