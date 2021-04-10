package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import com.oculus.common.build.BuildConfig;

public class MobileConfigDefaults {
    public static boolean preferWaitForNonDefault(int i) {
        return false;
    }

    public static boolean getBoolDefaults(long j) {
        return MobileConfigSpecifierUtil.getStdDefault(j);
    }

    public static long getLongDefaults(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        if (slotId != 0) {
            return slotId != 2 ? 0 : -4321;
        }
        return 975612;
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
        if (slotId != 0) {
            return slotId != 2 ? BuildConfig.PROVIDER_SUFFIX : "MobileConfig is a cross-platform framework for Android and iOS apps to access server-side configurations";
        }
        return "Lorem Ipsum";
    }
}
