package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Comparator;
import sun.misc.Unsafe;

@GwtIncompatible
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    private UnsignedBytes() {
    }

    public static int toInt(byte value) {
        return value & MAX_VALUE;
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long value) {
        Preconditions.checkArgument((value >> 8) == 0, "out of range: %s", value);
        return (byte) ((int) value);
    }

    public static byte saturatedCast(long value) {
        if (value > ((long) toInt((byte) -1))) {
            return -1;
        }
        if (value < 0) {
            return 0;
        }
        return (byte) ((int) value);
    }

    public static int compare(byte a, byte b) {
        return toInt(a) - toInt(b);
    }

    public static byte min(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        int min = toInt(array[0]);
        for (int i = 1; i < array.length; i++) {
            int next = toInt(array[i]);
            if (next < min) {
                min = next;
            }
        }
        return (byte) min;
    }

    public static byte max(byte... array) {
        Preconditions.checkArgument(array.length > 0);
        int max = toInt(array[0]);
        for (int i = 1; i < array.length; i++) {
            int next = toInt(array[i]);
            if (next > max) {
                max = next;
            }
        }
        return (byte) max;
    }

    @Beta
    public static String toString(byte x) {
        return toString(x, 10);
    }

    @Beta
    public static String toString(byte x, int radix) {
        Preconditions.checkArgument(radix >= 2 && radix <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", radix);
        return Integer.toString(toInt(x), radix);
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String string) {
        return parseUnsignedByte(string, 10);
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String string, int radix) {
        int parse = Integer.parseInt((String) Preconditions.checkNotNull(string), radix);
        if ((parse >> 8) == 0) {
            return (byte) parse;
        }
        throw new NumberFormatException("out of range: " + parse);
    }

    public static String join(String separator, byte... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * (separator.length() + 3));
        builder.append(toInt(array[0]));
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append(toString(array[i]));
        }
        return builder.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    @VisibleForTesting
    static class LexicographicalComparatorHolder {
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();
        static final String UNSAFE_COMPARATOR_NAME = (LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator");

        LexicographicalComparatorHolder() {
        }

        @VisibleForTesting
        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
            static final Unsafe theUnsafe = getUnsafe();

            static {
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || BYTE_ARRAY_BASE_OFFSET % 8 != 0 || theUnsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
                }
            }

            private static Unsafe getUnsafe() {
                try {
                    return Unsafe.getUnsafe();
                } catch (SecurityException e) {
                    try {
                        return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                            /* class com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.AnonymousClass1 */

                            @Override // java.security.PrivilegedExceptionAction
                            public Unsafe run() throws Exception {
                                Field[] declaredFields = Unsafe.class.getDeclaredFields();
                                for (Field f : declaredFields) {
                                    f.setAccessible(true);
                                    Object x = f.get(null);
                                    if (Unsafe.class.isInstance(x)) {
                                        return (Unsafe) Unsafe.class.cast(x);
                                    }
                                }
                                throw new NoSuchFieldError("the Unsafe");
                            }
                        });
                    } catch (PrivilegedActionException e2) {
                        throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                    }
                }
            }

            public int compare(byte[] left, byte[] right) {
                int minLength = Math.min(left.length, right.length);
                int strideLimit = minLength & -8;
                int i = 0;
                while (i < strideLimit) {
                    long lw = theUnsafe.getLong(left, ((long) BYTE_ARRAY_BASE_OFFSET) + ((long) i));
                    long rw = theUnsafe.getLong(right, ((long) BYTE_ARRAY_BASE_OFFSET) + ((long) i));
                    if (lw == rw) {
                        i += 8;
                    } else if (BIG_ENDIAN) {
                        return UnsignedLongs.compare(lw, rw);
                    } else {
                        int n = Long.numberOfTrailingZeros(lw ^ rw) & -8;
                        return ((int) ((lw >>> n) & 255)) - ((int) ((rw >>> n) & 255));
                    }
                }
                while (i < minLength) {
                    int result = UnsignedBytes.compare(left[i], right[i]);
                    if (result != 0) {
                        return result;
                    }
                    i++;
                }
                return left.length - right.length;
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }
        }

        /* access modifiers changed from: package-private */
        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public int compare(byte[] left, byte[] right) {
                int minLength = Math.min(left.length, right.length);
                for (int i = 0; i < minLength; i++) {
                    int result = UnsignedBytes.compare(left[i], right[i]);
                    if (result != 0) {
                        return result;
                    }
                }
                return left.length - right.length;
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        static Comparator<byte[]> getBestComparator() {
            try {
                return (Comparator) Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants()[0];
            } catch (Throwable th) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    private static byte flip(byte b) {
        return (byte) (b ^ MAX_POWER_OF_TWO);
    }

    public static void sort(byte[] array) {
        Preconditions.checkNotNull(array);
        sort(array, 0, array.length);
    }

    public static void sort(byte[] array, int fromIndex, int toIndex) {
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

    public static void sortDescending(byte[] array) {
        Preconditions.checkNotNull(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = (byte) (array[i] ^ Ascii.DEL);
        }
        Arrays.sort(array, fromIndex, toIndex);
        for (int i2 = fromIndex; i2 < toIndex; i2++) {
            array[i2] = (byte) (array[i2] ^ Ascii.DEL);
        }
    }
}
