package X;

import java.io.IOException;

/* renamed from: X.0HU  reason: invalid class name */
public class AnonymousClass0HU extends AnonymousClass0PU {
    public boolean A00;
    public final /* synthetic */ AnonymousClass0PK A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0HU(AnonymousClass0PK r1) {
        super(r1);
        this.A01 = r1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
    public final void close() throws IOException {
        if (!super.A00) {
            if (!this.A00) {
                A00(false);
            }
            super.A00 = true;
        }
    }

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
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
