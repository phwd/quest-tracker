package com.facebook.mobileconfig;

import com.facebook.common.time.TimeConstants;
import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;

public class MobileConfigDefaults {
    public static boolean preferWaitForNonDefault(int i) {
        return false;
    }

    public static boolean getBoolDefaults(long j) {
        return MobileConfigSpecifierUtil.getStdDefault(j);
    }

    public static long getLongDefaults(long j) {
        switch (MobileConfigSpecifierUtil.getSlotId(j)) {
            case 0:
            case 14:
                return 10;
            case 1:
            case 11:
            case 15:
                return 5;
            case 2:
                return 975612;
            case 3:
            case 17:
            default:
                return 0;
            case 4:
                return -4321;
            case 5:
                return 3;
            case 6:
                return 18000000;
            case 7:
                return 30;
            case 8:
                return 1;
            case 9:
                return 2147483000;
            case 10:
                return TimeConstants.MINUTES_PER_DAY;
            case 12:
                return 20;
            case 13:
                return 15;
            case 16:
                return 3600000;
            case 18:
                return -1;
        }
    }

    public static double getDoubleDefaults(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        if (slotId != 0) {
            return (slotId == 1 || slotId != 2) ? 0.0d : 9.876543210125E9d;
        }
        return -142.5d;
    }

    public static String getStringDefaults(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        if (slotId == 0) {
            return "Lorem Ipsum";
        }
        if (slotId == 9) {
            return "feed, store, discovery, settings";
        }
        if (slotId == 12) {
            return "current_content";
        }
        if (slotId == 2) {
            return "MobileConfig is a cross-platform framework for Android and iOS apps to access server-side configurations";
        }
        if (slotId == 3 || slotId == 4) {
            return "it.ikon.virtours.production01";
        }
        return slotId != 5 ? "" : "[203, 405, 515, 720]";
    }
}
