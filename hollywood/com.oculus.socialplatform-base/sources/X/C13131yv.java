package X;

import java.util.Arrays;

/* renamed from: X.1yv  reason: invalid class name and case insensitive filesystem */
public final class C13131yv<T> implements AbstractC12501xh<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public final AbstractC12501xh<? super T> A01;
    public final C13141yw<T> A02;

    private final void A00() {
        try {
            this.A02.A00.run();
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1y3.A01(th);
        }
    }

    private final void A01(Throwable th) {
        try {
            this.A02.A03.accept(th);
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            th = new AnonymousClass1Ox(Arrays.asList(th, th2));
        }
        this.A00 = EnumC12511xi.DISPOSED;
        this.A01.onError(th);
        A00();
    }

    @Override // X.AbstractC12501xh
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.validate(this.A00, r3)) {
            try {
                this.A02.A04.accept(r3);
                this.A00 = r3;
                this.A01.A8A(this);
            } catch (Throwable th) {
                C12261xA.A00(th);
                r3.dispose();
                this.A00 = EnumC12511xi.DISPOSED;
                AnonymousClass1z1.error(th, this.A01);
            }
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        try {
            this.A02.A02.run();
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1y3.A01(th);
        }
        this.A00.dispose();
        this.A00 = EnumC12511xi.DISPOSED;
    }

    @Override // X.AbstractC12501xh
    public final void onComplete() {
        AbstractC12271xB r0 = this.A00;
        EnumC12511xi r1 = EnumC12511xi.DISPOSED;
        if (r0 != r1) {
            try {
                this.A02.A01.run();
                this.A00 = r1;
                this.A01.onComplete();
                A00();
            } catch (Throwable th) {
                C12261xA.A00(th);
                A01(th);
            }
        }
    }

    @Override // X.AbstractC12501xh
    public final void onError(Throwable th) {
        if (this.A00 == EnumC12511xi.DISPOSED) {
            AnonymousClass1y3.A01(th);
        } else {
            A01(th);
        }
    }

    @Override // X.AbstractC12501xh
    public final void onSuccess(T t) {
        AbstractC12271xB r0 = this.A00;
        EnumC12511xi r1 = EnumC12511xi.DISPOSED;
        if (r0 != r1) {
            try {
                this.A02.A05.accept(t);
                this.A00 = r1;
                this.A01.onSuccess(t);
                A00();
            } catch (Throwable th) {
                C12261xA.A00(th);
                A01(th);
            }
        }
    }

    public C13131yv(AbstractC12501xh<? super T> r1, C13141yw<T> r2) {
        this.A01 = r1;
        this.A02 = r2;
    }
}
