package defpackage;

import J.N;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.omaha.notification.UpdateNotificationServiceBridge;

/* renamed from: Pp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0953Pp implements Runnable {
    public final ChromeActivity F;

    public RunnableC0953Pp(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void run() {
        AbstractC3103iq1 iq1;
        ChromeActivity chromeActivity = this.F;
        if (!chromeActivity.v()) {
            new Wp1(chromeActivity);
            if (chromeActivity.O0 == null) {
                M2 m2 = chromeActivity.Y;
                if (N.M09VlOh_("UpdateNotificationSchedulingIntegration")) {
                    iq1 = new UpdateNotificationServiceBridge(m2);
                } else {
                    iq1 = new C3616lq1(chromeActivity, m2);
                }
                chromeActivity.O0 = iq1;
            }
            chromeActivity.O0.f(chromeActivity.getIntent());
        }
    }
}
