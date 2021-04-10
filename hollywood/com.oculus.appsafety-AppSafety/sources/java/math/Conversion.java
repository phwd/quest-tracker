package java.math;

import dalvik.system.VMDebug;
import sun.misc.DoubleConsts;

/* access modifiers changed from: package-private */
public class Conversion {
    static final int[] bigRadices = {Integer.MIN_VALUE, 1162261467, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS, 1220703125, 362797056, 1977326743, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS, 387420489, 1000000000, 214358881, 429981696, 815730721, 1475789056, 170859375, VMDebug.KIND_THREAD_EXT_ALLOCATED_OBJECTS, 410338673, 612220032, 893871739, 1280000000, 1801088541, 113379904, 148035889, 191102976, 244140625, 308915776, 387420489, 481890304, 594823321, 729000000, 887503681, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS, 1291467969, 1544804416, 1838265625, 60466176};
    static final int[] digitFitInInt = {-1, -1, 31, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};

    private Conversion() {
    }

    /* JADX INFO: Multiple debug info for r13v6 int: [D('tempLen' int), D('bigRadix' int)] */
    static String bigInteger2String(BigInteger val, int radix) {
        int currentChar;
        int currentChar2;
        boolean z;
        val.prepareJavaRepresentation();
        int sign = val.sign;
        int numberLength = val.numberLength;
        int[] digits = val.digits;
        if (sign == 0) {
            return AndroidHardcodedSystemProperties.JAVA_VERSION;
        }
        if (numberLength == 1) {
            long v = ((long) digits[numberLength - 1]) & 4294967295L;
            if (sign < 0) {
                v = -v;
            }
            return Long.toString(v, radix);
        } else if (radix == 10 || radix < 2 || radix > 36) {
            return val.toString();
        } else {
            int resLengthInChars = ((int) ((((double) val.abs().bitLength()) / (Math.log((double) radix) / Math.log(2.0d))) + ((double) (sign < 0 ? 1 : 0)))) + 1;
            char[] result = new char[resLengthInChars];
            if (radix != 16) {
                int[] temp = new int[numberLength];
                System.arraycopy((Object) digits, 0, (Object) temp, 0, numberLength);
                int charsPerInt = digitFitInInt[radix];
                int bigRadix = bigRadices[radix - 2];
                currentChar = resLengthInChars;
                int tempLen = numberLength;
                while (true) {
                    int resDigit = Division.divideArrayByInt(temp, temp, tempLen, bigRadix);
                    while (true) {
                        currentChar--;
                        result[currentChar] = Character.forDigit(resDigit % radix, radix);
                        int i = resDigit / radix;
                        resDigit = i;
                        if (i == 0 || currentChar == 0) {
                            int delta = (charsPerInt - currentChar) + currentChar;
                        }
                    }
                    int delta2 = (charsPerInt - currentChar) + currentChar;
                    for (int i2 = 0; i2 < delta2 && currentChar > 0; i2++) {
                        currentChar--;
                        result[currentChar] = '0';
                    }
                    int i3 = tempLen - 1;
                    while (i3 > 0 && temp[i3] == 0) {
                        i3--;
                    }
                    tempLen = i3 + 1;
                    if (tempLen == 1) {
                        z = false;
                        if (temp[0] == 0) {
                            break;
                        }
                    } else {
                        z = false;
                    }
                }
            } else {
                currentChar = resLengthInChars;
                for (int i4 = 0; i4 < numberLength; i4++) {
                    for (int j = 0; j < 8 && currentChar > 0; j++) {
                        currentChar--;
                        result[currentChar] = Character.forDigit((digits[i4] >> (j << 2)) & 15, 16);
                    }
                }
            }
            while (result[currentChar] == '0') {
                currentChar++;
            }
            if (sign == -1) {
                int currentChar3 = currentChar - 1;
                result[currentChar3] = '-';
                currentChar2 = currentChar3;
            } else {
                currentChar2 = currentChar;
            }
            return new String(result, currentChar2, resLengthInChars - currentChar2);
        }
    }

