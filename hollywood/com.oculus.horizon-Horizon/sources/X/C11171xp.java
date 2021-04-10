package X;

/* renamed from: X.1xp  reason: invalid class name and case insensitive filesystem */
public class C11171xp implements AnonymousClass1zF {
    public final /* synthetic */ AbstractC11151xn A00;
    public final /* synthetic */ C11211xt A01;

    public C11171xp(C11211xt r1, AbstractC11151xn r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r3) {
        C11211xt r1;
        if (r3 instanceof C11081xd) {
            r1 = this.A01;
            r1.A03(r3);
        } else {
            r1 = this.A01;
            r1.A03(new C11081xd(r3));
        }
        r1.A04(this.A00);
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        C11211xt r3 = this.A01;
        r3.A02.A5O(19, "recording_stop_finished");
        AnonymousClass1xh r2 = r3.A06;
        if (r2 != null) {
            r3.A06 = null;
            r3.A05.post(new RunnableC11141xm(r3, r2));
        }
        for (AbstractC11261xz r1 : r3.A04.values()) {
            r1.A8r(null);
        }
        r3.A04(this.A00);
    }
}
