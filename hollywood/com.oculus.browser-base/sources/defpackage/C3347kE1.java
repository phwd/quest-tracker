package defpackage;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: kE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3347kE1 extends AbstractC1630aE1 {
    public static final C3347kE1 I = new C3347kE1(new Object[0], 0, null, 0, 0);

    /* renamed from: J  reason: collision with root package name */
    public final transient Object[] f10266J;
    public final transient Object[] K;
    public final transient int L;
    public final transient int M;
    public final transient int N;

    public C3347kE1(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.f10266J = objArr;
        this.K = objArr2;
        this.L = i2;
        this.M = i;
        this.N = i3;
    }

    @Override // defpackage.PD1
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.f10266J, 0, objArr, 0, this.N);
        return this.N + 0;
    }

    @Override // defpackage.PD1
    public final Object[] b() {
        return this.f10266J;
    }

    @Override // defpackage.PD1
    public final int c() {
        return 0;
    }

    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.K;
        if (obj == null || objArr == null) {
            return false;
        }
        int a2 = GD1.a(obj.hashCode());
        while (true) {
            int i = a2 & this.L;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            a2 = i + 1;
        }
    }

    @Override // defpackage.PD1
    public final int e() {
        return this.N;
    }

    @Override // defpackage.AbstractC1630aE1
    public final boolean h() {
        return true;
    }

    @Override // defpackage.AbstractC1630aE1
    public final int hashCode() {
        return this.M;
    }

    @Override // defpackage.AbstractC1630aE1
    public final MD1 l() {
        Object[] objArr = this.f10266J;
        int i = this.N;
        KD1 kd1 = MD1.G;
        if (i == 0) {
            return C1981cE1.H;
        }
        return new C1981cE1(objArr, i);
    }

    /* renamed from: m */
    public final AE1 iterator() {
        MD1 md1 = this.H;
        if (md1 == null) {
            md1 = l();
            this.H = md1;
        }
        return (AE1) md1.iterator();
    }

    public final int size() {
        return this.N;
    }
}
