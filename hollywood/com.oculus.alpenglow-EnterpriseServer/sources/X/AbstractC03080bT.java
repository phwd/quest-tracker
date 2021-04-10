package X;

/* renamed from: X.0bT  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03080bT<T> implements AbstractC07240oz<T> {
    public final AbstractC02990bJ A00;
    public final AnonymousClass0Lf A01;

    public abstract T A00(AbstractC02990bJ v);

    @Override // X.AbstractC07240oz
    public final T get() {
        C007508o scopeUnawareInjector = this.A00.getScopeUnawareInjector();
        AnonymousClass0Lf r2 = this.A01;
        Object A2P = r2.A2P();
        try {
            return A00(scopeUnawareInjector);
        } finally {
            r2.A2U(A2P);
        }
    }

    public AbstractC03080bT(AbstractC02990bJ r2) {
        this.A00 = r2;
        this.A01 = r2.getScopeAwareInjector();
    }
}
