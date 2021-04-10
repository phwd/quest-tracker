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
    public Future<OculusPackageStats> getPackageSizeInfoAsUser(String packageName, int userId) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public Future<OculusPackageStats> getPackageSizeInfo(String packageName) throws RemoteException {
        throw new RuntimeException("Stub!");
    }

    public Future<OculusPackageStats> getPackageSizeInfoAsUser(Context context, String packageName, int userId) throws AndroidException, IOException {
        throw new RuntimeException("Stub!");
    }

    public Future<OculusPackageStats> getPackageSizeInfo(Context context, String packageName) throws AndroidException, IOException {
        throw new RuntimeException("Stub!");
    }

    public static String getGroupOfPermission(PermissionInfo permission) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized PackageManager getInstance() {
        synchronized (PackageManager.class) {
            throw new RuntimeException("Stub!");
        }
    }
}
