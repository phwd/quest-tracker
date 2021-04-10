package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.20O  reason: invalid class name */
public class AnonymousClass20O<T> extends AbstractC13361zS<T> {
    public static final long serialVersionUID = -5502432239815349361L;
    public final AnonymousClass1yM<? super T> downstream;
    public T value;

    @Override // X.AbstractC12271xB
    public void dispose() {
        set(4);
        this.value = null;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    public AnonymousClass20O(AnonymousClass1yM<? super T> r1) {
        this.downstream = r1;
    }

    public final void A00(T t) {
        int i = get();
        if ((i & 54) == 0) {
            AnonymousClass1yM<? super T> r2 = this.downstream;
            if (i == 8) {
                this.value = t;
                lazySet(16);
                t = null;
            } else {
                lazySet(2);
            }
            r2.onNext(t);
            if (get() != 4) {
                r2.onComplete();
            }
        }
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (get() != 16) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }
}
