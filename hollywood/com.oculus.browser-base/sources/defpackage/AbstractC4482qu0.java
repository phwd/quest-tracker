package defpackage;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.IOException;
import java.util.UUID;
import org.chromium.base.ContextUtils;

/* renamed from: qu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4482qu0 {
    public static void a() {
        UUID uuid;
        if (Build.VERSION.SDK_INT >= 26) {
            Context applicationContext = ContextUtils.getApplicationContext();
            StorageManager storageManager = (StorageManager) applicationContext.getSystemService(StorageManager.class);
            StorageStatsManager storageStatsManager = (StorageStatsManager) applicationContext.getSystemService(StorageStatsManager.class);
            C4311pu0 pu0 = null;
            if (storageManager == null || storageStatsManager == null) {
                AbstractC1220Ua0.a("PackageMetrics", "StorageManager or StorageStatsManager is not found", new Object[0]);
            } else {
                String packageName = applicationContext.getPackageName();
                C4311pu0 pu02 = new C4311pu0(null);
                for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                    if ("mounted".equals(storageVolume.getState())) {
                        String uuid2 = storageVolume.getUuid();
                        if (uuid2 == null) {
                            try {
                                uuid = StorageManager.UUID_DEFAULT;
                            } catch (IllegalArgumentException e) {
                                AbstractC1220Ua0.a("PackageMetrics", AbstractC2531fV.f("Could not parse UUID ", uuid2), e);
                                uuid = null;
                            }
                        } else {
                            uuid = UUID.fromString(uuid2);
                        }
                        if (uuid != null) {
                            try {
                                StorageStats queryStatsForPackage = storageStatsManager.queryStatsForPackage(uuid, packageName, Process.myUserHandle());
                                pu02.f11097a += queryStatsForPackage.getAppBytes();
                                pu02.b = (queryStatsForPackage.getDataBytes() - queryStatsForPackage.getCacheBytes()) + pu02.b;
                                pu02.c += queryStatsForPackage.getCacheBytes();
                            } catch (PackageManager.NameNotFoundException | IOException | SecurityException e2) {
                                AbstractC1220Ua0.a("PackageMetrics", "Error calling into queryStatsForPackage", e2);
                            }
                        }
                    }
                }
                pu0 = pu02;
            }
            if (pu0 != null) {
                AbstractC3364kK0.e("Android.PackageStats.DataSize", (int) (pu0.b / 1048576), 1, 10000, 50);
                AbstractC3364kK0.e("Android.PackageStats.CacheSize", (int) (pu0.c / 1048576), 1, 10000, 50);
                AbstractC3100ip1.f10165a.d("Android.PackageStats.CodeSize", (int) (pu0.f11097a / 1048576));
            }
        }
    }
}
