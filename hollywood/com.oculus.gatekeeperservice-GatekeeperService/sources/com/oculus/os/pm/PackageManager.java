package com.oculus.os.pm;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.content.pm.PermissionInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.AndroidException;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import oculus.internal.PackageManagerAdapter;
import oculus.internal.PermissionGroupAdapter;

public class PackageManager {
    private static PackageManager packageManager = null;

    @Deprecated
    public Future<OculusPackageStats> getPackageSizeInfoAsUser(final String packageName, int userId) throws RemoteException {
        final CompletableFuture<OculusPackageStats> opsFuture = new CompletableFuture<>();
        PackageManagerAdapter.getInstance().getPackageSizeInfoAsUser(packageName, userId, new IPackageStatsObserver.Stub() {
            /* class com.oculus.os.pm.PackageManager.AnonymousClass1 */

            public void onGetStatsCompleted(PackageStats packageStats, boolean success) throws RemoteException {
                if (success) {
                    opsFuture.complete(PackageManager.convertPackageStatsToOculusPackageStats(packageStats));
                    return;
                }
                CompletableFuture completableFuture = opsFuture;
                completableFuture.completeExceptionally(new CancellationException("Could not get stats for package: " + packageName));
            }
        });
        return opsFuture;
    }

    @Deprecated
    public Future<OculusPackageStats> getPackageSizeInfo(String packageName) throws RemoteException {
        return getPackageSizeInfoAsUser(packageName, UserHandle.myUserId());
    }

    public Future<OculusPackageStats> getPackageSizeInfoAsUser(Context context, final String packageName, int userId) throws AndroidException, IOException {
        final CompletableFuture<OculusPackageStats> opsFuture = new CompletableFuture<>();
        PackageManagerAdapter.getInstance().getPackageSizeInfoAsUser(context, packageName, userId, new IPackageStatsObserver.Stub() {
            /* class com.oculus.os.pm.PackageManager.AnonymousClass2 */

            public void onGetStatsCompleted(PackageStats packageStats, boolean success) throws RemoteException {
                if (success) {
                    opsFuture.complete(PackageManager.convertPackageStatsToOculusPackageStats(packageStats));
                    return;
                }
                CompletableFuture completableFuture = opsFuture;
                completableFuture.completeExceptionally(new CancellationException("Could not get stats for package: " + packageName));
            }
        });
        return opsFuture;
    }

    public Future<OculusPackageStats> getPackageSizeInfo(Context context, String packageName) throws AndroidException, IOException {
        return getPackageSizeInfoAsUser(context, packageName, UserHandle.myUserId());
    }

    public static String getGroupOfPermission(PermissionInfo permission) {
        return new PermissionGroupAdapter().getGroupOfPermission(permission);
    }

    private PackageManager() {
    }

    public static synchronized PackageManager getInstance() {
        PackageManager packageManager2;
        synchronized (PackageManager.class) {
            if (packageManager == null) {
                packageManager = new PackageManager();
            }
            packageManager2 = packageManager;
        }
        return packageManager2;
    }

    /* access modifiers changed from: private */
    public static OculusPackageStats convertPackageStatsToOculusPackageStats(PackageStats packageStats) {
        if (packageStats == null) {
            return null;
        }
        OculusPackageStats ops = new OculusPackageStats();
        ops.packageName = packageStats.packageName;
        ops.cacheSize = packageStats.cacheSize;
        ops.codeSize = packageStats.codeSize;
        ops.dataSize = packageStats.dataSize;
        ops.externalCacheSize = packageStats.externalCacheSize;
        ops.externalCodeSize = packageStats.externalCodeSize;
        ops.externalDataSize = packageStats.externalDataSize;
        ops.externalMediaSize = packageStats.externalMediaSize;
        ops.externalObbSize = packageStats.externalObbSize;
        return ops;
    }
}
