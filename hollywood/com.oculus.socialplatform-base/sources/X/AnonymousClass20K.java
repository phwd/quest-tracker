package X;

import io.reactivex.annotations.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.20K  reason: invalid class name */
public final class AnonymousClass20K<T> extends AtomicInteger implements AnonymousClass12n<T>, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableScalarXMap$ScalarDisposable";
    public static final long serialVersionUID = 3880992722410194083L;
    public final AnonymousClass1yM<? super T> observer;
    public final T value;

    @Override // X.AbstractC13481zf
    public final void clear() {
        lazySet(3);
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        set(3);
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        lazySet(1);
        return 1;
    }

    @Override // X.AbstractC13481zf
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public AnonymousClass20K(AnonymousClass1yM<? super T> r1, T t) {
        this.observer = r1;
        this.value = t;
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (get() == 1) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        if (get() != 1) {
            return null;
        }
        lazySet(3);
        return this.value;
    }

    public final void run() {
        if (get() == 0 && compareAndSet(0, 2)) {
            this.observer.onNext(this.value);
            if (get() == 2) {
                lazySet(3);
                this.observer.onComplete();
            }
        }
    }
}
