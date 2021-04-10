package X;

public class Q1 extends ThreadLocal<QF> {
    public final /* synthetic */ C00103y A00;

    public Q1(C00103y r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final QF initialValue() {
        return new QF(this.A00.A04);
    }
}