    static String toDecimalScaledString(BigInteger val, int scale) {
        char[] result;
        int currentChar;
        val.prepareJavaRepresentation();
        int sign = val.sign;
        int numberLength = val.numberLength;
        int[] digits = val.digits;
        if (sign == 0) {
            switch (scale) {
                case 0:
                    return AndroidHardcodedSystemProperties.JAVA_VERSION;
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
                    StringBuilder result1 = new StringBuilder();
                    if (scale < 0) {
                        result1.append("0E+");
                    } else {
                        result1.append("0E");
                    }
                    result1.append(-scale);
                    return result1.toString();
            }
        } else {
            int resLengthInChars = (numberLength * 10) + 1 + 7;
            char[] result2 = new char[(resLengthInChars + 1)];
            int currentChar2 = resLengthInChars;
            if (numberLength == 1) {
                int highDigit = digits[0];
                if (highDigit < 0) {
                    long v = 4294967295L & ((long) highDigit);
                    while (true) {
                        v /= 10;
                        currentChar2--;
                        result = result2;
                        result[currentChar2] = (char) (((int) (v - (10 * v))) + 48);
                        if (v == 0) {
                            break;
                        }
                        result2 = result;
                    }
                } else {
                    result = result2;
                    int v2 = highDigit;
                    do {
                        v2 /= 10;
                        currentChar2--;
                        result[currentChar2] = (char) ((v2 - (v2 * 10)) + 48);
                    } while (v2 != 0);
                }
            } else {
                result = result2;
                int[] temp = new int[numberLength];
                int tempLen = numberLength;
                System.arraycopy((Object) digits, 0, (Object) temp, 0, tempLen);
                loop2:
                while (true) {
                    long result11 = 0;
                    for (int i1 = tempLen - 1; i1 >= 0; i1--) {
                        long res = divideLongByBillion((result11 << 32) + (((long) temp[i1]) & 4294967295L));
                        temp[i1] = (int) res;
                        result11 = (long) ((int) (res >> 32));
                    }
                    int resDigit = (int) result11;
                    do {
                        currentChar2--;
                        result[currentChar2] = (char) ((resDigit % 10) + 48);
                        int i = resDigit / 10;
                        resDigit = i;
                        if (i == 0) {
                            break;
                        }
                    } while (currentChar2 != 0);
                    int delta = (9 - currentChar2) + currentChar2;
                    currentChar = currentChar2;
                    for (int i2 = 0; i2 < delta && currentChar > 0; i2++) {
                        currentChar--;
                        result[currentChar] = '0';
                    }
                    int j = tempLen - 1;
                    while (temp[j] == 0) {
                        if (j == 0) {
                            break loop2;
                        }
                        j--;
                    }
                    tempLen = j + 1;
                    currentChar2 = currentChar;
                }
                currentChar2 = currentChar;
                while (result[currentChar2] == '0') {
                    currentChar2++;
                }
            }
            boolean negNumber = sign < 0;
            int exponent = ((resLengthInChars - currentChar2) - scale) - 1;
            if (scale == 0) {
                if (negNumber) {
                    currentChar2--;
                    result[currentChar2] = '-';
                }
                return new String(result, currentChar2, resLengthInChars - currentChar2);
            } else if (scale <= 0 || exponent < -6) {
                int startPoint = currentChar2 + 1;
                StringBuilder result12 = new StringBuilder((resLengthInChars + 16) - startPoint);
                if (negNumber) {
                    result12.append('-');
                }
                if (resLengthInChars - startPoint >= 1) {
                    result12.append(result[currentChar2]);
                    result12.append('.');
                    result12.append(result, currentChar2 + 1, (resLengthInChars - currentChar2) - 1);
                } else {
                    result12.append(result, currentChar2, resLengthInChars - currentChar2);
                }
                result12.append('E');
                if (exponent > 0) {
                    result12.append('+');
                }
                result12.append(Integer.toString(exponent));
                return result12.toString();
            } else if (exponent >= 0) {
                int insertPoint = currentChar2 + exponent;
                for (int j2 = resLengthInChars - 1; j2 >= insertPoint; j2--) {
                    result[j2 + 1] = result[j2];
                }
                result[insertPoint + 1] = '.';
                if (negNumber) {
                    currentChar2--;
                    result[currentChar2] = '-';
                }
                return new String(result, currentChar2, (resLengthInChars - currentChar2) + 1);
            } else {
                int j3 = 2;
                for (int i3 = 1; j3 < (-exponent) + i3; i3 = 1) {
                    currentChar2--;
                    result[currentChar2] = '0';
                    j3++;
                }
                int currentChar3 = currentChar2 - 1;
                result[currentChar3] = '.';
                int currentChar4 = currentChar3 - 1;
                result[currentChar4] = '0';
                if (negNumber) {
                    currentChar4--;
                    result[currentChar4] = '-';
                }
                return new String(result, currentChar4, resLengthInChars - currentChar4);
            }
        }
    }

