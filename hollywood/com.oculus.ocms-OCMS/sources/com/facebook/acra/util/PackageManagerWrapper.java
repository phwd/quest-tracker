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

    public PackageManagerWrapper(Context context2, String str) {
        this.context = context2;
        this.logTag = str;
    }

    public boolean hasPermission(String str) {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            if (packageManager.checkPermission(str, this.context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    @Nullable
    public PackageInfo getPackageInfo() {
        return getPackageInfo(this.context.getPackageName(), 0);
    }

    @Nullable
    public PackageInfo getPackageInfo(String str, int i) {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            return packageManager.getPackageInfo(str, i);
        } catch (PackageManager.NameNotFoundException unused) {
            BLog.v(this.logTag, "Failed to find PackageInfo for current App : %s", this.context.getPackageName());
            return null;
        } catch (RuntimeException unused2) {
            return null;
        }
    }

    @Nullable
    public String getInstallerPackageName() {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        return packageManager.getInstallerPackageName(this.context.getPackageName());
    }
}
