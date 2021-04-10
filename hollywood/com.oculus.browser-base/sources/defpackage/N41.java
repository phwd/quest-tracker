package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: N41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class N41 implements Runnable {
    public final SyncAndServicesSettings F;

    public N41(SyncAndServicesSettings syncAndServicesSettings) {
        this.F = syncAndServicesSettings;
    }

    public void run() {
        Activity u = this.F.u();
        AbstractC3535lK0.a("SyncPreferences_ManageGoogleAccountClicked");
        AbstractC4175p51.g(u, "https://myaccount.google.com/smartlink/home");
    }
}
