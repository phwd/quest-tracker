package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20V  reason: invalid class name */
public final class AnonymousClass20V<R> extends AtomicReference<AbstractC12271xB> implements AbstractC12501xh<R> {
    public static final long serialVersionUID = 8042919737683345351L;
    public volatile R item;
    public final AnonymousClass20R<?, R> parent;

    @Override // X.AbstractC12501xh
    public final void onComplete() {
        AnonymousClass20R<?, R> r2 = this.parent;
        if (r2.inner.compareAndSet(this, null)) {
            r2.A01();
        }
    }

    @Override // X.AbstractC12501xh
    public final void onError(Throwable th) {
        AnonymousClass20R<?, R> r2 = this.parent;
        if (!r2.inner.compareAndSet(this, null) || !r2.errors.A01(th)) {
            AnonymousClass1y3.A01(th);
            return;
        }
        if (!r2.delayErrors) {
            r2.upstream.dispose();
            AnonymousClass20R.A00(r2);
        }
        r2.A01();
    }

    @Override // X.AbstractC12501xh
    public final void onSuccess(R r) {
        this.item = r;
        this.parent.A01();
    }

    public AnonymousClass20V(AnonymousClass20R<?, R> r1) {
        this.parent = r1;
    }

    @Override // X.AbstractC12501xh
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
