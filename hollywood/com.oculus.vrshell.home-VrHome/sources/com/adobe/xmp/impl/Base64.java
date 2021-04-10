package com.adobe.xmp.impl;

public class Base64 {
    private static final byte EQUAL = -3;
    private static final byte INVALID = -1;
    private static final byte WHITESPACE = -2;
    private static byte[] ascii = new byte[255];
    private static byte[] base64 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    static {
        for (int idx = 0; idx < 255; idx++) {
            ascii[idx] = INVALID;
        }
        for (int idx2 = 0; idx2 < base64.length; idx2++) {
            ascii[base64[idx2]] = (byte) idx2;
        }
        ascii[9] = WHITESPACE;
        ascii[10] = WHITESPACE;
        ascii[13] = WHITESPACE;
        ascii[32] = WHITESPACE;
        ascii[61] = EQUAL;
    }

    public static final byte[] encode(byte[] src) {
        return encode(src, 0);
    }

    public static final byte[] encode(byte[] src, int lineFeed) {
        int lineFeed2 = (lineFeed / 4) * 4;
        if (lineFeed2 < 0) {
            lineFeed2 = 0;
        }
        int codeLength = ((src.length + 2) / 3) * 4;
        if (lineFeed2 > 0) {
            codeLength += (codeLength - 1) / lineFeed2;
        }
        byte[] dst = new byte[codeLength];
        int didx = 0;
        int sidx = 0;
        int lf = 0;
        while (sidx + 3 <= src.length) {
            int sidx2 = sidx + 1;
            int sidx3 = sidx2 + 1;
            int sidx4 = sidx3 + 1;
            int bits24 = ((src[sidx] & INVALID) << 16) | ((src[sidx2] & INVALID) << 8) | ((src[sidx3] & INVALID) << 0);
            int didx2 = didx + 1;
            dst[didx] = base64[(bits24 & 16515072) >> 18];
            int didx3 = didx2 + 1;
            dst[didx2] = base64[(bits24 & 258048) >> 12];
            int didx4 = didx3 + 1;
            dst[didx3] = base64[(bits24 & 4032) >> 6];
            didx = didx4 + 1;
            dst[didx4] = base64[bits24 & 63];
            lf += 4;
            if (didx >= codeLength || lineFeed2 <= 0 || lf % lineFeed2 != 0) {
                sidx = sidx4;
            } else {
                dst[didx] = 10;
                sidx = sidx4;
                didx++;
            }
        }
        if (src.length - sidx == 2) {
            int bits242 = ((src[sidx] & INVALID) << 16) | ((src[sidx + 1] & INVALID) << 8);
            int didx5 = didx + 1;
            dst[didx] = base64[(bits242 & 16515072) >> 18];
            int didx6 = didx5 + 1;
            dst[didx5] = base64[(bits242 & 258048) >> 12];
            int didx7 = didx6 + 1;
            dst[didx6] = base64[(bits242 & 4032) >> 6];
            int i = didx7 + 1;
            dst[didx7] = 61;
        } else if (src.length - sidx == 1) {
            int bits243 = (src[sidx] & INVALID) << 16;
            int didx8 = didx + 1;
            dst[didx] = base64[(bits243 & 16515072) >> 18];
            int didx9 = didx8 + 1;
            dst[didx8] = base64[(bits243 & 258048) >> 12];
            int didx10 = didx9 + 1;
            dst[didx9] = 61;
            int i2 = didx10 + 1;
            dst[didx10] = 61;
        }
        return dst;
    }

    public static final String encode(String src) {
        return new String(encode(src.getBytes()));
    }

    public static final byte[] decode(byte[] src) throws IllegalArgumentException {
        int srcLen = 0;
        for (byte b : src) {
            byte val = ascii[b];
            if (val >= 0) {
                src[srcLen] = val;
                srcLen++;
            } else if (val == -1) {
                throw new IllegalArgumentException("Invalid base 64 string");
            }
        }
        while (srcLen > 0 && src[srcLen - 1] == -3) {
            srcLen--;
        }
        byte[] dst = new byte[((srcLen * 3) / 4)];
        int sidx = 0;
        int didx = 0;
        while (didx < dst.length - 2) {
            dst[didx] = (byte) (((src[sidx] << 2) & 255) | ((src[sidx + 1] >>> 4) & 3));
            dst[didx + 1] = (byte) (((src[sidx + 1] << 4) & 255) | ((src[sidx + 2] >>> 2) & 15));
            dst[didx + 2] = (byte) (((src[sidx + 2] << 6) & 255) | (src[sidx + 3] & 63));
            sidx += 4;
            didx += 3;
        }
        if (didx < dst.length) {
            dst[didx] = (byte) (((src[sidx] << 2) & 255) | ((src[sidx + 1] >>> 4) & 3));
        }
        int didx2 = didx + 1;
        if (didx2 < dst.length) {
            dst[didx2] = (byte) (((src[sidx + 1] << 4) & 255) | ((src[sidx + 2] >>> 2) & 15));
        }
        return dst;
    }

    public static final String decode(String src) {
        return new String(decode(src.getBytes()));
    }
}
