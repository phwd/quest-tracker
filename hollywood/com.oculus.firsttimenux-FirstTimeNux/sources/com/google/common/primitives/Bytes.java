package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Bytes {
    private Bytes() {
    }

    public static int hashCode(byte value) {
        return value;
    }

    public static boolean contains(byte[] array, byte target) {
        for (byte value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(byte[] array, byte target) {
        return indexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int indexOf(byte[] array, byte target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte[] target) {
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

    public static int lastIndexOf(byte[] array, byte target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(byte[] array, byte target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    public static byte[] ensureCapacity(byte[] array, int minLength, int padding) {
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

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).toByteArray();
        }
        Object[] boxedArray = collection.toArray();
        int len = boxedArray.length;
        byte[] array = new byte[len];
        for (int i = 0; i < len; i++) {
            array[i] = ((Number) Preconditions.checkNotNull(boxedArray[i])).byteValue();
        }
        return array;
    }

    public static List<Byte> asList(byte... backingArray) {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }
        return new ByteArrayAsList(backingArray);
    }

    @GwtCompatible
    private static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final byte[] array;
        final int end;
        final int start;

        ByteArrayAsList(byte[] array2) {
            this(array2, 0, array2.length);
        }

        ByteArrayAsList(byte[] array2, int start2, int end2) {
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
        public Byte get(int index) {
            Preconditions.checkElementIndex(index, size());
            return Byte.valueOf(this.array[this.start + index]);
        }

        public boolean contains(Object target) {
            return (target instanceof Byte) && Bytes.indexOf(this.array, ((Byte) target).byteValue(), this.start, this.end) != -1;
        }

        public int indexOf(Object target) {
            int i;
            if (!(target instanceof Byte) || (i = Bytes.indexOf(this.array, ((Byte) target).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public int lastIndexOf(Object target) {
            int i;
            if (!(target instanceof Byte) || (i = Bytes.lastIndexOf(this.array, ((Byte) target).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return i - this.start;
        }

        public Byte set(int index, Byte element) {
            Preconditions.checkElementIndex(index, size());
            byte oldValue = this.array[this.start + index];
            this.array[this.start + index] = ((Byte) Preconditions.checkNotNull(element)).byteValue();
            return Byte.valueOf(oldValue);
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Byte> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
            if (fromIndex == toIndex) {
                return Collections.emptyList();
            }
            return new ByteArrayAsList(this.array, this.start + fromIndex, this.start + toIndex);
        }

        public boolean equals(@NullableDecl Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof ByteArrayAsList)) {
                return super.equals(object);
            }
            ByteArrayAsList that = (ByteArrayAsList) object;
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
                result = (result * 31) + Bytes.hashCode(this.array[i]);
            }
            return result;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(size() * 5);
            builder.append('[').append((int) this.array[this.start]);
            for (int i = this.start + 1; i < this.end; i++) {
                builder.append(", ").append((int) this.array[i]);
            }
            return builder.append(']').toString();
        }

        /* access modifiers changed from: package-private */
        public byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    public static void reverse(byte[] array) {
        Preconditions.checkNotNull(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(byte[] array, int fromIndex, int toIndex) {
        Preconditions.checkNotNull(array);
        Preconditions.checkPositionIndexes(fromIndex, toIndex, array.length);
        int i = fromIndex;
        for (int j = toIndex - 1; i < j; j--) {
            byte tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
        }
    }
}
