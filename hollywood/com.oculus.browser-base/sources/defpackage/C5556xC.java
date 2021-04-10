package defpackage;

/* renamed from: xC  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5556xC extends XA {
    @Override // defpackage.XA
    public void a(C5088uT0 ut0, float f, float f2, float f3) {
        ut0.d(0.0f, f3 * f2, 180.0f, 180.0f - f);
        double d = (double) f3;
        double d2 = (double) f2;
        ut0.c((float) (Math.sin(Math.toRadians((double) f)) * d * d2), (float) (Math.sin(Math.toRadians((double) (90.0f - f))) * d * d2));
    }
}
