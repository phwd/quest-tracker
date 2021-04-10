package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.20k  reason: invalid class name and case insensitive filesystem */
public final class C137820k<T> extends AbstractC137320f<T, T> {
    public final AbstractC13191z2<? super T> A00;

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.A01 == 0) {
            try {
                if (this.A00.test(t)) {
                    this.A04.onNext(t);
                }
            } catch (Throwable th) {
                C12261xA.A00(th);
                super.A00.dispose();
                onError(th);
            }
        } else {
            this.A04.onNext(null);
        }
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        T poll;
        do {
            poll = this.A02.poll();
            if (poll == null) {
                break;
            }
        } while (!this.A00.test(poll));
        return poll;
    }

    public C137820k(AnonymousClass1yM<? super T> r1, AbstractC13191z2<? super T> r2) {
        super(r1);
        this.A00 = r2;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        return A00(i);
    }
}
