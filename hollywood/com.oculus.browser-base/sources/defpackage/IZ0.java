package defpackage;

/* renamed from: IZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IZ0 {

    /* renamed from: a  reason: collision with root package name */
    public static float f8234a;
    public static final AbstractC3719mR b = new BZ0("DISCARD_AMOUNT");
    public static final AbstractC3719mR c = new CZ0("SCALE");
    public static final AbstractC3719mR d = new DZ0("SCROLL_OFFSET");
    public static final AbstractC3719mR e = new EZ0("X_IN_STACK_INFLUENCE");
    public static final AbstractC3719mR f = new FZ0("X_IN_STACK_OFFSET");
    public static final AbstractC3719mR g = new GZ0("Y_IN_STACK_INFLUENCE");
    public static final AbstractC3719mR h = new HZ0("Y_IN_STACK_OFFSET");
    public long A;
    public int B;
    public J70 C;
    public float i = 1.0f;
    public float j = 1.0f;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p = 1.0f;
    public float q = 1.0f;
    public float r;
    public float s;
    public float t;
    public boolean u;
    public int v;
    public boolean w;
    public float x;
    public float y;
    public float z = 1.0f;

    public IZ0(J70 j70) {
        this.C = j70;
    }

    public int a() {
        return this.C.q();
    }

    public float b(int i2) {
        if (i2 == 1) {
            return this.C.v();
        }
        return this.C.w();
    }

    public void c() {
        this.i = 1.0f;
        this.j = 1.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.s = 0.0f;
        this.t = 0.0f;
        this.u = false;
    }
}
