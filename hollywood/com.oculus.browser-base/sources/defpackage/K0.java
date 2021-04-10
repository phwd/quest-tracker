package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: K0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class K0 implements Runnable {
    public final AccountManagementFragment F;

    public K0(AccountManagementFragment accountManagementFragment) {
        this.F = accountManagementFragment;
    }

    public void run() {
        Activity u = this.F.u();
        AbstractC3535lK0.a("SyncPreferences_ManageGoogleAccountClicked");
        AbstractC4175p51.g(u, "https://myaccount.google.com/smartlink/home");
    }
}
