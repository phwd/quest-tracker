package X;

import java.io.IOException;

/* renamed from: X.0PT  reason: invalid class name */
public final class AnonymousClass0PT implements AnonymousClass0h1 {
    public boolean A00;
    public final AnonymousClass0OW A01;
    public final /* synthetic */ AnonymousClass0PK A02;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public final synchronized void close() throws IOException {
        if (!this.A00) {
            this.A00 = true;
            AnonymousClass0PK r2 = this.A02;
            r2.A01.A9F("0\r\n\r\n");
            AnonymousClass0PK.A00(this.A01);
            r2.A00 = 3;
        }
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public final synchronized void flush() throws IOException {
        if (!this.A00) {
            this.A02.A01.flush();
        }
    }

    public AnonymousClass0PT(AnonymousClass0PK r3) {
        this.A02 = r3;
        this.A01 = new AnonymousClass0OW(r3.A01.timeout());
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A01;
    }

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r4, long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            AnonymousClass0Oe r1 = this.A02.A01;
            r1.A95(j);
            r1.A9F("\r\n");
            r1.write(r4, j);
            r1.A9F("\r\n");
        }
    }
}
