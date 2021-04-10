package X;

/* renamed from: X.0Gj  reason: invalid class name and case insensitive filesystem */
public final class C01360Gj<T> extends AnonymousClass0Li<T> implements AbstractC02980bI<T> {
    public T A00;
    public boolean A01;
    public final C03060bR A02;
    public final AbstractC07240oz<T> A03;

    @Override // X.AbstractC07240oz, X.AbstractC02980bI
    public final synchronized T get() {
        if (!this.A01) {
            AnonymousClass0RZ r4 = AnonymousClass0RZ.A01.get();
            byte b = r4.A00;
            r4.A00 = (byte) (1 | b);
            try {
                AnonymousClass0RA A012 = this.A02.A01();
                try {
                    this.A00 = this.A03.get();
                    this.A01 = true;
                } finally {
                    C03060bR.A00(A012);
                }
            } finally {
                r4.A00 = b;
            }
        }
        return this.A00;
    }

    public C01360Gj(C03060bR r1, AbstractC07240oz<T> r2) {
        this.A02 = r1;
        this.A03 = r2;
    }
}
