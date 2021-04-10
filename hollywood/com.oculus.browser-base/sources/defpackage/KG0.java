package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: KG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KG0 implements Runnable {
    public void run() {
        TR tr = new TR();
        Object obj = ThreadUtils.f10596a;
        AccountManagerFacade instance = AccountManagerFacadeProvider.getInstance();
        instance.g(new C1071Rn(instance, tr));
    }
}
