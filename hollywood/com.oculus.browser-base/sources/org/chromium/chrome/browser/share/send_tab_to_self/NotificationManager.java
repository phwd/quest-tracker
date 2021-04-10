package org.chromium.chrome.browser.share.send_tab_to_self;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationManager {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean hideNotification(java.lang.String r6) {
        /*
            Xp0 r0 = defpackage.AbstractC1503Yp0.a(r6)
            r1 = 0
            if (r6 != 0) goto L_0x0009
        L_0x0007:
            r6 = r1
            goto L_0x0034
        L_0x0009:
            PU0 r2 = defpackage.NU0.f8549a
            Xp0 r6 = defpackage.AbstractC1503Yp0.a(r6)
            if (r6 != 0) goto L_0x0012
            goto L_0x0007
        L_0x0012:
            r3 = 0
            java.lang.String r4 = "send_tab_to_self.notification.active"
            java.util.Set r3 = r2.k(r4, r3)
            if (r3 != 0) goto L_0x0021
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            goto L_0x0027
        L_0x0021:
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>(r3)
            r3 = r5
        L_0x0027:
            java.lang.String r6 = defpackage.AbstractC1503Yp0.b(r6)
            boolean r6 = r3.remove(r6)
            if (r6 == 0) goto L_0x0034
            r2.q(r4, r3)
        L_0x0034:
            if (r6 != 0) goto L_0x0037
            return r1
        L_0x0037:
            android.content.Context r6 = org.chromium.base.ContextUtils.getApplicationContext()
            java.lang.String r1 = "notification"
            java.lang.Object r6 = r6.getSystemService(r1)
            android.app.NotificationManager r6 = (android.app.NotificationManager) r6
            int r0 = r0.f9236a
            java.lang.String r1 = "SendTabToSelf"
            r6.cancel(r1, r0)
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.share.send_tab_to_self.NotificationManager.hideNotification(java.lang.String):boolean");
    }

    public static boolean showNotification(String str, String str2, String str3, String str4, long j, Class cls) {
        HashSet hashSet;
        Notification notification;
        if (AbstractC1503Yp0.a(str) != null) {
            return false;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) applicationContext.getSystemService("notification");
        PU0 pu0 = NU0.f8549a;
        int f = pu0.f("send_tab_to_self.notification.next_id", -1);
        if (f >= 2147483646) {
            f = -1;
        }
        int i = f + 1;
        pu0.n("send_tab_to_self.notification.next_id", i);
        Uri parse = Uri.parse(str2);
        C3444kq0 b = AbstractC3786mq0.b(true, "sharing", null, new C0832Np0(15, "SendTabToSelf", i)).B(CB0.a(applicationContext, i, new Intent(applicationContext, cls).setData(parse).setAction("send_tab_to_self.tap").putExtra("send_tab_to_self.notification.guid", str), 0)).w(CB0.a(applicationContext, i, new Intent(applicationContext, cls).setData(parse).setAction("send_tab_to_self.dismiss").putExtra("send_tab_to_self.notification.guid", str), 0)).H(str3).F(applicationContext.getResources().getString(R.string.f61240_resource_name_obfuscated_RES_2131953441, parse.getHost(), str4)).r("SendTabToSelf").o(1).E(new long[0]).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).G(-1).b();
        if (b == null || (notification = b.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        AbstractC3102iq0.f10166a.b(15, b.f10306a);
        Set k = pu0.k("send_tab_to_self.notification.active", null);
        if (k == null) {
            hashSet = new HashSet();
        } else {
            hashSet = new HashSet(k);
        }
        if (hashSet.add(1 + "_" + i + "_" + str)) {
            pu0.q("send_tab_to_self.notification.active", hashSet);
        }
        if (j != Long.MAX_VALUE) {
            ((AlarmManager) applicationContext.getSystemService("alarm")).set(1, j, PendingIntent.getBroadcast(applicationContext, i, new Intent(applicationContext, cls).setData(Uri.parse(str2)).setAction("send_tab_to_self.timeout").putExtra("send_tab_to_self.notification.guid", str), 134217728));
        }
        return true;
    }
}
