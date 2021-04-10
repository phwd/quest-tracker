package defpackage;

/* renamed from: QZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class QZ {
    public static MZ a(int i, PZ pz, PF pf, int i2) {
        if (i == 0) {
            return new C0767Mn0(pz);
        }
        if (i == 1) {
            return new C3941nl(pz, new C3770ml());
        }
        if (i == 2) {
            return new C1938c00(a(0, pz, pf, i2), pf, i2);
        }
        if (i != 3) {
            return null;
        }
        return new C1938c00(a(1, pz, pf, i2), pf, i2);
    }
}
