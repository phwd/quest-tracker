package com.oculus.panellib;

import X.AnonymousClass006;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class AppDetails {
    public static final String TAG = "AppDetails";
    public final boolean isInstalled;
    public final Bundle metaData;
    public final int versionCode;
    public final String versionName;

    public static AppDetails getWithMetaData(Context context, String str) {
        return get(context, str, true);
    }

    public AppDetails(boolean z, Bundle bundle, int i, String str) {
        this.isInstalled = z;
        this.metaData = bundle;
        this.versionCode = i;
        this.versionName = str;
    }

    public static AppDetails get(Context context, String str) {
        return get(context, str, false);
    }

    public static AppDetails get(Context context, String str, boolean z) {
        boolean z2;
        RuntimeException e;
        Bundle bundle;
        String str2 = "0.0";
        int i = 0;
        Bundle bundle2 = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            int i2 = 0;
            if (z) {
                i2 = 128;
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(str, i2);
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
                    Log.e(TAG, AnonymousClass006.A09("Failed to look up package: '", str, "'"), e);
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
            Log.e(TAG, AnonymousClass006.A09("Failed to look up package: '", str, "'"), e);
        }
        return new AppDetails(z2, bundle2, i, str2);
    }
}
