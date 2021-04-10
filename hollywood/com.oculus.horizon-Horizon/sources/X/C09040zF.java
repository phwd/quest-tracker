package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.0zF  reason: invalid class name and case insensitive filesystem */
public final class C09040zF extends Writer {
    public final C09030zE A00 = new C09030zE();
    public final Appendable A01;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public C09040zF(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        this.A01.append((char) i);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        C09030zE r1 = this.A00;
        r1.A00 = cArr;
        this.A01.append(r1, i, i2 + i);
    }
}
