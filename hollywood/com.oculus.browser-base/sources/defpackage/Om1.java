package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: Om1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Om1 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC3615lq0 f8648a;
    public static int b;

    public static boolean a() {
        NotificationChannel notificationChannel;
        Context applicationContext = ContextUtils.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        boolean z = false;
        if (!new C0650Kp0(applicationContext).a()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 26 || (notificationChannel = ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).getNotificationChannel("browser")) == null) {
            return true;
        }
        if (notificationChannel.getImportance() != 0) {
            z = true;
        }
        return z;
    }

    public static AbstractC3615lq0 b() {
        return AbstractC3786mq0.a(true, "browser").d(1).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).j(false).q(true);
    }

    public static void c() {
        ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("tracing_status", 100);
        f8648a = null;
    }

    public static void d(Notification notification) {
        NotificationManager notificationManager = (NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification");
        if (notification == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            notificationManager.notify("tracing_status", 100, notification);
        }
    }
}
