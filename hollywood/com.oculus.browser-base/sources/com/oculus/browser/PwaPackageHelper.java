package com.oculus.browser;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.oculus.vrapi.SystemProps;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PwaPackageHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f9708a;
    public final PackageManager b = ContextUtils.getApplicationContext().getPackageManager();

    static {
        String str = "default";
        Class cls = SystemProps.f9720a;
        boolean z = false;
        try {
            str = (String) SystemProps.f9720a.getMethod("get", String.class, String.class).invoke(null, "ro.build.type", str);
        } catch (Exception unused) {
        }
        if (str.equals("userdev") || str.equals("userdebug")) {
            z = true;
        }
        f9708a = z;
    }

    public static Uri b(String str, boolean z) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        String host = parse.getHost();
        boolean z2 = true;
        boolean z3 = scheme != null && !scheme.isEmpty() && host != null && !host.isEmpty();
        if (!scheme.equals("https")) {
            if (!scheme.equals("http") || !host.equals("localhost")) {
                z2 = false;
            }
            if (!z || !z2) {
                z3 = false;
            }
        }
        if (z3) {
            return parse;
        }
        AbstractC1220Ua0.a("PwaPackageHelper", AbstractC2531fV.f("URL is not valid for PWA: ", str), new Object[0]);
        return null;
    }

    public static boolean getIsPwaInstalled(String str) {
        try {
            ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void launchPwa(String str) {
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse(str));
        intent.putExtra("uri", "/");
        ContextUtils.getApplicationContext().sendBroadcast(intent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x009e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public defpackage.SI0 a(java.lang.String r14, com.oculus.browser.ShellEnvironment r15) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.browser.PwaPackageHelper.a(java.lang.String, com.oculus.browser.ShellEnvironment):SI0");
    }
}
