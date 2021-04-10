package org.chromium.chrome.browser.notifications.scheduler;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DisplayAgent {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class NotificationData {

        /* renamed from: a  reason: collision with root package name */
        public final String f10705a;
        public final String b;
        public HashMap c = new HashMap();
        public ArrayList d = new ArrayList();

        public NotificationData(String str, String str2, SF sf) {
            this.f10705a = str;
            this.b = str2;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public final class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            VF vf = new VF(this, intent);
            C1321Vq.b().d(vf);
            C1321Vq.b().c(true, vf, null);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SystemData {

        /* renamed from: a  reason: collision with root package name */
        public int f10706a;
        public final String b;

        public SystemData(int i, String str) {
            this.f10706a = i;
            this.b = str;
        }
    }

    public static Intent a(Context context, int i, SystemData systemData) {
        Intent intent = new Intent(context, Receiver.class);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_INTENT_TYPE", i);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_SCHEDULER_CLIENT_TYPE ", systemData.f10706a);
        intent.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_GUID", systemData.b);
        return intent;
    }

    public static void addButton(NotificationData notificationData, String str, int i, String str2) {
        notificationData.d.add(new TF(str, i, str2));
    }

    public static void addIcon(NotificationData notificationData, int i, Bitmap bitmap, int i2) {
        if (i2 != 0) {
            notificationData.c.put(Integer.valueOf(i), new UF(i2));
        } else {
            notificationData.c.put(Integer.valueOf(i), new UF(bitmap));
        }
    }

    public static void b(String str) {
        ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("NotificationSchedulerDisplayAgent", str.hashCode());
    }

    public static NotificationData buildNotificationData(String str, String str2) {
        return new NotificationData(str, str2, null);
    }

    public static SystemData buildSystemData(int i, String str) {
        return new SystemData(i, str);
    }

    public static int c(int i, String str) {
        int hashCode = str.hashCode();
        return (hashCode * 31) + i + hashCode;
    }

    public static void showNotification(NotificationData notificationData, SystemData systemData) {
        Notification notification;
        Context applicationContext = ContextUtils.getApplicationContext();
        AbstractC3615lq0 b = AbstractC3786mq0.b(true, "browser", null, new C0832Np0(-1, "NotificationSchedulerDisplayAgent", systemData.b.hashCode()));
        b.H(notificationData.f10705a);
        b.F(notificationData.b);
        boolean containsKey = notificationData.c.containsKey(1);
        if (!containsKey || ((UF) notificationData.c.get(1)).f9015a == null) {
            int i = R.drawable.f29770_resource_name_obfuscated_RES_2131231017;
            if (containsKey && ((UF) notificationData.c.get(1)).b != 0) {
                i = ((UF) notificationData.c.get(1)).b;
            }
            b.A(i);
        } else {
            b.p(Icon.createWithBitmap(((UF) notificationData.c.get(1)).f9015a));
        }
        if (notificationData.c.containsKey(2) && ((UF) notificationData.c.get(2)).f9015a != null) {
            b.t(((UF) notificationData.c.get(2)).f9015a);
        }
        b.B(CB0.a(applicationContext, c(0, systemData.b), a(applicationContext, 0, systemData), 134217728));
        b.w(CB0.a(applicationContext, c(2, systemData.b), a(applicationContext, 2, systemData), 134217728));
        for (int i2 = 0; i2 < notificationData.d.size(); i2++) {
            TF tf = (TF) notificationData.d.get(i2);
            Intent a2 = a(applicationContext, 1, systemData);
            a2.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_TYPE", tf.b);
            a2.putExtra("org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_ID", tf.c);
            b.e(0, tf.f8947a, CB0.a(applicationContext, c(1, systemData.b), a2, 134217728), -1);
        }
        C3444kq0 b2 = b.b();
        NotificationManager notificationManager = (NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification");
        if (b2 == null || (notification = b2.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b2.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(-1, b2.f10306a);
    }
}
