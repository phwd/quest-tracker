package X;

import javax.inject.Provider;

/* renamed from: X.0mO  reason: invalid class name */
public abstract class AnonymousClass0mO<T> implements Provider<T> {
    public final AnonymousClass0lg A00;
    public final AnonymousClass0VD A01;

    public abstract T A01(AnonymousClass0lg v);

    @Override // javax.inject.Provider
    public final T get() {
        C002105e scopeUnawareInjector = this.A00.getScopeUnawareInjector();
        AnonymousClass0VD r2 = this.A01;
        Object A2s = r2.A2s();
        try {
            return A01(scopeUnawareInjector);
        } finally {
            r2.A2u(A2s);
        }
    }

    public AnonymousClass0mO(AnonymousClass0lg r2) {
        this.A00 = r2;
        this.A01 = r2.getScopeAwareInjector();
    }
}
