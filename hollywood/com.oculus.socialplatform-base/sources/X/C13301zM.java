package X;

/* renamed from: X.1zM  reason: invalid class name and case insensitive filesystem */
public final class C13301zM extends AbstractC13311zN<Void> implements AbstractC12941yc {
    public AbstractC12271xB A00;
    public final AnonymousClass1yM<?> A01;

    @Override // X.AbstractC13481zf
    public final void clear() {
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return true;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        return i & 2;
    }

    @Override // X.AbstractC12941yc
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A01.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        this.A01.onComplete();
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        this.A01.onError(th);
    }

    @Override // X.AbstractC13481zf
    public final Object poll() throws Exception {
        return null;
    }

    public C13301zM(AnonymousClass1yM<?> r1) {
        this.A01 = r1;
    }
}
