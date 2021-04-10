package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.SignOutDialogFragment;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Ib0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0492Ib0 implements Runnable {
    public final ManageSyncSettings F;

    public RunnableC0492Ib0(ManageSyncSettings manageSyncSettings) {
        this.F = manageSyncSettings;
    }

    public void run() {
        ManageSyncSettings manageSyncSettings = this.F;
        Objects.requireNonNull(manageSyncSettings);
        if (C5949zZ.a().c(Profile.b()).c()) {
            N.MX17n_KK(5, 0);
            SignOutDialogFragment l1 = SignOutDialogFragment.l1(0);
            l1.b1(manageSyncSettings, 0);
            l1.k1(manageSyncSettings.G(), "sign_out_dialog_tag");
        }
    }
}
