package com.oculus.os.pm;

import android.content.pm.PermissionInfo;

public class PackageManager {
    public static String getGroupOfPermission(PermissionInfo permission) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized PackageManager getInstance() {
        synchronized (PackageManager.class) {
            throw new RuntimeException("Stub!");
        }
    }
}
