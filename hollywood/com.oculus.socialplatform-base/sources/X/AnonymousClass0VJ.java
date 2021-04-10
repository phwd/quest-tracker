package X;

import javax.annotation.Nullable;

/* renamed from: X.0VJ  reason: invalid class name */
public abstract class AnonymousClass0VJ<T> implements AbstractC03180ld<T> {
    public T A00;
    public final byte A01 = C01130Rh.A01.get().A00;
    public final AnonymousClass0lg A02;
    @Nullable
    public volatile AbstractC03270lw A03;

    public abstract T A01(AnonymousClass0lg v);

    @Override // javax.inject.Provider, X.AbstractC03180ld
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AbstractC03270lw r4 = this.A03;
                    C01130Rh r3 = C01130Rh.A01.get();
                    byte b = this.A01;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2s = r4.A2s();
                    try {
                        this.A00 = A01(this.A02.getScopeUnawareInjector());
                        this.A03 = null;
                    } finally {
                        r4.A2u(A2s);
                        r3.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public AnonymousClass0VJ(AnonymousClass0lg r2) {
        this.A02 = r2;
        this.A03 = r2.getScopeAwareInjector();
    }
}
