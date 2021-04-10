package X;

import java.io.IOException;

public class Tk extends AbstractC0131Ob<T> {
    public AbstractC0131Ob<T> A00;
    public final /* synthetic */ boolean A01;
    public final /* synthetic */ boolean A02;
    public final /* synthetic */ HY A03;
    public final /* synthetic */ Tj A04;
    public final /* synthetic */ lQ A05;

    public Tk(Tj tj, boolean z, boolean z2, HY hy, lQ lQVar) {
        this.A04 = tj;
        this.A01 = z;
        this.A02 = z2;
        this.A03 = hy;
        this.A05 = lQVar;
    }

    @Override // X.AbstractC0131Ob
    public final T A02(lk lkVar) throws IOException {
        if (this.A01) {
            lkVar.A0Q();
            return null;
        }
        AbstractC0131Ob<T> ob = this.A00;
        if (ob == null) {
            ob = this.A03.A03(this.A04, this.A05);
            this.A00 = ob;
        }
        return ob.A02(lkVar);
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, T t) throws IOException {
        if (this.A02) {
            mmVar.A0B();
            return;
        }
        AbstractC0131Ob<T> ob = this.A00;
        if (ob == null) {
            ob = this.A03.A03(this.A04, this.A05);
            this.A00 = ob;
        }
        ob.A03(mmVar, t);
    }
}
