package defpackage;

import java.util.Objects;

/* renamed from: r2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4505r2 implements AbstractC1886bj0 {
    public final /* synthetic */ C4676s2 F;

    public C4505r2(C4676s2 s2Var) {
        this.F = s2Var;
    }

    @Override // defpackage.AbstractC1886bj0
    public void a(C4616ri0 ri0, boolean z) {
        if (ri0 instanceof SubMenuC4510r31) {
            ri0.k().c(false);
        }
        AbstractC1886bj0 bj0 = this.F.f11246J;
        if (bj0 != null) {
            bj0.a(ri0, z);
        }
    }

    @Override // defpackage.AbstractC1886bj0
    public boolean b(C4616ri0 ri0) {
        if (ri0 == null) {
            return false;
        }
        C4676s2 s2Var = this.F;
        int i = ((SubMenuC4510r31) ri0).A.f8568a;
        Objects.requireNonNull(s2Var);
        AbstractC1886bj0 bj0 = this.F.f11246J;
        if (bj0 != null) {
            return bj0.b(ri0);
        }
        return false;
    }
}
