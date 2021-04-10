package defpackage;

import J.N;
import android.accounts.Account;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: TR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class TR implements T0 {
    @Override // defpackage.T0
    public void a(int i) {
        if (AbstractC1254Un.a(i)) {
            Account account = (Account) AccountManagerFacadeProvider.getInstance().n().get(0);
            Profile b = Profile.b();
            if (C5949zZ.a().c(b).c()) {
                ProfileSyncService b2 = ProfileSyncService.b();
                N.MlP9oGhJ(b2.e, b2, 0);
            }
            SigninManager d = C5949zZ.a().d(b);
            d.k();
            if (d.i()) {
                d.t(29, account, new UR());
            }
        }
    }
}
