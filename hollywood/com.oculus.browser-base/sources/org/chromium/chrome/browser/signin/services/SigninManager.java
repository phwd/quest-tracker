package org.chromium.chrome.browser.signin.services;

import android.accounts.Account;
import org.chromium.base.Callback;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface SigninManager {
    boolean A();

    boolean B();

    String G();

    String J(String str);

    void K(Runnable runnable);

    void M(AbstractC2193dW0 dw0);

    IdentityManager f();

    void g(CoreAccountInfo coreAccountInfo, AbstractC2363eW0 ew0);

    void h(int i, AbstractC2705gW0 gw0, boolean z);

    boolean i();

    void j(String str, Callback callback);

    void k();

    void l();

    void m(AbstractC2534fW0 fw0);

    boolean o();

    boolean p();

    void q(AbstractC2193dW0 dw0);

    @Deprecated
    void t(int i, Account account, AbstractC2363eW0 ew0);

    void u(int i);

    void z(AbstractC2534fW0 fw0);
}
