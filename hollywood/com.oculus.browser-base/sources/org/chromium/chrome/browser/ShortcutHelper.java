package org.chromium.chrome.browser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import com.oculus.browser.R;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.webapps.WebappsUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ShortcutHelper {

    /* renamed from: a  reason: collision with root package name */
    public static Map f10603a = new HashMap();
    public static C3215jV0 b = new C3215jV0();

    public static Intent a(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3, long j, long j2, boolean z, boolean z2) {
        Intent intent = new Intent();
        Intent intent2 = intent.setPackage(ContextUtils.getApplicationContext().getPackageName());
        Objects.requireNonNull(b);
        intent2.setAction("com.google.android.apps.chrome.webapps.WebappManager.ACTION_START_WEBAPP").putExtra("org.chromium.chrome.browser.webapp_id", str).putExtra("org.chromium.chrome.browser.webapp_url", str2).putExtra("org.chromium.chrome.browser.webapp_scope", str3).putExtra("org.chromium.chrome.browser.webapp_name", str4).putExtra("org.chromium.chrome.browser.webapp_short_name", str5).putExtra("org.chromium.chrome.browser.webapp_icon", str6).putExtra("org.chromium.chrome.browser.webapp_shortcut_version", i).putExtra("org.chromium.chrome.browser.webapp_display_mode", i2).putExtra("org.chromium.content_public.common.orientation", i3).putExtra("org.chromium.chrome.browser.theme_color", j).putExtra("org.chromium.chrome.browser.background_color", j2).putExtra("org.chromium.chrome.browser.is_icon_generated", z).putExtra("org.chromium.chrome.browser.webapp_icon_adaptive", z2);
        return intent;
    }

    public static void addShortcut(String str, String str2, String str3, Bitmap bitmap, boolean z, int i, String str4) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
        intent.putExtra("org.chromium.chrome.browser.webapp_id", str);
        intent.putExtra("org.chromium.chrome.browser.webapp_source", i);
        intent.setPackage(ContextUtils.getApplicationContext().getPackageName());
        b.a(str3, bitmap, z, intent);
        if (!WebappsUtils.a()) {
            e(str3);
        }
    }

    public static void addWebapp(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, boolean z, int i, int i2, int i3, long j, long j2) {
        C2874hV0 hv0 = new C2874hV0(bitmap, str, str2, str3, str5, str6, i, i2, j, j2, str7, z, i3, str4);
        Executor executor = AbstractC2032cb.f9616a;
        hv0.f();
        ((ExecutorC1463Ya) executor).execute(hv0.e);
    }

    public static String b(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.lang.String r4) {
        /*
            java.lang.Object r0 = defpackage.Ux1.f9057a
            r0 = 0
            javax.crypto.SecretKey r1 = defpackage.Ux1.a()     // Catch:{ GeneralSecurityException -> 0x0014 }
            if (r1 != 0) goto L_0x000a
            goto L_0x001c
        L_0x000a:
            java.lang.String r2 = "HmacSHA256"
            javax.crypto.Mac r2 = javax.crypto.Mac.getInstance(r2)     // Catch:{ GeneralSecurityException -> 0x0014 }
            r2.init(r1)     // Catch:{ GeneralSecurityException -> 0x0014 }
            goto L_0x001d
        L_0x0014:
            r1 = move-exception
            java.lang.String r2 = "WebappAuthenticator"
            java.lang.String r3 = "Error in creating MAC instance"
            android.util.Log.w(r2, r3, r1)
        L_0x001c:
            r2 = r0
        L_0x001d:
            if (r2 != 0) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            byte[] r4 = defpackage.AbstractC3153j7.b(r4)
            byte[] r0 = r2.doFinal(r4)
        L_0x0028:
            r4 = 0
            java.lang.String r4 = android.util.Base64.encodeToString(r0, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ShortcutHelper.c(java.lang.String):java.lang.String");
    }

    public static String d(String str) {
        int i;
        Uri parse = Uri.parse(str);
        String encodedPath = parse.getEncodedPath();
        if (encodedPath == null) {
            i = -1;
        } else {
            i = encodedPath.lastIndexOf("/");
        }
        if (i < 0) {
            encodedPath = "/";
        } else if (i < encodedPath.length() - 1) {
            encodedPath = encodedPath.substring(0, i + 1);
        }
        Uri.Builder buildUpon = parse.buildUpon();
        buildUpon.encodedPath(encodedPath);
        buildUpon.fragment("");
        buildUpon.query("");
        return buildUpon.build().toString();
    }

    public static boolean doesOriginContainAnyInstalledTwa(String str) {
        C2756go1 go1 = AbstractC2957hy1.f10115a.d;
        return ((HashSet) go1.e()).contains(str.toLowerCase(Locale.getDefault()));
    }

    public static boolean doesOriginContainAnyInstalledWebApk(String str) {
        WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
        String lowerCase = str.toLowerCase(Locale.getDefault());
        for (Map.Entry entry : webappRegistry.b.entrySet()) {
            String b2 = webappRegistry.b((Xx1) entry.getValue());
            if (!b2.isEmpty() && b2.startsWith(lowerCase)) {
                return true;
            }
        }
        return false;
    }

    public static void e(String str) {
        f(ContextUtils.getApplicationContext().getString(R.string.f46570_resource_name_obfuscated_RES_2131951974, str));
    }

    public static void f(String str) {
        C1184Ti1.b(ContextUtils.getApplicationContext(), str, 0).b.show();
    }

    public static String[] getOriginsWithInstalledWebApksOrTwas() {
        Set a2 = AbstractC2957hy1.f10115a.a();
        return (String[]) a2.toArray(new String[((HashSet) a2).size()]);
    }

    public static void setForceWebApkUpdate(String str) {
        Xx1 xx1 = (Xx1) AbstractC2957hy1.f10115a.b.get(str);
        if (xx1 != null) {
            xx1.f(true);
        }
    }

    public static void showWebApkInstallInProgressToast() {
        f(ContextUtils.getApplicationContext().getString(R.string.f64620_resource_name_obfuscated_RES_2131953779));
    }

    public static void storeWebappSplashImage(String str, Bitmap bitmap) {
        Xx1 xx1 = (Xx1) AbstractC2957hy1.f10115a.b.get(str);
        if (xx1 == null) {
            f10603a.put(str, bitmap);
            return;
        }
        C3045iV0 iv0 = new C3045iV0(bitmap, xx1);
        Executor executor = AbstractC2032cb.f9616a;
        iv0.f();
        ((ExecutorC1463Ya) executor).execute(iv0.e);
    }
}
