package X;

import java.io.Closeable;

/* renamed from: X.0kC  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05650kC implements Closeable {
    public abstract long A00();

    public abstract C05820lT A01();

    public abstract AnonymousClass0Od A02();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        C05570jz.A06(A02());
    }
}
