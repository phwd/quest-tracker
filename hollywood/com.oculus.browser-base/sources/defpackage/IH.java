package defpackage;

/* renamed from: IH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IH {

    /* renamed from: a  reason: collision with root package name */
    public int f8213a;
    public int b;
    public int c;
    public int d;
    public int e;

    public IH(HH hh) {
    }

    public int a(int i) {
        if (i == 0) {
            return this.d;
        }
        if (i == 1) {
            return this.c;
        }
        if (i == 2) {
            return this.b;
        }
        if (i != 3) {
            return 0;
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IH)) {
            return false;
        }
        IH ih = (IH) obj;
        return this.f8213a == ih.f8213a && this.b == ih.b && this.c == ih.c && this.d == ih.d && this.e == ih.e;
    }

    public int hashCode() {
        return (((((((this.f8213a * 31 * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e;
    }
}
