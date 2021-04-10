package defpackage;

import org.chromium.chrome.browser.toolbar.LocationBarModel;

/* renamed from: vk1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5303vk1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f11494a;

    public C5303vk1(Uk1 uk1) {
        this.f11494a = uk1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Uk1 uk1 = this.f11494a;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        uk1.j0 = g70;
        ((D70) g70).Q.b(uk1.k0);
        M9 m9 = uk1.I;
        AbstractC2642g70 g702 = uk1.j0;
        m9.O = g702;
        ((D70) g702).Q.b(m9.P);
        LocationBarModel locationBarModel = uk1.Z;
        AbstractC2642g70 g703 = uk1.j0;
        locationBarModel.h = g703;
        Object obj2 = uk1.S.H;
        if (obj2 != null) {
            C2910hj hjVar = ((C2568fj) obj2).f9944a;
            hjVar.P = g703;
            ((D70) g703).Q.b(hjVar);
        }
    }
}
