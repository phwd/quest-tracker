package com.android.org.bouncycastle.crypto.digests;

import com.android.org.bouncycastle.util.Memoable;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;

public class SHA224Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 28;
    static final int[] K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int H6;
    private int H7;
    private int H8;
    private int[] X;
    private int xOff;

    public SHA224Digest() {
        this.X = new int[64];
        reset();
    }

    public SHA224Digest(SHA224Digest t) {
        super(t);
        this.X = new int[64];
        doCopy(t);
    }

    private void doCopy(SHA224Digest t) {
        super.copyIn(t);
        this.H1 = t.H1;
        this.H2 = t.H2;
        this.H3 = t.H3;
        this.H4 = t.H4;
        this.H5 = t.H5;
        this.H6 = t.H6;
        this.H7 = t.H7;
        this.H8 = t.H8;
        int[] iArr = t.X;
        System.arraycopy(iArr, 0, this.X, 0, iArr.length);
        this.xOff = t.xOff;
    }

    public SHA224Digest(byte[] encodedState) {
        super(encodedState);
        this.X = new int[64];
        this.H1 = Pack.bigEndianToInt(encodedState, 16);
        this.H2 = Pack.bigEndianToInt(encodedState, 20);
        this.H3 = Pack.bigEndianToInt(encodedState, 24);
        this.H4 = Pack.bigEndianToInt(encodedState, 28);
        this.H5 = Pack.bigEndianToInt(encodedState, 32);
        this.H6 = Pack.bigEndianToInt(encodedState, 36);
        this.H7 = Pack.bigEndianToInt(encodedState, 40);
        this.H8 = Pack.bigEndianToInt(encodedState, 44);
        this.xOff = Pack.bigEndianToInt(encodedState, 48);
        for (int i = 0; i != this.xOff; i++) {
            this.X[i] = Pack.bigEndianToInt(encodedState, (i * 4) + 52);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-224";
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 28;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] in, int inOff) {
        int inOff2 = inOff + 1;
        int inOff3 = inOff2 + 1;
        int n = (in[inOff] << Ascii.CAN) | ((in[inOff2] & UnsignedBytes.MAX_VALUE) << 16) | ((in[inOff3] & UnsignedBytes.MAX_VALUE) << 8) | (in[inOff3 + 1] & UnsignedBytes.MAX_VALUE);
        int[] iArr = this.X;
        int i = this.xOff;
        iArr[i] = n;
        int i2 = i + 1;
        this.xOff = i2;
        if (i2 == 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long bitLength) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.X;
        iArr[14] = (int) (bitLength >>> 32);
        iArr[15] = (int) (-1 & bitLength);
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int doFinal(byte[] out, int outOff) {
        finish();
        Pack.intToBigEndian(this.H1, out, outOff);
        Pack.intToBigEndian(this.H2, out, outOff + 4);
        Pack.intToBigEndian(this.H3, out, outOff + 8);
        Pack.intToBigEndian(this.H4, out, outOff + 12);
        Pack.intToBigEndian(this.H5, out, outOff + 16);
        Pack.intToBigEndian(this.H6, out, outOff + 20);
        Pack.intToBigEndian(this.H7, out, outOff + 24);
        reset();
        return 28;
    }

    @Override // com.android.org.bouncycastle.crypto.Digest, com.android.org.bouncycastle.crypto.digests.GeneralDigest
    public void reset() {
        super.reset();
        this.H1 = -1056596264;
        this.H2 = 914150663;
        this.H3 = 812702999;
        this.H4 = -150054599;
        this.H5 = -4191439;
        this.H6 = 1750603025;
        this.H7 = 1694076839;
        this.H8 = -1090891868;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.X;
            if (i != iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        for (int t = 16; t <= 63; t++) {
            int[] iArr = this.X;
            int Theta1 = Theta1(iArr[t - 2]);
            int[] iArr2 = this.X;
            iArr[t] = Theta1 + iArr2[t - 7] + Theta0(iArr2[t - 15]) + this.X[t - 16];
        }
        int a = this.H1;
        int b = this.H2;
        int c = this.H3;
        int d = this.H4;
        int e = this.H5;
        int f = this.H6;
        int g = this.H7;
        int h = this.H8;
        int t2 = 0;
        for (int i = 0; i < 8; i++) {
            int h2 = h + Sum1(e) + Ch(e, f, g) + K[t2] + this.X[t2];
            int d2 = d + h2;
            int h3 = h2 + Sum0(a) + Maj(a, b, c);
            int t3 = t2 + 1;
            int g2 = g + Sum1(d2) + Ch(d2, e, f) + K[t3] + this.X[t3];
            int c2 = c + g2;
            int g3 = g2 + Sum0(h3) + Maj(h3, a, b);
            int t4 = t3 + 1;
            int f2 = f + Sum1(c2) + Ch(c2, d2, e) + K[t4] + this.X[t4];
            int b2 = b + f2;
            int f3 = f2 + Sum0(g3) + Maj(g3, h3, a);
            int t5 = t4 + 1;
            int e2 = e + Sum1(b2) + Ch(b2, c2, d2) + K[t5] + this.X[t5];
            int a2 = a + e2;
            int e3 = e2 + Sum0(f3) + Maj(f3, g3, h3);
            int t6 = t5 + 1;
            int d3 = d2 + Sum1(a2) + Ch(a2, b2, c2) + K[t6] + this.X[t6];
            h = h3 + d3;
            d = d3 + Sum0(e3) + Maj(e3, f3, g3);
            int t7 = t6 + 1;
            int c3 = c2 + Sum1(h) + Ch(h, a2, b2) + K[t7] + this.X[t7];
            g = g3 + c3;
            c = c3 + Sum0(d) + Maj(d, e3, f3);
            int t8 = t7 + 1;
            int b3 = b2 + Sum1(g) + Ch(g, h, a2) + K[t8] + this.X[t8];
            f = f3 + b3;
            b = b3 + Sum0(c) + Maj(c, d, e3);
            int t9 = t8 + 1;
            int a3 = a2 + Sum1(f) + Ch(f, g, h) + K[t9] + this.X[t9];
            e = e3 + a3;
            a = a3 + Sum0(b) + Maj(b, c, d);
            t2 = t9 + 1;
        }
        this.H1 += a;
        this.H2 += b;
        this.H3 += c;
        this.H4 += d;
        this.H5 += e;
        this.H6 += f;
        this.H7 += g;
        this.H8 += h;
        this.xOff = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            this.X[i2] = 0;
        }
    }

    private int Ch(int x, int y, int z) {
        return (x & y) ^ ((~x) & z);
    }

    private int Maj(int x, int y, int z) {
        return ((x & y) ^ (x & z)) ^ (y & z);
    }

    private int Sum0(int x) {
        return (((x >>> 2) | (x << 30)) ^ ((x >>> 13) | (x << 19))) ^ ((x >>> 22) | (x << 10));
    }

    private int Sum1(int x) {
        return (((x >>> 6) | (x << 26)) ^ ((x >>> 11) | (x << 21))) ^ ((x >>> 25) | (x << 7));
    }

    private int Theta0(int x) {
        return (((x >>> 7) | (x << 25)) ^ ((x >>> 18) | (x << 14))) ^ (x >>> 3);
    }

    private int Theta1(int x) {
        return (((x >>> 17) | (x << 15)) ^ ((x >>> 19) | (x << 13))) ^ (x >>> 10);
    }

    @Override // com.android.org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA224Digest(this);
    }

    @Override // com.android.org.bouncycastle.util.Memoable
    public void reset(Memoable other) {
        doCopy((SHA224Digest) other);
    }

    @Override // com.android.org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] state = new byte[((this.xOff * 4) + 52)];
        super.populateState(state);
        Pack.intToBigEndian(this.H1, state, 16);
        Pack.intToBigEndian(this.H2, state, 20);
        Pack.intToBigEndian(this.H3, state, 24);
        Pack.intToBigEndian(this.H4, state, 28);
        Pack.intToBigEndian(this.H5, state, 32);
        Pack.intToBigEndian(this.H6, state, 36);
        Pack.intToBigEndian(this.H7, state, 40);
        Pack.intToBigEndian(this.H8, state, 44);
        Pack.intToBigEndian(this.xOff, state, 48);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.X[i], state, (i * 4) + 52);
        }
        return state;
    }
}
