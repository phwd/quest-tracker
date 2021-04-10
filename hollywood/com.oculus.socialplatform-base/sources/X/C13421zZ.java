package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1zZ  reason: invalid class name and case insensitive filesystem */
public final class C13421zZ<T, R> extends AtomicInteger implements AbstractC12271xB {
    public static final long serialVersionUID = -5556924161382950569L;
    public final AbstractC12721yD<? super R> downstream;
    public final C13431za<T>[] observers;
    public final Object[] values;
    public final AbstractC13031yl<? super Object[], ? extends R> zipper;

    public final void A00(Throwable th, int i) {
        if (getAndSet(0) > 0) {
            C13431za<T>[] r3 = this.observers;
            int length = r3.length;
            for (int i2 = 0; i2 < i; i2++) {
                EnumC12511xi.dispose(r3[i2]);
            }
            while (true) {
                i++;
                if (i < length) {
                    EnumC12511xi.dispose(r3[i]);
                } else {
                    this.downstream.onError(th);
                    return;
                }
            }
        } else {
            AnonymousClass1y3.A01(th);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (getAndSet(0) > 0) {
            for (C13431za<T> r0 : this.observers) {
                EnumC12511xi.dispose(r0);
            }
        }
    }

    public C13421zZ(AbstractC12721yD<? super R> r4, int i, AbstractC13031yl<? super Object[], ? extends R> r6) {
        super(i);
        this.downstream = r4;
        this.zipper = r6;
        C13431za<T>[] r2 = new C13431za[i];
        for (int i2 = 0; i2 < i; i2++) {
            r2[i2] = new C13431za<>(this, i2);
        }
        this.observers = r2;
        this.values = new Object[i];
    }
}
