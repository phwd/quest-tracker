package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

/* renamed from: Mp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0771Mp0 implements AbstractC0711Lp0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8505a;
    public final NotificationManager b;

    public C0771Mp0(Context context) {
        this.f8505a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    public void a(C3444kq0 kq0) {
        Notification notification;
        if (kq0 == null || (notification = kq0.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
            return;
        }
        NotificationManager notificationManager = this.b;
        C0832Np0 np0 = kq0.b;
        notificationManager.notify(np0.b, np0.c, notification);
    }
}
