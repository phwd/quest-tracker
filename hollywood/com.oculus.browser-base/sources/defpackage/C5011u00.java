package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: u00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5011u00 extends P00 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11380a = true;

    public C5011u00() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext instanceof AbstractC3226ja1) {
            this.f11380a = ((AbstractC3226ja1) applicationContext).y();
        }
    }

    @Override // defpackage.P00
    public void a() {
        if (!Z00.a() && this.f11380a) {
            AbstractC4841t00.a();
        }
    }

    @Override // defpackage.P00
    public void b() {
        Notification notification;
        if (this.f11380a) {
            Context applicationContext = ContextUtils.getApplicationContext();
            String string = applicationContext.getResources().getString(R.string.f49170_resource_name_obfuscated_RES_2131952234);
            AbstractC3615lq0 H = AbstractC3786mq0.b(true, "incognito", null, new C0832Np0(2, "incognito_tabs_open", 100)).H(applicationContext.getResources().getString(R.string.f49180_resource_name_obfuscated_RES_2131952235));
            Intent intent = new Intent(applicationContext, AbstractIntentServiceC5181v00.class);
            intent.setAction("com.google.android.apps.chrome.incognito.CLOSE_ALL_INCOGNITO");
            AbstractC3615lq0 r = H.B(new CB0(PendingIntent.getService(applicationContext, 0, intent, 134217728), 134217728)).F(string).u(true).d(-1).A(R.drawable.f33140_resource_name_obfuscated_RES_2131231354).j(false).q(true).r("Incognito");
            NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
            C3444kq0 b = r.b();
            if (b == null || (notification = b.f10306a) == null) {
                AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
            } else {
                C0832Np0 np0 = b.b;
                notificationManager.notify(np0.b, np0.c, notification);
            }
            AbstractC3102iq0.f10166a.b(2, b.f10306a);
        }
    }
}
