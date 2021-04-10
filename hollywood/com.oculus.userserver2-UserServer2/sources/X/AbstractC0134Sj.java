package X;

/* renamed from: X.Sj  reason: case insensitive filesystem */
public abstract class AbstractC0134Sj<T> implements AbstractC0192Xx<T> {
    public final SZ A00;
    public final BX A01;

    @Override // X.AbstractC0192Xx
    public final T get() {
        AnonymousClass3G scopeUnawareInjector = this.A00.getScopeUnawareInjector();
        BX bx = this.A01;
        Object A1U = bx.A1U();
        try {
            BW bw = (BW) this;
            try {
                return (T) IX.A00(bw.A00, scopeUnawareInjector);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(bw.A00)), e);
            }
        } finally {
            bx.A1W(A1U);
        }
    }

    public AbstractC0134Sj(SZ sz) {
        this.A00 = sz;
        this.A01 = sz.getScopeAwareInjector();
    }
}
