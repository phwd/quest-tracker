package com.google.common.primitives;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Shorts {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    private Shorts() {
    }

    public static int hashCode(short value) {
        return value;
    }

    public static short checkedCast(long value) {
        short result = (short) ((int) value);
        Preconditions.checkArgument(((long) result) == value, "Out of range: %s", value);
        return result;
    }

    public static short saturatedCast(long value) {
        if (value > 32767) {
            return Short.MAX_VALUE;
        }
        if (value < -32768) {
            return Short.MIN_VALUE;
        }
        return (short) ((int) value);
    }

    public static int compare(short a, short b) {
        return a - b;
    }

    public static boolean contains(short[] array, short target) {
        for (short value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(short[] array, short target) {
        return indexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(short[] array, short target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(short[] array, short[] target) {
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

    public static int lastIndexOf(short[] array, short target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(short[] array, short target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static short min(short... array) {
        Preconditions.checkArgument(array.length > 0);
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static short max(short... array) {
        Preconditions.checkArgument(array.length > 0);
        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    @Beta
    public static short constrainToRange(short value, short min, short max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", (int) min, (int) max);
        if (value < min) {
            return min;
        }
        return value < max ? value : max;
    }

    public static short[] concat(short[]... arrays) {
        int length = 0;
        for (short[] array : arrays) {
            length += array.length;
        }
        short[] result = new short[length];
        int pos = 0;
        for (short[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    @GwtIncompatible
    public static byte[] toByteArray(short value) {
        return new byte[]{(byte) (value >> 8), (byte) value};
    }

    @GwtIncompatible
    public static short fromByteArray(byte[] bytes) {
        boolean z;
        if (bytes.length >= 2) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "array too small: %s < %s", bytes.length, 2);
        return fromBytes(bytes[0], bytes[1]);
    }

    @GwtIncompatible
    public static short fromBytes(byte b1, byte b2) {
        return (short) ((b1 << 8) | (b2 & UnsignedBytes.MAX_VALUE));
    }

    private static final class ShortConverter extends Converter<String, Short> implements Serializable {
        static final ShortConverter INSTANCE = new ShortConverter();
        private static final long serialVersionUID = 1;

        private ShortConverter() {
        }

        /* access modifiers changed from: protected */
        public Short doForward(String value) {
            return Short.decode(value);
        }

        /* access modifiers changed from: protected */
        public String doBackward(Short value) {
            return value.toString();
        }

        public String toString() {
            return "Shorts.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    @Beta
    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] ensureCapacity(short[] array, int minLength, int padding) {
        boolean z;
        boolean z2 = true;
        if (minLength >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Invalid minLength: %s", minLength);
        if (padding < 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", padding);
        return array.length < minLength ? Arrays.copyOf(array, minLength + padding) : array;
    }

    public static String join(String separator, short... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 6);
        builder.append((int) array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append((int) array[i]);
        }
        return builder.toString();
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        public int compare(short[] left, short[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                int result = Shorts.compare(left[i], right[i]);
                if (result != 0) {
                    return result;
                }
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }
    }

    public static void sortDescending(short[] array) {
        Preconditions.checkNotNull(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(short[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void reverse(short[] array) {
        Preconditions.checkNotNull(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(short[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; j--) {
            short tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
        }
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).toShortArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        short[] array = new short[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((Number) Preconditions.checkNotNull(boxedArray[i])).shortValue();
        }
        return array;
    }

    public static List<Short> asList(short... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new ShortArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final short[] array;
        final int end;
        final int start;

        ShortArrayAsList(short[] array2) {
            this(array2, 0, array2.length);
        }

        ShortArrayAsList(short[] array2, int start2, int end2) {
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
        public Short get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Short.valueOf(this.array[this.start + index]);
        }

        public boolean contains(@NullableDecl Object target) {
            return (target instanceof Short) && Shorts.indexOf(this.array, ((Short) target).shortValue(), this.start, this.end) != -1;
        }

        public int indexOf(@NullableDecl Object target) {
            int i;
            if (!(target instanceof Short) || (i = Shorts.indexOf(this.array, ((Short) target).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public int lastIndexOf(@NullableDecl Object target) {
            int i;
            if (!(target instanceof Short) || (i = Shorts.lastIndexOf(this.array, ((Short) target).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public Short set(int index, Short element) {
            Preconditions.checkElementIndex(index, size());
            short oldValue = this.array[this.start + index];
            this.array[this.start + index] = ((Short) Preconditions.checkNotNull(element)).shortValue();
            return Short.valueOf(oldValue);
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Short> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new ShortArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        public boolean equals(@NullableDecl Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof ShortArrayAsList)) {
                return super.equals(object);
            }
            ShortArrayAsList that = (ShortArrayAsList) object;
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
                result = (result * 31) + Shorts.hashCode(this.array[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(size() * 6);
            builder.append('[').append((int) this.array[this.start]);
            for (int i = this.start + 1; i < this.end; i++) {
                builder.append(", ").append((int) this.array[i]);
            }
            return builder.append(']').toString();
        }

        /* access modifiers changed from: package-private */
        public short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}
