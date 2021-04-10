package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.146  reason: invalid class name */
public final class AnonymousClass146 extends Writer {
    public final AnonymousClass145 A00 = new AnonymousClass145();
    public final Appendable A01;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public AnonymousClass146(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        this.A01.append((char) i);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        AnonymousClass145 r1 = this.A00;
        r1.A00 = cArr;
        this.A01.append(r1, i, i2 + i);
    }
}
