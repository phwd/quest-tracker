package X;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.sa  reason: case insensitive filesystem */
public final class C0506sa implements Closeable {
    public final C0519t9 A00;
    public final InputStream A01;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    public C0506sa(C0519t9 t9Var, InputStream inputStream) {
        this.A00 = t9Var;
        this.A01 = inputStream;
    }
}
