package X;

public class Y1 implements eJ<T> {
    public final /* synthetic */ AnonymousClass0U A00;
    public final /* synthetic */ eJ A01;

    public Y1(AnonymousClass0U r1, eJ eJVar) {
        this.A00 = r1;
        this.A01 = eJVar;
    }

    @Override // X.eJ
    public final T get() {
        AnonymousClass0U r2 = this.A00;
        Object A1y = r2.A1y();
        try {
            return (T) this.A01.get();
        } finally {
            r2.A20(A1y);
        }
    }
}
