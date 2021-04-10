package java.lang;

public final class Long extends Number implements Comparable {
    public static final Class TYPE = Class.getPrimitiveClass("long");
    private static final long serialVersionUID = 4290774380558885855L;
    private final long value;

    public static int bitCount(long j) {
        long j2 = j - ((j >>> 1) & 6148914691236517205L);
        long j3 = (j2 & 3689348814741910323L) + ((j2 >>> 2) & 3689348814741910323L);
        long j4 = 1085102592571150095L & (j3 + (j3 >>> 4));
        long j5 = j4 + (j4 >>> 8);
        long j6 = j5 + (j5 >>> 16);
        return ((int) (j6 + (j6 >>> 32))) & 127;
    }

    public static int compare(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    public static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static long highestOneBit(long j) {
        long j2 = j | (j >> 1);
        long j3 = j2 | (j2 >> 2);
        long j4 = j3 | (j3 >> 4);
        long j5 = j4 | (j4 >> 8);
        long j6 = j5 | (j5 >> 16);
        long j7 = j6 | (j6 >> 32);
        return j7 - (j7 >>> 1);
    }

    public static int numberOfLeadingZeros(long j) {
        if (j == 0) {
            return 64;
        }
        int i = 1;
        int i2 = (int) (j >>> 32);
        if (i2 == 0) {
            i = 33;
            i2 = (int) j;
        }
        if ((i2 >>> 16) == 0) {
            i += 16;
            i2 <<= 16;
        }
        if ((i2 >>> 24) == 0) {
            i += 8;
            i2 <<= 8;
        }
        if ((i2 >>> 28) == 0) {
            i += 4;
            i2 <<= 4;
        }
        if ((i2 >>> 30) == 0) {
            i += 2;
            i2 <<= 2;
        }
        return i - (i2 >>> 31);
    }

    public static int numberOfTrailingZeros(long j) {
        if (j == 0) {
            return 64;
        }
        int i = 63;
        int i2 = (int) j;
        if (i2 != 0) {
            i = 31;
        } else {
            i2 = (int) (j >>> 32);
        }
        int i3 = i2 << 16;
        if (i3 != 0) {
            i -= 16;
        } else {
            i3 = i2;
        }
        int i4 = i3 << 8;
        if (i4 != 0) {
            i -= 8;
            i3 = i4;
        }
        int i5 = i3 << 4;
        if (i5 != 0) {
            i -= 4;
            i3 = i5;
        }
        int i6 = i3 << 2;
        if (i6 != 0) {
            i -= 2;
            i3 = i6;
        }
        return i - ((i3 << 1) >>> 31);
    }

    public static long reverseBytes(long j) {
        long j2 = ((j >>> 8) & 71777214294589695L) | ((j & 71777214294589695L) << 8);
        return (j2 >>> 48) | (j2 << 48) | ((j2 & 4294901760L) << 16) | (4294901760L & (j2 >>> 16));
    }

    public static int signum(long j) {
        return (int) (((-j) >>> 63) | (j >> 63));
    }

    static int stringSize(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    public static String toString(long j, int i) {
        if (i < 2 || i > 36) {
            i = 10;
        }
        if (i == 10) {
            return toString(j);
        }
        char[] cArr = new char[65];
        int i2 = 64;
        boolean z = j < 0;
        if (!z) {
            j = -j;
        }
        while (j <= ((long) (-i))) {
            long j2 = (long) i;
            cArr[i2] = Integer.digits[(int) (-(j % j2))];
            j /= j2;
            i2--;
        }
        cArr[i2] = Integer.digits[(int) (-j)];
        if (z) {
            i2--;
            cArr[i2] = '-';
        }
        new String(cArr, i2, 65 - i2);
        throw null;
    }

    public static String toHexString(long j) {
        toUnsignedString0(j, 4);
        throw null;
    }

    public static String toOctalString(long j) {
        toUnsignedString0(j, 3);
        throw null;
    }

    static String toUnsignedString0(long j, int i) {
        int max = Math.max(((64 - numberOfLeadingZeros(j)) + (i - 1)) / i, 1);
        char[] cArr = new char[max];
        formatUnsignedLong(j, i, cArr, 0, max);
        new String(cArr);
        throw null;
    }

    static int formatUnsignedLong(long j, int i, char[] cArr, int i2, int i3) {
        int i4 = (1 << i) - 1;
        do {
            i3--;
            cArr[i2 + i3] = Integer.digits[((int) j) & i4];
            j >>>= i;
            if (j == 0) {
                break;
            }
        } while (i3 > 0);
        return i3;
    }

    public static String toString(long j) {
        if (j == Long.MIN_VALUE) {
            return "-9223372036854775808";
        }
        int stringSize = j < 0 ? stringSize(-j) + 1 : stringSize(j);
        char[] cArr = new char[stringSize];
        getChars(j, stringSize, cArr);
        new String(cArr);
        throw null;
    }

    static void getChars(long j, int i, char[] cArr) {
        char c;
        if (j < 0) {
            c = '-';
            j = -j;
        } else {
            c = 0;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            int i2 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            int i3 = i - 1;
            cArr[i3] = Integer.DigitOnes[i2];
            i = i3 - 1;
            cArr[i] = Integer.DigitTens[i2];
            j = j2;
        }
        int i4 = (int) j;
        while (i4 >= 65536) {
            int i5 = i4 / 100;
            int i6 = i4 - (((i5 << 6) + (i5 << 5)) + (i5 << 2));
            int i7 = i - 1;
            cArr[i7] = Integer.DigitOnes[i6];
            i = i7 - 1;
            cArr[i] = Integer.DigitTens[i6];
            i4 = i5;
        }
        while (true) {
            int i8 = (52429 * i4) >>> 19;
            i--;
            cArr[i] = Integer.digits[i4 - ((i8 << 3) + (i8 << 1))];
            if (i8 == 0) {
                break;
            }
            i4 = i8;
        }
        if (c != 0) {
            cArr[i - 1] = c;
        }
    }

    public static long parseLong(String str, int i) {
        if (str == null) {
            throw new NumberFormatException("null");
        } else if (i < 2) {
            throw new NumberFormatException("radix " + i + " less than Character.MIN_RADIX");
        } else if (i <= 36) {
            long j = 0;
            int length = str.length();
            long j2 = -9223372036854775807L;
            if (length > 0) {
                boolean z = false;
                char charAt = str.charAt(0);
                int i2 = 1;
                if (charAt < '0') {
                    if (charAt == '-') {
                        j2 = Long.MIN_VALUE;
                        z = true;
                    } else if (charAt != '+') {
                        throw NumberFormatException.forInputString(str);
                    }
                    if (length == 1) {
                        throw NumberFormatException.forInputString(str);
                    }
                } else {
                    i2 = 0;
                }
                long j3 = (long) i;
                long j4 = j2 / j3;
                while (i2 < length) {
                    int i3 = i2 + 1;
                    int digit = Character.digit(str.charAt(i2), i);
                    if (digit < 0) {
                        throw NumberFormatException.forInputString(str);
                    } else if (j >= j4) {
                        long j5 = j * j3;
                        long j6 = (long) digit;
                        if (j5 >= j2 + j6) {
                            j = j5 - j6;
                            i2 = i3;
                        } else {
                            throw NumberFormatException.forInputString(str);
                        }
                    } else {
                        throw NumberFormatException.forInputString(str);
                    }
                }
                return z ? j : -j;
            }
            throw NumberFormatException.forInputString(str);
        } else {
            throw new NumberFormatException("radix " + i + " greater than Character.MAX_RADIX");
        }
    }

    public static long parseLong(String str) {
        return parseLong(str, 10);
    }

    /* access modifiers changed from: private */
    public static class LongCache {
        static final Long[] cache = new Long[256];

        static {
            int i = 0;
            while (true) {
                Long[] lArr = cache;
                if (i < lArr.length) {
                    lArr[i] = new Long((long) (i - 128));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static Long valueOf(long j) {
        if (j < -128 || j > 127) {
            return new Long(j);
        }
        return LongCache.cache[((int) j) + 128];
    }

    public Long(long j) {
        this.value = j;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) this.value;
    }

    public String toString() {
        return toString(this.value);
    }

    public int hashCode() {
        return hashCode(this.value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Long) || this.value != ((Long) obj).longValue()) {
            return false;
        }
        return true;
    }

    public int compareTo(Long l) {
        return compare(this.value, l.value);
    }

    public static int compareUnsigned(long j, long j2) {
        return compare(j - Long.MIN_VALUE, j2 - Long.MIN_VALUE);
    }
}
