package org.chromium.components.signin;

import android.accounts.Account;
import android.app.Activity;
import java.util.List;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface AccountManagerFacade {
    void a(W1 w1);

    void b(Account account, Activity activity, Callback callback);

    void c(String str);

    boolean d();

    void e(Runnable runnable);

    String f(String str);

    void g(Callback callback);

    boolean h();

    void i(Account account, T0 t0);

    void j(Runnable runnable);

    ZG0 k();

    C4839t l(Account account, String str);

    void m(W1 w1);

    List n();

    void o(Callback callback);

    boolean p();

    void q(Callback callback);
}
