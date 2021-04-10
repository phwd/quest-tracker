package com.facebook.common.procread;

import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ByteParse {
    private static final char[] SPACES = {' ', '\t', '\n', 11, '\f', '\r'};
    private static int maxExponent = 511;
    private static double[] powersOf10 = {10.0d, 100.0d, 10000.0d, 1.0E8d, 1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};

    private static boolean isAlphabetic(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isUpperCase(char c) {
        return 'A' <= c && c <= 'Z';
    }

    private ByteParse() {
    }

    public static float strtof(byte[] bArr, int i, @Nullable StrofResult strofResult) {
        boolean z;
        int i2;
        double d;
        boolean z2;
        boolean z3;
        int i3;
        int length = bArr.length;
        int i4 = i;
        while (i4 < length && isSpace((char) bArr[i4])) {
            i4++;
        }
        if (i4 >= length || bArr[i4] != 45) {
            if (i4 < length && bArr[i4] == 43) {
                i4++;
            }
            z = false;
        } else {
            i4++;
            z = true;
        }
        int i5 = -1;
        int i6 = 0;
        while (i4 < length) {
            byte b = bArr[i4];
            if (!isDigit((char) b)) {
                if (b != 46 || i5 >= 0) {
                    break;
                }
                i5 = i6;
            }
            i4++;
            i6++;
        }
        int i7 = i4 - i6;
        if (i5 < 0) {
            i5 = i6;
        } else {
            i6--;
        }
        int i8 = 18;
        if (i6 > 18) {
            i2 = i5 - 18;
        } else {
            i2 = i5 - i6;
            i8 = i6;
        }
        if (i8 == 0) {
            d = 0.0d;
            i4 = i;
        } else {
            int i9 = 0;
            while (i8 > 9 && i7 < length) {
                byte b2 = bArr[i7];
                i7++;
                if (b2 == 46) {
                    b2 = bArr[i7];
                    i7++;
                }
                i9 = (i9 * 10) + (b2 - 48);
                i8--;
            }
            int i10 = 0;
            while (i8 > 0 && i7 < length) {
                byte b3 = bArr[i7];
                i7++;
                if (b3 == 46) {
                    b3 = bArr[i7];
                    i7++;
                }
                i10 = (i10 * 10) + (b3 - 48);
                i8--;
            }
            double d2 = (double) i9;
            Double.isNaN(d2);
            double d3 = (double) i10;
            Double.isNaN(d3);
            double d4 = (d2 * 1.0E9d) + d3;
            if (i4 >= length || !(bArr[i4] == 69 || bArr[i4] == 101)) {
                z3 = true;
                i3 = 0;
                z2 = false;
            } else {
                i4++;
                if (i4 >= length || bArr[i4] != 45) {
                    z3 = true;
                    if (i4 < length && bArr[i4] == 43) {
                        i4++;
                    }
                    i3 = 0;
                    z2 = false;
                } else {
                    z3 = true;
                    i4++;
                    i3 = 0;
                    z2 = true;
                }
                while (i4 < length && isDigit((char) bArr[i4])) {
                    i3 = (i3 * 10) + (bArr[i4] - 48);
                    i4++;
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
            int i13 = 0;
            while (i12 != 0) {
                if ((i12 & 1) != 0) {
                    d5 *= powersOf10[i13];
                }
                i12 >>= 1;
                i13++;
            }
            d = z3 ? d4 / d5 : d4 * d5;
        }
        if (z) {
            d *= -1.0d;
        }
        float f = (float) d;
        if (strofResult != null) {
            strofResult.result = f;
            strofResult.end = i4;
        }
        return f;
    }

    public static long strtoll(byte[] bArr, int i, int i2, @Nullable StrtollResult strtollResult) {
        byte b;
        boolean z;
        int i3;
        long j;
        int length = bArr.length;
        if (i >= length) {
            return 0;
        }
        int i4 = i;
        do {
            b = bArr[i4];
            i4++;
            if (i4 >= length) {
                break;
            }
        } while (isSpace((char) b));
        if (b == 45) {
            b = bArr[i4];
            i4++;
            z = true;
        } else {
            if (b == 43) {
                b = bArr[i4];
                i4++;
            }
            z = false;
        }
        int i5 = 16;
        if ((i2 == 0 || i2 == 16) && b == 48 && i4 < length && (bArr[i4] == 120 || bArr[i4] == 88)) {
            b = bArr[1];
            i4 += 2;
        } else {
            i5 = i2;
        }
        if (i4 >= length) {
            return 0;
        }
        if (i5 == 0) {
            i5 = b == 48 ? 8 : 10;
        }
        long j2 = (long) i5;
        long j3 = Long.MAX_VALUE;
        int i6 = (int) (Long.MAX_VALUE % j2);
        long j4 = Long.MAX_VALUE / j2;
        long j5 = 0;
        char c = 0;
        while (i4 <= length) {
            char c2 = (char) b;
            if (isDigit(c2)) {
                i3 = b - 48;
            } else if (!isAlphabetic(c2)) {
                break;
            } else {
                i3 = b - (isUpperCase(c2) ? (byte) 55 : 87);
            }
            byte b2 = (byte) i3;
            if (b2 >= i5) {
                break;
            }
            if (c < 0 || j5 > j4 || (j5 == j4 && b2 > i6)) {
                j = j4;
                c = 65535;
            } else {
                j = j4;
                j5 = (j5 * j2) + ((long) b2);
                c = 1;
            }
            if (i4 < length) {
                b = bArr[i4];
            } else {
                b = 0;
            }
            i4++;
            j4 = j;
        }
        if (c < 0) {
            if (z) {
                j3 = Long.MIN_VALUE;
            }
            j5 = j3;
            Log.i("ByteParse", "strtoll: Out of range");
        } else if (z) {
            j5 = -j5;
        }
        int i7 = c != 0 ? i4 - 1 : i;
        if (strtollResult != null) {
            strtollResult.result = j5;
            strtollResult.end = i7;
        }
        return j5;
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
