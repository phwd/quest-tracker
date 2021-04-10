package org.chromium.chrome.browser.webapps;

import J.N;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.components.webapps.WebappsIconUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebApkInstallService {
    public static void a(String str, String str2, String str3, Bitmap bitmap, String str4, PendingIntent pendingIntent) {
        Context applicationContext = ContextUtils.getApplicationContext();
        AbstractC3615lq0 a2 = AbstractC3786mq0.a(false, "browser");
        a2.H(str2).F(str4).t(bitmap).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).I(pendingIntent).f(System.currentTimeMillis()).g(N.MR6Af3ZS(str3, 1)).x(true);
        ((NotificationManager) applicationContext.getSystemService("notification")).notify(AbstractC2531fV.f("webapk_install_notification_tag_prefix.", str), -1, a2.c());
    }

    public static void cancelNotification(String str) {
        ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("webapk_install_notification_tag_prefix." + str, -1);
    }

    public static void showInstallInProgressNotification(String str, String str2, String str3, Bitmap bitmap, boolean z) {
        String string = ContextUtils.getApplicationContext().getResources().getString(R.string.f56210_resource_name_obfuscated_RES_2131952938, str2);
        if (z && WebappsIconUtils.a()) {
            bitmap = WebappsIconUtils.b(bitmap);
        }
        a(str, str2, str3, bitmap, string, null);
        ShortcutHelper.f(string);
    }

    public static void showInstalledNotification(String str, String str2, String str3, String str4, Bitmap bitmap, boolean z) {
        Context applicationContext = ContextUtils.getApplicationContext();
        PendingIntent activity = PendingIntent.getActivity(applicationContext, 0, Mw1.a(str, str4, false), 134217728);
        if (z && WebappsIconUtils.a()) {
            bitmap = WebappsIconUtils.b(bitmap);
        }
        a(str2, str3, str4, bitmap, applicationContext.getResources().getString(R.string.f56220_resource_name_obfuscated_RES_2131952939), activity);
    }
}
