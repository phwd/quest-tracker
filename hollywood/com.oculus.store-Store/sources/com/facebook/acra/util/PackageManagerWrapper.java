package com.facebook.acra.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PackageManagerWrapper {
    private final Context context;
    private final String logTag;

    public PackageManagerWrapper(Context context2, String logTag2) {
        this.context = context2;
        this.logTag = logTag2;
    }

    public boolean hasPermission(String permission) {
        PackageManager pm = this.context.getPackageManager();
        if (pm == null) {
            return false;
        }
        try {
            if (pm.checkPermission(permission, this.context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Nullable
    public PackageInfo getPackageInfo() {
        return getPackageInfo(this.context.getPackageName(), 0);
    }

    @Nullable
    public PackageInfo getPackageInfo(String packageName, int flags) {
        PackageManager pm = this.context.getPackageManager();
        if (pm == null) {
            return null;
        }
        try {
            return pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            BLog.v(this.logTag, "Failed to find PackageInfo for current App : %s", this.context.getPackageName());
            return null;
        } catch (RuntimeException e2) {
            return null;
        }
    }

    @Nullable
    public String getInstallerPackageName() {
        PackageManager pm = this.context.getPackageManager();
        if (pm == null) {
            return null;
        }
        return pm.getInstallerPackageName(this.context.getPackageName());
    }
}
