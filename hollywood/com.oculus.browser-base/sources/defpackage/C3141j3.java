package defpackage;

/* renamed from: j3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3141j3 {

    /* renamed from: a  reason: collision with root package name */
    public int f10183a;
    public int b;
    public Object c;
    public int d;

    public C3141j3(int i, int i2, int i3, Object obj) {
        this.f10183a = i;
        this.b = i2;
        this.d = i3;
        this.c = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3141j3)) {
            return false;
        }
        C3141j3 j3Var = (C3141j3) obj;
        int i = this.f10183a;
        if (i != j3Var.f10183a) {
            return false;
        }
        if (i == 8 && Math.abs(this.d - this.b) == 1 && this.d == j3Var.b && this.b == j3Var.d) {
            return true;
        }
        if (this.d != j3Var.d || this.b != j3Var.b) {
            return false;
        }
        Object obj2 = this.c;
        if (obj2 != null) {
            if (!obj2.equals(j3Var.c)) {
                return false;
            }
        } else if (j3Var.c != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.f10183a * 31) + this.b) * 31) + this.d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i = this.f10183a;
        sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.b);
        sb.append("c:");
        sb.append(this.d);
        sb.append(",p:");
        sb.append(this.c);
        sb.append("]");
        return sb.toString();
    }
}
