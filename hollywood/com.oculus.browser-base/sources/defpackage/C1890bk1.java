package defpackage;

import com.google.android.material.appbar.AppBarLayout;

/* renamed from: bk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1890bk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f9560a;

    public C1890bk1(Uk1 uk1) {
        this.f9560a = uk1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AppBarLayout appBarLayout;
        Uk1 uk1 = this.f9560a;
        AbstractC2451f01 f01 = (AbstractC2451f01) obj;
        uk1.S0 = f01;
        C4963tk1 tk1 = new C4963tk1(uk1);
        uk1.T0 = tk1;
        ((C3818n01) f01).c.L.b(tk1);
        C5133uk1 uk12 = new C5133uk1(uk1);
        uk1.U0 = uk12;
        AbstractC4096of1 of1 = ((C3818n01) uk1.S0).g;
        if (of1 != null && (appBarLayout = ((C4778sf1) of1).b.i0) != null) {
            appBarLayout.a(uk12);
        }
    }
}
