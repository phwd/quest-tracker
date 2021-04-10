package X;

import java.io.Closeable;

/* renamed from: X.0D0  reason: invalid class name */
public final class AnonymousClass0D0 implements Closeable {
    public boolean A00;
    public AnonymousClass0D2 A01;
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
                AnonymousClass0D2 r0 = this.A01;
                synchronized (r0.A02) {
                    AnonymousClass0D2.A00(r0);
                    r0.A03.remove(this);
                }
                this.A01 = null;
                this.A02 = null;
            }
        }
    }

    public AnonymousClass0D0(AnonymousClass0D2 r2, Runnable runnable) {
        this.A01 = r2;
        this.A02 = runnable;
    }
}
