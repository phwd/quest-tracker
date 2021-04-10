package defpackage;

import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: c1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1940c1 implements Runnable {
    public final AccountManagerFacade F;

    public RunnableC1940c1(AccountManagerFacade accountManagerFacade) {
        this.F = accountManagerFacade;
    }

    public void run() {
        AccountManagerFacade accountManagerFacade = this.F;
        AccountManagerFacadeProvider.c = accountManagerFacade;
        AccountManagerFacadeProvider.f10888a.set(accountManagerFacade);
    }
}
