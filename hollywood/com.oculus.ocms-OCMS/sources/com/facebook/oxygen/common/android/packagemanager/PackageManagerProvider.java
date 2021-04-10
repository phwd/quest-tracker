package com.facebook.oxygen.common.android.packagemanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.oxygen.common.android.packagemanager.OxpPackageManager;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PackageManagerProvider {
    private static boolean shouldUseOxp() {
        return false;
    }

    private PackageManagerProvider() {
    }

    @Nullable
    public static PackageManager getPackageManager(@Nullable Context context, @Nullable String str) {
        if (context != null) {
            return context.getPackageManager();
        }
        return null;
    }

    @Nullable
    public static PackageManager createPackageManager(@Nullable Context context, @Nullable OxpPackageManager.Counters counters) {
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        return (packageManager == null || counters == null || !shouldUseOxp()) ? packageManager : new OxpPackageManager(packageManager, counters);
    }
}
