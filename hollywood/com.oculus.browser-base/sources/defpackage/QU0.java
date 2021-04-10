package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.chromium.base.ContextUtils;

/* renamed from: QU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class QU0 {
    public static void a(int i, String str, int i2, CB0 cb0, String str2, String str3, int i3, int i4, int i5) {
        Notification notification;
        Bitmap decodeResource;
        Context applicationContext = ContextUtils.getApplicationContext();
        Resources resources = applicationContext.getResources();
        AbstractC3615lq0 G = AbstractC3786mq0.b(true, "sharing", null, new C0832Np0(i, str, i2)).B(cb0).H(str2).F(str3).D(applicationContext.getResources().getColor(i5)).r(str).o(1).A(i3).x(true).G(-1);
        if (!(i4 == 0 || (decodeResource = BitmapFactory.decodeResource(resources, i4)) == null)) {
            G.t(decodeResource);
        }
        C3444kq0 b = G.b();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        if (b == null || (notification = b.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(i, b.f10306a);
    }
}
