package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xg  reason: invalid class name and case insensitive filesystem */
public final class C12491xg<T> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1vA<T>, AbstractC12271xB {
    public static final long serialVersionUID = -2467358622224974244L;
    public final AbstractC12501xh<? super T> downstream;

    @Override // X.AnonymousClass1vA
    public final void onError(Throwable th) {
        AbstractC12271xB r1;
        Throwable th2 = th;
        if (th == null) {
            th2 = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj == r0 || (r1 = (AbstractC12271xB) getAndSet(r0)) == r0) {
            AnonymousClass1y3.A01(th);
            return;
        }
        try {
            this.downstream.onError(th2);
        } finally {
            if (r1 != null) {
                r1.dispose();
            }
        }
    }

    @Override // X.AnonymousClass1vA
    public final void A9i(AbstractC06511aN r2) {
        EnumC12511xi.set(this, new C12471xe(r2));
    }

    public C12491xg(AbstractC12501xh<? super T> r1) {
        this.downstream = r1;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass1vA
    public final void onComplete() {
        AbstractC12271xB r1;
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj != r0 && (r1 = (AbstractC12271xB) getAndSet(r0)) != r0) {
            try {
                this.downstream.onComplete();
            } finally {
                if (r1 != null) {
                    r1.dispose();
                }
            }
        }
    }

    @Override // X.AnonymousClass1vA
    public final void onSuccess(T t) {
        AbstractC12271xB r3;
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj != r0 && (r3 = (AbstractC12271xB) getAndSet(r0)) != r0) {
            if (t == null) {
                try {
                    this.downstream.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    if (r3 != null) {
                        r3.dispose();
                    }
                    throw th;
                }
            } else {
                this.downstream.onSuccess(t);
            }
            if (r3 != null) {
                r3.dispose();
            }
        }
    }

    public final String toString() {
        return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
    }
}
