package defpackage;

/* renamed from: wj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5468wj extends OK {
    public boolean F;
    public final /* synthetic */ UH0 G;
    public final /* synthetic */ C5638xj H;

    public C5468wj(C5638xj xjVar, UH0 uh0) {
        this.H = xjVar;
        this.G = uh0;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void g(int i) {
        AbstractC4277pj pjVar = this.H.F.a0;
        if (pjVar == null || !pjVar.s()) {
            ((GP0) this.H.M.get()).a(this.G);
            this.F = true;
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void i(int i) {
        if (i == 0) {
            C5638xj xjVar = this.H;
            if (xjVar.H || !xjVar.N.b()) {
                AbstractC4277pj pjVar = this.H.F.a0;
                if (pjVar != null) {
                    pjVar.f();
                }
                C5638xj xjVar2 = this.H;
                xjVar2.H = false;
                xjVar2.v(true);
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void k(int i) {
        if (this.F) {
            ((GP0) this.H.M.get()).b.a(true);
            this.F = false;
        }
        C5638xj xjVar = this.H;
        AbstractC4277pj pjVar = xjVar.F.a0;
        if (pjVar != null) {
            AbstractC4277pj pjVar2 = (AbstractC4277pj) xjVar.G.peek();
            if (pjVar != null && pjVar2 != null && pjVar2.k() < pjVar.k()) {
                this.H.G.add(pjVar);
                this.H.F.v(0, true, 0);
            }
        }
    }
}
