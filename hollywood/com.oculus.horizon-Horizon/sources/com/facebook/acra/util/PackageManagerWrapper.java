package com.facebook.acra.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class PackageManagerWrapper {
    public final Context context;
    public final String logTag;

    @Nullable
    public String getInstallerPackageName() {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        return packageManager.getInstallerPackageName(this.context.getPackageName());
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

    public PackageManagerWrapper(Context context2, String str) {
        this.context = context2;
        this.logTag = str;
    }

    @Nullable
    public PackageInfo getPackageInfo() {
        return getPackageInfo(this.context.getPackageName(), 0);
    }

    @Nullable
    public PackageInfo getPackageInfo(String str, int i) {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(str, i);
            } catch (PackageManager.NameNotFoundException unused) {
                this.context.getPackageName();
            } catch (RuntimeException unused2) {
                return null;
            }
        }
        return null;
    }
}
