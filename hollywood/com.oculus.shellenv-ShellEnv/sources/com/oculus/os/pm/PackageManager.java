package com.oculus.os.pm;

import android.content.Context;
import android.content.pm.PermissionInfo;
import java.util.concurrent.Future;

public class PackageManager {
    PackageManager() {
        throw new RuntimeException("Stub!");
    }

    public static String getGroupOfPermission(PermissionInfo permissionInfo) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized PackageManager getInstance() {
        synchronized (PackageManager.class) {
            throw new RuntimeException("Stub!");
        }
    }

    public Future getPackageSizeInfo(Context context, String str) {
        throw new RuntimeException("Stub!");
    }

    public Future getPackageSizeInfo(String str) {
        throw new RuntimeException("Stub!");
    }

    public Future getPackageSizeInfoAsUser(Context context, String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public Future getPackageSizeInfoAsUser(String str, int i) {
        throw new RuntimeException("Stub!");
    }
}
