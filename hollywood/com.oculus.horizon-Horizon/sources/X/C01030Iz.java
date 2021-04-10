package X;

import javax.annotation.Nullable;
import javax.inject.Provider;

/* renamed from: X.0Iz  reason: invalid class name and case insensitive filesystem */
public final class C01030Iz<T> implements AnonymousClass0p1<T> {
    public T A00;
    @Nullable
    public Provider<T> A01;
    public final byte A02 = AnonymousClass0Qe.A01.get().A00;
    @Nullable
    public volatile AnonymousClass0pL A03;

    @Override // javax.inject.Provider, X.AnonymousClass0p1
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AnonymousClass0pL r4 = this.A03;
                    AnonymousClass0Qe r3 = AnonymousClass0Qe.A01.get();
                    byte b = this.A02;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2Y = r4.A2Y();
                    try {
                        this.A00 = this.A01.get();
                        this.A01 = null;
                        this.A03 = null;
                    } finally {
                        r4.A2b(A2Y);
                        r3.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public C01030Iz(Provider<T> provider, AnonymousClass0pL r3) {
        this.A01 = provider;
        this.A03 = r3;
    }
}
