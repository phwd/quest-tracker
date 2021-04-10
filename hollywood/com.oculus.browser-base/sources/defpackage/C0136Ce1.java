package defpackage;

/* renamed from: Ce1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0136Ce1 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public static final double f7825a = Math.pow(75.0d, 2.0d);
    public final C5210vA b;
    public final float c;
    public final boolean d;

    public C0136Ce1(C4700sA sAVar, C5210vA vAVar, int i, int i2, boolean z) {
        float f = sAVar.c;
        this.c = f;
        this.b = vAVar;
        boolean z2 = true;
        if (vAVar != null && !z) {
            float f2 = (vAVar.f11463a - ((float) i)) * f;
            float f3 = (vAVar.b - ((float) i2)) * f;
            if (((double) ((f3 * f3) + (f2 * f2))) > f7825a) {
                z2 = false;
            }
        }
        this.d = z2;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return !this.d;
    }
}
