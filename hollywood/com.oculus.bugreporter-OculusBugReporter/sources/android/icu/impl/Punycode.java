package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.StringPrepParseException;
import android.icu.text.UTF16;

public final class Punycode {
    private static final int BASE = 36;
    private static final int CAPITAL_A = 65;
    private static final int CAPITAL_Z = 90;
    private static final int DAMP = 700;
    private static final char DELIMITER = '-';
    private static final char HYPHEN = '-';
    private static final int INITIAL_BIAS = 72;
    private static final int INITIAL_N = 128;
    private static final int SKEW = 38;
    private static final int SMALL_A = 97;
    private static final int SMALL_Z = 122;
    private static final int TMAX = 26;
    private static final int TMIN = 1;
    private static final int ZERO = 48;
    static final int[] basicToDigit = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    private static int adaptBias(int delta, int length, boolean firstTime) {
        int delta2;
        if (firstTime) {
            delta2 = delta / 700;
        } else {
            delta2 = delta / 2;
        }
        int delta3 = delta2 + (delta2 / length);
        int count = 0;
        while (delta3 > 455) {
            delta3 /= 35;
            count += 36;
        }
        return ((delta3 * 36) / (delta3 + 38)) + count;
    }

    private static char asciiCaseMap(char b, boolean uppercase) {
        if (uppercase) {
            if ('a' > b || b > 'z') {
                return b;
            }
            return (char) (b - ' ');
        } else if ('A' > b || b > 'Z') {
            return b;
        } else {
            return (char) (b + ' ');
        }
    }

    private static char digitToBasic(int digit, boolean uppercase) {
        if (digit >= 26) {
            return (char) (digit + 22);
        }
        if (uppercase) {
            return (char) (digit + 65);
        }
        return (char) (digit + 97);
    }

    public static StringBuilder encode(CharSequence src, boolean[] caseFlags) throws StringPrepParseException {
        int i;
        int srcCPCount;
        int n;
        int srcLength = src.length();
        int[] cpBuffer = new int[srcLength];
        StringBuilder dest = new StringBuilder(srcLength);
        int srcCPCount2 = 0;
        int j = 0;
        while (true) {
            int i2 = 0;
            int i3 = 1;
            if (j < srcLength) {
                char c = src.charAt(j);
                if (isBasic(c)) {
                    srcCPCount = srcCPCount2 + 1;
                    cpBuffer[srcCPCount2] = 0;
                    dest.append(caseFlags != null ? asciiCaseMap(c, caseFlags[j]) : c);
                } else {
                    if (caseFlags != null && caseFlags[j]) {
                        i2 = 1;
                    }
                    int n2 = i2 << 31;
                    if (UTF16.isSurrogate(c)) {
                        if (!UTF16.isLeadSurrogate(c) || j + 1 >= srcLength) {
                            break;
                        }
                        char c2 = src.charAt(j + 1);
                        if (!UTF16.isTrailSurrogate(c2)) {
                            break;
                        }
                        j++;
                        n = n2 | UCharacter.getCodePoint(c, c2);
                    } else {
                        n = n2 | c;
                    }
                    srcCPCount = srcCPCount2 + 1;
                    cpBuffer[srcCPCount2] = n;
                }
                srcCPCount2 = srcCPCount;
                j++;
            } else {
                int basicLength = dest.length();
                if (basicLength > 0) {
                    dest.append('-');
                }
                int n3 = 128;
                int delta = 0;
                int bias = 72;
                int handledCPCount = basicLength;
                while (handledCPCount < srcCPCount2) {
                    int m = Integer.MAX_VALUE;
                    int j2 = 0;
                    while (true) {
                        i = Integer.MAX_VALUE;
                        if (j2 >= srcCPCount2) {
                            break;
                        }
                        int q = Integer.MAX_VALUE & cpBuffer[j2];
                        if (n3 <= q && q < m) {
                            m = q;
                        }
                        j2++;
                    }
                    if (m - n3 <= (Integer.MAX_VALUE - delta) / (handledCPCount + 1)) {
                        int delta2 = delta + ((m - n3) * (handledCPCount + 1));
                        int j3 = 0;
                        while (j3 < srcCPCount2) {
                            int q2 = cpBuffer[j3] & i;
                            if (q2 < m) {
                                delta2++;
                            } else if (q2 == m) {
                                int q3 = delta2;
                                int k = 36;
                                while (true) {
                                    int t = k - bias;
                                    if (t < i3) {
                                        t = 1;
                                    } else if (k >= bias + 26) {
                                        t = 26;
                                    }
                                    if (q3 < t) {
                                        break;
                                    }
                                    dest.append(digitToBasic(((q3 - t) % (36 - t)) + t, false));
                                    q3 = (q3 - t) / (36 - t);
                                    k += 36;
                                    i3 = 1;
                                }
                                dest.append(digitToBasic(q3, cpBuffer[j3] < 0));
                                bias = adaptBias(delta2, handledCPCount + 1, handledCPCount == basicLength);
                                handledCPCount++;
                                delta2 = 0;
                            }
                            j3++;
                            i3 = 1;
                            i = Integer.MAX_VALUE;
                        }
                        delta = delta2 + 1;
                        n3 = m + 1;
                        i3 = 1;
                    } else {
                        throw new IllegalStateException("Internal program error");
                    }
                }
                return dest;
            }
        }
        throw new StringPrepParseException("Illegal char found", 1);
    }

