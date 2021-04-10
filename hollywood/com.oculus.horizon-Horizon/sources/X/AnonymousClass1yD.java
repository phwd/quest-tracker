package X;

/* renamed from: X.1yD  reason: invalid class name */
public class AnonymousClass1yD implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ C11211xt A01;

    public AnonymousClass1yD(C11211xt r1, AbstractC11131xk r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        this.A01.A04(new C11271yH(this, th));
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        this.A01.A01 = AnonymousClass1y5.PREPARED;
        this.A00.onSuccess();
    }
}
