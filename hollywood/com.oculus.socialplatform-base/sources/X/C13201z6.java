package X;

import java.util.Arrays;

/* renamed from: X.1z6  reason: invalid class name and case insensitive filesystem */
public final class C13201z6 implements AbstractC12271xB, AbstractC12941yc {
    public AbstractC12271xB A00;
    public final AbstractC12941yc A01;
    public final /* synthetic */ AnonymousClass1z7 A02;

    public C13201z6(AnonymousClass1z7 r1, AbstractC12941yc r2) {
        this.A02 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC12941yc
    public final void A8A(AbstractC12271xB r3) {
        try {
            this.A02.A04.accept(r3);
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
        try {
            this.A02.A01.run();
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1y3.A01(th);
        }
        this.A00.dispose();
    }

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        if (this.A00 != EnumC12511xi.DISPOSED) {
            try {
                AnonymousClass1z7 r1 = this.A02;
                r1.A00.run();
                r1.A02.run();
                this.A01.onComplete();
                try {
                    r1.A06.run();
                } catch (Throwable th) {
                    C12261xA.A00(th);
                    AnonymousClass1y3.A01(th);
                }
            } catch (Throwable th2) {
                C12261xA.A00(th2);
                this.A01.onError(th2);
            }
        }
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        if (this.A00 == EnumC12511xi.DISPOSED) {
            AnonymousClass1y3.A01(th);
            return;
        }
        try {
            AnonymousClass1z7 r1 = this.A02;
            r1.A03.accept(th);
            r1.A02.run();
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            th = new AnonymousClass1Ox(Arrays.asList(th, th2));
        }
        this.A01.onError(th);
        try {
            this.A02.A06.run();
        } catch (Throwable th3) {
            C12261xA.A00(th3);
            AnonymousClass1y3.A01(th3);
        }
    }
}
