package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;
    private static final int[] maxSafeDigits = new int[37];
    private static final long[] maxValueDivs = new long[37];
    private static final int[] maxValueMods = new int[37];

    private UnsignedLongs() {
    }

    private static long flip(long a) {
        return Long.MIN_VALUE ^ a;
    }

    public static int compare(long a, long b) {
        return Longs.compare(flip(a), flip(b));
    }

    public static long min(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long min = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            long next = flip(array[i]);
            if (next < min) {
                min = next;
            }
        }
        return flip(min);
    }

    public static long max(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long max = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            long next = flip(array[i]);
            if (next > max) {
                max = next;
            }
        }
        return flip(max);
    }

    public static String join(String separator, long... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(toString(array[0]));
        for (int i = 1; i < array.length; i++) {
            builder.append(separator);
            builder.append(toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                if (left[i] != right[i]) {
                    return UnsignedLongs.compare(left[i], right[i]);
                }
            }
            return left.length - right.length;
        }
    }

    public static long divide(long dividend, long divisor) {
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return 0;
            }
            return 1;
        } else if (dividend >= 0) {
            return dividend / divisor;
        } else {
            int i = 1;
            long quotient = ((dividend >>> 1) / divisor) << 1;
            if (compare(dividend - (quotient * divisor), divisor) < 0) {
                i = 0;
            }
            return ((long) i) + quotient;
        }
    }

    public static long remainder(long dividend, long divisor) {
        long j = 0;
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return dividend;
            }
            return dividend - divisor;
        } else if (dividend >= 0) {
            return dividend % divisor;
        } else {
            long rem = dividend - ((((dividend >>> 1) / divisor) << 1) * divisor);
            if (compare(rem, divisor) >= 0) {
                j = divisor;
            }
            return rem - j;
        }
    }

    public static long parseUnsignedLong(String s) {
        return parseUnsignedLong(s, 10);
    }

    public static long decode(String stringValue) {
        ParseRequest request = ParseRequest.fromString(stringValue);
        try {
            return parseUnsignedLong(request.rawValue, request.radix);
        } catch (NumberFormatException e) {
            NumberFormatException decodeException = new NumberFormatException("Error parsing value: " + stringValue);
            decodeException.initCause(e);
            throw decodeException;
        }
    }

    public static long parseUnsignedLong(String s, int radix) {
        Preconditions.checkNotNull(s);
        if (s.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (radix < 2 || radix > 36) {
            throw new NumberFormatException("illegal radix: " + radix);
        } else {
            int max_safe_pos = maxSafeDigits[radix] - 1;
            long value = 0;
            for (int pos = 0; pos < s.length(); pos++) {
                int digit = Character.digit(s.charAt(pos), radix);
                if (digit == -1) {
                    throw new NumberFormatException(s);
                } else if (pos <= max_safe_pos || !overflowInParse(value, digit, radix)) {
                    value = (((long) radix) * value) + ((long) digit);
                } else {
                    throw new NumberFormatException("Too large for unsigned long: " + s);
                }
            }
            return value;
        }
    }

    private static boolean overflowInParse(long current, int digit, int radix) {
        if (current < 0) {
            return true;
        }
        long[] jArr = maxValueDivs;
        if (current < jArr[radix]) {
            return false;
        }
        if (current <= jArr[radix] && digit <= maxValueMods[radix]) {
            return false;
        }
        return true;
    }

    public static String toString(long x) {
        return toString(x, 10);
    }

    public static String toString(long x, int radix) {
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", Integer.valueOf(radix));
        if (x == 0) {
            return "0";
        }
        char[] buf = new char[64];
        int i = buf.length;
        if (x < 0) {
            long quotient = divide(x, (long) radix);
            i--;
            buf[i] = Character.forDigit((int) (x - (((long) radix) * quotient)), radix);
            x = quotient;
        }
        while (x > 0) {
            i--;
            buf[i] = Character.forDigit((int) (x % ((long) radix)), radix);
            x /= (long) radix;
        }
        return new String(buf, i, buf.length - i);
    }

    static {
        BigInteger overflow = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            maxValueDivs[i] = divide(-1, (long) i);
            maxValueMods[i] = (int) remainder(-1, (long) i);
            maxSafeDigits[i] = overflow.toString(i).length() - 1;
        }
    }
}
