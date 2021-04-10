package X;

import javax.annotation.Nullable;

/* renamed from: X.0Lg  reason: invalid class name and case insensitive filesystem */
public final class C01740Lg<T> implements AbstractC02980bI<T> {
    public T A00;
    @Nullable
    public AbstractC07240oz<T> A01;
    public final byte A02 = AnonymousClass0RZ.A01.get().A00;
    @Nullable
    public volatile AbstractC03040bP A03;

    @Override // X.AbstractC07240oz, X.AbstractC02980bI
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    AbstractC03040bP r4 = this.A03;
                    AnonymousClass0RZ r3 = AnonymousClass0RZ.A01.get();
                    byte b = this.A02;
                    byte b2 = r3.A00;
                    r3.A00 = (byte) (b | b2);
                    Object A2P = r4.A2P();
                    try {
                        this.A00 = this.A01.get();
                        this.A01 = null;
                        this.A03 = null;
                    } finally {
                        r4.A2U(A2P);
                        r3.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public C01740Lg(AbstractC07240oz<T> r2, AbstractC03040bP r3) {
        this.A01 = r2;
        this.A03 = r3;
    }
}
