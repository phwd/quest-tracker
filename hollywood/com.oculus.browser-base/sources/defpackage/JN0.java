package defpackage;

/* renamed from: JN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JN0 extends XA {
    @Override // defpackage.XA
    public void a(C5088uT0 ut0, float f, float f2, float f3) {
        ut0.d(0.0f, f3 * f2, 180.0f, 180.0f - f);
        float f4 = f3 * 2.0f * f2;
        C4408qT0 qt0 = new C4408qT0(0.0f, 0.0f, f4, f4);
        qt0.g = 180.0f;
        qt0.h = f;
        ut0.g.add(qt0);
        C4066oT0 ot0 = new C4066oT0(qt0);
        float f5 = f + 180.0f;
        boolean z = f < 0.0f;
        float f6 = z ? 0.0f : 180.0f;
        float f7 = z ? (180.0f + f5) % 360.0f : f5;
        ut0.a(f6);
        ut0.h.add(ot0);
        ut0.e = f7;
        float f8 = (f4 + 0.0f) * 0.5f;
        float f9 = (f4 - 0.0f) / 2.0f;
        double d = (double) f5;
        ut0.c = (((float) Math.cos(Math.toRadians(d))) * f9) + f8;
        ut0.d = (f9 * ((float) Math.sin(Math.toRadians(d)))) + f8;
    }
}
