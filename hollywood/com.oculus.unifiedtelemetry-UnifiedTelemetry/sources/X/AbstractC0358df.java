package X;

import java.io.Closeable;

/* renamed from: X.df  reason: case insensitive filesystem */
public abstract class AbstractC0358df implements Closeable {
    public abstract long A00();

    public abstract C0366dn A01();

    public abstract KC A02();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        dZ.A06(A02());
    }
}
