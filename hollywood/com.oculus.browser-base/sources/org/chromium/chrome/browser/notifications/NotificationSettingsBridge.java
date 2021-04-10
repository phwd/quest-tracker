package org.chromium.chrome.browser.notifications;

import J.N;
import android.app.NotificationChannel;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationSettingsBridge {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SiteChannel {

        /* renamed from: a  reason: collision with root package name */
        public final String f10703a;
        public final String b;
        public final long c;
        public final int d;

        public SiteChannel(String str, String str2, long j, int i) {
            this.f10703a = str;
            this.b = str2;
            this.c = j;
            this.d = i;
        }

        public String getId() {
            return this.f10703a;
        }

        public String getOrigin() {
            return this.b;
        }

        public int getStatus() {
            return this.d;
        }

        public long getTimestamp() {
            return this.c;
        }
    }

    public static SiteChannel createChannel(String str, long j, boolean z) {
        MX0 mx0 = LX0.f8421a;
        SiteChannel c = mx0.c(str);
        if (c == null) {
            ((C0771Mp0) mx0.f8480a).b.createNotificationChannelGroup(((C0218Dn) AbstractC2421er.f9885a.get("sites")).a(ContextUtils.getApplicationContext().getResources()));
            StringBuilder i = AbstractC2531fV.i("web:");
            i.append(C3640ly1.b(str).d());
            i.append(";");
            i.append(j);
            String sb = i.toString();
            int i2 = !z ? 1 : 0;
            c = new SiteChannel(sb, str, j, i2);
            AbstractC0711Lp0 lp0 = mx0.f8480a;
            NotificationChannel notificationChannel = new NotificationChannel(sb, N.MR6Af3ZS(str, 1), i2 == 1 ? 0 : 3);
            notificationChannel.setGroup("sites");
            ((C0771Mp0) lp0).b.createNotificationChannel(notificationChannel);
        }
        return c;
    }

    public static void deleteChannel(String str) {
        ((C0771Mp0) LX0.f8421a.f8480a).b.deleteNotificationChannel(str);
    }

    public static int getChannelStatus(String str) {
        return LX0.f8421a.b(str);
    }

    public static SiteChannel[] getSiteChannels() {
        return LX0.f8421a.d();
    }
}
