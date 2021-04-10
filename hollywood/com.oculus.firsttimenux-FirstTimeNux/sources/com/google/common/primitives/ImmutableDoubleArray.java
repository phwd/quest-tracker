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
public final class ImmutableDoubleArray implements Serializable {
    private static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);
    private final double[] array;
    private final int end;
    private final transient int start;

    public static ImmutableDoubleArray of() {
        return EMPTY;
    }

    public static ImmutableDoubleArray of(double e0) {
        return new ImmutableDoubleArray(new double[]{e0});
    }

    public static ImmutableDoubleArray of(double e0, double e1) {
        return new ImmutableDoubleArray(new double[]{e0, e1});
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2});
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3});
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4});
    }

    public static ImmutableDoubleArray of(double e0, double e1, double e2, double e3, double e4, double e5) {
        return new ImmutableDoubleArray(new double[]{e0, e1, e2, e3, e4, e5});
    }

    public static ImmutableDoubleArray of(double first, double... rest) {
        boolean z;
        if (rest.length <= 2147483646) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "the total number of elements must fit in an int");
        double[] array2 = new double[(rest.length + 1)];
        array2[0] = first;
        System.arraycopy(rest, 0, array2, 1, rest.length);
        return new ImmutableDoubleArray(array2);
    }

    public static ImmutableDoubleArray copyOf(double[] values) {
        if (values.length == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(Arrays.copyOf(values, values.length));
    }

    public static ImmutableDoubleArray copyOf(Collection<Double> values) {
        return values.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(values));
    }

    public static ImmutableDoubleArray copyOf(Iterable<Double> values) {
        if (values instanceof Collection) {
            return copyOf((Collection<Double>) ((Collection) values));
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
        private double[] array;
        private int count = 0;

        Builder(int initialCapacity) {
            this.array = new double[initialCapacity];
        }

        public Builder add(double value) {
            ensureRoomFor(1);
            this.array[this.count] = value;
            this.count++;
            return this;
        }

        public Builder addAll(double[] values) {
            ensureRoomFor(values.length);
            System.arraycopy(values, 0, this.array, this.count, values.length);
            this.count += values.length;
            return this;
        }

        public Builder addAll(Iterable<Double> values) {
            if (values instanceof Collection) {
                return addAll((Collection) values);
            }
            for (Double value : values) {
                add(value.doubleValue());
            }
            return this;
        }

        public Builder addAll(Collection<Double> values) {
            ensureRoomFor(values.size());
            for (Double value : values) {
                double[] dArr = this.array;
                int i = this.count;
                this.count = i + 1;
                dArr[i] = value.doubleValue();
            }
            return this;
        }

        public Builder addAll(ImmutableDoubleArray values) {
            ensureRoomFor(values.length());
            System.arraycopy(values.array, values.start, this.array, this.count, values.length());
            this.count += values.length();
            return this;
        }

        private void ensureRoomFor(int numberToAdd) {
            int newCount = this.count + numberToAdd;
            if (newCount > this.array.length) {
                double[] newArray = new double[expandedCapacity(this.array.length, newCount)];
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
        public ImmutableDoubleArray build() {
            return this.count == 0 ? ImmutableDoubleArray.EMPTY : new ImmutableDoubleArray(this.array, 0, this.count);
        }
    }

    private ImmutableDoubleArray(double[] array2) {
        this(array2, 0, array2.length);
    }

    private ImmutableDoubleArray(double[] array2, int start2, int end2) {
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

    public double get(int index) {
        Preconditions.checkElementIndex(index, length());
        return this.array[this.start + index];
    }

    public int indexOf(double target) {
        for (int i = this.start; i < this.end; i++) {
            if (areEqual(this.array[i], target)) {
                return i - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(double target) {
        for (int i = this.end - 1; i >= this.start; i--) {
            if (areEqual(this.array[i], target)) {
                return i - this.start;
            }
        }
        return -1;
    }

    public boolean contains(double target) {
        return indexOf(target) >= 0;
    }

    public double[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableDoubleArray subArray(int startIndex, int endIndex) {
        Preconditions.checkPositionIndexes(startIndex, endIndex, length());
        if (startIndex == endIndex) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(this.array, this.start + startIndex, this.start + endIndex);
    }

    public List<Double> asList() {
        return new AsList();
    }

    /* access modifiers changed from: package-private */
    public static class AsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private final ImmutableDoubleArray parent;

        private AsList(ImmutableDoubleArray parent2) {
            this.parent = parent2;
        }

        public int size() {
            return this.parent.length();
        }

        @Override // java.util.List, java.util.AbstractList
        public Double get(int index) {
            return Double.valueOf(this.parent.get(index));
        }

        public boolean contains(Object target) {
            return indexOf(target) >= 0;
        }

        public int indexOf(Object target) {
            if (target instanceof Double) {
                return this.parent.indexOf(((Double) target).doubleValue());
            }
            return -1;
        }

        public int lastIndexOf(Object target) {
            if (target instanceof Double) {
                return this.parent.lastIndexOf(((Double) target).doubleValue());
            }
            return -1;
        }

        @Override // java.util.List, java.util.AbstractList
        public List<Double> subList(int fromIndex, int toIndex) {
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
                if (!(element instanceof Double)) {
                    return false;
                }
                int i2 = i + 1;
                if (!ImmutableDoubleArray.areEqual(this.parent.array[i], ((Double) element).doubleValue())) {
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
        if (!(object instanceof ImmutableDoubleArray)) {
            return false;
        }
        ImmutableDoubleArray that = (ImmutableDoubleArray) object;
        if (length() != that.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!areEqual(get(i), that.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static boolean areEqual(double a, double b) {
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
    }

    public int hashCode() {
        int hash = 1;
        for (int i = this.start; i < this.end; i++) {
            hash = (hash * 31) + Doubles.hashCode(this.array[i]);
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

    public ImmutableDoubleArray trimmed() {
        return isPartialView() ? new ImmutableDoubleArray(toArray()) : this;
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
