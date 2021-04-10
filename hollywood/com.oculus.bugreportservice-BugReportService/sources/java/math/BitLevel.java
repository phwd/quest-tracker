package java.math;

/* access modifiers changed from: package-private */
public class BitLevel {
    static int bitLength(BigInteger bigInteger) {
        bigInteger.prepareJavaRepresentation();
        int i = bigInteger.sign;
        if (i == 0) {
            return 0;
        }
        int i2 = bigInteger.numberLength;
        int i3 = i2 << 5;
        int i4 = bigInteger.digits[i2 - 1];
        if (i < 0 && bigInteger.getFirstNonzeroDigit() == bigInteger.numberLength - 1) {
            i4--;
        }
        return i3 - Integer.numberOfLeadingZeros(i4);
    }

    static boolean nonZeroDroppedBits(int i, int[] iArr) {
        int i2 = i >> 5;
        int i3 = i & 31;
        int i4 = 0;
        while (i4 < i2 && iArr[i4] == 0) {
            i4++;
        }
        if (i4 == i2 && (iArr[i4] << (32 - i3)) == 0) {
            return false;
        }
        return true;
    }

    static void shiftLeftOneBit(int[] iArr, int[] iArr2, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr2[i3];
            iArr[i3] = i2 | (i4 << 1);
            i2 = i4 >>> 31;
        }
        if (i2 != 0) {
            iArr[i] = i2;
        }
    }

    static BigInteger shiftLeftOneBit(BigInteger bigInteger) {
        bigInteger.prepareJavaRepresentation();
        int i = bigInteger.numberLength;
        int i2 = i + 1;
        int[] iArr = new int[i2];
        shiftLeftOneBit(iArr, bigInteger.digits, i);
        return new BigInteger(bigInteger.sign, i2, iArr);
    }

    static BigInteger shiftRight(BigInteger bigInteger, int i) {
        bigInteger.prepareJavaRepresentation();
        int i2 = i >> 5;
        int i3 = i & 31;
        int i4 = bigInteger.numberLength;
        if (i2 >= i4) {
            return bigInteger.sign < 0 ? BigInteger.MINUS_ONE : BigInteger.ZERO;
        }
        int i5 = i4 - i2;
        int i6 = i5 + 1;
        int[] iArr = new int[i6];
        shiftRight(iArr, i5, bigInteger.digits, i2, i3);
        if (bigInteger.sign < 0) {
            int i7 = 0;
            while (i7 < i2 && bigInteger.digits[i7] == 0) {
                i7++;
            }
            if (i7 < i2 || (i3 > 0 && (bigInteger.digits[i7] << (32 - i3)) != 0)) {
                int i8 = 0;
                while (i8 < i5 && iArr[i8] == -1) {
                    iArr[i8] = 0;
                    i8++;
                }
                if (i8 == i5) {
                    i5 = i6;
                }
                iArr[i8] = iArr[i8] + 1;
            }
        }
        return new BigInteger(bigInteger.sign, i5, iArr);
    }

    static boolean shiftRight(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        boolean z = true;
        while (i5 < i2) {
            z &= iArr2[i5] == 0;
            i5++;
        }
        if (i3 == 0) {
            System.arraycopy(iArr2, i2, iArr, 0, i);
        } else {
            int i6 = 32 - i3;
            z &= (iArr2[i5] << i6) == 0;
            while (i4 < i - 1) {
                int i7 = i4 + i2;
                iArr[i4] = (iArr2[i7 + 1] << i6) | (iArr2[i7] >>> i3);
                i4++;
            }
            iArr[i4] = iArr2[i2 + i4] >>> i3;
        }
        return z;
    }
}
