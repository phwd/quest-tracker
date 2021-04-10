package X;

/* renamed from: X.1xq  reason: invalid class name and case insensitive filesystem */
public class C11181xq implements AnonymousClass1zF {
    public final /* synthetic */ AbstractC11151xn A00;
    public final /* synthetic */ C11211xt A01;

    public C11181xq(C11211xt r1, AbstractC11151xn r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r3) {
        C11211xt r1 = this.A01;
        r1.A03(r3);
        r1.A04(this.A00);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        C11211xt r3 = this.A01;
        r3.A03.A02(new C11171xp(r3, this.A00));
    }
}
