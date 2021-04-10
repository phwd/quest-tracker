package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;

/* renamed from: Oc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0859Oc extends AbstractC1740ar1 implements Jq1 {
    public final ViewGroup F;
    public C4305ps0 G;
    public C2379ed H;
    public C5836ys0 I;

    public C0859Oc(ViewGroup viewGroup, AbstractC0920Pc pc, AbstractC0046As0 as0, Qq1 qq1, M2 m2, Q31 q31, C1595a3 a3Var, Q31 q312, AbstractC4422qa0 qa0) {
        this.F = viewGroup;
        Context context = viewGroup.getContext();
        UH0 uh0 = new UH0(AbstractC5701y31.f);
        C4935tb0 tb0 = new C4935tb0();
        uh0.m(AbstractC5701y31.b, as0);
        QH0 qh0 = AbstractC5701y31.f11659a;
        uh0.j(qh0, false);
        uh0.m(AbstractC5701y31.c, tb0);
        this.G = new C4305ps0(context, new C4767sc(this));
        C2379ed edVar = new C2379ed(context, pc, qq1, new AutocompleteController(), uh0, new Handler(), m2, q31, a3Var, q312, qa0);
        this.H = edVar;
        C4305ps0 ps0 = this.G;
        ps0.getClass();
        C4937tc tcVar = new C4937tc(ps0);
        C4044oJ oJVar = edVar.g0;
        Context context2 = edVar.F;
        AbstractC0920Pc pc2 = edVar.G;
        Qq1 qq12 = edVar.H;
        Objects.requireNonNull(oJVar);
        C3019iJ iJVar = new C3019iJ(oJVar);
        C3189jJ jJVar = new C3189jJ(oJVar);
        C3360kJ kJVar = new C3360kJ(oJVar);
        C3531lJ lJVar = new C3531lJ(oJVar);
        C3702mJ mJVar = new C3702mJ(oJVar);
        oJVar.c = new YW(context2, edVar, pc2);
        oJVar.f10541a.add(new YJ(context2, edVar, pc2, jJVar, kJVar, lJVar));
        oJVar.f10541a.add(new U6(context2, edVar, qq12, iJVar));
        oJVar.f10541a.add(new C4824sv(context2, edVar, jJVar));
        oJVar.f10541a.add(new C5241vL(context2, edVar, iJVar));
        oJVar.f10541a.add(new C5795ye1(context2, edVar));
        oJVar.f10541a.add(new C4617ri1(context2, tcVar));
        oJVar.f10541a.add(new C1190Tl0(context2, edVar, jJVar));
        oJVar.f10541a.add(new C1359Wg(context2, edVar, qq12, jJVar, mJVar));
        uh0.m(AbstractC5701y31.e, this.H);
        C0798Nc nc = new C0798Nc(this, context, tb0);
        C5107uc ucVar = new C5107uc(this);
        A31 a31 = nc.b;
        if (a31 != null) {
            Objects.requireNonNull(this);
            this.I = a31.b;
        } else {
            nc.f8558a.add(ucVar);
        }
        P70.a(uh0, qh0, nc, new C5277vc());
        f();
    }

    @Override // defpackage.Jq1
    public void b(String str, String str2) {
        this.H.o(str);
    }

    @Override // defpackage.AbstractC1740ar1
    public void d(boolean z) {
        C2379ed edVar = this.H;
        edVar.S = z ? 2 : 0;
        edVar.t();
    }

    @Override // defpackage.AbstractC1740ar1
    public void e(boolean z) {
        MZ mz;
        C2379ed edVar = this.H;
        if (z) {
            edVar.V = false;
            edVar.Q = System.currentTimeMillis();
            edVar.S = 1;
            if (edVar.O) {
                edVar.q();
            } else {
                edVar.K.add(new RunnableC1469Yc(edVar));
            }
        } else {
            if (edVar.O) {
                edVar.h0.b();
            }
            boolean z2 = edVar.V;
            int i = I31.b;
            AbstractC3100ip1.f10165a.a("Omnibox.FocusResultedInNavigation", z2);
            edVar.S = 0;
            edVar.T = 0;
            edVar.U = -1;
            edVar.k();
        }
        C4044oJ oJVar = edVar.g0;
        if (!z && (mz = oJVar.f) != null) {
            mz.a();
        }
        if (!z) {
            oJVar.k = false;
        }
        Objects.requireNonNull(oJVar.c);
        for (int i2 = 0; i2 < oJVar.f10541a.size(); i2++) {
            ((B31) oJVar.f10541a.get(i2)).b(z);
        }
    }

    public void f() {
        C2379ed edVar = this.H;
        ViewGroup viewGroup = this.F;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int layoutDirection = viewGroup.getLayoutDirection();
        C4215pJ pJVar = edVar.h0;
        if (pJVar.b != layoutDirection) {
            pJVar.b = layoutDirection;
            for (int i = 0; i < pJVar.d.size(); i++) {
                ((C2848hJ) pJVar.d.get(i)).b.l(AbstractC4851t31.b, layoutDirection);
            }
        }
    }
}
