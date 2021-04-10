package X;

import javax.inject.Provider;

/* renamed from: X.0pP  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06650pP<T> implements Provider<T> {
    public final AbstractC06640p5 A00;
    public final AnonymousClass0Iy A01;

    public abstract T A00(AbstractC06640p5 v);

    @Override // javax.inject.Provider
    public final T get() {
        AnonymousClass04R scopeUnawareInjector = this.A00.getScopeUnawareInjector();
        AnonymousClass0Iy r2 = this.A01;
        Object A2Y = r2.A2Y();
        try {
            return A00(scopeUnawareInjector);
        } finally {
            r2.A2b(A2Y);
        }
    }

    public AbstractC06650pP(AbstractC06640p5 r2) {
        this.A00 = r2;
        this.A01 = r2.getScopeAwareInjector();
    }
}
