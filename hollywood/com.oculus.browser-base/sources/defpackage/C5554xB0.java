package defpackage;

/* renamed from: xB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5554xB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1870be f11598a;
    public final /* synthetic */ AB0 b;

    public C5554xB0(AB0 ab0, C1870be beVar) {
        this.b = ab0;
        this.f11598a = beVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1870be beVar = (C1870be) obj;
        AB0 ab0 = this.b;
        if (ab0.w != null) {
            if (beVar != null) {
                if (!beVar.f9599a) {
                    ab0.B.c = -1;
                } else if (this.f11598a == null) {
                    C5084uR0 ur0 = ab0.B;
                    ur0.f11410a.add(0, beVar);
                    ur0.c = 0;
                }
            }
            this.b.s();
            AB0 ab02 = this.b;
            ab02.w.o(4, ab02.B);
        }
    }
}
