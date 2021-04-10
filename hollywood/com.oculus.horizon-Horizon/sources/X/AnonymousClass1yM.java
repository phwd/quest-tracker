package X;

/* renamed from: X.1yM  reason: invalid class name */
public class AnonymousClass1yM implements AbstractC11131xk {
    public final /* synthetic */ AnonymousClass1yN A00;

    public AnonymousClass1yM(AnonymousClass1yN r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        AnonymousClass1yL r2 = this.A00.A00;
        AnonymousClass1z6.A01(r2.A01, r2.A00, th);
        AbstractC11151xn r0 = r2.A02;
        if (r0 != null) {
            r0.A6B();
        }
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        AnonymousClass1yL r2 = this.A00.A00;
        AnonymousClass1z6.A00(r2.A01, r2.A00);
        AbstractC11151xn r0 = r2.A02;
        if (r0 != null) {
            r0.A6B();
        }
    }
}
