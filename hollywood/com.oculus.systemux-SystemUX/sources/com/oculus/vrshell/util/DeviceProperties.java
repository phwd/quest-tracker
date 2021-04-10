package com.oculus.vrshell.util;

import android.os.Build;
import com.oculus.os.VrApiManager;

public class DeviceProperties {
    private static final int sDeviceClass1 = 1551834924;
    private static final int sDeviceClass2 = 1455215167;

    public static boolean supports3DoFAppsLibrary() {
        return sDeviceClass1 == Build.PRODUCT.hashCode();
    }

    public static boolean supportsDisablingOculusButtonDuringFastMovement() {
        return sDeviceClass1 == Build.PRODUCT.hashCode();
    }

    public static boolean supportsBrightness() {
        return sDeviceClass1 != Build.PRODUCT.hashCode();
    }

    public static boolean supportsDownloadableEnvrionments() {
        return sDeviceClass1 != Build.PRODUCT.hashCode();
    }

    public static boolean supportsFitAndFocusEntitlement() {
        return sDeviceClass1 == Build.PRODUCT.hashCode();
    }

    public static boolean supportsAppMigration() {
        return sDeviceClass1 != Build.PRODUCT.hashCode();
    }

    public static boolean supportsBackwardCompatibilityTutorial() {
        return sDeviceClass1 == Build.PRODUCT.hashCode();
    }

    public static String platformResourceAPK() {
        if (sDeviceClass1 == Build.PRODUCT.hashCode()) {
            return "apk://com.oculus.firsttimenuxresources/";
        }
        return "apk://com.oculus.firsttimenux" + Build.PRODUCT + "resources/";
    }

    public static boolean supportsExperimental120hzRefreshRate() {
        for (int i : VrApiManager.getInstance().getSupportedDisplayRefreshRates()) {
            if (i == 120) {
                return true;
            }
        }
        return false;
    }
}
