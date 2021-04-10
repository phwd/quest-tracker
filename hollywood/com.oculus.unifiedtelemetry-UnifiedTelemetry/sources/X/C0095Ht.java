package X;

import javax.annotation.Nullable;

/* renamed from: X.Ht  reason: case insensitive filesystem */
public final class C0095Ht<T> implements AbstractC0246Xt<T> {
    public T A00;
    @Nullable
    public eJ<T> A01;
    public final byte A02 = Qe.A01.get().A00;
    @Nullable
    public volatile Y0 A03;

    @Override // X.eJ, X.AbstractC0246Xt
    public final T get() {
        if (this.A03 != null) {
            synchronized (this) {
                if (this.A03 != null) {
                    Y0 y0 = this.A03;
                    Qe qe = Qe.A01.get();
                    byte b = this.A02;
                    byte b2 = qe.A00;
                    qe.A00 = (byte) (b | b2);
                    Object A1y = y0.A1y();
                    try {
                        this.A00 = this.A01.get();
                        this.A01 = null;
                        this.A03 = null;
                    } finally {
                        y0.A20(A1y);
                        qe.A00 = b2;
                    }
                }
            }
        }
        return this.A00;
    }

    public C0095Ht(eJ<T> eJVar, Y0 y0) {
        this.A01 = eJVar;
        this.A03 = y0;
    }
}
