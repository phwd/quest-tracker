package X;

import java.util.concurrent.Executor;

/* renamed from: X.st  reason: case insensitive filesystem */
public final class C1106st implements AbstractC0349Sq {
    public AbstractC0346Si A00;
    public final Object A01 = new Object();
    public final Executor A02;

    @Override // X.AbstractC0349Sq
    public final void A6G(Sk sk) {
        AbstractC0346Si si;
        synchronized (this.A01) {
            si = this.A00;
        }
        if (si != null) {
            this.A02.execute(new RunnableC0347So(this, sk));
        }
    }

    public C1106st(Executor executor, AbstractC0346Si si) {
        this.A02 = executor;
        this.A00 = si;
    }
}
