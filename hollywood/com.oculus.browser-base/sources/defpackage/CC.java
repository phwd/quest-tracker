package defpackage;

/* renamed from: CC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CC {

    /* renamed from: a  reason: collision with root package name */
    public final int f7794a;
    public final int b;

    public CC(int i, int i2) {
        this.f7794a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || CC.class != obj.getClass()) {
            return false;
        }
        CC cc = (CC) obj;
        return this.b == cc.b && this.f7794a == cc.f7794a;
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + this.f7794a;
    }
}
