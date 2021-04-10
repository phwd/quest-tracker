package defpackage;

/* renamed from: cE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1981cE1 extends MD1 {
    public static final MD1 H = new C1981cE1(new Object[0], 0);
    public final transient Object[] I;

    /* renamed from: J  reason: collision with root package name */
    public final transient int f9593J;

    public C1981cE1(Object[] objArr, int i) {
        this.I = objArr;
        this.f9593J = i;
    }

    @Override // defpackage.MD1, defpackage.PD1
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.I, 0, objArr, 0, this.f9593J);
        return this.f9593J + 0;
    }

    @Override // defpackage.PD1
    public final Object[] b() {
        return this.I;
    }

    @Override // defpackage.PD1
    public final int c() {
        return 0;
    }

    @Override // defpackage.PD1
    public final int e() {
        return this.f9593J;
    }

    @Override // java.util.List
    public final Object get(int i) {
        DD1.a(i, this.f9593J);
        return this.I[i];
    }

    public final int size() {
        return this.f9593J;
    }
}
