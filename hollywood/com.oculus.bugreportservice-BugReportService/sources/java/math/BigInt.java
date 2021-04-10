package java.math;

import libcore.util.NativeAllocationRegistry;

/* access modifiers changed from: package-private */
public final class BigInt {
    private static NativeAllocationRegistry registry = NativeAllocationRegistry.createMalloced(BigInt.class.getClassLoader(), NativeBN.getNativeFinalizer());
    private transient long bignum = 0;

    BigInt() {
    }

    public String toString() {
        return decString();
    }

    /* access modifiers changed from: package-private */
    public boolean hasNativeBignum() {
        return this.bignum != 0;
    }

    private void makeValid() {
        if (this.bignum == 0) {
            this.bignum = NativeBN.BN_new();
            registry.registerNativeAllocation(this, this.bignum);
        }
    }

    private static BigInt newBigInt() {
        BigInt bigInt = new BigInt();
        bigInt.bignum = NativeBN.BN_new();
        registry.registerNativeAllocation(bigInt, bigInt.bignum);
        return bigInt;
    }

    static int cmp(BigInt bigInt, BigInt bigInt2) {
        return NativeBN.BN_cmp(bigInt.bignum, bigInt2.bignum);
    }

    /* access modifiers changed from: package-private */
    public void putCopy(BigInt bigInt) {
        makeValid();
        NativeBN.BN_copy(this.bignum, bigInt.bignum);
    }

    /* access modifiers changed from: package-private */
    public BigInt copy() {
        BigInt bigInt = new BigInt();
        bigInt.putCopy(this);
        return bigInt;
    }

    /* access modifiers changed from: package-private */
    public void putLongInt(long j) {
        makeValid();
        NativeBN.putLongInt(this.bignum, j);
    }

    /* access modifiers changed from: package-private */
    public void putULongInt(long j, boolean z) {
        makeValid();
        NativeBN.putULongInt(this.bignum, j, z);
    }

    private NumberFormatException invalidBigInteger(String str) {
        throw new NumberFormatException("Invalid BigInteger: " + str);
    }

    /* access modifiers changed from: package-private */
    public void putDecString(String str) {
        String checkString = checkString(str, 10);
        makeValid();
        if (NativeBN.BN_dec2bn(this.bignum, checkString) < checkString.length()) {
            invalidBigInteger(str);
            throw null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[LOOP:0: B:12:0x0027->B:18:0x0039, LOOP_START, PHI: r1 r9 
      PHI: (r1v1 boolean) = (r1v0 boolean), (r1v2 boolean) binds: [B:11:0x0025, B:18:0x0039] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v3 int) = (r9v2 int), (r9v4 int) binds: [B:11:0x0025, B:18:0x0039] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String checkString(java.lang.String r9, int r10) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x004b
            int r0 = r9.length()
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L_0x0020
            char r3 = r9.charAt(r1)
            r4 = 43
            if (r3 != r4) goto L_0x0019
            java.lang.String r9 = r9.substring(r2)
            int r0 = r0 + -1
            goto L_0x0020
        L_0x0019:
            r4 = 45
            if (r3 != r4) goto L_0x0020
            r3 = r9
            r9 = r2
            goto L_0x0022
        L_0x0020:
            r3 = r9
            r9 = r1
        L_0x0022:
            int r4 = r0 - r9
            r5 = 0
            if (r4 == 0) goto L_0x0047
        L_0x0027:
            if (r9 >= r0) goto L_0x0040
            char r4 = r3.charAt(r9)
            int r6 = java.lang.Character.digit(r4, r10)
            r7 = -1
            if (r6 == r7) goto L_0x003c
            r6 = 128(0x80, float:1.794E-43)
            if (r4 <= r6) goto L_0x0039
            r1 = r2
        L_0x0039:
            int r9 = r9 + 1
            goto L_0x0027
        L_0x003c:
            r8.invalidBigInteger(r3)
            throw r5
        L_0x0040:
            if (r1 == 0) goto L_0x0046
            java.lang.String r3 = toAscii(r3, r10)
        L_0x0046:
            return r3
        L_0x0047:
            r8.invalidBigInteger(r3)
            throw r5
        L_0x004b:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "s == null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: java.math.BigInt.checkString(java.lang.String, int):java.lang.String");
    }

