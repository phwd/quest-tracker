package X;

import java.util.TimeZone;

/* renamed from: X.14F  reason: invalid class name */
public final class AnonymousClass14F {
    public static final TimeZone A00 = TimeZone.getTimeZone("UTC");

    public static int A00(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i3 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i4 = -digit;
            } else {
                throw new NumberFormatException(AnonymousClass006.A07("Invalid number: ", str.substring(i, i2)));
            }
        } else {
            i3 = i;
            i4 = 0;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int digit2 = Character.digit(str.charAt(i3), 10);
            if (digit2 >= 0) {
                i4 = (i4 * 10) - digit2;
                i3 = i5;
            } else {
                throw new NumberFormatException(AnonymousClass006.A07("Invalid number: ", str.substring(i, i2)));
            }
        }
        return -i4;
    }

    public static boolean A01(String str, int i, char c) {
        if (i >= str.length() || str.charAt(i) != c) {
            return false;
        }
        return true;
    }
}
