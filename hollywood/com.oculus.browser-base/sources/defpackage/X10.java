package defpackage;

import android.graphics.Insets;

/* renamed from: X10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class X10 {

    /* renamed from: a  reason: collision with root package name */
    public static final X10 f9186a = new X10(0, 0, 0, 0);
    public final int b;
    public final int c;
    public final int d;
    public final int e;

    public X10(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public static X10 a(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return f9186a;
        }
        return new X10(i, i2, i3, i4);
    }

    public Insets b() {
        return Insets.of(this.b, this.c, this.d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || X10.class != obj.getClass()) {
            return false;
        }
        X10 x10 = (X10) obj;
        return this.e == x10.e && this.b == x10.b && this.d == x10.d && this.c == x10.c;
    }

    public int hashCode() {
        return (((((this.b * 31) + this.c) * 31) + this.d) * 31) + this.e;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Insets{left=");
        i.append(this.b);
        i.append(", top=");
        i.append(this.c);
        i.append(", right=");
        i.append(this.d);
        i.append(", bottom=");
        i.append(this.e);
        i.append('}');
        return i.toString();
    }
}
