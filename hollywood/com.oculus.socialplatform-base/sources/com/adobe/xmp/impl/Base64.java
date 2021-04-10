package com.adobe.xmp.impl;

import com.facebook.acra.util.UrlEncodingWriter;
import com.oculus.localmedia.MediaProviderUtils;

public class Base64 {
    public static final byte EQUAL = -3;
    public static final byte INVALID = -1;
    public static final byte WHITESPACE = -2;
    public static byte[] ascii;
    public static byte[] base64;

    static {
        byte[] bArr = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        base64 = bArr;
        byte[] bArr2 = new byte[MediaProviderUtils.JPEG_HEADER];
        ascii = bArr2;
        int i = 0;
        int i2 = 0;
        do {
            bArr2[i2] = -1;
            i2++;
        } while (i2 < 255);
        do {
            bArr2[bArr[i]] = (byte) i;
            i++;
        } while (i < 64);
        bArr2[9] = -2;
        bArr2[10] = -2;
        bArr2[13] = -2;
        bArr2[32] = -2;
        bArr2[61] = -3;
    }

    public static final String decode(String str) {
        return new String(decode(str.getBytes()));
    }

    public static final byte[] decode(byte[] bArr) throws IllegalArgumentException {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            byte b2 = ascii[b];
            if (b2 >= 0) {
                bArr[i2] = b2;
                i2++;
            } else if (b2 == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (i2 > 0) {
            int i3 = i2 - 1;
            if (bArr[i3] != -3) {
                break;
            }
            i2 = i3;
        }
        int i4 = (i2 * 3) >> 2;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        while (i < i4 - 2) {
            int i6 = i5 + 1;
            bArr2[i] = (byte) (((bArr[i5] << 2) & MediaProviderUtils.JPEG_HEADER) | ((bArr[i6] >>> 4) & 3));
            int i7 = i5 + 2;
            bArr2[i + 1] = (byte) (((bArr[i6] << 4) & MediaProviderUtils.JPEG_HEADER) | ((bArr[i7] >>> 2) & 15));
            bArr2[i + 2] = (byte) (((bArr[i7] << 6) & MediaProviderUtils.JPEG_HEADER) | (bArr[i5 + 3] & UrlEncodingWriter.UTF16_REPLACEMENT_BYTE));
            i5 += 4;
            i += 3;
        }
        if (i < i4) {
            bArr2[i] = (byte) (((bArr[i5] << 2) & MediaProviderUtils.JPEG_HEADER) | ((bArr[i5 + 1] >>> 4) & 3));
        }
        int i8 = i + 1;
        if (i8 < i4) {
            bArr2[i8] = (byte) (((bArr[i5 + 2] >>> 2) & 15) | ((bArr[i5 + 1] << 4) & MediaProviderUtils.JPEG_HEADER));
        }
        return bArr2;
    }

    public static final String encode(String str) {
        return new String(encode(str.getBytes(), 0));
    }

    public static final byte[] encode(byte[] bArr) {
        return encode(bArr, 0);
    }

    public static final byte[] encode(byte[] bArr, int i) {
        int i2 = (i >> 2) << 2;
        if (i2 < 0) {
            i2 = 0;
        }
        int length = bArr.length;
        int i3 = ((length + 2) / 3) << 2;
        if (i2 > 0) {
            i3 += (i3 - 1) / i2;
        }
        byte[] bArr2 = new byte[i3];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 + 3 <= length) {
            int i7 = i4 + 1;
            int i8 = i7 + 1;
            i4 = i8 + 1;
            int i9 = ((bArr[i4] & INVALID) << 16) | ((bArr[i7] & INVALID) << 8) | ((bArr[i8] & INVALID) << 0);
            int i10 = i5 + 1;
            byte[] bArr3 = base64;
            bArr2[i5] = bArr3[(i9 & 16515072) >> 18];
            int i11 = i10 + 1;
            bArr2[i10] = bArr3[(i9 & 258048) >> 12];
            int i12 = i11 + 1;
            bArr2[i11] = bArr3[(i9 & 4032) >> 6];
            i5 = i12 + 1;
            bArr2[i12] = bArr3[i9 & 63];
            i6 += 4;
            if (i5 < i3 && i2 > 0 && i6 % i2 == 0) {
                bArr2[i5] = 10;
                i5++;
            }
        }
        int i13 = length - i4;
        if (i13 == 2) {
            int i14 = ((bArr[i4 + 1] & INVALID) << 8) | ((bArr[i4] & INVALID) << 16);
            int i15 = i5 + 1;
            byte[] bArr4 = base64;
            bArr2[i5] = bArr4[(i14 & 16515072) >> 18];
            int i16 = i15 + 1;
            bArr2[i15] = bArr4[(i14 & 258048) >> 12];
            bArr2[i16] = bArr4[(i14 & 4032) >> 6];
            bArr2[i16 + 1] = 61;
        } else if (i13 == 1) {
            int i17 = (bArr[i4] & INVALID) << 16;
            int i18 = i5 + 1;
            byte[] bArr5 = base64;
            bArr2[i5] = bArr5[(i17 & 16515072) >> 18];
            int i19 = (i17 & 258048) >> 12;
            int i20 = i18 + 1;
            bArr2[i18] = bArr5[i19];
            bArr2[i20] = 61;
            bArr2[i20 + 1] = 61;
            return bArr2;
        }
        return bArr2;
    }
}
