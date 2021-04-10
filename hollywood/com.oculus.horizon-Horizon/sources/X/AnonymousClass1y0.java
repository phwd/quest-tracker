package X;

/* renamed from: X.1y0  reason: invalid class name */
public class AnonymousClass1y0 implements AbstractC11151xn {
    public final /* synthetic */ AbstractC11151xn A00;
    public final /* synthetic */ C11211xt A01;

    public AnonymousClass1y0(C11211xt r1, AbstractC11151xn r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11151xn
    public final void A6B() {
        C11211xt r2 = this.A01;
        r2.A01 = AnonymousClass1y5.STOPPED;
        for (AbstractC11261xz r0 : r2.A04.values()) {
            r0.release();
        }
        r2.A06 = null;
        AbstractC11151xn r02 = this.A00;
        if (r02 != null) {
            r02.A6B();
        }
    }
}
