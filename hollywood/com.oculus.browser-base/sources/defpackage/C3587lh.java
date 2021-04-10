package defpackage;

/* renamed from: lh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3587lh extends AbstractC2032cb {
    public final /* synthetic */ C3245jh i;
    public final /* synthetic */ C3929nh j;

    public C3587lh(C3929nh nhVar, C3245jh jhVar) {
        this.j = nhVar;
        this.i = jhVar;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        C3929nh nhVar = this.j;
        C3245jh jhVar = this.i;
        double a2 = ((double) nhVar.d.a(4)) / 100.0d;
        double a3 = (double) nhVar.d.a(1);
        double a4 = (double) nhVar.d.a(3);
        if (jhVar.d) {
            if (jhVar.e == Double.POSITIVE_INFINITY && a4 > 0.0d) {
                jhVar.e = Math.ceil((1.0d - a2) * (a3 / a4) * 3600.0d);
            }
        } else if (a4 < 0.0d) {
            jhVar.f = Math.floor((a3 / (-a4)) * a2 * 3600.0d);
        }
        return this.i;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.f10508a.a((C3245jh) obj);
    }
}
