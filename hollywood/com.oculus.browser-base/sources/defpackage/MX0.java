package defpackage;

import android.app.NotificationChannel;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.notifications.NotificationSettingsBridge;

/* renamed from: MX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MX0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0711Lp0 f8480a;

    public MX0(AbstractC0711Lp0 lp0) {
        this.f8480a = lp0;
    }

    public static boolean e(String str) {
        return str.startsWith("web:") && str.substring(4).contains(";");
    }

    public String a(String str) {
        NotificationSettingsBridge.SiteChannel c = c(str);
        boolean z = c == null;
        if (z) {
            AbstractC3100ip1.f10165a.a("Notifications.Android.SitesChannel", true);
        }
        if (z) {
            return "sites";
        }
        return c.getId();
    }

    public int b(String str) {
        NotificationChannel notificationChannel = ((C0771Mp0) this.f8480a).b.getNotificationChannel(str);
        if (notificationChannel == null) {
            return 2;
        }
        return notificationChannel.getImportance() != 0 ? 0 : 1;
    }

    public final NotificationSettingsBridge.SiteChannel c(String str) {
        String d = C3640ly1.b(str).d();
        NotificationSettingsBridge.SiteChannel[] d2 = d();
        for (NotificationSettingsBridge.SiteChannel siteChannel : d2) {
            if (siteChannel.getOrigin().equals(d)) {
                return siteChannel;
            }
        }
        return null;
    }

    public NotificationSettingsBridge.SiteChannel[] d() {
        List<NotificationChannel> notificationChannels = ((C0771Mp0) this.f8480a).b.getNotificationChannels();
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : notificationChannels) {
            if (e(notificationChannel.getId())) {
                String[] split = notificationChannel.getId().substring(4).split(";");
                arrayList.add(new NotificationSettingsBridge.SiteChannel(notificationChannel.getId(), split[0], Long.parseLong(split[1]), notificationChannel.getImportance() != 0 ? 0 : 1));
            }
        }
        return (NotificationSettingsBridge.SiteChannel[]) arrayList.toArray(new NotificationSettingsBridge.SiteChannel[arrayList.size()]);
    }
}
