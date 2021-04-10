package com.facebook.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.DeadObjectException;
import com.facebook.common.build.BuildConstants;

public class DeviceUtil {
    public static boolean isPackageInstalledOnDevice(PackageManager packageManager, String str) {
        for (int i = 2; i >= 0; i--) {
            try {
                packageManager.getPackageInfo(str, 128);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof DeadObjectException) {
                    return false;
                }
                if (i == 0) {
                    throw e;
                }
            }
        }
        throw new IllegalStateException("should be unreachable");
    }

    public static boolean isPackageEnabled(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 128);
            if (packageInfo.applicationInfo == null || !packageInfo.applicationInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof DeadObjectException) {
                return false;
            }
            throw e;
        }
    }

    public static boolean isFB4AInstalledOnDevice(Context context) {
        return isPackageInstalledOnDevice(context.getPackageManager(), BuildConstants.getMainFbAppPackageName());
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean isLowRamDevice(Context context) {
        ActivityManager activityManager;
        if (Build.VERSION.SDK_INT >= 19 && (activityManager = (ActivityManager) context.getSystemService("activity")) != null) {
            return activityManager.isLowRamDevice();
        }
        return false;
    }
}
