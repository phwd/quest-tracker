package X;

import java.io.IOException;

/* renamed from: X.9G  reason: invalid class name */
public class AnonymousClass9G extends L0 {
    public boolean A00;
    public final /* synthetic */ C0127Kx A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass9G(C0127Kx kx) {
        super(kx);
        this.A01 = kx;
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!super.A00) {
            if (!this.A00) {
                A00(false);
            }
            super.A00 = true;
        }
    }

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A03("byteCount < 0: ", j));
        } else if (!super.A00) {
            if (!this.A00) {
                long read = this.A01.A04.read(r6, j);
                if (read != -1) {
                    return read;
                }
                this.A00 = true;
                A00(true);
            }
            return -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }
}
