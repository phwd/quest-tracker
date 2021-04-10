package defpackage;

/* renamed from: kc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3401kc {

    /* renamed from: a  reason: collision with root package name */
    public int f10289a;
    public int b;
    public float c;
    public float d;
    public long e = Long.MIN_VALUE;
    public long f = 0;
    public int g = 0;
    public int h = 0;
    public long i = -1;
    public float j;
    public int k;

    public final float a(long j2) {
        long j3 = this.e;
        if (j2 < j3) {
            return 0.0f;
        }
        long j4 = this.i;
        if (j4 < 0 || j2 < j4) {
            return View$OnTouchListenerC5038u90.b(((float) (j2 - j3)) / ((float) this.f10289a), 0.0f, 1.0f) * 0.5f;
        }
        float f2 = this.j;
        return (View$OnTouchListenerC5038u90.b(((float) (j2 - j4)) / ((float) this.k), 0.0f, 1.0f) * f2) + (1.0f - f2);
    }
}
