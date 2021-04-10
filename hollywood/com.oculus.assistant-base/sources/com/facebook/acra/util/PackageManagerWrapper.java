package com.facebook.acra.util;

import X.C0139Dd;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.common.stringformat.StringFormatUtil;

public final class PackageManagerWrapper {
    public final Context context;
    public final String logTag;

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

    public PackageInfo getPackageInfo() {
        return getPackageInfo(this.context.getPackageName(), 0);
    }

    public PackageInfo getPackageInfo(String str, int i) {
        PackageManager packageManager = this.context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(str, i);
            } catch (PackageManager.NameNotFoundException unused) {
                String str2 = this.logTag;
                String packageName = this.context.getPackageName();
                if (C0139Dd.A01.A3Y(2)) {
                    C0139Dd.A0C(str2, StringFormatUtil.formatStrLocaleSafe("Failed to find PackageInfo for current App : %s", packageName));
                }
            } catch (RuntimeException unused2) {
                return null;
            }
        }
        return null;
    }
}
