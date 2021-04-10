package com.oculus.bugreportservice;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SystemInfoUtils {
    private static final HashSet CORE_APPS = new HashSet(Arrays.asList("com.oculus.vrshell", "com.oculus.horizon", "com.oculus.updater", "com.oculus.ocms", "com.oculus.tv", "com.oculus.browser", "com.oculus.mediaplayer", "com.oculus.vrshell.home", "com.oculus.systemdriver", "com.oculus.systemactivities"));
    private final Context mContext;

    public SystemInfoUtils(Context context) {
        this.mContext = context;
    }

    public String getPackagesInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Package          Version          Version Code Info\n");
        Iterator it = CORE_APPS.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(str, 0);
                sb.append(str);
                sb.append("\t");
                sb.append(packageInfo.versionName);
                sb.append("\t");
                sb.append(packageInfo.versionCode);
                sb.append("\n");
            } catch (Exception e) {
                Log.w("SystemInfoUtils", "Couldn't get info for package", e);
            }
        }
        for (PackageInfo packageInfo2 : this.mContext.getPackageManager().getInstalledPackages(0)) {
            if (!CORE_APPS.contains(packageInfo2.packageName)) {
                sb.append(packageInfo2.packageName);
                sb.append("\t");
                sb.append(packageInfo2.versionName);
                sb.append("\t");
                sb.append(packageInfo2.versionCode);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getSystemDetails() {
        return "Build Version: " + Build.FINGERPRINT + "\n";
    }
}
