package X;

import javax.annotation.Nullable;

public final class BY<T> implements SY<T> {
    public T A00;
    @Nullable
    public AbstractC0192Xx<T> A01;
    public final byte A02 = PE.A01.get().A00;
    @Nullable
    public volatile Sf A03;

    @Override // X.AbstractC0192Xx
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    Sf sf = this.A03;
                    PE pe = PE.A01.get();
                    byte b = this.A02;
                    byte b2 = pe.A00;
                    pe.A00 = (byte) (b | b2);
                    Object A1U = sf.A1U();
                    try {
                        this.A00 = this.A01.get();
                        this.A01 = null;
                        this.A03 = null;
                    } finally {
                        sf.A1W(A1U);
                        pe.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public BY(AbstractC0192Xx<T> xx, Sf sf) {
        this.A01 = xx;
        this.A03 = sf;
    }
}
