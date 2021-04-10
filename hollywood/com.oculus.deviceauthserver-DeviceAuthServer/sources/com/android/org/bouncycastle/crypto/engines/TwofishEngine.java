package com.android.org.bouncycastle.crypto.engines;

import com.android.org.bouncycastle.crypto.BlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.DataLengthException;
import com.android.org.bouncycastle.crypto.OutputLengthException;
import com.android.org.bouncycastle.crypto.params.KeyParameter;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;

public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;
    private static final byte[][] P = {new byte[]{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, UnsignedBytes.MAX_POWER_OF_TWO, 120, -28, -35, -47, 56, Ascii.CR, -58, 53, -104, Ascii.CAN, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, Ascii.EM, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, Ascii.SYN, Ascii.FF, -29, 97, -64, -116, 58, -11, 115, 44, 37, Ascii.VT, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, Ascii.FS, Ascii.RS, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, Ascii.GS, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, Ascii.ESC, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, Ascii.DEL, -43, Ascii.SUB, 75, Ascii.SO, -89, 90, 40, Ascii.DC4, 63, 41, -120, 60, 76, 2, -72, -38, -80, Ascii.ETB, 85, Ascii.US, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, Ascii.NAK, 34, Ascii.DC2, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, SignedBytes.MAX_POWER_OF_TWO, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, Ascii.SI, 0, 111, -99, 54, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, Ascii.SI, -8, Ascii.ESC, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, Ascii.SO, UnsignedBytes.MAX_POWER_OF_TWO, 93, -46, -43, -96, -124, 7, Ascii.DC4, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, SignedBytes.MAX_POWER_OF_TWO, -25, 43, -30, 121, Ascii.FF, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, Ascii.ETB, 102, -108, -95, Ascii.GS, 61, -16, -34, -77, Ascii.VT, 114, -89, Ascii.FS, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, Ascii.SUB, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, Ascii.CAN, 35, -35, Ascii.US, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, Ascii.EM, -115, -27, -104, 87, 103, Ascii.DEL, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, Ascii.NAK, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, Ascii.DC2, -94, Ascii.CR, 82, -69, 2, 47, -87, -41, 97, Ascii.RS, -76, 80, 4, -10, -62, Ascii.SYN, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[MAX_KEY_BITS];
    private int[] gMDS1 = new int[MAX_KEY_BITS];
    private int[] gMDS2 = new int[MAX_KEY_BITS];
    private int[] gMDS3 = new int[MAX_KEY_BITS];
    private int[] gSBox;
    private int[] gSubKeys;
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] m1 = new int[2];
        int[] mX = new int[2];
        int[] mY = new int[2];
        for (int i = 0; i < MAX_KEY_BITS; i++) {
            int j = P[0][i] & UnsignedBytes.MAX_VALUE;
            m1[0] = j;
            mX[0] = Mx_X(j) & 255;
            mY[0] = Mx_Y(j) & 255;
            int j2 = P[1][i] & UnsignedBytes.MAX_VALUE;
            m1[1] = j2;
            mX[1] = Mx_X(j2) & 255;
            mY[1] = Mx_Y(j2) & 255;
            this.gMDS0[i] = m1[1] | (mX[1] << 8) | (mY[1] << 16) | (mY[1] << 24);
            this.gMDS1[i] = mY[0] | (mY[0] << 8) | (mX[0] << 16) | (m1[0] << 24);
            this.gMDS2[i] = (mY[1] << 24) | mX[1] | (mY[1] << 8) | (m1[1] << 16);
            this.gMDS3[i] = mX[0] | (m1[0] << 8) | (mY[0] << 16) | (mX[0] << 24);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public void init(boolean encrypting2, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.encrypting = encrypting2;
            this.workingKey = ((KeyParameter) params).getKey();
            byte[] bArr = this.workingKey;
            this.k64Cnt = bArr.length / 8;
            setKey(bArr);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + params.getClass().getName());
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        } else if (inOff + 16 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 16 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (this.encrypting) {
            encryptBlock(in, inOff, out, outOff);
            return 16;
        } else {
            decryptBlock(in, inOff, out, outOff);
            return 16;
        }
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0093, code lost:
        if (r2 != r11) goto L_0x0104;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setKey(byte[] r23) {
        /*
        // Method dump skipped, instructions count: 572
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.crypto.engines.TwofishEngine.setKey(byte[]):void");
    }

    private void encryptBlock(byte[] src, int srcIndex, byte[] dst, int dstIndex) {
        int x0 = BytesTo32Bits(src, srcIndex) ^ this.gSubKeys[0];
        int x1 = BytesTo32Bits(src, srcIndex + 4) ^ this.gSubKeys[1];
        int x2 = BytesTo32Bits(src, srcIndex + 8) ^ this.gSubKeys[2];
        int x3 = BytesTo32Bits(src, srcIndex + 12) ^ this.gSubKeys[3];
        int t0 = 8;
        int r = 0;
        while (r < 16) {
            int t02 = Fe32_0(x0);
            int t1 = Fe32_3(x1);
            int[] iArr = this.gSubKeys;
            int k = t0 + 1;
            int x22 = x2 ^ ((t02 + t1) + iArr[t0]);
            x2 = (x22 >>> 1) | (x22 << 31);
            int k2 = k + 1;
            x3 = ((x3 << 1) | (x3 >>> 31)) ^ (((t1 * 2) + t02) + iArr[k]);
            int t03 = Fe32_0(x2);
            int t12 = Fe32_3(x3);
            int[] iArr2 = this.gSubKeys;
            int k3 = k2 + 1;
            int x02 = x0 ^ ((t03 + t12) + iArr2[k2]);
            x0 = (x02 >>> 1) | (x02 << 31);
            x1 = ((x1 << 1) | (x1 >>> 31)) ^ (((t12 * 2) + t03) + iArr2[k3]);
            r += 2;
            t0 = k3 + 1;
        }
        Bits32ToBytes(this.gSubKeys[4] ^ x2, dst, dstIndex);
        Bits32ToBytes(this.gSubKeys[5] ^ x3, dst, dstIndex + 4);
        Bits32ToBytes(this.gSubKeys[6] ^ x0, dst, dstIndex + 8);
        Bits32ToBytes(this.gSubKeys[7] ^ x1, dst, dstIndex + 12);
    }

    private void decryptBlock(byte[] src, int srcIndex, byte[] dst, int dstIndex) {
        int x2 = BytesTo32Bits(src, srcIndex) ^ this.gSubKeys[4];
        int x3 = BytesTo32Bits(src, srcIndex + 4) ^ this.gSubKeys[5];
        int x0 = BytesTo32Bits(src, srcIndex + 8) ^ this.gSubKeys[6];
        int x1 = BytesTo32Bits(src, srcIndex + 12) ^ this.gSubKeys[7];
        int t0 = 39;
        int r = 0;
        while (r < 16) {
            int t02 = Fe32_0(x2);
            int t1 = Fe32_3(x3);
            int[] iArr = this.gSubKeys;
            int k = t0 - 1;
            int x12 = x1 ^ (((t1 * 2) + t02) + iArr[t0]);
            int k2 = k - 1;
            x0 = ((x0 << 1) | (x0 >>> 31)) ^ ((t02 + t1) + iArr[k]);
            x1 = (x12 >>> 1) | (x12 << 31);
            int t03 = Fe32_0(x0);
            int t12 = Fe32_3(x1);
            int[] iArr2 = this.gSubKeys;
            int k3 = k2 - 1;
            int x32 = x3 ^ (((t12 * 2) + t03) + iArr2[k2]);
            x2 = ((x2 << 1) | (x2 >>> 31)) ^ ((t03 + t12) + iArr2[k3]);
            x3 = (x32 >>> 1) | (x32 << 31);
            r += 2;
            t0 = k3 - 1;
        }
        Bits32ToBytes(this.gSubKeys[0] ^ x0, dst, dstIndex);
        Bits32ToBytes(this.gSubKeys[1] ^ x1, dst, dstIndex + 4);
        Bits32ToBytes(this.gSubKeys[2] ^ x2, dst, dstIndex + 8);
        Bits32ToBytes(this.gSubKeys[3] ^ x3, dst, dstIndex + 12);
    }

    private int F32(int x, int[] k32) {
        int b0 = b0(x);
        int b1 = b1(x);
        int b2 = b2(x);
        int b3 = b3(x);
        int k0 = k32[0];
        int k1 = k32[1];
        int k2 = k32[2];
        int k3 = k32[3];
        int i = this.k64Cnt & 3;
        if (i == 0) {
            b0 = (P[1][b0] & UnsignedBytes.MAX_VALUE) ^ b0(k3);
            b1 = (P[0][b1] & UnsignedBytes.MAX_VALUE) ^ b1(k3);
            b2 = (P[0][b2] & UnsignedBytes.MAX_VALUE) ^ b2(k3);
            b3 = (P[1][b3] & UnsignedBytes.MAX_VALUE) ^ b3(k3);
        } else if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return 0;
                }
            }
            int[] iArr = this.gMDS0;
            byte[][] bArr = P;
            int i2 = iArr[(bArr[0][(bArr[0][b0] & UnsignedBytes.MAX_VALUE) ^ b0(k1)] & UnsignedBytes.MAX_VALUE) ^ b0(k0)];
            int[] iArr2 = this.gMDS1;
            byte[][] bArr2 = P;
            int i3 = i2 ^ iArr2[(bArr2[0][(bArr2[1][b1] & UnsignedBytes.MAX_VALUE) ^ b1(k1)] & UnsignedBytes.MAX_VALUE) ^ b1(k0)];
            int[] iArr3 = this.gMDS2;
            byte[][] bArr3 = P;
            int i4 = iArr3[(bArr3[1][(bArr3[0][b2] & UnsignedBytes.MAX_VALUE) ^ b2(k1)] & UnsignedBytes.MAX_VALUE) ^ b2(k0)] ^ i3;
            int[] iArr4 = this.gMDS3;
            byte[][] bArr4 = P;
            return i4 ^ iArr4[(bArr4[1][(bArr4[1][b3] & UnsignedBytes.MAX_VALUE) ^ b3(k1)] & UnsignedBytes.MAX_VALUE) ^ b3(k0)];
        } else {
            return ((this.gMDS1[(P[0][b1] & UnsignedBytes.MAX_VALUE) ^ b1(k0)] ^ this.gMDS0[(P[0][b0] & UnsignedBytes.MAX_VALUE) ^ b0(k0)]) ^ this.gMDS2[(P[1][b2] & UnsignedBytes.MAX_VALUE) ^ b2(k0)]) ^ this.gMDS3[(P[1][b3] & UnsignedBytes.MAX_VALUE) ^ b3(k0)];
        }
        b0 = (P[1][b0] & UnsignedBytes.MAX_VALUE) ^ b0(k2);
        b1 = (P[1][b1] & UnsignedBytes.MAX_VALUE) ^ b1(k2);
        b2 = (P[0][b2] & UnsignedBytes.MAX_VALUE) ^ b2(k2);
        b3 = (P[0][b3] & UnsignedBytes.MAX_VALUE) ^ b3(k2);
        int[] iArr5 = this.gMDS0;
        byte[][] bArr5 = P;
        int i22 = iArr5[(bArr5[0][(bArr5[0][b0] & UnsignedBytes.MAX_VALUE) ^ b0(k1)] & UnsignedBytes.MAX_VALUE) ^ b0(k0)];
        int[] iArr22 = this.gMDS1;
        byte[][] bArr22 = P;
        int i32 = i22 ^ iArr22[(bArr22[0][(bArr22[1][b1] & UnsignedBytes.MAX_VALUE) ^ b1(k1)] & UnsignedBytes.MAX_VALUE) ^ b1(k0)];
        int[] iArr32 = this.gMDS2;
        byte[][] bArr32 = P;
        int i42 = iArr32[(bArr32[1][(bArr32[0][b2] & UnsignedBytes.MAX_VALUE) ^ b2(k1)] & UnsignedBytes.MAX_VALUE) ^ b2(k0)] ^ i32;
        int[] iArr42 = this.gMDS3;
        byte[][] bArr42 = P;
        return i42 ^ iArr42[(bArr42[1][(bArr42[1][b3] & UnsignedBytes.MAX_VALUE) ^ b3(k1)] & UnsignedBytes.MAX_VALUE) ^ b3(k0)];
    }

    private int RS_MDS_Encode(int k0, int k1) {
        int r = k1;
        for (int i = 0; i < 4; i++) {
            r = RS_rem(r);
        }
        int r2 = r ^ k0;
        for (int i2 = 0; i2 < 4; i2++) {
            r2 = RS_rem(r2);
        }
        return r2;
    }

    private int RS_rem(int x) {
        int b = (x >>> 24) & 255;
        int i = 0;
        int g2 = ((b << 1) ^ ((b & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i2 = b >>> 1;
        if ((b & 1) != 0) {
            i = 166;
        }
        int g3 = (i2 ^ i) ^ g2;
        return ((((x << 8) ^ (g3 << 24)) ^ (g2 << 16)) ^ (g3 << 8)) ^ b;
    }

    private int LFSR1(int x) {
        return (x >> 1) ^ ((x & 1) != 0 ? GF256_FDBK_2 : 0);
    }

    private int LFSR2(int x) {
        int i = 0;
        int i2 = (x >> 2) ^ ((x & 2) != 0 ? GF256_FDBK_2 : 0);
        if ((x & 1) != 0) {
            i = GF256_FDBK_4;
        }
        return i2 ^ i;
    }

    private int Mx_X(int x) {
        return LFSR2(x) ^ x;
    }

    private int Mx_Y(int x) {
        return (LFSR1(x) ^ x) ^ LFSR2(x);
    }

    private int b0(int x) {
        return x & 255;
    }

    private int b1(int x) {
        return (x >>> 8) & 255;
    }

    private int b2(int x) {
        return (x >>> 16) & 255;
    }

    private int b3(int x) {
        return (x >>> 24) & 255;
    }

    private int Fe32_0(int x) {
        int[] iArr = this.gSBox;
        return iArr[(((x >>> 24) & 255) * 2) + 513] ^ ((iArr[((x & 255) * 2) + 0] ^ iArr[(((x >>> 8) & 255) * 2) + 1]) ^ iArr[(((x >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int x) {
        int[] iArr = this.gSBox;
        return iArr[(((x >>> 16) & 255) * 2) + 513] ^ ((iArr[(((x >>> 24) & 255) * 2) + 0] ^ iArr[((x & 255) * 2) + 1]) ^ iArr[(((x >>> 8) & 255) * 2) + 512]);
    }

    private int BytesTo32Bits(byte[] b, int p) {
        return (b[p] & UnsignedBytes.MAX_VALUE) | ((b[p + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((b[p + 2] & UnsignedBytes.MAX_VALUE) << 16) | ((b[p + 3] & UnsignedBytes.MAX_VALUE) << 24);
    }

    private void Bits32ToBytes(int in, byte[] b, int offset) {
        b[offset] = (byte) in;
        b[offset + 1] = (byte) (in >> 8);
        b[offset + 2] = (byte) (in >> 16);
        b[offset + 3] = (byte) (in >> 24);
    }
}
