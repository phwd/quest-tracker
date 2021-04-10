package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Ge  reason: case insensitive filesystem */
public final class C0084Ge {
    public static void A01(int i, int i2, int i3) {
        if ((i2 | i3) < 0 || i2 > i || i - i2 < i3) {
            StringBuilder sb = new StringBuilder("length=");
            sb.append(i);
            sb.append("; regionStart=");
            sb.append(i2);
            sb.append("; regionLength=");
            sb.append(i3);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
    }

    public static StringIndexOutOfBoundsException A00(String str, int i, int i2) {
        int length = str.length();
        StringBuilder sb = new StringBuilder("length=");
        sb.append(length);
        sb.append("; regionStart=");
        sb.append(i);
        sb.append("; regionLength=");
        sb.append(i2);
        return new StringIndexOutOfBoundsException(sb.toString());
    }
}
