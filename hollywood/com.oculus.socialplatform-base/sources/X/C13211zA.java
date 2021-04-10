package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1zA  reason: invalid class name and case insensitive filesystem */
public final class C13211zA extends AtomicInteger implements AbstractC12941yc {
    public static final long serialVersionUID = -7965400327305809232L;
    public final AbstractC12941yc downstream;
    public int index;
    public final AnonymousClass1xW sd = new AnonymousClass1xW();
    public final AbstractC12981yg[] sources;

    public final void A00() {
        if (!EnumC12511xi.isDisposed((AbstractC12271xB) this.sd.get()) && getAndIncrement() == 0) {
            AbstractC12981yg[] r2 = this.sources;
            while (!EnumC12511xi.isDisposed((AbstractC12271xB) this.sd.get())) {
                int i = this.index;
                this.index = i + 1;
                if (i == r2.length) {
                    this.downstream.onComplete();
                    return;
                }
                r2[i].AAZ(this);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    @Override // X.AbstractC12941yc
    public final void A8A(AbstractC12271xB r2) {
        EnumC12511xi.replace(this.sd, r2);
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    public C13211zA(AbstractC12941yc r2, AbstractC12981yg[] r3) {
        this.downstream = r2;
        this.sources = r3;
    }

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        A00();
    }
}
