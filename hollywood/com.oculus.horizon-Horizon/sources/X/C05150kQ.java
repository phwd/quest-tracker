package X;

import java.util.regex.Pattern;

/* renamed from: X.0kQ  reason: invalid class name and case insensitive filesystem */
public final class C05150kQ {
    public static final Pattern A00 = Pattern.compile("[-_./;:]");

    public static C04860jn A01(String str, String str2, String str3) {
        int i;
        String str4 = null;
        if (str != null) {
            String trim = str.trim();
            if (trim.length() != 0) {
                String[] split = A00.split(trim);
                int i2 = 0;
                int A002 = A00(split[0]);
                int length = split.length;
                if (length > 1) {
                    i = A00(split[1]);
                } else {
                    i = 0;
                }
                if (length > 2) {
                    i2 = A00(split[2]);
                }
                if (length > 3) {
                    str4 = split[3];
                }
                return new C04860jn(A002, i, i2, str4, str2, str3);
            }
        }
        return null;
    }

    public static final void A02() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    public static int A00(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i = (i * 10) + (charAt - '0');
        }
        return i;
    }
}
