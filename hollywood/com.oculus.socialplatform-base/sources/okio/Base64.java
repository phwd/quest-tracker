package okio;

import com.facebook.acra.util.UrlEncodingWriter;
import java.io.UnsupportedEncodingException;

public final class Base64 {
    public static final byte[] MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] URL_MAP = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    public static String encodeUrl(byte[] bArr) {
        return encode(bArr, URL_MAP);
    }

    public static byte[] decode(String str) {
        int i;
        int length = str.length();
        while (length > 0 && ((r1 = str.charAt(length - 1)) == '=' || r1 == '\n' || r1 == '\r' || r1 == ' ' || r1 == '\t')) {
            length--;
        }
        int i2 = (int) ((((long) length) * 6) / 8);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i3 < length) {
                char charAt = str.charAt(i3);
                if (charAt >= 'A' && charAt <= 'Z') {
                    i = charAt - 'A';
                } else if (charAt >= 'a' && charAt <= 'z') {
                    i = charAt - 'G';
                } else if (charAt >= '0' && charAt <= '9') {
                    i = charAt + 4;
                } else if (charAt == '+' || charAt == '-') {
                    i = 62;
                } else if (charAt == '/' || charAt == '_') {
                    i = 63;
                } else {
                    if (!(charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
                        break;
                    }
                    i3++;
                }
                i5 = (i5 << 6) | ((byte) i);
                i4++;
                if (i4 % 4 == 0) {
                    int i7 = i6 + 1;
                    bArr[i6] = (byte) (i5 >> 16);
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) (i5 >> 8);
                    i6 = i8 + 1;
                    bArr[i8] = (byte) i5;
                }
                i3++;
            } else {
                int i9 = i4 % 4;
                if (i9 != 1) {
                    if (i9 == 2) {
                        bArr[i6] = (byte) ((i5 << 12) >> 16);
                        i6++;
                    } else if (i9 == 3) {
                        int i10 = i5 << 6;
                        int i11 = i6 + 1;
                        bArr[i6] = (byte) (i10 >> 16);
                        i6 = i11 + 1;
                        bArr[i11] = (byte) (i10 >> 8);
                    }
                    if (i6 == i2) {
                        return bArr;
                    }
                    byte[] bArr2 = new byte[i6];
                    System.arraycopy(bArr, 0, bArr2, 0, i6);
                    return bArr2;
                }
            }
        }
        return null;
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, MAP);
    }

    public static String encode(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[(((length + 2) / 3) << 2)];
        int i = length % 3;
        int i2 = length - i;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4 += 3) {
            int i5 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i4] & com.adobe.xmp.impl.Base64.INVALID) >> 2];
            int i6 = i5 + 1;
            int i7 = i4 + 1;
            bArr3[i5] = bArr2[((bArr[i4] & 3) << 4) | ((bArr[i7] & com.adobe.xmp.impl.Base64.INVALID) >> 4)];
            int i8 = i6 + 1;
            int i9 = i4 + 2;
            bArr3[i6] = bArr2[((bArr[i7] & 15) << 2) | ((bArr[i9] & com.adobe.xmp.impl.Base64.INVALID) >> 6)];
            i3 = i8 + 1;
            bArr3[i8] = bArr2[bArr[i9] & UrlEncodingWriter.UTF16_REPLACEMENT_BYTE];
        }
        if (i == 1) {
            int i10 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i2] & com.adobe.xmp.impl.Base64.INVALID) >> 2];
            int i11 = i10 + 1;
            bArr3[i10] = bArr2[(bArr[i2] & 3) << 4];
            bArr3[i11] = 61;
            bArr3[i11 + 1] = 61;
        } else if (i == 2) {
            int i12 = i3 + 1;
            bArr3[i3] = bArr2[(bArr[i2] & com.adobe.xmp.impl.Base64.INVALID) >> 2];
            int i13 = i12 + 1;
            int i14 = (bArr[i2] & 3) << 4;
            int i15 = i2 + 1;
            bArr3[i12] = bArr2[((bArr[i15] & com.adobe.xmp.impl.Base64.INVALID) >> 4) | i14];
            bArr3[i13] = bArr2[(bArr[i15] & 15) << 2];
            bArr3[i13 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
