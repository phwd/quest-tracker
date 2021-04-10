package org.chromium.chrome.browser.announcement;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AnnouncementNotificationManager {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            S6 s6 = new S6(this, intent, context);
            C1321Vq.b().d(s6);
            C1321Vq.b().c(true, s6, null);
        }
    }

    public static CB0 a(Context context, int i, String str) {
        Intent intent = new Intent(context, Receiver.class);
        intent.putExtra("org.chromium.chrome.browser.announcement.EXTRA_INTENT_TYPE", i);
        intent.putExtra("org.chromium.chrome.browser.announcement.EXTRA_URL", str);
        return CB0.a(context, i, intent, 134217728);
    }

    public static boolean isFirstRun() {
        return !YQ.a();
    }

    public static void showNotification(String str) {
        Notification notification;
        Context applicationContext = ContextUtils.getApplicationContext();
        AbstractC3615lq0 q = AbstractC3786mq0.b(true, "announcement", null, new C0832Np0(21, "announcement_notification", 100)).H(applicationContext.getString(R.string.f63560_resource_name_obfuscated_RES_2131953673)).B(a(applicationContext, 1, str)).w(a(applicationContext, 2, str)).F(applicationContext.getString(R.string.f63540_resource_name_obfuscated_RES_2131953671)).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).j(false).x(true).q(true);
        q.e(0, applicationContext.getString(R.string.f63530_resource_name_obfuscated_RES_2131953670), a(applicationContext, 3, str), 13);
        q.e(0, applicationContext.getString(R.string.f63550_resource_name_obfuscated_RES_2131953672), a(applicationContext, 4, str), 14);
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        C3444kq0 b = q.b();
        if (b == null || (notification = b.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(21, b.f10306a);
    }
}
