package X;

import java.io.IOException;
import java.io.Writer;

public final class hI extends Writer {
    public final hJ A00 = new hJ();
    public final Appendable A01;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public hI(Appendable appendable) {
        this.A01 = appendable;
    }

    @Override // java.io.Writer
    public final void write(int i) throws IOException {
        this.A01.append((char) i);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        hJ hJVar = this.A00;
        hJVar.A00 = cArr;
        this.A01.append(hJVar, i, i2 + i);
    }
}
