package defpackage;

import java.util.List;

/* renamed from: k41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3317k41 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4172p41 f10259a;
    public final boolean b;
    public final List c;
    public final C5232vH0 d;

    public C3317k41(C4172p41 p41, boolean z, List list, C5232vH0 vh0) {
        this.f10259a = p41;
        this.b = z;
        this.c = list;
        this.d = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4172p41 p41 = this.f10259a;
        boolean z = this.b;
        List list = this.c;
        C5232vH0 vh0 = this.d;
        Void r6 = (Void) obj;
        C5232vH0 vh02 = p41.c;
        C3659m41 m41 = new C3659m41(p41, z, list, vh0);
        C3830n41 n41 = new C3830n41();
        vh02.h(m41);
        vh02.a(n41);
    }
}
