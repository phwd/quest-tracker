package X;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1yU  reason: invalid class name and case insensitive filesystem */
public final class C12871yU<T> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final long serialVersionUID = -7251123623727029452L;
    public final AbstractC12881yV onComplete;
    public final AbstractC12851yS<? super Throwable> onError;
    public final AbstractC12851yS<? super T> onNext;
    public final AbstractC12851yS<? super AbstractC12271xB> onSubscribe;

    public C12871yU(AbstractC12851yS<? super T> r1, AbstractC12851yS<? super Throwable> r2, AbstractC12881yV r3, AbstractC12851yS<? super AbstractC12271xB> r4) {
        this.onNext = r1;
        this.onError = r2;
        this.onComplete = r3;
        this.onSubscribe = r4;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.setOnce(this, r2)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                C12261xA.A00(th);
                r2.dispose();
                onError(th);
            }
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj != r0) {
            lazySet(r0);
            try {
                this.onComplete.run();
            } catch (Throwable th) {
                C12261xA.A00(th);
                AnonymousClass1y3.A01(th);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        Object obj = get();
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (obj == r0) {
            AnonymousClass1y3.A01(th);
            return;
        }
        lazySet(r0);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            AnonymousClass1y3.A01(new AnonymousClass1Ox(Arrays.asList(th, th2)));
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (get() != EnumC12511xi.DISPOSED) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                C12261xA.A00(th);
                ((AbstractC12271xB) get()).dispose();
                onError(th);
            }
        }
    }
}
