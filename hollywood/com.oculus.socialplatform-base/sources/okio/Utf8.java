package okio;

import X.AnonymousClass006;

public final class Utf8 {
    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i, int i2) {
        long j;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A03("beginIndex < 0: ", i));
        } else if (i2 >= i) {
            int length = str.length();
            if (i2 <= length) {
                long j2 = 0;
                while (i < i2) {
                    char charAt = str.charAt(i);
                    if (charAt < 128) {
                        j2++;
                    } else {
                        if (charAt < 2048) {
                            j = 2;
                        } else if (charAt < 55296 || charAt > 57343) {
                            j = 3;
                        } else {
                            int i3 = i + 1;
                            char charAt2 = i3 < i2 ? str.charAt(i3) : 0;
                            if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                                j2++;
                                i = i3;
                            } else {
                                j2 += 4;
                                i += 2;
                            }
                        }
                        j2 += j;
                    }
                    i++;
                }
                return j2;
            }
            throw new IllegalArgumentException(AnonymousClass006.A05("endIndex > string.length: ", i2, " > ", length));
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("endIndex < beginIndex: ", i2, " < ", i));
        }
    }
}
