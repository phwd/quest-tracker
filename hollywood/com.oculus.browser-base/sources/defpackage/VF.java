package defpackage;

import J.N;
import android.content.Intent;
import org.chromium.chrome.browser.notifications.scheduler.DisplayAgent;

/* renamed from: VF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VF extends PK {
    public final /* synthetic */ Intent F;

    public VF(DisplayAgent.Receiver receiver, Intent intent) {
        this.F = intent;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        Intent intent = this.F;
        int h = U20.h(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_INTENT_TYPE", -1);
        String n = U20.n(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_GUID");
        int h2 = U20.h(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_SCHEDULER_CLIENT_TYPE ", 0);
        if (h == 0) {
            N.MJnQd5Zg(h2, 0, n, 0, null);
            DisplayAgent.b(n);
        } else if (h == 1) {
            N.MJnQd5Zg(h2, 1, n, U20.h(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_TYPE", 0), U20.n(intent, "org.chromium.chrome.browser.notifications.scheduler.EXTRA_ACTION_BUTTON_ID"));
            DisplayAgent.b(n);
        } else if (h == 2) {
            N.MJnQd5Zg(h2, 2, n, 0, null);
        }
    }
}
