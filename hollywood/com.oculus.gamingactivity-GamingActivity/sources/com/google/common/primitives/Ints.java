package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Ints {
    public static final int BYTES = 4;
    public static final int MAX_POWER_OF_TWO = 1073741824;

    private Ints() {
    }

    public static int hashCode(int value) {
        return value;
    }

    public static int checkedCast(long value) {
        int result = (int) value;
        Preconditions.checkArgument(((long) result) == value, "Out of range: %s", value);
        return result;
    }

    public static int saturatedCast(long value) {
        if (value > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (value < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    public static int compare(int a, int b) {
        if (a < b) {
            return -1;
        }
        return a > b ? 1 : 0;
    }

    public static boolean contains(int[] array, int target) {
        for (int value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(int[] array, int target) {
        return indexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(int[] array, int target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(int[] array, int[] target) {
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

    public static int lastIndexOf(int[] array, int target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(int[] array, int target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int min(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int max(int... array) {
        Preconditions.checkArgument(array.length > 0);
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    @Beta
    public static int constrainToRange(int value, int min, int max) {
        Preconditions.checkArgument(min <= max, "min (%s) must be less than or equal to max (%s)", min, max);
        return Math.min(Math.max(value, min), max);
    }

    public static int[] concat(int[]... arrays) {
        int length = 0;
        for (int[] array : arrays) {
            length += array.length;
        }
        int[] result = new int[length];
        int pos = 0;
        for (int[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    public static byte[] toByteArray(int value) {
        return new byte[]{(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value};
    }

    public static int fromByteArray(byte[] bytes) {
        boolean z;
        if (bytes.length >= 4) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "array too small: %s < %s", bytes.length, 4);
        return fromBytes(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return (b1 << Ascii.CAN) | ((b2 & UnsignedBytes.MAX_VALUE) << 16) | ((b3 & UnsignedBytes.MAX_VALUE) << 8) | (b4 & UnsignedBytes.MAX_VALUE);
    }

    private static final class IntConverter extends Converter<String, Integer> implements Serializable {
        static final IntConverter INSTANCE = new IntConverter();
        private static final long serialVersionUID = 1;

        private IntConverter() {
        }

        /* access modifiers changed from: protected */
        public Integer doForward(String value) {
            return Integer.decode(value);
        }

        /* access modifiers changed from: protected */
        public String doBackward(Integer value) {
            return value.toString();
        }

        public String toString() {
            return "Ints.stringConverter()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    @Beta
    public static Converter<String, Integer> stringConverter() {
        return IntConverter.INSTANCE;
    }

    public static int[] ensureCapacity(int[] array, int minLength, int padding) {
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

    public static String join(String separator, int... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 5);
        builder.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator).append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public int compare(int[] left, int[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                int result = Ints.compare(left[i], right[i]);
                if (result != 0) {
                    return result;
                }
            }
            return left.length - right.length;
        }

        public String toString() {
            return "Ints.lexicographicalComparator()";
        }
    }

    public static void sortDescending(int[] array) {
        Preconditions.checkNotNull(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void reverse(int[] array) {
        Preconditions.checkNotNull(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(int[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; j--) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
        }
    }

    public static int[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof IntArrayAsList) {
            return ((IntArrayAsList) collection).toIntArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((Number) Preconditions.checkNotNull(boxedArray[i])).intValue();
        }
        return array;
    }

    public static List<Integer> asList(int... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new IntArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final int[] array;
        final int end;
        final int start;

        IntArrayAsList(int[] array2) {
            this(array2, 0, array2.length);
        }

        IntArrayAsList(int[] array2, int start2, int end2) {
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
        public Integer get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Integer.valueOf(this.array[this.start + index]);
        }

        public boolean contains(Object target) {
            return (target instanceof Integer) && Ints.indexOf(this.array, ((Integer) target).intValue(), this.start, this.end) != -1;
        }

        public int indexOf(Object target) {
            int i;
            if (!(target instanceof Integer) || (i = Ints.indexOf(this.array, ((Integer) target).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public int lastIndexOf(Object target) {
            int i;
            if (!(target instanceof Integer) || (i = Ints.lastIndexOf(this.array, ((Integer) target).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public Integer set(int index, Integer element) {
            Preconditions.checkElementIndex(index, size());
            int oldValue = this.array[this.start + index];
            this.array[this.start + index] = ((Integer) Preconditions.checkNotNull(element)).intValue();
            return Integer.valueOf(oldValue);
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Integer> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new IntArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        public boolean equals(@NullableDecl Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof IntArrayAsList)) {
                return super.equals(object);
            }
            IntArrayAsList that = (IntArrayAsList) object;
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
                result = (result * 31) + Ints.hashCode(this.array[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(size() * 5);
            builder.append('[').append(this.array[this.start]);
            for (int i = this.start + 1; i < this.end; i++) {
                builder.append(", ").append(this.array[i]);
            }
            return builder.append(']').toString();
        }

        /* access modifiers changed from: package-private */
        public int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    @Beta
    @NullableDecl
    public static Integer tryParse(String string) {
        return tryParse(string, 10);
    }

    @Beta
    @NullableDecl
    public static Integer tryParse(String string, int radix) {
        Long result = Longs.tryParse(string, radix);
        if (result == null || result.longValue() != ((long) result.intValue())) {
            return null;
        }
        return Integer.valueOf(result.intValue());
    }
}
