package defpackage;

/* renamed from: JA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JA0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TA0 f8276a;

    public JA0(TA0 ta0) {
        this.f8276a = ta0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0974Pz0 pz0 = (C0974Pz0) obj;
        this.f8276a.m(pz0.f8727a);
        if (((AB0) this.f8276a.I).q()) {
            this.f8276a.o(1, pz0.b);
            this.f8276a.o(2, pz0.c);
        }
        if (((AB0) this.f8276a.I).p()) {
            this.f8276a.o(3, pz0.d);
        }
        TA0 ta0 = this.f8276a;
        C5721yA0 ya0 = ta0.f0;
        C5084uR0 ur0 = pz0.e;
        ya0.j0 = ur0.d;
        ta0.o(4, ur0);
        this.f8276a.n();
        this.f8276a.f(false);
        this.f8276a.P.addOnLayoutChangeListener(new SA0(this.f8276a, false));
    }
}
