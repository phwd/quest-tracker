package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.0PN  reason: invalid class name */
public final class AnonymousClass0PN implements AnonymousClass0h1 {
    public long A00;
    public boolean A01;
    public final AnonymousClass0OW A02;
    public final /* synthetic */ AnonymousClass0PK A03;

    public AnonymousClass0PN(AnonymousClass0PK r3, long j) {
        this.A03 = r3;
        this.A02 = new AnonymousClass0OW(r3.A01.timeout());
        this.A00 = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public final void close() throws IOException {
        if (!this.A01) {
            this.A01 = true;
            if (this.A00 <= 0) {
                AnonymousClass0PK.A00(this.A02);
                this.A03.A00 = 3;
                return;
            }
            throw new ProtocolException("unexpected end of stream");
        }
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public final void flush() throws IOException {
        if (!this.A01) {
            this.A03.A01.flush();
        }
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A02;
    }

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r8, long j) throws IOException {
        if (!this.A01) {
            long j2 = r8.A00;
            if ((0 | j) < 0 || 0 > j2 || j2 - 0 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            long j3 = this.A00;
            if (j <= j3) {
                this.A03.A01.write(r8, j);
                this.A00 -= j;
                return;
            }
            throw new ProtocolException("expected " + j3 + " bytes but received " + j);
        }
        throw new IllegalStateException("closed");
    }
}
