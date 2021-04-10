package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yK  reason: invalid class name and case insensitive filesystem */
public final class C12791yK<T> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1vb<T>, AbstractC12271xB {
    public static final long serialVersionUID = -3434801548987643227L;
    public final AnonymousClass1yM<? super T> observer;

    @Override // X.AnonymousClass22F
    public final void onError(Throwable th) {
        Throwable th2 = th;
        if (th == null) {
            th2 = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (!EnumC12511xi.isDisposed((AbstractC12271xB) get())) {
            try {
                this.observer.onError(th2);
            } finally {
                dispose();
            }
        } else {
            AnonymousClass1y3.A01(th);
        }
    }

    @Override // X.AnonymousClass1vb
    public final void A9i(AbstractC06511aN r2) {
        A9q(new C12471xe(r2));
    }

    @Override // X.AnonymousClass22F
    public final void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!EnumC12511xi.isDisposed((AbstractC12271xB) get())) {
            this.observer.onNext(t);
        }
    }

    public C12791yK(AnonymousClass1yM<? super T> r1) {
        this.observer = r1;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass22F
    public final void onComplete() {
        if (!EnumC12511xi.isDisposed((AbstractC12271xB) get())) {
            try {
                this.observer.onComplete();
            } finally {
                dispose();
            }
        }
    }

    public final String toString() {
        return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
    }

    @Override // X.AnonymousClass1vb
    public final void A9q(AbstractC12271xB r1) {
        EnumC12511xi.set(this, r1);
    }
}
