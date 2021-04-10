package com.oculus.device;

import android.os.Build;
import com.oculus.common.serial.BuildSerialUtil;

public enum DeviceType {
    Unknown("", "Unknown Device", ""),
    Pacific("PH", "Oculus Go", "PACIFIC"),
    Monterey("SH", "Oculus Quest", "MONTEREY"),
    Hollywood("HH", "Oculus Quest 2", "HOLLYWOOD"),
    Seacliff("", "Oculus Seacliff", "SEACLIFF");
    
    public static final int PART_CODE_OFFSET = 3;
    public final String hmdType;
    public final String partCode;
    public final String publicName;

    public static DeviceType current() {
        DeviceType fromDeviceModel = fromDeviceModel(Build.DEVICE);
        if (fromDeviceModel == Unknown) {
            return fromSerialNumber(BuildSerialUtil.A00());
        }
        return fromDeviceModel;
    }

    /* access modifiers changed from: public */
    DeviceType(String str, String str2, String str3) {
        this.partCode = str;
        this.publicName = str2;
        this.hmdType = str3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0026  */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.device.DeviceType fromDeviceModel(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1329022146: goto L_0x000a;
                case -1329022145: goto L_0x000d;
                case -807117175: goto L_0x0010;
                case -319084279: goto L_0x0013;
                case 3304: goto L_0x001e;
                case 875062001: goto L_0x0029;
                case 1455215167: goto L_0x0034;
                default: goto L_0x0007;
            }
        L_0x0007:
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Unknown
            return r0
        L_0x000a:
            java.lang.String r0 = "monterey_proto0"
            goto L_0x0015
        L_0x000d:
            java.lang.String r0 = "monterey_proto1"
            goto L_0x0015
        L_0x0010:
            java.lang.String r0 = "pacific"
            goto L_0x0020
        L_0x0013:
            java.lang.String r0 = "monterey"
        L_0x0015:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Monterey
            return r0
        L_0x001e:
            java.lang.String r0 = "go"
        L_0x0020:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Pacific
            return r0
        L_0x0029:
            java.lang.String r0 = "seacliff"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Seacliff
            return r0
        L_0x0034:
            java.lang.String r0 = "hollywood"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0007
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Hollywood
            return r0
            switch-data {-1329022146->0x000a, -1329022145->0x000d, -807117175->0x0010, -319084279->0x0013, 3304->0x001e, 875062001->0x0029, 1455215167->0x0034, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.DeviceType.fromDeviceModel(java.lang.String):com.oculus.device.DeviceType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        if (r2.startsWith(r1.partCode) != false) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.device.DeviceType fromSerialNumber(java.lang.String r2) {
        /*
            int r1 = r2.length()
            r0 = 3
            if (r1 <= r0) goto L_0x0032
            java.lang.String r2 = r2.substring(r0)
            com.oculus.device.DeviceType r1 = com.oculus.device.DeviceType.Pacific
            java.lang.String r0 = r1.partCode
            boolean r0 = r2.startsWith(r0)
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "PP"
            boolean r0 = r2.startsWith(r0)
            if (r0 != 0) goto L_0x0031
            com.oculus.device.DeviceType r1 = com.oculus.device.DeviceType.Monterey
            java.lang.String r0 = r1.partCode
            boolean r0 = r2.startsWith(r0)
            if (r0 != 0) goto L_0x0031
            com.oculus.device.DeviceType r1 = com.oculus.device.DeviceType.Hollywood
            java.lang.String r0 = r1.partCode
            boolean r0 = r2.startsWith(r0)
            if (r0 == 0) goto L_0x0032
        L_0x0031:
            return r1
        L_0x0032:
            com.oculus.device.DeviceType r0 = com.oculus.device.DeviceType.Unknown
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.DeviceType.fromSerialNumber(java.lang.String):com.oculus.device.DeviceType");
    }

    public static boolean is6DOF() {
        DeviceType current = current();
        if (current == Unknown || current == Pacific) {
            return false;
        }
        return true;
    }
}
