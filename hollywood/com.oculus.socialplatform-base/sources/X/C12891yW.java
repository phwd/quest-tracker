package X;

import java.util.Arrays;

/* renamed from: X.1yW  reason: invalid class name and case insensitive filesystem */
public final class C12891yW<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public boolean A01;
    public final AnonymousClass1yM<? super T> A02;
    public final AbstractC12881yV A03;
    public final AbstractC12881yV A04;
    public final AbstractC12851yS<? super Throwable> A05;
    public final AbstractC12851yS<? super T> A06;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A02.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A01) {
            try {
                this.A04.run();
                this.A01 = true;
                this.A02.onComplete();
                try {
                    this.A03.run();
                } catch (Throwable th) {
                    C12261xA.A00(th);
                    AnonymousClass1y3.A01(th);
                }
            } catch (Throwable th2) {
                C12261xA.A00(th2);
                onError(th2);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.A01) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A01 = true;
        try {
            this.A05.accept(th);
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            th = new AnonymousClass1Ox(Arrays.asList(th, th2));
        }
        this.A02.onError(th);
        try {
            this.A03.run();
        } catch (Throwable th3) {
            C12261xA.A00(th3);
            AnonymousClass1y3.A01(th3);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.A01) {
            try {
                this.A06.accept(t);
                this.A02.onNext(t);
            } catch (Throwable th) {
                C12261xA.A00(th);
                this.A00.dispose();
                onError(th);
            }
        }
    }

    public C12891yW(AnonymousClass1yM<? super T> r1, AbstractC12851yS<? super T> r2, AbstractC12851yS<? super Throwable> r3, AbstractC12881yV r4, AbstractC12881yV r5) {
        this.A02 = r1;
        this.A06 = r2;
        this.A05 = r3;
        this.A04 = r4;
        this.A03 = r5;
    }
}
