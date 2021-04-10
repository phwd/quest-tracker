package defpackage;

import java.util.Objects;

/* renamed from: IA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IA0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TA0 f8204a;

    public IA0(TA0 ta0) {
        this.f8204a = ta0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0974Pz0 pz0 = (C0974Pz0) obj;
        TA0 ta0 = this.f8204a;
        ta0.l0 = false;
        ta0.m(pz0.f8727a);
        if (((AB0) this.f8204a.I).q()) {
            this.f8204a.o(1, pz0.b);
            this.f8204a.o(2, pz0.c);
        }
        if (((AB0) this.f8204a.I).p()) {
            this.f8204a.o(3, pz0.d);
        }
        this.f8204a.o(4, pz0.e);
        C5084uR0 ur0 = this.f8204a.q0;
        if (ur0 == null || ur0.d() != null) {
            this.f8204a.h(null);
        } else {
            TA0 ta02 = this.f8204a;
            ta02.h(ta02.c0);
        }
        this.f8204a.n();
        Objects.requireNonNull(this.f8204a);
    }
}
