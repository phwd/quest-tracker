package com.google.common.primitives;

import com.facebook.systrace.Systrace;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

@Beta
@GwtCompatible
public final class UnsignedInts {
    static final long INT_MASK = 4294967295L;

    private UnsignedInts() {
    }

    static int flip(int value) {
        return Integer.MIN_VALUE ^ value;
    }

    public static int compare(int a, int b) {
        return Ints.compare(flip(a), flip(b));
    }

    public static long toLong(int value) {
        return ((long) value) & INT_MASK;
    }

    public static int checkedCast(long value) {
        Preconditions.checkArgument((value >> 32) == 0, "out of range: %s", value);
        return (int) value;
    }

    public static int saturatedCast(long value) {
        if (value <= 0) {
            return 0;
        }
        if (value >= Systrace.TRACE_TAG_COMPACTDISK) {
            return -1;
        }
        return (int) value;
    }

    public static int min(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int min = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            int next = flip(array[i]);
            if (next < min) {
                min = next;
            }
        }
        return flip(min);
    }

    public static int max(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int max = flip(array[0]);
        for (int i = 1; i < array.length; i++) {
            int next = flip(array[i]);
            if (next > max) {
                max = next;
            }
        }
        return flip(max);
    }

    public static String join(String separator, int... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(toString(array[0]));
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append(toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public int compare(int[] left, int[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                if (left[i] != right[i]) {
                    return UnsignedInts.compare(left[i], right[i]);
                }
            }
            return left.length - right.length;
        }

        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }
    }

    public static void sort(int[] array) {
        Preconditions.checkNotNull(array);
        sort(array, 0, array.length);
    }

    public static void sort(int[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = flip(array[i]);
        }
        Arrays.sort(array, fromIndex, toIndex);
        for (int i2 = fromIndex; i2 < toIndex; i2++) {
            array[i2] = flip(array[i2]);
        }
    }

    public static void sortDescending(int[] array) {
        Preconditions.checkNotNull(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = array[i] ^ Integer.MAX_VALUE;
        }
        Arrays.sort(array, fromIndex, toIndex);
        for (int i2 = fromIndex; i2 < toIndex; i2++) {
            array[i2] = array[i2] ^ Integer.MAX_VALUE;
        }
    }

    public static int divide(int dividend, int divisor) {
        return (int) (toLong(dividend) / toLong(divisor));
    }

    public static int remainder(int dividend, int divisor) {
        return (int) (toLong(dividend) % toLong(divisor));
    }

    @CanIgnoreReturnValue
    public static int decode(String stringValue) {
        ParseRequest request = ParseRequest.fromString(stringValue);
        try {
            return parseUnsignedInt(request.rawValue, request.radix);
        } catch (NumberFormatException e) {
            NumberFormatException decodeException = new NumberFormatException("Error parsing value: " + stringValue);
            decodeException.initCause(e);
            throw decodeException;
        }
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String s) {
        return parseUnsignedInt(s, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String string, int radix) {
        Preconditions.checkNotNull(string);
        long result = Long.parseLong(string, radix);
        if ((INT_MASK & result) == result) {
            return (int) result;
        }
        throw new NumberFormatException("Input " + string + " in base " + radix + " is not in the range of an unsigned integer");
    }

    public static String toString(int x) {
        return toString(x, 10);
    }

    public static String toString(int x, int radix) {
        return Long.toString(((long) x) & INT_MASK, radix);
    }
}
