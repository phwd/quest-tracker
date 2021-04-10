package defpackage;

/* renamed from: HW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HW {

    /* renamed from: a  reason: collision with root package name */
    public final int f8160a;
    public final int b;

    public HW(int i, int i2) {
        this.f8160a = i;
        this.b = i2;
    }

    public int a() {
        return this.b - this.f8160a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HW.class != obj.getClass()) {
            return false;
        }
        HW hw = (HW) obj;
        return this.b == hw.b && this.f8160a == hw.f8160a;
    }

    public int hashCode() {
        return (this.f8160a * 31) + this.b;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("[");
        i.append(this.f8160a);
        i.append(", ");
        i.append(this.b);
        i.append("]");
        return i.toString();
    }
}
