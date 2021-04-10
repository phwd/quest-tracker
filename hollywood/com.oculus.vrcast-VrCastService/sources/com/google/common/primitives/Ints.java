package com.google.common.primitives;

import com.apple.dnssd.DNSSD;
import java.util.Arrays;

public final class Ints {
    private static final byte[] asciiDigits = new byte[DNSSD.REGISTRATION_DOMAINS];

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    static {
        Arrays.fill(asciiDigits, (byte) -1);
        for (int i = 0; i <= 9; i++) {
            asciiDigits[i + 48] = (byte) i;
        }
        for (int i2 = 0; i2 <= 26; i2++) {
            byte[] bArr = asciiDigits;
            byte b = (byte) (i2 + 10);
            bArr[i2 + 65] = b;
            bArr[i2 + 97] = b;
        }
    }
}
