package com.oculus.vrcast.googlecast;

/* access modifiers changed from: package-private */
public class Util {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    Util() {
    }

    static String bytesToHex(byte[] bArr, int i, int i2) {
        char[] cArr = new char[(i2 * 2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i + i3] & 255;
            int i5 = i3 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i5] = cArr2[i4 >>> 4];
            cArr[i5 + 1] = cArr2[i4 & 15];
        }
        return new String(cArr);
    }
}
