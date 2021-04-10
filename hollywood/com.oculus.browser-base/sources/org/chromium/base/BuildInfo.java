package org.chromium.base;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BuildInfo {

    /* renamed from: a  reason: collision with root package name */
    public static String f10582a = "";
    public final String b;
    public final long c;
    public final String d;
    public final long e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final boolean n;

    public BuildInfo(AbstractC0395Gk gk) {
        String str;
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            PackageManager packageManager = applicationContext.getPackageManager();
            boolean z = false;
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            long c2 = c(packageInfo);
            this.c = c2;
            PackageInfo packageInfo2 = null;
            this.d = packageName;
            this.e = c2;
            this.f = b(packageInfo.versionName);
            this.b = b(packageManager.getApplicationLabel(packageInfo.applicationInfo));
            this.g = b(packageManager.getInstallerPackageName(packageName));
            try {
                packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            this.h = packageInfo2 != null ? String.valueOf(c(packageInfo2)) : "gms versionCode not available.";
            String str2 = "true";
            try {
                packageManager.getPackageInfo("projekt.substratum", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                str2 = "false";
            }
            this.l = str2;
            try {
                str = ContextUtils.getApplicationContext().getString(R.string.f59560_resource_name_obfuscated_RES_2131953273);
            } catch (Exception unused3) {
                str = "Not found";
            }
            this.m = str;
            this.i = TextUtils.join(", ", Build.SUPPORTED_ABIS);
            this.k = String.format("@%x_%x", Long.valueOf(this.e), Long.valueOf(packageInfo.lastUpdateTime));
            String str3 = Build.FINGERPRINT;
            this.j = str3.substring(0, Math.min(str3.length(), 128));
            UiModeManager uiModeManager = (UiModeManager) applicationContext.getSystemService("uimode");
            if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                z = true;
            }
            this.n = z;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean a() {
        String str = Build.TYPE;
        return "eng".equals(str) || "userdebug".equals(str) || "userdev".equals(str);
    }

    public static String b(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static long c(PackageInfo packageInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return C4179p7.a(packageInfo);
        }
        return (long) packageInfo.versionCode;
    }

    public static String[] getAll() {
        BuildInfo buildInfo = AbstractC0456Hk.f8178a;
        String packageName = ContextUtils.getApplicationContext().getPackageName();
        String[] strArr = new String[25];
        strArr[0] = Build.BRAND;
        strArr[1] = Build.DEVICE;
        strArr[2] = Build.ID;
        strArr[3] = Build.MANUFACTURER;
        strArr[4] = Build.MODEL;
        strArr[5] = String.valueOf(Build.VERSION.SDK_INT);
        strArr[6] = Build.TYPE;
        strArr[7] = Build.BOARD;
        strArr[8] = packageName;
        strArr[9] = String.valueOf(buildInfo.c);
        strArr[10] = buildInfo.b;
        strArr[11] = buildInfo.d;
        strArr[12] = String.valueOf(buildInfo.e);
        strArr[13] = buildInfo.f;
        strArr[14] = buildInfo.j;
        strArr[15] = buildInfo.h;
        strArr[16] = buildInfo.g;
        strArr[17] = buildInfo.i;
        strArr[18] = f10582a;
        strArr[19] = buildInfo.l;
        strArr[20] = buildInfo.m;
        strArr[21] = buildInfo.k;
        strArr[22] = String.valueOf(ContextUtils.getApplicationContext().getApplicationInfo().targetSdkVersion);
        String str = "1";
        strArr[23] = a() ? str : "0";
        if (!buildInfo.n) {
            str = "0";
        }
        strArr[24] = str;
        return strArr;
    }
}
