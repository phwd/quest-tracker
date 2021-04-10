package X;

/* renamed from: X.1xs  reason: invalid class name and case insensitive filesystem */
public class C11201xs implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11261xz A00;
    public final /* synthetic */ AbstractC11151xn A01;
    public final /* synthetic */ C11211xt A02;

    public C11201xs(C11211xt r1, AbstractC11261xz r2, AbstractC11151xn r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        C11211xt r1 = this.A02;
        r1.A03(new C11081xd(th));
        r1.A04(this.A01);
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
    }
}