    private static String toAscii(String str, int i) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            int digit = Character.digit(charAt, i);
            if (digit >= 0 && digit <= 9) {
                charAt = (char) (digit + 48);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void putBigEndian(byte[] bArr, boolean z) {
        makeValid();
        NativeBN.BN_bin2bn(bArr, bArr.length, z, this.bignum);
    }

    /* access modifiers changed from: package-private */
    public void putLittleEndianInts(int[] iArr, boolean z) {
        makeValid();
        NativeBN.litEndInts2bn(iArr, iArr.length, z, this.bignum);
    }

    /* access modifiers changed from: package-private */
    public void putBigEndianTwosComplement(byte[] bArr) {
        makeValid();
        NativeBN.twosComp2bn(bArr, bArr.length, this.bignum);
    }

    /* access modifiers changed from: package-private */
    public long longInt() {
        return NativeBN.longInt(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public String decString() {
        return NativeBN.BN_bn2dec(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public byte[] bigEndianMagnitude() {
        return NativeBN.BN_bn2bin(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public int[] littleEndianIntsMagnitude() {
        return NativeBN.bn2litEndInts(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public int sign() {
        return NativeBN.sign(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public void setSign(int i) {
        if (i > 0) {
            NativeBN.BN_set_negative(this.bignum, 0);
        } else if (i < 0) {
            NativeBN.BN_set_negative(this.bignum, 1);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean twosCompFitsIntoBytes(int i) {
        return (NativeBN.bitLength(this.bignum) + 7) / 8 <= i;
    }

    /* access modifiers changed from: package-private */
    public int bitLength() {
        return NativeBN.bitLength(this.bignum);
    }

    /* access modifiers changed from: package-private */
    public boolean isBitSet(int i) {
        return NativeBN.BN_is_bit_set(this.bignum, i);
    }

    static BigInt shift(BigInt bigInt, int i) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_shift(newBigInt.bignum, bigInt.bignum, i);
        return newBigInt;
    }

    /* access modifiers changed from: package-private */
    public void multiplyByPositiveInt(int i) {
        NativeBN.BN_mul_word(this.bignum, i);
    }

    static BigInt addition(BigInt bigInt, BigInt bigInt2) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_add(newBigInt.bignum, bigInt.bignum, bigInt2.bignum);
        return newBigInt;
    }

    /* access modifiers changed from: package-private */
    public void add(BigInt bigInt) {
        long j = this.bignum;
        NativeBN.BN_add(j, j, bigInt.bignum);
    }

    static BigInt subtraction(BigInt bigInt, BigInt bigInt2) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_sub(newBigInt.bignum, bigInt.bignum, bigInt2.bignum);
        return newBigInt;
    }

    static BigInt gcd(BigInt bigInt, BigInt bigInt2) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_gcd(newBigInt.bignum, bigInt.bignum, bigInt2.bignum);
        return newBigInt;
    }

    static BigInt product(BigInt bigInt, BigInt bigInt2) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_mul(newBigInt.bignum, bigInt.bignum, bigInt2.bignum);
        return newBigInt;
    }

    static BigInt bigExp(BigInt bigInt, BigInt bigInt2) {
        BigInt newBigInt = newBigInt();
        NativeBN.BN_exp(newBigInt.bignum, bigInt.bignum, bigInt2.bignum);
        return newBigInt;
    }

    static BigInt exp(BigInt bigInt, int i) {
        BigInt bigInt2 = new BigInt();
        bigInt2.putLongInt((long) i);
        return bigExp(bigInt, bigInt2);
    }

    static void division(BigInt bigInt, BigInt bigInt2, BigInt bigInt3, BigInt bigInt4) {
        long j;
        long j2 = 0;
        if (bigInt3 != null) {
            bigInt3.makeValid();
            j = bigInt3.bignum;
        } else {
            j = 0;
        }
        if (bigInt4 != null) {
            bigInt4.makeValid();
            j2 = bigInt4.bignum;
        }
        NativeBN.BN_div(j, j2, bigInt.bignum, bigInt2.bignum);
    }
}
