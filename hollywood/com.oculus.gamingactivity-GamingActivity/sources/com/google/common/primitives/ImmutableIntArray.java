package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
@Beta
@GwtCompatible
public final class ImmutableIntArray implements Serializable {
    private static final ImmutableIntArray EMPTY = new ImmutableIntArray(new int[0]);
    private final int[] array;
    private final int end;
    private final transient int start;

    public static ImmutableIntArray of() {
        return EMPTY;
    }

    public static ImmutableIntArray of(int e0) {
        return new ImmutableIntArray(new int[]{e0});
    }

    public static ImmutableIntArray of(int e0, int e1) {
        return new ImmutableIntArray(new int[]{e0, e1});
    }

    public static ImmutableIntArray of(int e0, int e1, int e2) {
        return new ImmutableIntArray(new int[]{e0, e1, e2});
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3});
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3, int e4) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3, e4});
    }

    public static ImmutableIntArray of(int e0, int e1, int e2, int e3, int e4, int e5) {
        return new ImmutableIntArray(new int[]{e0, e1, e2, e3, e4, e5});
    }

    public static ImmutableIntArray of(int first, int... rest) {
        boolean z;
        if (rest.length <= 2147483646) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "the total number of elements must fit in an int");
        int[] array2 = new int[(rest.length + 1)];
        array2[0] = first;
        System.arraycopy(rest, 0, array2, 1, rest.length);
        return new ImmutableIntArray(array2);
    }

    public static ImmutableIntArray copyOf(int[] values) {
        return values.length == 0 ? EMPTY : new ImmutableIntArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableIntArray copyOf(Collection<Integer> values) {
        return values.isEmpty() ? EMPTY : new ImmutableIntArray(Ints.toArray(values));
    }

    public static ImmutableIntArray copyOf(Iterable<Integer> values) {
        if (values instanceof Collection) {
            return copyOf((Collection<Integer>) ((Collection) values));
        }
        return builder().addAll(values).build();
    }

    public static Builder builder(int initialCapacity) {
        Preconditions.checkArgument(initialCapacity >= 0, "Invalid initialCapacity: %s", initialCapacity);
        return new Builder(initialCapacity);
    }

    public static Builder builder() {
        return new Builder(10);
    }

    @CanIgnoreReturnValue
    public static final class Builder {
        private int[] array;
        private int count = 0;

        Builder(int initialCapacity) {
            this.array = new int[initialCapacity];
        }

        public Builder add(int value) {
            ensureRoomFor(1);
            this.array[this.count] = value;
            this.count++;
            return this;
        }

        public Builder addAll(int[] values) {
            ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public Builder addAll(Iterable<Integer> values) {
            if (values instanceof Collection) {
                return addAll((Collection) values);
            }
            for (Integer value : values) {
                add(value.intValue());
            }
            return this;
        }

        public Builder addAll(Collection<Integer> values) {
            ensureRoomFor(values.size());
            for (Integer value : values) {
                int[] iArr = this.array;
                int i = this.count;
                this.count = i + 1;
                iArr[i] = value.intValue();
            }
            return this;
        }

        public Builder addAll(ImmutableIntArray values) {
            ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count += values.length();
            return this;
        }

        private void ensureRoomFor(int numberToAdd) {
            int newCount = this.count + numberToAdd;
            if (newCount > this.array.length) {
                int[] newArray = new int[expandedCapacity(this.array.length, newCount)];
                System.arraycopy(this.array, 0, newArray, 0, this.count);
                this.array = newArray;
            }
        }

        private static int expandedCapacity(int oldCapacity, int minCapacity) {
            if (minCapacity < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int newCapacity = (oldCapacity >> 1) + oldCapacity + 1;
            if (newCapacity < minCapacity) {
                newCapacity = Integer.highestOneBit(minCapacity - 1) << 1;
            }
            if (newCapacity < 0) {
                return Integer.MAX_VALUE;
            }
            return newCapacity;
        }

        @CheckReturnValue
        public ImmutableIntArray build() {
            return this.count == 0 ? ImmutableIntArray.EMPTY : new ImmutableIntArray(this.array, 0, this.count);
        }
    }

    private ImmutableIntArray(int[] array2) {
        this(array2, 0, array2.length);
    }

    private ImmutableIntArray(int[] array2, int start2, int end2) {
        this.array = array2;
        this.start = start2;
        this.end = end2;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int get(int index) {
        Preconditions.checkElementIndex(index, length());
        return this.array[this.start + index];
    }

    public int indexOf(int target) {
        for (int i = this.start; i < this.end; i++) {
            if (this.array[i] == target) {
                return i - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(int target) {
        for (int i = this.end - 1; i >= this.start; i--) {
            if (this.array[i] == target) {
                return i - this.start;
            }
        }
        return -1;
    }

    public boolean contains(int target) {
        return indexOf(target) >= 0;
    }

    public int[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableIntArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, length());
        if (startIndex == endIndex) {
            return EMPTY;
        }
        return new ImmutableIntArray(this.array, this.start + startIndex, this.start + endIndex);
    }

    public List<Integer> asList() {
        return new AsList();
    }

    /* access modifiers changed from: package-private */
    public static class AsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private final ImmutableIntArray parent;

        private AsList(ImmutableIntArray parent2) {
            this.parent = parent2;
        }

        public int size() {
            return this.parent.length();
        }

        @Override // java.util.List, java.util.AbstractList
        public Integer get(int index) {
            return Integer.valueOf(this.parent.get(index));
        }

        public boolean contains(Object target) {
            return indexOf(target) >= 0;
        }

        public int indexOf(Object target) {
            if (target instanceof Integer) {
                return this.parent.indexOf(((Integer) target).intValue());
            }
            return -1;
        }

        public int lastIndexOf(Object target) {
            if (target instanceof Integer) {
                return this.parent.lastIndexOf(((Integer) target).intValue());
            }
            return -1;
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Integer> subList(int fromIndex, int toIndex) {
            return this.parent.subArray(fromIndex, toIndex).asList();
        }

        public boolean equals(@NullableDecl Object object) {
            if (object instanceof AsList) {
                return this.parent.equals(((AsList) object).parent);
            }
            if (!(object instanceof List)) {
                return false;
            }
            List<?> that = (List) object;
            if (size() != that.size()) {
                return false;
            }
            int i = this.parent.start;
            for (Object element : that) {
                if (!(element instanceof Integer)) {
                    return false;
                }
                int i2 = i + 1;
                if (this.parent.array[i] != ((Integer) element).intValue()) {
                    return false;
                }
                i = i2;
            }
            return true;
        }

        public int hashCode() {
            return this.parent.hashCode();
        }

        public String toString() {
            return this.parent.toString();
        }
    }

    public boolean equals(@NullableDecl Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ImmutableIntArray)) {
            return false;
        }
        ImmutableIntArray that = (ImmutableIntArray) object;
        if (length() != that.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (get(i) != that.get(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hash = 1;
        for (int i = this.start; i < this.end; i++) {
            hash = (hash * 31) + Ints.hashCode(this.array[i]);
        }
        return hash;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder(length() * 5);
        builder.append('[').append(this.array[this.start]);
        for (int i = this.start + 1; i < this.end; i++) {
            builder.append(", ").append(this.array[i]);
        }
        builder.append(']');
        return builder.toString();
    }

    public ImmutableIntArray trimmed() {
        return isPartialView() ? new ImmutableIntArray(toArray()) : this;
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return trimmed();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }
}
