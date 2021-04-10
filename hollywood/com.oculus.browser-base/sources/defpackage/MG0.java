package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: MG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MG0 implements Runnable {
    public MG0(OG0 og0) {
    }

    public void run() {
        TR tr = new TR();
        Object obj = ThreadUtils.f10596a;
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        instance.g(new C1071Rn(instance, tr));
        AccountManagerFacadeProvider.getInstance().a(new LG0(this));
    }
}
