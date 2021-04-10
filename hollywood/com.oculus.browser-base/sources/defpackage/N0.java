package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.signin.SigninUtils;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: N0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class N0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AccountManagementFragment f8517a;

    public N0(AccountManagementFragment accountManagementFragment) {
        this.f8517a = accountManagementFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AccountManagementFragment accountManagementFragment = this.f8517a;
        Intent intent = (Intent) obj;
        if (accountManagementFragment.a0() && accountManagementFragment.Z()) {
            if (intent != null) {
                accountManagementFragment.c1(intent);
            } else {
                SigninUtils.a(accountManagementFragment.u());
            }
            if (accountManagementFragment.H0 != 0 && accountManagementFragment.U()) {
                accountManagementFragment.u().finish();
            }
        }
    }
}
