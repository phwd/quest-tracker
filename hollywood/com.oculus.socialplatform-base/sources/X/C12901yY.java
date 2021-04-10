package X;

import java.util.NoSuchElementException;

/* renamed from: X.1yY  reason: invalid class name and case insensitive filesystem */
public final class C12901yY<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public T A01;
    public boolean A02;
    public final AbstractC12721yD<? super T> A03;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A03.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A02) {
            this.A02 = true;
            T t = this.A01;
            this.A01 = null;
            if (t == null) {
                this.A03.onError(new NoSuchElementException());
            } else {
                this.A03.onSuccess(t);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.A02) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A02 = true;
        this.A03.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.A02) {
            return;
        }
        if (this.A01 != null) {
            this.A02 = true;
            this.A00.dispose();
            this.A03.onError(new IllegalArgumentException("Sequence contains more than one element!"));
            return;
        }
        this.A01 = t;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1yD<-TT;>;TT;)V */
    public C12901yY(AbstractC12721yD r1) {
        this.A03 = r1;
    }
}
