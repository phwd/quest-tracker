package defpackage;

import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: sN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4736sN0 implements AbstractC3467ky {

    /* renamed from: a  reason: collision with root package name */
    public final Uk1 f11269a;

    public C4736sN0(Uk1 uk1) {
        this.f11269a = uk1;
    }

    @Override // defpackage.AbstractC3467ky
    public void a(Object obj) {
        Uk1 uk1 = this.f11269a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        AbstractC1772b2 b2Var = uk1.o0.f10460a;
        if (!booleanValue && b2Var != null) {
            Ty1 ty1 = (Ty1) b2Var;
            if (!ty1.s) {
                ty1.s = true;
                ty1.g(false);
            }
        }
        if (!DeviceFormFactor.a(uk1.s0)) {
            return;
        }
        if (booleanValue) {
            uk1.p0.c();
        } else {
            uk1.p0.b();
        }
    }
}
