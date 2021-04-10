package defpackage;

/* renamed from: vB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5214vB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5960zd f11464a;
    public final /* synthetic */ AB0 b;

    public C5214vB0(AB0 ab0, C5960zd zdVar) {
        this.b = ab0;
        this.f11464a = zdVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC1797bA0 ba0;
        C5960zd zdVar = (C5960zd) obj;
        AB0 ab0 = this.b;
        if (ab0.w != null) {
            if (zdVar != null) {
                ab0.i.j = null;
                if (!zdVar.f9599a) {
                    ab0.D.c = -1;
                } else if (this.f11464a == null) {
                    C3980ny nyVar = ab0.D;
                    nyVar.f11410a.add(0, zdVar);
                    nyVar.c = 0;
                } else {
                    AbstractC5724yB0 yb0 = ab0.m;
                    C0911Oy0 oy0 = new C0911Oy0();
                    oy0.e = zdVar.q;
                    oy0.f = zdVar.r;
                    oy0.d = zdVar.s;
                    C0289Es es = (C0289Es) yb0;
                    EA0 ea0 = es.f7982a;
                    if (!(ea0 == null || !es.g || (ba0 = ea0.A) == null)) {
                        ((C4018oA0) ba0).k0(oy0);
                    }
                }
            }
            AB0 ab02 = this.b;
            ab02.w.o(3, ab02.D);
            if (!this.b.e.isEmpty()) {
                AB0 ab03 = this.b;
                ab03.d.post((Runnable) ab03.e.remove());
            }
        }
    }
}
