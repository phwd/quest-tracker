package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* renamed from: Hv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Hv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Hv1 f8189a = a(0).a();
    public static final Hv1 b = a(1).a();
    public final int c;
    public final Integer d;
    public final Integer e;
    public final Integer f;
    public final Integer g;
    public final Integer h;
    public final Integer i;
    public final Integer j;
    public Long k;

    public Hv1(Gv1 gv1, Fv1 fv1) {
        this.c = gv1.f8121a;
        this.d = gv1.b;
        this.e = gv1.c;
        this.f = gv1.d;
        this.g = gv1.e;
        this.h = gv1.f;
        this.i = gv1.g;
        this.j = gv1.h;
        this.k = gv1.i;
    }

    public static Gv1 a(int i2) {
        Gv1 gv1 = new Gv1();
        gv1.f8121a = i2;
        return gv1;
    }

    public C1578Zw0 b(boolean z) {
        C1578Zw0 zw0 = C1578Zw0.e;
        C1578Zw0 zw02 = C1578Zw0.e;
        C1578Zw0 zw03 = new C1578Zw0();
        C1456Xw0 xw0 = C1456Xw0.e;
        C1456Xw0 xw02 = C1456Xw0.e;
        C1456Xw0 xw03 = new C1456Xw0();
        int i2 = this.c;
        if (i2 == 2) {
            xw03.h = 3;
            xw03.g |= 1;
        } else if (i2 == 3) {
            xw03.h = 1;
            xw03.g |= 1;
        } else if (i2 == 4) {
            xw03.h = 2;
            xw03.g |= 1;
        } else if (i2 != 5) {
            xw03.h = 0;
            xw03.g |= 1;
        } else {
            xw03.h = 4;
            xw03.g |= 1;
        }
        Integer num = this.d;
        if (num != null) {
            int intValue = num.intValue();
            xw03.g |= 2;
            xw03.i = intValue;
        }
        Integer num2 = this.e;
        if (num2 != null) {
            int intValue2 = num2.intValue();
            xw03.g |= 4;
            xw03.j = intValue2;
        }
        Integer num3 = this.f;
        if (num3 != null) {
            int intValue3 = num3.intValue();
            xw03.g |= 8;
            xw03.k = intValue3;
        }
        Integer num4 = this.g;
        if (num4 != null) {
            int intValue4 = num4.intValue();
            xw03.g |= 16;
            xw03.l = intValue4;
        }
        Integer num5 = this.h;
        if (num5 != null) {
            int intValue5 = num5.intValue();
            xw03.g |= 32;
            xw03.m = intValue5;
        }
        Integer num6 = this.i;
        if (num6 != null) {
            int intValue6 = num6.intValue();
            xw03.g |= 64;
            xw03.n = intValue6;
        }
        Integer num7 = this.j;
        if (num7 != null) {
            int intValue7 = num7.intValue();
            xw03.g |= 128;
            xw03.o = intValue7;
        }
        C2163dI0 di0 = C2163dI0.f9768a;
        di0.b(xw03).c(xw03);
        if (xw03.i()) {
            zw03.i = xw03;
            zw03.h = 2;
            Long l = this.k;
            if (l != null) {
                long longValue = l.longValue();
                zw03.g |= 8;
                zw03.k = longValue;
            }
            zw03.g |= 4;
            zw03.j = z;
            di0.b(zw03).c(zw03);
            if (zw03.i()) {
                return zw03;
            }
            throw new C5488wp1();
        }
        throw new C5488wp1();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Hv1)) {
            return false;
        }
        Hv1 hv1 = (Hv1) obj;
        if (!Objects.equals(Integer.valueOf(this.c), Integer.valueOf(hv1.c)) || !Objects.equals(this.d, hv1.d) || !Objects.equals(this.e, hv1.e) || !Objects.equals(this.f, hv1.f) || !Objects.equals(this.g, hv1.g) || !Objects.equals(this.h, hv1.h) || !Objects.equals(this.i, hv1.i) || !Objects.equals(this.j, hv1.j)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.c), this.d, this.e, this.f, this.g, this.h, this.i, this.j});
    }
}
