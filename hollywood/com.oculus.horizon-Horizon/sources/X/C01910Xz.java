package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Xz  reason: invalid class name and case insensitive filesystem */
public final class C01910Xz {
    public static final char[] A00 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] A02(String str) throws IllegalArgumentException {
        int i = 0;
        if (str == null || str.isEmpty()) {
            return new byte[0];
        }
        int length = str.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[(length >> 1)];
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                char charAt = str.charAt(i);
                i = i3 + 1;
                bArr[i2] = (byte) ((A00(charAt) << 4) + A00(str.charAt(i3)));
                i2++;
            }
            return bArr;
        }
        throw new IllegalArgumentException("String is not valid hexadecimal");
    }

    public static byte A00(char c) throws IllegalArgumentException {
        int i;
        if ('0' > c || c > '9') {
            char c2 = 'a';
            if ('a' > c || c > 'f') {
                c2 = 'A';
                if ('A' > c || c > 'F') {
                    throw new IllegalArgumentException("Non hexadecimal charter found");
                }
            }
            i = (c - c2) + 10;
        } else {
            i = c - '0';
        }
        return (byte) i;
    }

    public static String A01(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = A00;
            cArr[i] = cArr2[(b & 240) >>> 4];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }
}
