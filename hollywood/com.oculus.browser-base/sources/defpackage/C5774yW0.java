package defpackage;

import java.util.Objects;

/* renamed from: yW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5774yW0 implements AbstractC5507ww {

    /* renamed from: a  reason: collision with root package name */
    public final AW0 f11686a;

    public C5774yW0(AW0 aw0) {
        this.f11686a = aw0;
    }

    @Override // defpackage.AbstractC5507ww
    public void a(C5677xw xwVar) {
        AW0 aw0 = this.f11686a;
        Objects.requireNonNull(aw0);
        float a2 = xwVar.a();
        if (aw0.Z != null) {
            float min = Math.min(aw0.F, aw0.G) * 0.7f;
            float g = AbstractC2882hZ0.g(a2, min, true);
            float t = aw0.Z.t();
            float f = 1.0f - g;
            aw0.Z.k(J70.l, t * f);
            aw0.Z.k(J70.m, (aw0.Z.s() / 2.0f) * f);
            aw0.Z.k(J70.g, g);
            aw0.Z.k(J70.x, g);
            aw0.Z.k(J70.t, AbstractC2882hZ0.f(a2, min));
        }
    }
}
