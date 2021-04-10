package defpackage;

import java.util.Objects;

/* renamed from: BE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BE1 extends AbstractC1630aE1 {
    public final transient Object I;

    /* renamed from: J  reason: collision with root package name */
    public transient int f7725J;

    public BE1(Object obj) {
        Objects.requireNonNull(obj);
        this.I = obj;
    }

    @Override // defpackage.PD1
    public final int a(Object[] objArr, int i) {
        objArr[0] = this.I;
        return 1;
    }

    public final boolean contains(Object obj) {
        return this.I.equals(obj);
    }

    @Override // defpackage.AbstractC1630aE1
    public final boolean h() {
        return this.f7725J != 0;
    }

    @Override // defpackage.AbstractC1630aE1
    public final int hashCode() {
        int i = this.f7725J;
        if (i != 0) {
            return i;
        }
        int hashCode = this.I.hashCode();
        this.f7725J = hashCode;
        return hashCode;
    }

    @Override // defpackage.AbstractC1630aE1
    public final MD1 l() {
        Object obj = this.I;
        KD1 kd1 = MD1.G;
        Object[] objArr = {obj};
        for (int i = 0; i <= 0; i++) {
            AbstractC2322eE1.a(objArr[0], 0);
        }
        return new C1981cE1(objArr, 1);
    }

    /* renamed from: m */
    public final AE1 iterator() {
        return new YD1(this.I);
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        String obj = this.I.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public BE1(Object obj, int i) {
        this.I = obj;
        this.f7725J = i;
    }
}
