package X;

import javax.annotation.Nullable;

/* renamed from: X.Bd  reason: case insensitive filesystem */
public abstract class AbstractC0032Bd<T> implements SY<T> {
    public T A00;
    public final byte A01 = PE.A01.get().A00;
    public final SZ A02;
    @Nullable
    public volatile Sf A03;

    @Override // X.AbstractC0192Xx
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    Sf sf = this.A03;
                    PE pe = PE.A01.get();
                    byte b = this.A01;
                    byte b2 = pe.A00;
                    pe.A00 = (byte) (b | b2);
                    Object A1U = sf.A1U();
                    try {
                        AnonymousClass8B r1 = (AnonymousClass8B) this;
                        try {
                            this.A00 = (T) IX.A00(r1.A00, this.A02.getScopeUnawareInjector());
                            this.A03 = null;
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(r1.A00)), e);
                        }
                    } finally {
                        sf.A1W(A1U);
                        pe.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public AbstractC0032Bd(SZ sz) {
        this.A02 = sz;
        this.A03 = sz.getScopeAwareInjector();
    }
}