    /* JADX INFO: Multiple debug info for r0v2 int: [D('startPoint' int), D('value' long)] */
    static String toDecimalScaledString(long value, int scale) {
        long value2 = value;
        boolean negNumber = value2 < 0;
        if (negNumber) {
            value2 = -value2;
        }
        if (value2 == 0) {
            switch (scale) {
                case 0:
                    return AndroidHardcodedSystemProperties.JAVA_VERSION;
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
                    StringBuilder result1 = new StringBuilder();
                    if (scale < 0) {
                        result1.append("0E+");
                    } else {
                        result1.append("0E");
                    }
                    result1.append(scale == Integer.MIN_VALUE ? "2147483648" : Integer.toString(-scale));
                    return result1.toString();
            }
        } else {
            char[] result = new char[(18 + 1)];
            int currentChar = 18;
            long v = value2;
            while (true) {
                v /= 10;
                currentChar--;
                result[currentChar] = (char) ((int) ((v - (10 * v)) + 48));
                if (v == 0) {
                    break;
                }
            }
            long exponent = ((((long) 18) - ((long) currentChar)) - ((long) scale)) - 1;
            if (scale == 0) {
                if (negNumber) {
                    currentChar--;
                    result[currentChar] = '-';
                }
                return new String(result, currentChar, 18 - currentChar);
            } else if (scale <= 0 || exponent < -6) {
                int startPoint = currentChar + 1;
                StringBuilder result12 = new StringBuilder((18 + 16) - startPoint);
                if (negNumber) {
                    result12.append('-');
                }
                if (18 - startPoint >= 1) {
                    result12.append(result[currentChar]);
                    result12.append('.');
                    result12.append(result, currentChar + 1, (18 - currentChar) - 1);
                } else {
                    result12.append(result, currentChar, 18 - currentChar);
                }
                result12.append('E');
                if (exponent > 0) {
                    result12.append('+');
                }
                result12.append(Long.toString(exponent));
                return result12.toString();
            } else if (exponent >= 0) {
                int insertPoint = ((int) exponent) + currentChar;
                for (int j = 18 - 1; j >= insertPoint; j--) {
                    result[j + 1] = result[j];
                }
                result[insertPoint + 1] = '.';
                if (negNumber) {
                    currentChar--;
                    result[currentChar] = '-';
                }
                return new String(result, currentChar, (18 - currentChar) + 1);
            } else {
                int j2 = 2;
                while (((long) j2) < (-exponent) + 1) {
                    currentChar--;
                    result[currentChar] = '0';
                    j2++;
                    value2 = value2;
                }
                int currentChar2 = currentChar - 1;
                result[currentChar2] = '.';
                int currentChar3 = currentChar2 - 1;
                result[currentChar3] = '0';
                if (negNumber) {
                    currentChar3--;
                    result[currentChar3] = '-';
                }
                return new String(result, currentChar3, 18 - currentChar3);
            }
        }
    }

    static long divideLongByBillion(long a) {
        long quot;
        long bLong;
        if (a >= 0) {
            quot = a / 1000000000;
            bLong = a % 1000000000;
        } else {
            long aPos = a >>> 1;
            quot = aPos / 500000000;
            bLong = ((aPos % 500000000) << 1) + (1 & a);
        }
        return (bLong << 32) | (4294967295L & quot);
    }

    static double bigInteger2Double(BigInteger val) {
        val.prepareJavaRepresentation();
        if (val.numberLength < 2 || (val.numberLength == 2 && val.digits[1] > 0)) {
            return (double) val.longValue();
        }
        if (val.numberLength > 32) {
            return val.sign > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        int bitLen = val.abs().bitLength();
        long exponent = (long) (bitLen - 1);
        int delta = bitLen - 54;
        long mantissa = val.abs().shiftRight(delta).longValue() & 9007199254740991L;
        if (exponent == 1023) {
            if (mantissa == 9007199254740991L) {
                return val.sign > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
            if (mantissa == 9007199254740990L) {
                return val.sign > 0 ? Double.MAX_VALUE : -1.7976931348623157E308d;
            }
        }
        if ((mantissa & 1) == 1 && ((mantissa & 2) == 2 || BitLevel.nonZeroDroppedBits(delta, val.digits))) {
            mantissa += 2;
        }
        return Double.longBitsToDouble((val.sign < 0 ? Long.MIN_VALUE : 0) | (((exponent + 1023) << 52) & DoubleConsts.EXP_BIT_MASK) | (mantissa >> 1));
    }
}
