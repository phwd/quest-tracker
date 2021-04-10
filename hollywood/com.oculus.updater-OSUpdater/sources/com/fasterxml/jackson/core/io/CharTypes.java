package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes {
    private static final byte[] HEX_BYTES;
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
    static final int[] sHexValues = new int[128];
    static final int[] sInputCodes;
    static final int[] sInputCodesComment = new int[256];
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUtf8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sOutputEscapes128;

    private CharTypes() {
    }

    static {
        int length = HEX_CHARS.length;
        HEX_BYTES = new byte[length];
        for (int i = 0; i < length; i++) {
            HEX_BYTES[i] = (byte) HEX_CHARS[i];
        }
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 32; i2++) {
            iArr[i2] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        sInputCodes = iArr;
        int[] iArr2 = sInputCodes;
        int[] iArr3 = new int[iArr2.length];
        System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
        for (int i3 = 128; i3 < 256; i3++) {
            iArr3[i3] = (i3 & 224) == 192 ? 2 : (i3 & 240) == 224 ? 3 : (i3 & 248) == 240 ? 4 : -1;
        }
        sInputCodesUtf8 = iArr3;
        int[] iArr4 = new int[256];
        Arrays.fill(iArr4, -1);
        for (int i4 = 33; i4 < 256; i4++) {
            if (Character.isJavaIdentifierPart((char) i4)) {
                iArr4[i4] = 0;
            }
        }
        iArr4[64] = 0;
        iArr4[35] = 0;
        iArr4[42] = 0;
        iArr4[45] = 0;
        iArr4[43] = 0;
        sInputCodesJsNames = iArr4;
        int[] iArr5 = new int[256];
        int[] iArr6 = sInputCodesJsNames;
        System.arraycopy(iArr6, 0, iArr5, 0, iArr6.length);
        Arrays.fill(iArr5, 128, 128, 0);
        sInputCodesUtf8JsNames = iArr5;
        System.arraycopy(sInputCodesUtf8, 128, sInputCodesComment, 128, 128);
        Arrays.fill(sInputCodesComment, 0, 32, -1);
        int[] iArr7 = sInputCodesComment;
        iArr7[9] = 0;
        iArr7[10] = 10;
        iArr7[13] = 13;
        iArr7[42] = 42;
        int[] iArr8 = new int[128];
        for (int i5 = 0; i5 < 32; i5++) {
            iArr8[i5] = -1;
        }
        iArr8[34] = 34;
        iArr8[92] = 92;
        iArr8[8] = 98;
        iArr8[9] = 116;
        iArr8[12] = 102;
        iArr8[10] = 110;
        iArr8[13] = 114;
        sOutputEscapes128 = iArr8;
        Arrays.fill(sHexValues, -1);
        for (int i6 = 0; i6 < 10; i6++) {
            sHexValues[i6 + 48] = i6;
        }
        for (int i7 = 0; i7 < 6; i7++) {
            int[] iArr9 = sHexValues;
            int i8 = i7 + 10;
            iArr9[i7 + 97] = i8;
            iArr9[i7 + 65] = i8;
        }
    }

    public static void appendQuoted(StringBuilder sb, String str) {
        int[] iArr = sOutputEscapes128;
        int length = iArr.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            if (charAt >= length || iArr[charAt] == 0) {
                sb.append(charAt);
            } else {
                sb.append('\\');
                int i2 = iArr[charAt];
                if (i2 < 0) {
                    sb.append('u');
                    sb.append('0');
                    sb.append('0');
                    sb.append(HEX_CHARS[charAt >> 4]);
                    sb.append(HEX_CHARS[charAt & 15]);
                } else {
                    sb.append((char) i2);
                }
            }
        }
    }
}
