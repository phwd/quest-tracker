package com.oculus.panellib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class AppDetails {
    public final boolean isInstalled;
    public final Bundle metaData;
    public final int versionCode;
    public final String versionName;

    private AppDetails(boolean z, Bundle bundle, int i, String str) {
        this.isInstalled = z;
        this.metaData = bundle;
        this.versionCode = i;
        this.versionName = str;
    }

    private static AppDetails get(Context context, String str, boolean z) {
        boolean z2;
        RuntimeException e;
        Bundle bundle;
        String str2 = "0.0";
        int i = 0;
        Bundle bundle2 = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, z ? 128 : 0);
            z2 = true;
            if (z) {
                try {
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (applicationInfo != null) {
                        bundle = applicationInfo.metaData;
                    } else {
                        bundle = new Bundle();
                    }
                    bundle2 = bundle;
                } catch (PackageManager.NameNotFoundException unused) {
                } catch (RuntimeException e2) {
                    e = e2;
                    Log.e("AppDetails", "Failed to look up package: '" + str + "'", e);
                    return new AppDetails(z2, bundle2, i, str2);
                }
            }
            i = packageInfo.versionCode;
            str2 = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused2) {
            z2 = false;
        } catch (RuntimeException e3) {
            e = e3;
            z2 = false;
            Log.e("AppDetails", "Failed to look up package: '" + str + "'", e);
        }
        return new AppDetails(z2, bundle2, i, str2);
    }

    public static AppDetails get(Context context, String str) {
        return get(context, str, false);
    }
}
