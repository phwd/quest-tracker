package org.chromium.chrome.browser.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlatformUtil {
    public static void launchExternalProtocol(String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        intent.addCategory("android.intent.category.BROWSABLE");
        try {
            applicationContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            AbstractC1220Ua0.a("PlatformUtil", "cannot find activity to launch %s", str, e);
        }
    }
}
