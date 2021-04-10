package defpackage;

import android.accounts.Account;
import android.app.Activity;
import android.os.UserManager;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: XQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class XQ {
    public static void a(boolean z) {
        NU0.f8549a.m("first_run_signin_complete", z);
    }

    public static void b(Activity activity) {
        SigninManager d = C5949zZ.a().d(Profile.b());
        d.k();
        if (YQ.a()) {
            PU0 pu0 = NU0.f8549a;
            if (!pu0.d("first_run_signin_complete", false)) {
                String i = pu0.i("first_run_signin_account_name", null);
                if (ZQ.f9344a == null) {
                    ZQ.f9344a = Boolean.valueOf(AccountManagerFacadeProvider.getInstance().p());
                }
                if (!((ZQ.f9344a.booleanValue() && (((UserManager) ContextUtils.getApplicationContext().getSystemService("user")).getUserRestrictions().getBoolean("no_modify_accounts", false) ^ true)) || (AccountManagerFacadeProvider.getInstance().n().isEmpty() ^ true)) || !d.i() || TextUtils.isEmpty(i)) {
                    a(true);
                    return;
                }
                Account c = V1.c(AccountManagerFacadeProvider.getInstance().n(), i);
                if (c == null) {
                    a(true);
                } else {
                    d.t(0, c, new WQ(pu0.d("first_run_signin_setup", false), activity));
                }
            }
        }
    }

    public static void c() {
        SigninManager d = C5949zZ.a().d(Profile.b());
        if (!d.i() && YQ.a() && NU0.f8549a.d("first_run_signin_complete", false)) {
            d.k();
        }
    }
}
