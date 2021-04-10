package java.math;

/* access modifiers changed from: package-private */
public class BitLevel {
    private BitLevel() {
    }

    static int bitLength(BigInteger val) {
        val.prepareJavaRepresentation();
        if (val.sign == 0) {
            return 0;
        }
        int bLength = val.numberLength << 5;
        int highDigit = val.digits[val.numberLength - 1];
        if (val.sign < 0 && val.getFirstNonzeroDigit() == val.numberLength - 1) {
            highDigit--;
        }
        return bLength - Integer.numberOfLeadingZeros(highDigit);
    }

    static int bitCount(BigInteger val) {
        val.prepareJavaRepresentation();
        int bCount = 0;
        if (val.sign == 0) {
            return 0;
        }
        int i = val.getFirstNonzeroDigit();
        if (val.sign > 0) {
            while (i < val.numberLength) {
                bCount += Integer.bitCount(val.digits[i]);
                i++;
            }
            return bCount;
        }
        int bitCount = Integer.bitCount(-val.digits[i]);
        while (true) {
            bCount += bitCount;
            i++;
            if (i >= val.numberLength) {
                return (val.numberLength << 5) - bCount;
            }
            bitCount = Integer.bitCount(~val.digits[i]);
        }
    }

    static boolean testBit(BigInteger val, int n) {
        val.prepareJavaRepresentation();
        return (val.digits[n >> 5] & (1 << (n & 31))) != 0;
    }

    static boolean nonZeroDroppedBits(int numberOfBits, int[] digits) {
        int intCount = numberOfBits >> 5;
        int bitCount = numberOfBits & 31;
        int i = 0;
        while (i < intCount && digits[i] == 0) {
            i++;
        }
        return (i == intCount && (digits[i] << (32 - bitCount)) == 0) ? false : true;
    }

    static void shiftLeftOneBit(int[] result, int[] source, int srcLen) {
        int carry = 0;
        for (int i = 0; i < srcLen; i++) {
            int val = source[i];
            result[i] = (val << 1) | carry;
            carry = val >>> 31;
        }
        if (carry != 0) {
            result[srcLen] = carry;
        }
    }

    static BigInteger shiftLeftOneBit(BigInteger source) {
        source.prepareJavaRepresentation();
        int srcLen = source.numberLength;
        int resLen = srcLen + 1;
        int[] resDigits = new int[resLen];
        shiftLeftOneBit(resDigits, source.digits, srcLen);
        return new BigInteger(source.sign, resLen, resDigits);
    }

    static BigInteger shiftRight(BigInteger source, int count) {
        source.prepareJavaRepresentation();
        int intCount = count >> 5;
        int count2 = count & 31;
        if (intCount >= source.numberLength) {
            return source.sign < 0 ? BigInteger.MINUS_ONE : BigInteger.ZERO;
        }
        int resLength = source.numberLength - intCount;
        int[] resDigits = new int[(resLength + 1)];
        shiftRight(resDigits, resLength, source.digits, intCount, count2);
        if (source.sign < 0) {
            int i = 0;
            while (i < intCount && source.digits[i] == 0) {
                i++;
            }
            if (i < intCount || (count2 > 0 && (source.digits[i] << (32 - count2)) != 0)) {
                int i2 = 0;
                while (i2 < resLength && resDigits[i2] == -1) {
                    resDigits[i2] = 0;
                    i2++;
                }
                if (i2 == resLength) {
                    resLength++;
                }
                resDigits[i2] = resDigits[i2] + 1;
            }
        }
        return new BigInteger(source.sign, resLength, resDigits);
    }

    static boolean shiftRight(int[] result, int resultLen, int[] source, int intCount, int count) {
        boolean z;
        boolean allZero = true;
        int i = 0;
        while (true) {
            z = false;
            if (i >= intCount) {
                break;
            }
            if (source[i] == 0) {
                z = true;
            }
            allZero &= z;
            i++;
        }
        if (count == 0) {
            System.arraycopy((Object) source, intCount, (Object) result, 0, resultLen);
        } else {
            int leftShiftCount = 32 - count;
            if ((source[i] << leftShiftCount) == 0) {
                z = true;
            }
            allZero &= z;
            int i2 = 0;
            while (i2 < resultLen - 1) {
                result[i2] = (source[i2 + intCount] >>> count) | (source[(i2 + intCount) + 1] << leftShiftCount);
                i2++;
            }
            result[i2] = source[i2 + intCount] >>> count;
            int i3 = i2 + 1;
        }
        return allZero;
    }

    static BigInteger flipBit(BigInteger val, int n) {
        val.prepareJavaRepresentation();
        int resSign = val.sign == 0 ? 1 : val.sign;
        int intCount = n >> 5;
        int resLength = Math.max(intCount + 1, val.numberLength) + 1;
        int[] resDigits = new int[resLength];
        int bitNumber = 1 << (n & 31);
        System.arraycopy((Object) val.digits, 0, (Object) resDigits, 0, val.numberLength);
        if (val.sign >= 0) {
            resDigits[intCount] = resDigits[intCount] ^ bitNumber;
        } else if (intCount >= val.numberLength) {
            resDigits[intCount] = bitNumber;
        } else {
            int firstNonZeroDigit = val.getFirstNonzeroDigit();
            if (intCount > firstNonZeroDigit) {
                resDigits[intCount] = resDigits[intCount] ^ bitNumber;
            } else if (intCount < firstNonZeroDigit) {
                resDigits[intCount] = -bitNumber;
                int i = intCount + 1;
                while (i < firstNonZeroDigit) {
                    resDigits[i] = -1;
                    i++;
                }
                int i2 = resDigits[i];
                resDigits[i] = i2 - 1;
                resDigits[i] = i2;
            } else {
                resDigits[intCount] = -((-resDigits[intCount]) ^ bitNumber);
                if (resDigits[intCount] == 0) {
                    int i3 = intCount + 1;
                    while (resDigits[i3] == -1) {
                        resDigits[i3] = 0;
                        i3++;
                    }
                    resDigits[i3] = resDigits[i3] + 1;
                }
            }
        }
        return new BigInteger(resSign, resLength, resDigits);
    }
}
