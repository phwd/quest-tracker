package X;

import java.io.Closeable;

public abstract class XJ implements Closeable {
    public final long A00() {
        if (!(this instanceof ET)) {
            return ((C0050Ea) this).A00;
        }
        String A00 = ((ET) this).A00.A00("Content-Length");
        if (A00 == null) {
            return -1;
        }
        try {
            return Long.parseLong(A00);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final Dp A01() {
        if (!(this instanceof ET)) {
            return ((C0050Ea) this).A02;
        }
        return ((ET) this).A01;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        XD.A06(A01());
    }
}
