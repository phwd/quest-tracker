package X;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

public final class D1 implements Closeable {
    public boolean A00;
    public boolean A01;
    public final Object A02 = new Object();
    public final List<C0080Cz> A03 = new ArrayList();
    public final ScheduledExecutorService A04 = C0078Cx.A03.A02;

    public static void A00(D1 d1) {
        if (d1.A01) {
            throw new IllegalStateException("Object already closed");
        }
    }

    public final boolean A01() {
        boolean z;
        synchronized (this.A02) {
            A00(this);
            z = this.A00;
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.A02) {
            if (!this.A01) {
                List<C0080Cz> list = this.A03;
                for (C0080Cz cz : list) {
                    cz.close();
                }
                list.clear();
                this.A01 = true;
            }
        }
    }

    public final String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(A01()));
    }
}
