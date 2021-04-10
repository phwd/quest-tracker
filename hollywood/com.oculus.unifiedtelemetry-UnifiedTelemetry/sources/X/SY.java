package X;

public class SY implements AbstractC0132Os {
    public final /* synthetic */ AbstractC0131Ob A00;
    public final /* synthetic */ lQ A01;

    public SY(lQ lQVar, AbstractC0131Ob ob) {
        this.A01 = lQVar;
        this.A00 = ob;
    }

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        if (lQVar.equals(this.A01)) {
            return this.A00;
        }
        return null;
    }
}
