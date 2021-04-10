package X;

/* renamed from: X.36  reason: invalid class name */
public final class AnonymousClass36 extends AbstractC1135tb {
    public boolean A00;
    public final /* synthetic */ tY A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass36(tY tYVar) {
        super(tYVar);
        this.A01 = tYVar;
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r6, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (!super.A00) {
            if (!this.A00) {
                long A4c = this.A01.A04.A4c(r6, j);
                if (A4c != -1) {
                    return A4c;
                }
                this.A00 = true;
                A00(true);
            }
            return -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        if (!super.A00) {
            if (!this.A00) {
                A00(false);
            }
            super.A00 = true;
        }
    }
}
