package X;

public class Sg implements AbstractC0192Xx<T> {
    public final /* synthetic */ AnonymousClass0S A00;
    public final /* synthetic */ AbstractC0192Xx A01;

    public Sg(AnonymousClass0S r1, AbstractC0192Xx xx) {
        this.A00 = r1;
        this.A01 = xx;
    }

    @Override // X.AbstractC0192Xx
    public final T get() {
        AnonymousClass0S r2 = this.A00;
        Object A1U = r2.A1U();
        try {
            return (T) this.A01.get();
        } finally {
            r2.A1W(A1U);
        }
    }
}
