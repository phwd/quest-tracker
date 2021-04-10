package X;

import java.io.Closeable;

/* renamed from: X.Cz  reason: case insensitive filesystem */
public final class C0080Cz implements Closeable {
    public boolean A00;
    public D1 A01;
    public Runnable A02;
    public final Object A03 = new Object();

    public final void A00() {
        synchronized (this.A03) {
            if (!this.A00) {
                this.A02.run();
                close();
            } else {
                throw new IllegalStateException("Object already closed");
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.A03) {
            if (!this.A00) {
                this.A00 = true;
                D1 d1 = this.A01;
                synchronized (d1.A02) {
                    D1.A00(d1);
                    d1.A03.remove(this);
                }
                this.A01 = null;
                this.A02 = null;
            }
        }
    }

    public C0080Cz(D1 d1, Runnable runnable) {
        this.A01 = d1;
        this.A02 = runnable;
    }
}
