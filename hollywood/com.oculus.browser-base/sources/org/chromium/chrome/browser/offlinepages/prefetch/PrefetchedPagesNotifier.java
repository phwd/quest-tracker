package org.chromium.chrome.browser.offlinepages.prefetch;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.notifications.settings.NotificationSettings;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefetchedPagesNotifier {

    /* renamed from: a  reason: collision with root package name */
    public static PrefetchedPagesNotifier f10723a;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ClickReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            NU0.f8549a.n("prefetch_notification_ignored_counter", 0);
            PrefetchedPagesNotifier.a(2);
            DownloadUtils.showDownloadManager(null, null, 12, true);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SettingsReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            PrefetchedPagesNotifier.a(3);
            String name = NotificationSettings.class.getName();
            Intent l = AbstractC2531fV.l(context, XS0.class);
            if (!(context instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            l.putExtra("show_fragment", name);
            l.addFlags(32768);
            context.startActivity(l);
        }
    }

    public static void a(int i) {
        RunnableC5226vF0 vf0 = new RunnableC5226vF0(i);
        BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) AbstractC4280pk.a();
        if (!browserStartupControllerImpl.f()) {
            browserStartupControllerImpl.a(new C5396wF0(vf0));
        } else {
            vf0.run();
        }
    }

    public static PrefetchedPagesNotifier b() {
        if (f10723a == null) {
            f10723a = new PrefetchedPagesNotifier();
        }
        return f10723a;
    }

    public static void showDebuggingNotification(String str) {
        b().d(str);
    }

    public void c(int i) {
        AbstractC3364kK0.g("OfflinePages.Prefetching.NotificationAction", i, 4);
    }

    public void d(String str) {
        Notification notification;
        Context applicationContext = ContextUtils.getApplicationContext();
        CB0 a2 = CB0.a(applicationContext, 0, new Intent(applicationContext, ClickReceiver.class), 0);
        AbstractC3615lq0 A = AbstractC3786mq0.b(true, "content_suggestions", null, new C0832Np0(12, "OfflineContentSuggestionsNotification", 1)).x(true).B(a2).H(String.format(applicationContext.getString(R.string.f56530_resource_name_obfuscated_RES_2131952970), applicationContext.getString(R.string.f46950_resource_name_obfuscated_RES_2131952012))).F(String.format(applicationContext.getString(R.string.f56520_resource_name_obfuscated_RES_2131952969), str)).r("OfflineContentSuggestionsNotification").o(-1).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017);
        if (Build.VERSION.SDK_INT < 26) {
            A.e(R.drawable.f34890_resource_name_obfuscated_RES_2131231529, applicationContext.getString(R.string.f61350_resource_name_obfuscated_RES_2131953452), CB0.a(applicationContext, 0, new Intent(applicationContext, SettingsReceiver.class), 0), 9);
        }
        C3444kq0 b = A.b();
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
        if (b == null || (notification = b.f10306a) == null) {
            AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
        } else {
            C0832Np0 np0 = b.b;
            notificationManager.notify(np0.b, np0.c, notification);
        }
        NU0.f8549a.c("prefetch_notification_ignored_counter");
        c(1);
        AbstractC3102iq0.f10166a.b(12, b.f10306a);
    }
}
