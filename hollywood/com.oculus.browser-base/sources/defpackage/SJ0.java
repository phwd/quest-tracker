package defpackage;

/* renamed from: SJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SJ0 {

    /* renamed from: a  reason: collision with root package name */
    public int f8889a;
    public int b;

    public SJ0(int i, int i2) {
        this.f8889a = Math.min(i, i2);
        this.b = Math.max(i, i2);
    }

    public void a(int i, int i2) {
        this.f8889a = Math.min(Math.max(this.f8889a, i), i2);
        this.b = Math.max(Math.min(this.b, i2), i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SJ0)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        SJ0 sj0 = (SJ0) obj;
        return this.f8889a == sj0.f8889a && this.b == sj0.b;
    }

    public int hashCode() {
        return (this.b * 31) + (this.f8889a * 11);
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("[ ");
        i.append(this.f8889a);
        i.append(", ");
        i.append(this.b);
        i.append(" ]");
        return i.toString();
    }
}
