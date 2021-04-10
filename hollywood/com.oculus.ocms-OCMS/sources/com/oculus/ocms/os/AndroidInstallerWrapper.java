package com.oculus.ocms.os;

import android.content.pm.PackageManager;

public class AndroidInstallerWrapper {
    public static boolean isPackageInstalledAsUser(PackageManager packageManager, String str, int i) {
        try {
            packageManager.getPackageInfoAsUser(str, 0, i);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
