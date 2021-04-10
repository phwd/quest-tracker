package com.adobe.xmp.impl;

public class Base64 {
    private static byte[] ascii = new byte[255];
    private static byte[] base64 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    static {
        for (int idx = 0; idx < 255; idx++) {
            ascii[idx] = -1;
        }
        for (int idx2 = 0; idx2 < base64.length; idx2++) {
            ascii[base64[idx2]] = (byte) idx2;
        }
        ascii[9] = -2;
        ascii[10] = -2;
        ascii[13] = -2;
        ascii[32] = -2;
        ascii[61] = -3;
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
}
