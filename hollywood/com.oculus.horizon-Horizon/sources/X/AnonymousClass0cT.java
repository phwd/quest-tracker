package X;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0cT  reason: invalid class name */
public final class AnonymousClass0cT implements Closeable {
    public final C03210cR A00;
    public final InputStream A01;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    public AnonymousClass0cT(C03210cR r1, InputStream inputStream) {
        this.A00 = r1;
        this.A01 = inputStream;
    }
}
