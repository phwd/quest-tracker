package java.math;

/* access modifiers changed from: package-private */
public class Conversion {
    static final int[] bigRadices = {Integer.MIN_VALUE, 1162261467, 1073741824, 1220703125, 362797056, 1977326743, 1073741824, 387420489, 1000000000, 214358881, 429981696, 815730721, 1475789056, 170859375, 268435456, 410338673, 612220032, 893871739, 1280000000, 1801088541, 113379904, 148035889, 191102976, 244140625, 308915776, 387420489, 481890304, 594823321, 729000000, 887503681, 1073741824, 1291467969, 1544804416, 1838265625, 60466176};
    static final int[] digitFitInInt = {-1, -1, 31, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};

    static String bigInteger2String(BigInteger bigInteger, int i) {
        int i2;
        bigInteger.prepareJavaRepresentation();
        int i3 = bigInteger.sign;
        int i4 = bigInteger.numberLength;
        int[] iArr = bigInteger.digits;
        if (i3 == 0) {
            return "0";
        }
        if (i4 == 1) {
            long j = ((long) iArr[i4 - 1]) & 4294967295L;
            if (i3 < 0) {
                j = -j;
            }
            return Long.toString(j, i);
        } else if (i == 10 || i < 2 || i > 36) {
            return bigInteger.toString();
        } else {
            int bitLength = ((int) ((((double) bigInteger.abs().bitLength()) / (Math.log((double) i) / Math.log(2.0d))) + ((double) (i3 < 0 ? 1 : 0)))) + 1;
            char[] cArr = new char[bitLength];
            if (i != 16) {
                int[] iArr2 = new int[i4];
                System.arraycopy(iArr, 0, iArr2, 0, i4);
                int i5 = digitFitInInt[i];
                int i6 = bigRadices[i - 2];
                int i7 = bitLength;
                while (true) {
                    int divideArrayByInt = Division.divideArrayByInt(iArr2, iArr2, i4, i6);
                    int i8 = i7;
                    do {
                        i8--;
                        cArr[i8] = Character.forDigit(divideArrayByInt % i, i);
                        divideArrayByInt /= i;
                        if (divideArrayByInt == 0) {
                            break;
                        }
                    } while (i8 != 0);
                    int i9 = (i5 - i7) + i8;
                    i2 = i8;
                    for (int i10 = 0; i10 < i9 && i2 > 0; i10++) {
                        i2--;
                        cArr[i2] = '0';
                    }
                    int i11 = i4 - 1;
                    while (i11 > 0 && iArr2[i11] == 0) {
                        i11--;
                    }
                    i4 = i11 + 1;
                    if (i4 == 1 && iArr2[0] == 0) {
                        break;
                    }
                    i7 = i2;
                }
            } else {
                i2 = bitLength;
                for (int i12 = 0; i12 < i4; i12++) {
                    for (int i13 = 0; i13 < 8 && i2 > 0; i13++) {
                        i2--;
                        cArr[i2] = Character.forDigit((iArr[i12] >> (i13 << 2)) & 15, 16);
                    }
                }
            }
            while (cArr[i2] == '0') {
                i2++;
            }
            if (i3 == -1) {
                i2--;
                cArr[i2] = '-';
            }
            new String(cArr, i2, bitLength - i2);
            throw null;
        }
    }

    static String toDecimalScaledString(long j, int i) {
        String str;
        long j2 = j;
        boolean z = j2 < 0;
        if (z) {
            j2 = -j2;
        }
        if (j2 == 0) {
            switch (i) {
                case 0:
                    return "0";
                case 1:
                    return "0.0";
                case 2:
                    return "0.00";
                case 3:
                    return "0.000";
                case 4:
                    return "0.0000";
                case 5:
                    return "0.00000";
                case 6:
                    return "0.000000";
                default:
                    StringBuilder sb = new StringBuilder();
                    if (i < 0) {
                        sb.append("0E+");
                    } else {
                        sb.append("0E");
                    }
                    if (i == Integer.MIN_VALUE) {
                        str = "2147483648";
                    } else {
                        str = Integer.toString(-i);
                    }
                    sb.append(str);
                    return sb.toString();
            }
        } else {
            char[] cArr = new char[19];
            int i2 = 18;
            while (true) {
                long j3 = j2 / 10;
                i2--;
                cArr[i2] = (char) ((int) ((j2 - (10 * j3)) + 48));
                if (j3 == 0) {
                    break;
                }
                j2 = j3;
            }
            long j4 = ((((long) 18) - ((long) i2)) - ((long) i)) - 1;
            if (i == 0) {
                if (z) {
                    i2--;
                    cArr[i2] = '-';
                }
                new String(cArr, i2, 18 - i2);
                throw null;
            } else if (i <= 0 || j4 < -6) {
                int i3 = i2 + 1;
                StringBuilder sb2 = new StringBuilder(34 - i3);
                if (z) {
                    sb2.append('-');
                }
                if (18 - i3 >= 1) {
                    sb2.append(cArr[i2]);
                    sb2.append('.');
                    sb2.append(cArr, i3, (18 - i2) - 1);
                } else {
                    sb2.append(cArr, i2, 18 - i2);
                }
                sb2.append('E');
                if (j4 > 0) {
                    sb2.append('+');
                }
                sb2.append(Long.toString(j4));
                return sb2.toString();
            } else if (j4 >= 0) {
                int i4 = ((int) j4) + i2;
                for (int i5 = 17; i5 >= i4; i5--) {
                    cArr[i5 + 1] = cArr[i5];
                }
                cArr[i4 + 1] = '.';
                if (z) {
                    i2--;
                    cArr[i2] = '-';
                }
                new String(cArr, i2, (18 - i2) + 1);
                throw null;
            } else {
                for (int i6 = 2; ((long) i6) < (-j4) + 1; i6++) {
                    i2--;
                    cArr[i2] = '0';
                }
                int i7 = i2 - 1;
                cArr[i7] = '.';
                int i8 = i7 - 1;
                cArr[i8] = '0';
                if (z) {
                    i8--;
                    cArr[i8] = '-';
                }
                new String(cArr, i8, 18 - i8);
                throw null;
            }
        }
    }

    static double bigInteger2Double(BigInteger bigInteger) {
        bigInteger.prepareJavaRepresentation();
        int i = bigInteger.numberLength;
        if (i < 2 || (i == 2 && bigInteger.digits[1] > 0)) {
            return (double) bigInteger.longValue();
        }
        if (bigInteger.numberLength > 32) {
            return bigInteger.sign > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        int bitLength = bigInteger.abs().bitLength();
        long j = (long) (bitLength - 1);
        int i2 = bitLength - 54;
        long longValue = bigInteger.abs().shiftRight(i2).longValue() & 9007199254740991L;
        if (j == 1023) {
            if (longValue == 9007199254740991L) {
                return bigInteger.sign > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
            if (longValue == 9007199254740990L) {
                return bigInteger.sign > 0 ? Double.MAX_VALUE : -1.7976931348623157E308d;
            }
        }
        if ((longValue & 1) == 1 && ((longValue & 2) == 2 || BitLevel.nonZeroDroppedBits(i2, bigInteger.digits))) {
            longValue += 2;
        }
        return Double.longBitsToDouble((longValue >> 1) | (bigInteger.sign < 0 ? Long.MIN_VALUE : 0) | (((j + 1023) << 52) & 9218868437227405312L));
    }
}
