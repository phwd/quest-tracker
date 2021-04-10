package com.oculus.vrshell.util;

import X.AnonymousClass006;
import android.os.Build;
import com.oculus.os.VrApiManager;

public class DeviceProperties {
    public static final int sDeviceClass1 = 1551834924;
    public static final int sDeviceClass2 = 1455215167;

    public static String getFallbackDefaultEnvironmentURI() {
        int hashCode = Build.PRODUCT.hashCode();
        if (hashCode == 1455215167) {
            return "apk://com.oculus.environment.prod.bubbles/assets/scene.zip";
        }
        if (hashCode != 1551834924) {
            return "";
        }
        return "apk://com.oculus.environment.prod.dome/assets/scene.zip";
    }

    public static String platformResourceAPK() {
        String str = Build.PRODUCT;
        if (1551834924 == str.hashCode()) {
            return "apk://com.oculus.firsttimenuxresources/";
        }
        return AnonymousClass006.A09("apk://com.oculus.firsttimenux", str, "resources/");
    }

    public static boolean supports3DoFAppsLibrary() {
        if (1551834924 == Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsAppMigration() {
        if (1551834924 != Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsBackwardCompatibilityTutorial() {
        if (1551834924 == Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsBrightness() {
        if (1551834924 != Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsDisablingOculusButtonDuringFastMovement() {
        if (1551834924 == Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsDownloadableEnvrionments() {
        if (1551834924 != Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
    }

    public static boolean supportsFitAndFocusEntitlement() {
        if (1551834924 == Build.PRODUCT.hashCode()) {
            return true;
        }
        return false;
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
