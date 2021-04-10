package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.20h  reason: invalid class name and case insensitive filesystem */
public final class C137520h<T, U> extends AbstractC137320f<T, U> {
    public final AbstractC13031yl<? super T, ? extends U> A00;

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.A03) {
            return;
        }
        if (this.A01 != 0) {
            this.A04.onNext(null);
            return;
        }
        try {
            Object apply = this.A00.apply(t);
            AnonymousClass219.A01(apply, "The mapper function returned a null value.");
            this.A04.onNext(apply);
        } catch (Throwable th) {
            C12261xA.A00(th);
            super.A00.dispose();
            onError(th);
        }
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final U poll() throws Exception {
        T poll = this.A02.poll();
        if (poll == null) {
            return null;
        }
        U u = (U) this.A00.apply(poll);
        AnonymousClass219.A01(u, "The mapper function returned a null value.");
        return u;
    }

    public C137520h(AnonymousClass1yM<? super U> r1, AbstractC13031yl<? super T, ? extends U> r2) {
        super(r1);
        this.A00 = r2;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        return A00(i);
    }
}
