package defpackage;

/* renamed from: Xo1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Xo1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9235a;
    public final int b;

    public Xo1(int i, int i2) {
        this.f9235a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Xo1.class != obj.getClass()) {
            return false;
        }
        Xo1 xo1 = (Xo1) obj;
        return this.f9235a == xo1.f9235a && this.b == xo1.b;
    }

    public int hashCode() {
        return (this.f9235a * 31) + this.b;
    }
}
