package defpackage;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import org.chromium.chrome.browser.signin.SigninUtils;
import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: J0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class J0 implements Runnable {
    public final AccountManagementFragment F;
    public final Account G;

    public J0(AccountManagementFragment accountManagementFragment, Account account) {
        this.F = accountManagementFragment;
        this.G = account;
    }

    public void run() {
        AccountManagementFragment accountManagementFragment = this.F;
        Account account = this.G;
        Activity u = accountManagementFragment.u();
        if (Build.VERSION.SDK_INT >= 26) {
            SigninUtils.a(u);
            return;
        }
        Intent intent = new Intent("android.settings.ACCOUNT_SYNC_SETTINGS");
        intent.putExtra("account", account);
        U20.q(u, intent);
    }
}
