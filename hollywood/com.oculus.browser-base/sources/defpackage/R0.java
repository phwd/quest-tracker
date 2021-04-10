package defpackage;

import java.util.Collections;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.components.signin.AccountManagerFacade;

/* renamed from: R0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class R0 implements AccountManagerFacade {
    @Override // org.chromium.components.signin.AccountManagerFacade
    public void g(Callback callback) {
        ((C1769b1) this).e(new S0(this, callback));
    }

    @Override // org.chromium.components.signin.AccountManagerFacade
    public List n() {
        try {
            return ((C1769b1) this).y();
        } catch (Q0 unused) {
            return Collections.emptyList();
        }
    }
}
