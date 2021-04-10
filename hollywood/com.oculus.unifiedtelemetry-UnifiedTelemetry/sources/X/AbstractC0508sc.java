package X;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: X.sc  reason: case insensitive filesystem */
public abstract class AbstractC0508sc implements Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this instanceof C0488ry) {
            ((C0488ry) this).A01.close();
        }
    }
}
