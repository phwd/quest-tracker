package X;

/* renamed from: X.1yX  reason: invalid class name */
public final class AnonymousClass1yX<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public final AnonymousClass1yM<? super T> A01;
    public final AbstractC12881yV A02;
    public final AbstractC12851yS<? super AbstractC12271xB> A03;

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        try {
            this.A03.accept(r3);
            if (EnumC12511xi.validate(this.A00, r3)) {
                this.A00 = r3;
                this.A01.A8A(this);
            }
        } catch (Throwable th) {
            C12261xA.A00(th);
            r3.dispose();
            this.A00 = EnumC12511xi.DISPOSED;
            AnonymousClass1z1.error(th, this.A01);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        AbstractC12271xB r1 = this.A00;
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (r1 != r0) {
            this.A00 = r0;
            try {
                this.A02.run();
            } catch (Throwable th) {
                C12261xA.A00(th);
                AnonymousClass1y3.A01(th);
            }
            r1.dispose();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        AbstractC12271xB r1 = this.A00;
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (r1 != r0) {
            this.A00 = r0;
            this.A01.onComplete();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        AbstractC12271xB r1 = this.A00;
        EnumC12511xi r0 = EnumC12511xi.DISPOSED;
        if (r1 != r0) {
            this.A00 = r0;
            this.A01.onError(th);
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        this.A01.onNext(t);
    }

    public AnonymousClass1yX(AnonymousClass1yM<? super T> r1, AbstractC12851yS<? super AbstractC12271xB> r2, AbstractC12881yV r3) {
        this.A01 = r1;
        this.A03 = r2;
        this.A02 = r3;
    }
}
