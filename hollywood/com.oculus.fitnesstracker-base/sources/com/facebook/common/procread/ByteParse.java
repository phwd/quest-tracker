package com.facebook.common.procread;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ByteParse {
    private static final char[] SPACES = {' ', '\t', '\n', 11, '\f', '\r'};
    private static int maxExponent = 511;
    private static double[] powersOf10 = {10.0d, 100.0d, 10000.0d, 1.0E8d, 1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    public static float strtof(byte[] bArr, int i, StrofResult strofResult) {
        boolean z;
        int i2;
        double d;
        boolean z2;
        int i3;
        int length = bArr.length;
        while (i < length && isSpace((char) bArr[i])) {
            i++;
        }
        int i4 = 0;
        boolean z3 = true;
        if (i >= length || bArr[i] != 45) {
            if (i < length && bArr[i] == 43) {
                i++;
            }
            z = false;
        } else {
            i++;
            z = true;
        }
        int i5 = -1;
        int i6 = 0;
        while (i < length) {
            byte b = bArr[i];
            if (!isDigit((char) b)) {
                if (b != 46 || i5 >= 0) {
                    break;
                }
                i5 = i6;
            }
            i++;
            i6++;
        }
        int i7 = i - i6;
        if (i5 < 0) {
            i5 = i6;
        } else {
            i6--;
        }
        if (i6 > 18) {
            i2 = i5 - 18;
            i6 = 18;
        } else {
            i2 = i5 - i6;
        }
        if (i6 == 0) {
            d = 0.0d;
        } else {
            int i8 = 0;
            while (i6 > 9 && i7 < length) {
                byte b2 = bArr[i7];
                i7++;
                if (b2 == 46) {
                    b2 = bArr[i7];
                    i7++;
                }
                i8 = (i8 * 10) + (b2 - 48);
                i6--;
            }
            int i9 = 0;
            while (i6 > 0 && i7 < length) {
                byte b3 = bArr[i7];
                i7++;
                if (b3 == 46) {
                    b3 = bArr[i7];
                    i7++;
                }
                i9 = (i9 * 10) + (b3 - 48);
                i6--;
            }
            double d2 = (double) i8;
            Double.isNaN(d2);
            double d3 = (double) i9;
            Double.isNaN(d3);
            double d4 = (d2 * 1.0E9d) + d3;
            if (i >= length || !(bArr[i] == 69 || bArr[i] == 101)) {
                i3 = 0;
                z2 = false;
            } else {
                int i10 = i + 1;
                if (i10 >= length || bArr[i10] != 45) {
                    if (i10 < length && bArr[i10] == 43) {
                        i10++;
                    }
                    i3 = 0;
                    z2 = false;
                } else {
                    i10++;
                    i3 = 0;
                    z2 = true;
                }
                while (i10 < length && isDigit((char) bArr[i10])) {
                    i3 = (i3 * 10) + (bArr[i10] - 48);
                    i10++;
                }
            }
            int i11 = z2 ? i2 - i3 : i2 + i3;
            if (i11 < 0) {
                i11 = -i11;
            } else {
                z3 = false;
            }
            int i12 = maxExponent;
            if (i11 > i12) {
                Log.i("ByteParse", "strtof: Out of range");
            } else {
                i12 = i11;
            }
            double d5 = 1.0d;
            while (i12 != 0) {
                if ((i12 & 1) != 0) {
                    d5 *= powersOf10[i4];
                }
                i12 >>= 1;
                i4++;
            }
            d = z3 ? d4 / d5 : d4 * d5;
        }
        if (z) {
            d *= -1.0d;
        }
        return (float) d;
    }

    public static long strtoll(byte[] bArr, int i, int i2, StrtollResult strtollResult) {
        byte b;
        boolean z;
        byte b2;
        int length = bArr.length;
        long j = 0;
        if (i >= length) {
            return 0;
        }
        do {
            b = bArr[i];
            i++;
            if (i >= length) {
                break;
            }
        } while (isSpace((char) b));
        if (b == 45) {
            b = bArr[i];
            i++;
            z = true;
        } else {
            if (b == 43) {
                b = bArr[i];
                i++;
            }
            z = false;
        }
        if (i >= length) {
            return 0;
        }
        char c = 0;
        while (i <= length) {
            char c2 = (char) b;
            if (isDigit(c2)) {
                b2 = 48;
            } else {
                if (!(('a' <= c2 && c2 <= 'z') || ('A' <= c2 && c2 <= 'Z'))) {
                    break;
                }
                b2 = 'A' <= c2 && c2 <= 'Z' ? (byte) 55 : 87;
            }
            byte b3 = (byte) (b - b2);
            if (b3 >= 10) {
                break;
            }
            if (c < 0 || j > 922337203685477580L || (j == 922337203685477580L && b3 > 7)) {
                c = 65535;
            } else {
                j = (j * 10) + ((long) b3);
                c = 1;
            }
            if (i < length) {
                b = bArr[i];
            } else {
                b = 0;
            }
            i++;
        }
        if (c >= 0) {
            return z ? -j : j;
        }
        long j2 = z ? Long.MIN_VALUE : Long.MAX_VALUE;
        Log.i("ByteParse", "strtoll: Out of range");
        return j2;
    }

    private static boolean isSpace(char c) {
        int i = 0;
        while (true) {
            char[] cArr = SPACES;
            if (i >= cArr.length) {
                return false;
            }
            if (cArr[i] == c) {
                return true;
            }
            i++;
        }
    }
}
