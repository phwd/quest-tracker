package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess {
    private static final DoubleArrayList EMPTY_LIST = new DoubleArrayList();
    private double[] array;
    private int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    public static DoubleArrayList emptyList() {
        return EMPTY_LIST;
    }

    DoubleArrayList() {
        this(new double[10], 0);
    }

    private DoubleArrayList(double[] dArr, int i) {
        this.array = dArr;
        this.size = i;
    }

    @Override // com.google.protobuf.AbstractProtobufList
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleArrayList)) {
            return super.equals(obj);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
        if (this.size != doubleArrayList.size) {
            return false;
        }
        double[] dArr = doubleArrayList.array;
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Internal.hashLong(Double.doubleToLongBits(this.array[i2]));
        }
        return i;
    }

    /* Return type fixed from 'com.google.protobuf.Internal$DoubleList' to match base method */
    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.DoubleList
    public Internal.ProtobufList<Double> mutableCopyWithCapacity(int i) {
        if (i >= this.size) {
            return new DoubleArrayList(Arrays.copyOf(this.array, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List, java.util.AbstractList
    public Double get(int i) {
        return Double.valueOf(getDouble(i));
    }

    public double getDouble(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    public int size() {
        return this.size;
    }

    public Double set(int i, Double d) {
        return Double.valueOf(setDouble(i, d.doubleValue()));
    }

    public double setDouble(int i, double d) {
        ensureIsMutable();
        ensureIndexInRange(i);
        double[] dArr = this.array;
        double d2 = dArr[i];
        dArr[i] = d;
        return d2;
    }

    public void add(int i, Double d) {
        addDouble(i, d.doubleValue());
    }

    private void addDouble(int i, double d) {
        int i2;
        ensureIsMutable();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        double[] dArr = this.array;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.array, i, dArr2, i + 1, this.size - i);
            this.array = dArr2;
        }
        this.array[i] = d;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.protobuf.AbstractProtobufList
    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof DoubleArrayList)) {
            return super.addAll(collection);
        } else {
            DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
            int i = doubleArrayList.size;
            if (i == 0) {
                return false;
            }
            int i2 = this.size;
            if (Integer.MAX_VALUE - i2 >= i) {
                int i3 = i2 + i;
                double[] dArr = this.array;
                if (i3 > dArr.length) {
                    this.array = Arrays.copyOf(dArr, i3);
                }
                System.arraycopy(doubleArrayList.array, 0, this.array, this.size, doubleArrayList.size);
                this.size = i3;
                ((AbstractList) this).modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
    }

    @Override // java.util.List, com.google.protobuf.AbstractProtobufList
    public boolean remove(Object obj) {
        ensureIsMutable();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.array[i]))) {
                double[] dArr = this.array;
                System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.AbstractList
    public Double remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        double[] dArr = this.array;
        double d = dArr[i];
        System.arraycopy(dArr, i + 1, dArr, i, this.size - i);
        this.size--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    private void ensureIndexInRange(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }
}
