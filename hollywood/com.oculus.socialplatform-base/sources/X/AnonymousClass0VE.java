package X;

import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.0VE  reason: invalid class name */
public final class AnonymousClass0VE<T> implements AbstractC03180ld<T> {
    public T A00;
    @Nullable
    public Provider<T> A01;
    public final byte A02 = C01130Rh.A01.get().A00;
    @Nullable
    public volatile AbstractC03270lw A03;

    @Override // javax.inject.Provider, X.AbstractC03180ld
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AbstractC03270lw r4 = this.A03;
                    C01130Rh r3 = C01130Rh.A01.get();
                    byte b = this.A02;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2s = r4.A2s();
                    try {
                        this.A00 = this.A01.get();
                        this.A01 = null;
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

    public AnonymousClass0VE(Provider<T> provider, AbstractC03270lw r3) {
        this.A01 = provider;
        this.A03 = r3;
    }
}
