package defpackage;

/* renamed from: Bs1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Bs1 extends As1 {

    /* renamed from: a  reason: collision with root package name */
    public C0485Hy0[] f7766a = null;
    public String b;
    public int c = 0;
    public int d;

    public Bs1() {
        super(null);
    }

    public C0485Hy0[] getPathData() {
        return this.f7766a;
    }

    public String getPathName() {
        return this.b;
    }

    public void setPathData(C0485Hy0[] hy0Arr) {
        if (!AbstractC0546Iy0.a(this.f7766a, hy0Arr)) {
            this.f7766a = AbstractC0546Iy0.d(hy0Arr);
            return;
        }
        C0485Hy0[] hy0Arr2 = this.f7766a;
        for (int i = 0; i < hy0Arr.length; i++) {
            hy0Arr2[i].f8192a = hy0Arr[i].f8192a;
            for (int i2 = 0; i2 < hy0Arr[i].b.length; i2++) {
                hy0Arr2[i].b[i2] = hy0Arr[i].b[i2];
            }
        }
    }

    public Bs1(Bs1 bs1) {
        super(null);
        this.b = bs1.b;
        this.d = bs1.d;
        this.f7766a = AbstractC0546Iy0.d(bs1.f7766a);
    }
}
