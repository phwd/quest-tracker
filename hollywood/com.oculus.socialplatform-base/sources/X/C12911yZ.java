package X;

import java.util.NoSuchElementException;

/* renamed from: X.1yZ  reason: invalid class name and case insensitive filesystem */
public final class C12911yZ<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public long A00;
    public AbstractC12271xB A01;
    public boolean A02;
    public final AnonymousClass1yM<? super T> A03;
    public final boolean A04;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A01, r2)) {
            this.A01 = r2;
            this.A03.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A01.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A02) {
            this.A02 = true;
            boolean z = this.A04;
            AnonymousClass1yM<? super T> r1 = this.A03;
            if (z) {
                r1.onError(new NoSuchElementException());
            } else {
                r1.onComplete();
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
        if (!this.A02) {
            long j = this.A00;
            if (j == 0) {
                this.A02 = true;
                this.A01.dispose();
                AnonymousClass1yM<? super T> r0 = this.A03;
                r0.onNext(t);
                r0.onComplete();
                return;
            }
            this.A00 = j + 1;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TT;>;JTT;Z)V */
    public C12911yZ(AnonymousClass1yM r1, boolean z) {
        this.A03 = r1;
        this.A04 = z;
    }
}
