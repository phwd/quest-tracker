package X;

/* renamed from: X.0r  reason: invalid class name and case insensitive filesystem */
public class C00080r extends C00100t {
    public static final Integer A03(String str) {
        char c;
        boolean z;
        int i;
        C0514bB.A02(str, "$this$toIntOrNull");
        C0514bB.A02(str, "$this$toIntOrNull");
        int length = str.length();
        if (length != 0) {
            int i2 = 0;
            char charAt = str.charAt(0);
            if (charAt < '0') {
                c = 65535;
            } else {
                c = 1;
                if (charAt == '0') {
                    c = 0;
                }
            }
            int i3 = -2147483647;
            int i4 = 1;
            if (c >= 0) {
                z = false;
                i4 = 0;
            } else if (length != 1) {
                if (charAt == '-') {
                    i3 = Integer.MIN_VALUE;
                    z = true;
                } else if (charAt == '+') {
                    z = false;
                }
            }
            int i5 = -59652323;
            while (i4 < length) {
                int digit = Character.digit((int) str.charAt(i4), 10);
                if (digit >= 0 && ((i2 >= i5 || (i5 == -59652323 && i2 >= (i5 = i3 / 10))) && (i = i2 * 10) >= i3 + digit)) {
                    i2 = i - digit;
                    i4++;
                }
            }
            if (!z) {
                i2 = -i2;
            }
            return Integer.valueOf(i2);
        }
        return null;
    }
}
