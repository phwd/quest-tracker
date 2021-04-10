package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

/* renamed from: SA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SA1 implements AbstractC1801bB1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2313eB1 f8881a;

    public SA1(C2313eB1 eb1) {
        this.f8881a = eb1;
    }

    @Override // defpackage.AbstractC1801bB1
    public final AbstractC4439qg b(AbstractC4439qg qgVar) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // defpackage.AbstractC1801bB1
    public final void c(int i) {
    }

    @Override // defpackage.AbstractC1801bB1
    public final void d(ConnectionResult connectionResult, C2470f7 f7Var, boolean z) {
    }

    @Override // defpackage.AbstractC1801bB1
    public final boolean disconnect() {
        return true;
    }

    @Override // defpackage.AbstractC1801bB1
    public final void e() {
        C2313eB1 eb1 = this.f8881a;
        eb1.f9838a.lock();
        try {
            eb1.k = new JA1(eb1, eb1.h, eb1.i, eb1.d, eb1.j, eb1.f9838a, eb1.c);
            eb1.k.g();
            eb1.b.signalAll();
        } finally {
            eb1.f9838a.unlock();
        }
    }

    @Override // defpackage.AbstractC1801bB1
    public final void f(Bundle bundle) {
    }

    @Override // defpackage.AbstractC1801bB1
    public final void g() {
        for (AbstractC2129d7 d7Var : this.f8881a.f.values()) {
            d7Var.disconnect();
        }
        this.f8881a.m.p = Collections.emptySet();
    }
}
