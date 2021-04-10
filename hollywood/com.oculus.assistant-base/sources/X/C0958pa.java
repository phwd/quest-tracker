package X;

import java.io.Closeable;

/* renamed from: X.pa  reason: case insensitive filesystem */
public final class C0958pa implements Closeable {
    public final K3 A00;
    public final K4 A01;

    public C0958pa(K3 k3, K4 k4) {
        C0514bB.A02(k3, "writableDatabase");
        C0514bB.A02(k4, "changeHandle");
        this.A00 = k3;
        this.A01 = k4;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A01.close();
    }
}
