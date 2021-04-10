package X;

import java.io.IOException;
import java.io.Writer;

public final class VJ extends Writer {
    public final VH A00 = new VH();
    public final Appendable A01;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public VJ(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        this.A01.append((char) i);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        VH vh = this.A00;
        vh.A00 = cArr;
        this.A01.append(vh, i, i2 + i);
    }
}
