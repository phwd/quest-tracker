package defpackage;

import android.accounts.Account;

/* renamed from: W0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W0 extends AbstractC2032cb {
    public final /* synthetic */ Account i;
    public final /* synthetic */ T0 j;
    public final /* synthetic */ C1769b1 k;

    public W0(C1769b1 b1Var, Account account, T0 t0) {
        this.k = b1Var;
        this.i = account;
        this.j = t0;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (C1769b1.r(this.k, this.i, "service_uca")) {
            return 1;
        }
        if (C1769b1.r(this.k, this.i, "service_usm")) {
            return 2;
        }
        return 0;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.a(((Integer) obj).intValue());
    }
}
