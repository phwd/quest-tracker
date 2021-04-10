package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.notifications.NotificationIntentInterceptor$Receiver;

/* renamed from: Ip0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0528Ip0 {
    public static PendingIntent a(int i, int i2, C0832Np0 np0, CB0 cb0) {
        int i3;
        PendingIntent pendingIntent;
        int i4 = 0;
        if (cb0 != null) {
            pendingIntent = cb0.f7793a;
            i3 = cb0.b;
        } else {
            pendingIntent = null;
            i3 = 0;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, NotificationIntentInterceptor$Receiver.class);
        intent.setAction("notifications.NotificationIntentInterceptor.INTENT_ACTION");
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_INTENT_TYPE", i);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_NOTIFICATION_TYPE", np0.f8577a);
        intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_CREATE_TIME", System.currentTimeMillis());
        if (i == 1) {
            intent.putExtra("notifications.NotificationIntentInterceptor.EXTRA_ACTION_TYPE", i2);
        }
        intent.addFlags(268435456);
        int i5 = ((((np0.f8577a * 31) + i) * 31) + i2) * 31;
        String str = np0.b;
        if (str != null) {
            i4 = str.hashCode();
        }
        return PendingIntent.getBroadcast(applicationContext, ((i5 + i4) * 31) + np0.c, intent, i3);
    }
}
