package defpackage;

import android.view.View;

/* renamed from: ae1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1692ae1 implements AbstractC0072Bd1 {
    public final /* synthetic */ C1872be1 F;

    public C1692ae1(C1872be1 be1, C1474Yd1 yd1) {
        this.F = be1;
    }

    @Override // defpackage.AbstractC0072Bd1
    public View b() {
        C3859nE0 ne0 = this.F.f9550J;
        if (ne0 == null) {
            return null;
        }
        return ne0.e;
    }

    @Override // defpackage.AbstractC0072Bd1
    public int c() {
        return 2;
    }

    @Override // defpackage.AbstractC0072Bd1
    public void d() {
        C1872be1 be1 = this.F;
        C0090Bk bk = be1.K;
        if (bk != null && be1.P == -1) {
            be1.P = bk.q();
        }
        C1872be1.c(this.F, true);
    }

    @Override // defpackage.AbstractC0072Bd1
    public void e() {
        C1872be1 be1 = this.F;
        C0090Bk bk = be1.K;
        if (bk != null) {
            bk.p(be1.P);
            be1.P = -1;
        }
        C1872be1.c(this.F, false);
    }
}
