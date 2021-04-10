package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.announcement.AnnouncementNotificationManager;

/* renamed from: S6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class S6 extends PK {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ Context G;

    public S6(AnnouncementNotificationManager.Receiver receiver, Intent intent, Context context) {
        this.F = intent;
        this.G = context;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        int h = U20.h(this.F, "org.chromium.chrome.browser.announcement.EXTRA_INTENT_TYPE", 0);
        String n = U20.n(this.F, "org.chromium.chrome.browser.announcement.EXTRA_URL");
        if (h == 1) {
            AbstractActivityC5822yn1.r1(this.G, n);
        } else if (h == 3) {
            ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("announcement_notification", 100);
        } else if (h == 4) {
            AbstractActivityC5822yn1.r1(this.G, n);
            ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("announcement_notification", 100);
        }
    }
}
