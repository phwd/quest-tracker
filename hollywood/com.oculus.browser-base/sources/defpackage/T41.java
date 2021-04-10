package defpackage;

import android.accounts.Account;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: T41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T41 extends AbstractC2705gW0 {

    /* renamed from: a  reason: collision with root package name */
    public final Account f8936a;

    public T41(Account account) {
        this.f8936a = account;
    }

    @Override // defpackage.AbstractC2705gW0
    public void b() {
        Account account = this.f8936a;
        int i = SyncAndServicesSettings.G0;
        C5949zZ.a().d(Profile.b()).t(28, account, null);
    }
}
