package X;

import java.io.Closeable;

/* renamed from: X.bs  reason: case insensitive filesystem */
public abstract class AbstractC0555bs implements Closeable {
    public final long A00() {
        if (!(this instanceof C1137td)) {
            return ((C1144tk) this).A00;
        }
        String A00 = ((C1137td) this).A00.A00("Content-Length");
        if (A00 == null) {
            return -1;
        }
        try {
            return Long.parseLong(A00);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final t4 A01() {
        if (!(this instanceof C1137td)) {
            return ((C1144tk) this).A01;
        }
        return ((C1137td) this).A01;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        C0561by.A06(A01());
    }
}
