package com.facebook.common.procread;

import android.util.Log;

public final class ByteParse {
    private static final char[] SPACES = {' ', '\t', '\n', 11, '\f', '\r'};
    private static int maxExponent = 511;
    private static double[] powersOf10 = {10.0d, 100.0d, 10000.0d, 1.0E8d, 1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};

    public static float strtof(byte[] string, int start, StrofResult outRes) {
        boolean sign;
        int fracExp;
        int pPtr;
        int exp;
        boolean expSign;
        double fraction;
        int stringLen = string.length;
        int endIdx = -1;
        boolean expSign2 = false;
        int exp2 = 0;
        int pPtr2 = start;
        while (pPtr2 < stringLen && isSpace((char) string[pPtr2])) {
            pPtr2++;
        }
        if (pPtr2 >= stringLen || string[pPtr2] != 45) {
            if (pPtr2 < stringLen && string[pPtr2] == 43) {
                pPtr2++;
            }
            sign = false;
        } else {
            sign = true;
            pPtr2++;
        }
        int decPt = -1;
        int mantSize = 0;
        while (pPtr2 < stringLen) {
            byte c = string[pPtr2];
            if (!isDigit((char) c)) {
                if (c != 46 || decPt >= 0) {
                    break;
                }
                decPt = mantSize;
            }
            pPtr2++;
            mantSize++;
        }
        int pPtr3 = pPtr2 - mantSize;
        if (decPt < 0) {
            decPt = mantSize;
        } else {
            mantSize--;
        }
        if (mantSize > 18) {
            fracExp = decPt - 18;
            mantSize = 18;
        } else {
            fracExp = decPt - mantSize;
        }
        if (mantSize == 0) {
            fraction = 0.0d;
            pPtr = start;
        } else {
            int frac1 = 0;
            while (mantSize > 9 && pPtr3 < stringLen) {
                byte c2 = string[pPtr3];
                pPtr3++;
                if (c2 == 46) {
                    c2 = string[pPtr3];
                    pPtr3++;
                }
                frac1 = (frac1 * 10) + (c2 - 48);
                mantSize--;
            }
            int frac2 = 0;
            while (mantSize > 0 && pPtr3 < stringLen) {
                byte c3 = string[pPtr3];
                pPtr3++;
                if (c3 == 46) {
                    c3 = string[pPtr3];
                    pPtr3++;
                }
                frac2 = (frac2 * 10) + (c3 - 48);
                mantSize--;
            }
            double fraction2 = (1.0E9d * ((double) frac1)) + ((double) frac2);
            pPtr = pPtr2;
            if (pPtr < stringLen && (string[pPtr] == 69 || string[pPtr] == 101)) {
                pPtr++;
                if (pPtr >= stringLen || string[pPtr] != 45) {
                    if (pPtr < stringLen && string[pPtr] == 43) {
                        pPtr++;
                    }
                    expSign2 = false;
                } else {
                    expSign2 = true;
                    pPtr++;
                }
                while (pPtr < stringLen && isDigit((char) string[pPtr])) {
                    exp2 = (exp2 * 10) + (string[pPtr] - 48);
                    pPtr++;
                }
            }
            if (expSign2) {
                exp = fracExp - exp2;
            } else {
                exp = exp2 + fracExp;
            }
            if (exp < 0) {
                expSign = true;
                exp = -exp;
            } else {
                expSign = false;
            }
            if (exp > maxExponent) {
                exp = maxExponent;
                Log.i("ByteParse", "strtof: Out of range");
            }
            double dblExp = 1.0d;
            int dPtr = 0;
            while (exp != 0) {
                if ((exp & 1) != 0) {
                    dblExp *= powersOf10[dPtr];
                }
                exp >>= 1;
                dPtr++;
            }
            if (expSign) {
                fraction = fraction2 / dblExp;
            } else {
                fraction = fraction2 * dblExp;
            }
        }
        if (-1 == -1) {
            endIdx = pPtr;
        }
        if (sign) {
            fraction *= -1.0d;
        }
        float result = (float) fraction;
        if (outRes != null) {
            outRes.result = result;
            outRes.end = endIdx;
        }
        return result;
    }

    public static long strtoll(byte[] string, int start, int givenBase, StrtollResult outRes) {
        byte c;
        byte c2;
        int strLen = string.length;
        if (start >= strLen) {
            return 0;
        }
        int sIdx = start;
        int endIdx = -1;
        int base = givenBase;
        boolean neg = false;
        do {
            c = string[sIdx];
            sIdx++;
            if (sIdx >= strLen) {
                break;
            }
        } while (isSpace((char) c));
        if (c == 45) {
            neg = true;
            c = string[sIdx];
            sIdx++;
        } else if (c == 43) {
            c = string[sIdx];
            sIdx++;
        }
        if ((base == 0 || base == 16) && c == 48 && sIdx < strLen && (string[sIdx] == 120 || string[sIdx] == 88)) {
            c = string[1];
            sIdx += 2;
            base = 16;
        }
        if (sIdx >= strLen) {
            return 0;
        }
        if (base == 0) {
            if (c == 48) {
                base = 8;
            } else {
                base = 10;
            }
        }
        long cutoff = neg ? Long.MAX_VALUE : Long.MAX_VALUE;
        int cutlim = (int) (cutoff % ((long) base));
        long cutoff2 = cutoff / ((long) base);
        long acc = 0;
        int any = 0;
        while (sIdx <= strLen) {
            if (isDigit((char) c)) {
                c2 = (byte) (c - 48);
            } else if (!isAlphabetic((char) c)) {
                break;
            } else {
                c2 = (byte) (c - (isUpperCase((char) c) ? (byte) 55 : 87));
            }
            if (c2 >= base) {
                break;
            }
            if (any < 0 || acc > cutoff2 || (acc == cutoff2 && c2 > cutlim)) {
                any = -1;
            } else {
                any = 1;
                acc = (acc * ((long) base)) + ((long) c2);
            }
            c = sIdx < strLen ? string[sIdx] : 0;
            sIdx++;
        }
        if (any < 0) {
            acc = neg ? Long.MIN_VALUE : Long.MAX_VALUE;
            Log.i("ByteParse", "strtoll: Out of range");
        } else if (neg) {
            acc = -acc;
        }
        if (-1 == -1) {
            if (any != 0) {
                endIdx = sIdx - 1;
            } else {
                endIdx = start;
            }
        }
        if (outRes == null) {
            return acc;
        }
        outRes.result = acc;
        outRes.end = endIdx;
        return acc;
    }

    private static boolean isSpace(char c) {
        for (int i = 0; i < SPACES.length; i++) {
            if (SPACES[i] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAlphabetic(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isUpperCase(char c) {
        return 'A' <= c && c <= 'Z';
    }
}
