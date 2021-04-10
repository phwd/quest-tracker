package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* renamed from: Iv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Iv1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Iv1 f8258a = new Iv1(null, null, null, null);
    public final String b;
    public final String c;
    public final Integer d;
    public final Long e;

    public Iv1(String str, String str2, Integer num, Long l) {
        this.b = str;
        this.c = str2;
        this.d = num;
        this.e = l;
    }

    public C1578Zw0 a(boolean z) {
        C1578Zw0 zw0 = C1578Zw0.e;
        C1578Zw0 zw02 = C1578Zw0.e;
        C1578Zw0 zw03 = new C1578Zw0();
        C1517Yw0 yw0 = C1517Yw0.e;
        C1517Yw0 yw02 = C1517Yw0.e;
        C1517Yw0 yw03 = new C1517Yw0();
        String str = this.c;
        if (str != null) {
            yw03.g |= 1;
            yw03.h = str;
        }
        Integer num = this.d;
        if (num != null) {
            int intValue = num.intValue();
            yw03.g |= 2;
            yw03.i = intValue;
        }
        C2163dI0 di0 = C2163dI0.f9768a;
        di0.b(yw03).c(yw03);
        if (yw03.i()) {
            zw03.i = yw03;
            zw03.h = 1;
            Long l = this.e;
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
        if (!(obj instanceof Iv1)) {
            return false;
        }
        Iv1 iv1 = (Iv1) obj;
        if (!Objects.equals(this.b, iv1.b) || !Objects.equals(this.c, iv1.c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c});
    }
}
