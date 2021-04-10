package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    private Longs() {
    }

    public static int hashCode(long value) {
        return (int) ((value >>> 32) ^ value);
    }

    public static int compare(long a, long b) {
        if (a < b) {
            return -1;
        }
        return a > b ? 1 : 0;
    }

    public static boolean contains(long[] array, long target) {
        for (long value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(long[] array, long target) {
        return indexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(long[] array, long target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(long[] array, long[] target) {
        Preconditions.checkNotNull(array, "array");
        Preconditions.checkNotNull(target, "target");
        if (target.length == 0) {
            return 0;
        }
        for (int i = 0; i < (array.length - target.length) + 1; i++) {
            for (int j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                }
            }
            return i;
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(long[] array, long target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static long min(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static long max(long... array) {
        Preconditions.checkArgument(array.length > 0);
        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static long[] concat(long[]... arrays) {
        int length = 0;
        for (long[] array : arrays) {
            length += array.length;
        }
        long[] result = new long[length];
        int pos = 0;
        for (long[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    public static byte[] toByteArray(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) ((int) (255 & value));
            value >>= 8;
        }
        return result;
    }

    public static long fromByteArray(byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 8, "array too small: %s < %s", Integer.valueOf(bytes.length), 8);
        return fromBytes(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
    }

    public static long fromBytes(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((((long) b1) & 255) << 56) | ((((long) b2) & 255) << 48) | ((((long) b3) & 255) << 40) | ((((long) b4) & 255) << 32) | ((((long) b5) & 255) << 24) | ((((long) b6) & 255) << 16) | ((((long) b7) & 255) << 8) | (255 & ((long) b8));
    }

    @Beta
    public static Long tryParse(String string) {
        if (((String) Preconditions.checkNotNull(string)).isEmpty()) {
            return null;
        }
        int index = 0;
        boolean negative = string.charAt(0) == '-';
        if (negative) {
            index = 1;
        }
        if (index == string.length()) {
            return null;
        }
        int index2 = index + 1;
        int digit = string.charAt(index) - 48;
        if (digit < 0 || digit > 9) {
            return null;
        }
        long accum = (long) (-digit);
        while (index2 < string.length()) {
            int index3 = index2 + 1;
            int digit2 = string.charAt(index2) - 48;
            if (digit2 < 0 || digit2 > 9 || accum < -922337203685477580L) {
                return null;
            }
            long accum2 = accum * 10;
            if (accum2 < ((long) digit2) - Long.MIN_VALUE) {
                return null;
            }
            accum = accum2 - ((long) digit2);
            index2 = index3;
        }
        if (negative) {
            return Long.valueOf(accum);
        }
        if (accum == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-accum);
    }

    private static final class LongConverter extends Converter<String, Long> implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1;

        private LongConverter() {
        }

        /* access modifiers changed from: protected */
        public Long doForward(String value) {
            return Long.decode(value);
        }

        /* access modifiers changed from: protected */
        public String doBackward(Long value) {
            return value.toString();
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    @Beta
    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] ensureCapacity(long[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", Integer.valueOf(minLength));
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", Integer.valueOf(padding));
        if (array.length < minLength) {
            return copyOf(array, minLength + padding);
        }
        return array;
    }

    private static long[] copyOf(long[] original, int length) {
        long[] copy = new long[length];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, length));
        return copy;
    }

    public static String join(String separator, long... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 10);
        builder.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator);
            builder.append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public int compare(long[] left, long[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                int result = Longs.compare(left[i], right[i]);
                if (result != 0) {
                    return result;
                }
            }
            return left.length - right.length;
        }
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        long[] array = new long[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((Number) Preconditions.checkNotNull(boxedArray[i])).longValue();
        }
        return array;
    }

    public static List<Long> asList(long... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new LongArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final long[] array;
        final int end;
        final int start;

        LongArrayAsList(long[] array2) {
            this(array2, 0, array2.length);
        }

        LongArrayAsList(long[] array2, int start2, int end2) {
            this.array = array2;
            this.start = start2;
            this.end = end2;
        }

        public int size() {
            return this.end - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.List, java.util.AbstractList
        public Long get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Long.valueOf(this.array[this.start + index]);
        }

        public boolean contains(Object target) {
            return (target instanceof Long) && Longs.indexOf(this.array, ((Long) target).longValue(), this.start, this.end) != -1;
        }

        public int indexOf(Object target) {
            int i;
            if (!(target instanceof Long) || (i = Longs.indexOf(this.array, ((Long) target).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public int lastIndexOf(Object target) {
            int i;
            if (!(target instanceof Long) || (i = Longs.lastIndexOf(this.array, ((Long) target).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public Long set(int index, Long element) {
            Preconditions.checkElementIndex(index, size());
            long[] jArr = this.array;
            int i = this.start;
            long oldValue = jArr[i + index];
            jArr[i + index] = ((Long) Preconditions.checkNotNull(element)).longValue();
            return Long.valueOf(oldValue);
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Long> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            long[] jArr = this.array;
            int i = this.start;
            return new LongArrayAsList(jArr, i + fromIndex, i + toIndex);
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof LongArrayAsList)) {
                return super.equals(object);
            }
            LongArrayAsList that = (LongArrayAsList) object;
            int size = size();
            if (that.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (this.array[this.start + i] != that.array[that.start + i]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int result = 1;
            for (int i = this.start; i < this.end; i++) {
                result = (result * 31) + Longs.hashCode(this.array[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(size() * 10);
            builder.append('[');
            builder.append(this.array[this.start]);
            int i = this.start;
            while (true) {
                i++;
                if (i < this.end) {
                    builder.append(", ");
                    builder.append(this.array[i]);
                } else {
                    builder.append(']');
                    return builder.toString();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public long[] toLongArray() {
            int size = size();
            long[] result = new long[size];
            System.arraycopy(this.array, this.start, result, 0, size);
            return result;
        }
    }
}
