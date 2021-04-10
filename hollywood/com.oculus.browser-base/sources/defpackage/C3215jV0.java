package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.components.webapps.WebappsUtils;

/* renamed from: jV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3215jV0 {
    public void a(String str, Bitmap bitmap, boolean z, Intent intent) {
        Icon icon;
        if (WebappsUtils.a()) {
            Map map = ShortcutHelper.f10603a;
            String stringExtra = intent.getStringExtra("org.chromium.chrome.browser.webapp_id");
            Context applicationContext = ContextUtils.getApplicationContext();
            if (bitmap == null) {
                AbstractC1220Ua0.a("ShortcutHelper", AbstractC2531fV.g("Failed to find an icon for ", str, ", not adding."), new Object[0]);
                return;
            }
            if (z) {
                icon = Icon.createWithAdaptiveBitmap(bitmap);
            } else {
                icon = Icon.createWithBitmap(bitmap);
            }
            try {
                ((ShortcutManager) ContextUtils.getApplicationContext().getSystemService(ShortcutManager.class)).requestPinShortcut(new ShortcutInfo.Builder(applicationContext, stringExtra).setShortLabel(str).setLongLabel(str).setIcon(icon).setIntent(intent).build(), null);
            } catch (IllegalStateException unused) {
            }
        } else {
            Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str);
            intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
            ContextUtils.getApplicationContext().sendBroadcast(intent2);
        }
    }
}
