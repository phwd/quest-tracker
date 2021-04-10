package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import org.chromium.base.ContextUtils;

/* renamed from: jq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3273jq0 {

    /* renamed from: a  reason: collision with root package name */
    public final PU0 f10239a = NU0.f8549a;
    public final C0650Kp0 b = new C0650Kp0(ContextUtils.getApplicationContext());

    public C3273jq0(AbstractC2932hq0 hq0) {
    }

    public static void c(String str, int i) {
        if (i != -1) {
            AbstractC3364kK0.g(str, i, 28);
        }
    }

    public static void d(String str, long j) {
        if (j != -1) {
            AbstractC3364kK0.e(str, (int) AbstractC4089od0.d((System.currentTimeMillis() - j) / 60000, 0, 2147483647L), 1, 10080, 50);
        }
    }

    public final void a(int i, String str) {
        if (!this.b.a()) {
            int f = this.f10239a.f("NotificationUmaTracker.LastShownNotificationType", -1);
            if (f != -1) {
                this.f10239a.l("NotificationUmaTracker.LastShownNotificationType");
                c("Mobile.SystemNotification.BlockedAfterShown", f);
            }
            c("Mobile.SystemNotification.Blocked", i);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26 && str != null) {
            NotificationChannel notificationChannel = ((NotificationManager) ContextUtils.getApplicationContext().getSystemService(NotificationManager.class)).getNotificationChannel(str);
            if (notificationChannel != null && notificationChannel.getImportance() == 0) {
                c("Mobile.SystemNotification.ChannelBlocked", i);
                return;
            }
        }
        this.f10239a.n("NotificationUmaTracker.LastShownNotificationType", i);
        c("Mobile.SystemNotification.Shown", i);
    }

    public void b(int i, Notification notification) {
        if (i != -1 && notification != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                a(i, notification.getChannelId());
            } else {
                a(i, null);
            }
        }
    }
}
