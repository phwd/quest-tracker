package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
public final class Booleans {
    private Booleans() {
    }

    public static int hashCode(boolean value) {
        return value ? 1231 : 1237;
    }

    public static int compare(boolean a, boolean b) {
        if (a == b) {
            return 0;
        }
        return a ? 1 : -1;
    }

    public static boolean contains(boolean[] array, boolean target) {
        for (boolean value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(boolean[] array, boolean target) {
        return indexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(boolean[] array, boolean target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(boolean[] array, boolean[] target) {
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

    public static int lastIndexOf(boolean[] array, boolean target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(boolean[] array, boolean target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] concat(boolean[]... arrays) {
        int length = 0;
        for (boolean[] array : arrays) {
            length += array.length;
        }
        boolean[] result = new boolean[length];
        int pos = 0;
        for (boolean[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    public static boolean[] ensureCapacity(boolean[] array, int minLength, int padding) {
        Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", Integer.valueOf(minLength));
        Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", Integer.valueOf(padding));
        if (array.length < minLength) {
            return copyOf(array, minLength + padding);
        }
        return array;
    }

    private static boolean[] copyOf(boolean[] original, int length) {
        boolean[] copy = new boolean[length];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, length));
        return copy;
    }

    public static String join(String separator, boolean... array) {
        Preconditions.checkNotNull(separator);
        if (array.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder(array.length * 7);
        builder.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            builder.append(separator);
            builder.append(array[i]);
        }
        return builder.toString();
    }

    public static Comparator<boolean[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    private enum LexicographicalComparator implements Comparator<boolean[]> {
        INSTANCE;

        public int compare(boolean[] left, boolean[] right) {
            int minLength = Math.min(left.length, right.length);
            for (int i = 0; i < minLength; i++) {
                int result = Booleans.compare(left[i], right[i]);
                if (result != 0) {
                    return result;
                }
            }
            return left.length - right.length;
        }
    }

    public static boolean[] toArray(Collection<Boolean> collection) {
        if (collection instanceof BooleanArrayAsList) {
            return ((BooleanArrayAsList) collection).toBooleanArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        boolean[] array = new boolean[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((Boolean) Preconditions.checkNotNull(boxedArray[i])).booleanValue();
        }
        return array;
    }

    public static List<Boolean> asList(boolean... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new BooleanArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class BooleanArrayAsList extends AbstractList<Boolean> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final boolean[] array;
        final int end;
        final int start;

        BooleanArrayAsList(boolean[] array2) {
            this(array2, 0, array2.length);
        }

        BooleanArrayAsList(boolean[] array2, int start2, int end2) {
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
        public Boolean get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Boolean.valueOf(this.array[this.start + index]);
        }

        public boolean contains(Object target) {
            return (target instanceof Boolean) && Booleans.indexOf(this.array, ((Boolean) target).booleanValue(), this.start, this.end) != -1;
        }

        public int indexOf(Object target) {
            int i;
            if (!(target instanceof Boolean) || (i = Booleans.indexOf(this.array, ((Boolean) target).booleanValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public int lastIndexOf(Object target) {
            int i;
            if (!(target instanceof Boolean) || (i = Booleans.lastIndexOf(this.array, ((Boolean) target).booleanValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public Boolean set(int index, Boolean element) {
            Preconditions.checkElementIndex(index, size());
            boolean[] zArr = this.array;
            int i = this.start;
            boolean oldValue = zArr[i + index];
            zArr[i + index] = ((Boolean) Preconditions.checkNotNull(element)).booleanValue();
            return Boolean.valueOf(oldValue);
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Boolean> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            boolean[] zArr = this.array;
            int i = this.start;
            return new BooleanArrayAsList(zArr, i + fromIndex, i + toIndex);
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof BooleanArrayAsList)) {
                return super.equals(object);
            }
            BooleanArrayAsList that = (BooleanArrayAsList) object;
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
                result = (result * 31) + Booleans.hashCode(this.array[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(size() * 7);
            builder.append(this.array[this.start] ? "[true" : "[false");
            int i = this.start;
            while (true) {
                i++;
                if (i < this.end) {
                    builder.append(this.array[i] ? ", true" : ", false");
                } else {
                    builder.append(']');
                    return builder.toString();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean[] toBooleanArray() {
            int size = size();
            boolean[] result = new boolean[size];
            System.arraycopy(this.array, this.start, result, 0, size);
            return result;
        }
    }

    @Beta
    public static int countTrue(boolean... values) {
        int count = 0;
        for (boolean value : values) {
            if (value) {
                count++;
            }
        }
        return count;
    }
}
