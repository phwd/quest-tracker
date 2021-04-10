package defpackage;

import android.content.Intent;
import android.net.Uri;

/* renamed from: Mw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Mw1 {
    public static Intent a(String str, String str2, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.setPackage(str);
        intent.addFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.webapk_force_navigation", z);
        return intent;
    }
}
