package X;

/* renamed from: X.1xj  reason: invalid class name */
public class AnonymousClass1xj implements AbstractC11131xk {
    public final /* synthetic */ C11191xr A00;
    public final /* synthetic */ AnonymousClass1xh A01;
    public final /* synthetic */ AbstractC11151xn A02;
    public final /* synthetic */ C11211xt A03;

    public AnonymousClass1xj(C11211xt r1, AbstractC11151xn r2, C11191xr r3, AnonymousClass1xh r4) {
        this.A03 = r1;
        this.A02 = r2;
        this.A00 = r3;
        this.A01 = r4;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11211xt r1 = this.A03;
        r1.A06 = this.A01;
        r1.A03(new C11081xd(th));
        this.A02.A6B();
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        this.A03.A06(this.A02, this.A00, this.A01);
    }
}
