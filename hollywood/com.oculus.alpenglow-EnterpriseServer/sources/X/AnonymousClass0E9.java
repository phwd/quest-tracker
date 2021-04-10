package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0E9  reason: invalid class name */
public final class AnonymousClass0E9 extends Writer {
    public final C01210Dz A00 = new C01210Dz();
    public final Appendable A01;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public AnonymousClass0E9(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        this.A01.append((char) i);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        C01210Dz r1 = this.A00;
        r1.A00 = cArr;
        this.A01.append(r1, i, i2 + i);
    }
}