    private static boolean isBasic(int ch) {
        return ch < 128;
    }

    private static boolean isBasicUpperCase(int ch) {
        return 65 <= ch && ch >= 90;
    }

    private static boolean isSurrogate(int ch) {
        return (ch & -2048) == 55296;
    }

    public static StringBuilder decode(CharSequence src, boolean[] caseFlags) throws StringPrepParseException {
        int destCPCount;
        int codeUnitIndex;
        int bias;
        int srcLength = src.length();
        StringBuilder dest = new StringBuilder(src.length());
        int j = srcLength;
        while (j > 0) {
            j--;
            if (src.charAt(j) == '-') {
                break;
            }
        }
        int destCPCount2 = j;
        int basicLength = j;
        int j2 = 0;
        while (j2 < basicLength) {
            char b = src.charAt(j2);
            if (isBasic(b)) {
                dest.append(b);
                if (caseFlags != null && j2 < caseFlags.length) {
                    caseFlags[j2] = isBasicUpperCase(b);
                }
                j2++;
            } else {
                throw new StringPrepParseException("Illegal char found", 0);
            }
        }
        int n = 128;
        int i = 0;
        int bias2 = 72;
        int firstSupplementaryIndex = 1000000000;
        int in = basicLength > 0 ? basicLength + 1 : 0;
        while (in < srcLength) {
            int w = 1;
            int k = 36;
            while (in < srcLength) {
                int in2 = in + 1;
                int digit = basicToDigit[src.charAt(in) & 255];
                if (digit < 0) {
                    throw new StringPrepParseException("Invalid char found", 0);
                } else if (digit <= (Integer.MAX_VALUE - i) / w) {
                    i += digit * w;
                    int t = k - bias2;
                    if (t < 1) {
                        t = 1;
                    } else if (k >= bias2 + 26) {
                        t = 26;
                    }
                    if (digit < t) {
                        int destCPCount3 = destCPCount2 + 1;
                        int bias3 = adaptBias(i - i, destCPCount3, i == 0);
                        if (i / destCPCount3 <= Integer.MAX_VALUE - n) {
                            n += i / destCPCount3;
                            int i2 = i % destCPCount3;
                            if (n > 1114111 || isSurrogate(n)) {
                                throw new StringPrepParseException("Illegal char found", 1);
                            }
                            int cpLength = Character.charCount(n);
                            if (i2 <= firstSupplementaryIndex) {
                                codeUnitIndex = i2;
                                destCPCount = destCPCount3;
                                if (cpLength > 1) {
                                    firstSupplementaryIndex = codeUnitIndex;
                                } else {
                                    firstSupplementaryIndex++;
                                }
                            } else {
                                destCPCount = destCPCount3;
                                codeUnitIndex = dest.offsetByCodePoints(firstSupplementaryIndex, i2 - firstSupplementaryIndex);
                            }
                            if (caseFlags != null) {
                                bias = bias3;
                                if (dest.length() + cpLength <= caseFlags.length) {
                                    if (codeUnitIndex < dest.length()) {
                                        System.arraycopy((Object) caseFlags, codeUnitIndex, (Object) caseFlags, codeUnitIndex + cpLength, dest.length() - codeUnitIndex);
                                    }
                                    caseFlags[codeUnitIndex] = isBasicUpperCase(src.charAt(in2 - 1));
                                    if (cpLength == 2) {
                                        caseFlags[codeUnitIndex + 1] = false;
                                    }
                                }
                            } else {
                                bias = bias3;
                            }
                            if (cpLength == 1) {
                                dest.insert(codeUnitIndex, (char) n);
                            } else {
                                dest.insert(codeUnitIndex, UTF16.getLeadSurrogate(n));
                                dest.insert(codeUnitIndex + 1, UTF16.getTrailSurrogate(n));
                            }
                            i = i2 + 1;
                            j2 = j2;
                            in = in2;
                            srcLength = srcLength;
                            basicLength = basicLength;
                            destCPCount2 = destCPCount;
                            bias2 = bias;
                        } else {
                            throw new StringPrepParseException("Illegal char found", 1);
                        }
                    } else if (w <= Integer.MAX_VALUE / (36 - t)) {
                        w *= 36 - t;
                        k += 36;
                        j2 = j2;
                        in = in2;
                        srcLength = srcLength;
                        basicLength = basicLength;
                    } else {
                        throw new StringPrepParseException("Illegal char found", 1);
                    }
                } else {
                    throw new StringPrepParseException("Illegal char found", 1);
                }
            }
            throw new StringPrepParseException("Illegal char found", 1);
        }
        return dest;
    }
}
