package com.oculus.os.pm;

import android.content.Context;
import android.content.pm.PermissionInfo;
import android.os.RemoteException;
import android.util.AndroidException;
import java.io.IOException;
import java.util.concurrent.Future;

public class PackageManager {
    PackageManager() {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public Future<OculusPackageStats> getPackageSizeInfoAsUser(String str, int i) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public Future<OculusPackageStats> getPackageSizeInfo(String str) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public Future<OculusPackageStats> getPackageSizeInfoAsUser(Context context, String str, int i) throws AndroidException, IOException {
        throw new RuntimeException("Stub!");
    }

    public Future<OculusPackageStats> getPackageSizeInfo(Context context, String str) throws AndroidException, IOException {
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
}
