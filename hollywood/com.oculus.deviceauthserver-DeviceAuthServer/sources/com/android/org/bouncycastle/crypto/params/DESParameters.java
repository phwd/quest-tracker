package com.android.org.bouncycastle.crypto.params;

import com.google.common.base.Ascii;

public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    private static byte[] DES_weak_keys = {1, 1, 1, 1, 1, 1, 1, 1, Ascii.US, Ascii.US, Ascii.US, Ascii.US, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.SO, -32, -32, -32, -32, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, Ascii.US, -32, Ascii.US, -32, Ascii.SO, -15, Ascii.SO, -15, 1, -32, 1, -32, 1, -15, 1, -15, Ascii.US, -2, Ascii.US, -2, Ascii.SO, -2, Ascii.SO, -2, 1, Ascii.US, 1, Ascii.US, 1, Ascii.SO, 1, Ascii.SO, -32, -2, -32, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, -32, Ascii.US, -32, Ascii.US, -15, Ascii.SO, -15, Ascii.SO, -32, 1, -32, 1, -15, 1, -15, 1, -2, Ascii.US, -2, Ascii.US, -2, Ascii.SO, -2, Ascii.SO, Ascii.US, 1, Ascii.US, 1, Ascii.SO, 1, Ascii.SO, 1, -2, -32, -2, -32, -2, -15, -2, -15};
    private static final int N_DES_WEAK_KEYS = 16;

    public DESParameters(byte[] key) {
        super(key);
        if (isWeakKey(key, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    public static boolean isWeakKey(byte[] key, int offset) {
        if (key.length - offset >= 8) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 8; j++) {
                    if (key[j + offset] != DES_weak_keys[(i * 8) + j]) {
                    }
                }
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("key material too short.");
    }

    public static void setOddParity(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            bytes[i] = (byte) ((b & 254) | (((((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6)) ^ (b >> 7)) ^ 1) & 1));
        }
    }
}
