package defpackage;

/* renamed from: TL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TL0 implements SL0 {

    /* renamed from: a  reason: collision with root package name */
    public float f8952a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g = 1.0f;
    public float h = 1.0f;
    public float i = 1.0f;
    public float j = 1.0f;
    public float k;

    public float a(float f2) {
        return f2 * this.g * this.j;
    }

    public int b() {
        return (int) Math.ceil((double) a(this.f));
    }

    public float c() {
        return a(this.f8952a);
    }

    public float d() {
        return a(this.b);
    }

    public int e() {
        return (int) Math.floor((double) d());
    }
}
