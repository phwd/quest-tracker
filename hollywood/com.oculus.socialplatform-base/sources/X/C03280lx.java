package X;

import javax.inject.Provider;

/* renamed from: X.0lx  reason: invalid class name and case insensitive filesystem */
public class C03280lx implements Provider<T> {
    public final /* synthetic */ AbstractC000300x A00;
    public final /* synthetic */ Provider A01;

    public C03280lx(AbstractC000300x r1, Provider provider) {
        this.A00 = r1;
        this.A01 = provider;
    }

    @Override // javax.inject.Provider
    public final T get() {
        AbstractC000300x r2 = this.A00;
        Object A2s = r2.A2s();
        try {
            return (T) this.A01.get();
        } finally {
            r2.A2u(A2s);
        }
    }
}
