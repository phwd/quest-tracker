package com.oculus.panellib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.adobe.xmp.options.PropertyOptions;

public class AppDetails {
    private static final String TAG = "AppDetails";
    public final boolean isInstalled;
    public final Bundle metaData;
    public final int versionCode;
    public final String versionName;

    private AppDetails(boolean isInstalled2, Bundle metaData2, int versionCode2, String versionName2) {
        this.isInstalled = isInstalled2;
        this.metaData = metaData2;
        this.versionCode = versionCode2;
        this.versionName = versionName2;
    }

    private static AppDetails get(Context context, String packageName, boolean fetchMetaData) {
        boolean isInstalled2 = false;
        Bundle metaData2 = null;
        int versionCode2 = 0;
        String versionName2 = "0.0";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, fetchMetaData ? PropertyOptions.HAS_TYPE : 0);
            isInstalled2 = true;
            if (fetchMetaData) {
                ApplicationInfo appInfo = info.applicationInfo;
                if (appInfo != null) {
                    metaData2 = appInfo.metaData;
                } else {
                    metaData2 = new Bundle();
                }
            }
            versionCode2 = info.versionCode;
            versionName2 = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        } catch (RuntimeException e2) {
            Log.e(TAG, "Failed to look up package: '" + packageName + "'", e2);
        }
        return new AppDetails(isInstalled2, metaData2, versionCode2, versionName2);
    }

    public static AppDetails get(Context context, String packageName) {
        return get(context, packageName, false);
    }

    public static AppDetails getWithMetaData(Context context, String packageName) {
        return get(context, packageName, true);
    }
}
