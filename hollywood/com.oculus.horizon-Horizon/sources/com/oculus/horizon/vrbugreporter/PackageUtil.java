package com.oculus.horizon.vrbugreporter;

import X.AnonymousClass0NO;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class PackageUtil {
    public static final List<String> CORE_APP_PACKAGE_NAMES = ImmutableList.A05();
    public static final String TAG = "PackageUtil";

    public interface PackageCallback {
        void A6N(String str, String str2, int i);
    }

    public static String A00(Context context) {
        final StringBuilder sb = new StringBuilder("Package                 Version         Version Code Info\n");
        AnonymousClass1 r5 = new PackageCallback() {
            /* class com.oculus.horizon.vrbugreporter.PackageUtil.AnonymousClass1 */

            @Override // com.oculus.horizon.vrbugreporter.PackageUtil.PackageCallback
            public final void A6N(String str, String str2, int i) {
                StringBuilder sb = sb;
                sb.append(str);
                sb.append("\t");
                sb.append(str2);
                sb.append("\t");
                sb.append(i);
                sb.append("\n");
            }
        };
        PackageManager packageManager = context.getPackageManager();
        for (String str : CORE_APP_PACKAGE_NAMES) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                r5.A6N(packageInfo.packageName, packageInfo.versionName, packageInfo.versionCode);
            } catch (PackageManager.NameNotFoundException e) {
                AnonymousClass0NO.A0B(TAG, "One of the core packages is not installed", e);
            }
        }
        for (PackageInfo packageInfo2 : packageManager.getInstalledPackages(0)) {
            if (!CORE_APP_PACKAGE_NAMES.contains(packageInfo2.packageName)) {
                r5.A6N(packageInfo2.packageName, packageInfo2.versionName, packageInfo2.versionCode);
            }
        }
        return sb.toString();
    }
}
