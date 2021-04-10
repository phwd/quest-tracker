package com.fasterxml.jackson.dataformat.smile;

public class SmileUtil {
    public static int zigzagDecode(int i) {
        return (i & 1) == 0 ? i >>> 1 : (i >>> 1) ^ -1;
    }

    public static long zigzagDecode(long j) {
        return (1 & j) == 0 ? j >>> 1 : (j >>> 1) ^ -1;
    }

    public static int zigzagEncode(int i) {
        return i < 0 ? (i << 1) ^ -1 : i << 1;
    }

    public static long zigzagEncode(long j) {
        return j < 0 ? (j << 1) ^ -1 : j << 1;
    }
}
