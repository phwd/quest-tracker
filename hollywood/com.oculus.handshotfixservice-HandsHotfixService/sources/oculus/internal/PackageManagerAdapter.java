package oculus.internal;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.AndroidException;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class PackageManagerAdapter {
    private static final String EXP_PATH = "/Android/obb/";
    private static final String PACKAGE_SERVICE = "package";
    private static final String TAG = PackageManagerAdapter.class.getSimpleName();
    private static PackageManagerAdapter adapter = null;
    private static IPackageManager packageManager = null;
    private IBinder binder = null;

    public void getPackageSizeInfoAsUser(Context context, String packageName, int userId, IPackageStatsObserver observer) throws AndroidException, IOException {
        if (ensureConnection()) {
            ApplicationInfo info = packageManager.getApplicationInfo(packageName, 0, userId);
            if (info != null) {
                observer.onGetStatsCompleted(convertStorageStatsToLegacyPackageStats(((StorageStatsManager) context.getSystemService(StorageStatsManager.class)).queryStatsForPackage(info.storageUuid, packageName, UserHandle.getUserHandleForUid(userId)), packageName), true);
                return;
            }
            throw new RemoteException("No package named: " + packageName + " for user: " + userId);
        }
        throw new RemoteException("Unable to connect to PackageManagerService");
    }

    public void getPackageSizeInfoAsUser(String packageName, int userId, IPackageStatsObserver observer) throws RemoteException {
        throw new RuntimeException("Retrieving a package size info without a context is unsupported in Android 10");
    }

    private PackageManagerAdapter() {
        ensureConnection();
    }

    public static synchronized PackageManagerAdapter getInstance() {
        PackageManagerAdapter packageManagerAdapter;
        synchronized (PackageManagerAdapter.class) {
            if (adapter == null) {
                adapter = new PackageManagerAdapter();
            }
            packageManagerAdapter = adapter;
        }
        return packageManagerAdapter;
    }

    private synchronized boolean ensureConnection() {
        if (this.binder != null && this.binder.pingBinder() && this.binder.isBinderAlive()) {
            return true;
        }
        return connect();
    }

    private boolean connect() {
        this.binder = ServiceManager.checkService(PACKAGE_SERVICE);
        IBinder iBinder = this.binder;
        if (iBinder == null) {
            packageManager = null;
            return false;
        }
        packageManager = IPackageManager.Stub.asInterface(iBinder);
        if (packageManager != null) {
            return true;
        }
        return false;
    }

    private static PackageStats convertStorageStatsToLegacyPackageStats(StorageStats storageStats, String packageName) {
        PackageStats ps = new PackageStats(packageName);
        ps.codeSize = storageStats.getCodeBytes();
        ps.dataSize = storageStats.getDataBytes();
        ps.cacheSize = storageStats.getCacheBytes();
        ps.externalObbSize = getAPKExpansionFilesSize(packageName);
        return ps;
    }

    private static long getAPKExpansionFilesSize(String packageName) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0;
        }
        File root = Environment.getExternalStorageDirectory();
        File expPath = new File(root.toString() + EXP_PATH + packageName);
        if (expPath.exists()) {
            return getTotalSizeForPath(packageName, expPath);
        }
        return 0;
    }

    private static long getTotalSizeForPath(String packageName, File path) {
        final AtomicLong size = new AtomicLong(0);
        try {
            Files.walkFileTree(path.toPath(), new FileVisitor<Path>() {
                /* class oculus.internal.PackageManagerAdapter.AnonymousClass1 */

                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    size.addAndGet(basicFileAttributes.size());
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                    throw e;
                }

                public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                    if (e == null) {
                        return FileVisitResult.CONTINUE;
                    }
                    throw e;
                }
            });
            return size.get();
        } catch (IOException ioe) {
            Log.w(TAG, String.format("Failed to get total size for package: %s, reason: %s", packageName, ioe.getLocalizedMessage()));
            return 0;
        }
    }
}
