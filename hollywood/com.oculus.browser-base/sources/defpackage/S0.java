package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.signin.AccountManagerFacade;

/* renamed from: S0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class S0 implements Runnable {
    public final AccountManagerFacade F;
    public final Callback G;

    public S0(AccountManagerFacade accountManagerFacade, Callback callback) {
        this.F = accountManagerFacade;
        this.G = callback;
    }

    public void run() {
        this.G.onResult(this.F.n());
    }
}
