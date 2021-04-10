package defpackage;

import J.N;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;

/* renamed from: ei0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2395ei0 {
    public static Intent a(Uri uri, String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String normalizeMimeType = Intent.normalizeMimeType(str);
        if (TextUtils.isEmpty(normalizeMimeType)) {
            intent.setData(uri);
        } else {
            intent.setDataAndType(uri, normalizeMimeType);
        }
        intent.addFlags(1);
        intent.addFlags(2);
        intent.addFlags(268435456);
        if (str2 != null) {
            intent.putExtra("android.intent.extra.ORIGINATING_URI", Uri.parse(str2));
        }
        if (str3 != null) {
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse(str2));
        }
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent b(android.net.Uri r16, android.net.Uri r17, java.lang.String r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 370
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2395ei0.b(android.net.Uri, android.net.Uri, java.lang.String, boolean):android.content.Intent");
    }

    public static boolean c() {
        if (!(SysUtils.isLowEndDevice() && Build.VERSION.SDK_INT >= 26)) {
            RestrictionsManager restrictionsManager = (RestrictionsManager) ContextUtils.getApplicationContext().getSystemService("restrictions");
            if (!(restrictionsManager.hasRestrictionsProvider() || !restrictionsManager.getApplicationRestrictions().isEmpty())) {
                return false;
            }
        }
        return N.M09VlOh_("HandleMediaIntents");
    }
}
