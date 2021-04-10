package X;

import java.io.Closeable;
import java.io.InputStream;

public final class KS implements Closeable {
    public final KQ A00;
    public final InputStream A01;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A01.close();
    }

    public KS(KQ kq, InputStream inputStream) {
        this.A00 = kq;
        this.A01 = inputStream;
    }
}
