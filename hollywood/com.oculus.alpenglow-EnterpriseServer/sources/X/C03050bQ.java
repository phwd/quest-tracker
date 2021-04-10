package X;

/* renamed from: X.0bQ  reason: invalid class name and case insensitive filesystem */
public class C03050bQ implements AbstractC07240oz<T> {
    public final /* synthetic */ AbstractC006205y A00;
    public final /* synthetic */ AbstractC07240oz A01;

    public C03050bQ(AbstractC006205y r1, AbstractC07240oz r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC07240oz
    public final T get() {
        AbstractC006205y r2 = this.A00;
        Object A2P = r2.A2P();
        try {
            return (T) this.A01.get();
        } finally {
            r2.A2U(A2P);
        }
    }
}
