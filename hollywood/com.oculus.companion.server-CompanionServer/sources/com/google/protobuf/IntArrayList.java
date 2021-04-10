package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess {
    private static final IntArrayList EMPTY_LIST = new IntArrayList();
    private int[] array;
    private int size;

    static {
        EMPTY_LIST.makeImmutable();
    }

    public static IntArrayList emptyList() {
        return EMPTY_LIST;
    }

    IntArrayList() {
        this(new int[10], 0);
    }

    private IntArrayList(int[] iArr, int i) {
        this.array = iArr;
        this.size = i;
    }

    @Override // com.google.protobuf.AbstractProtobufList
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntArrayList)) {
            return super.equals(obj);
        }
        IntArrayList intArrayList = (IntArrayList) obj;
        if (this.size != intArrayList.size) {
            return false;
        }
        int[] iArr = intArrayList.array;
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.protobuf.AbstractProtobufList
    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.array[i2];
        }
        return i;
    }

    /* Return type fixed from 'com.google.protobuf.Internal$IntList' to match base method */
    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.IntList
    public Internal.ProtobufList<Integer> mutableCopyWithCapacity(int i) {
        if (i >= this.size) {
            return new IntArrayList(Arrays.copyOf(this.array, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List, java.util.AbstractList
    public Integer get(int i) {
        return Integer.valueOf(getInt(i));
    }

    @Override // com.google.protobuf.Internal.IntList
    public int getInt(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    public int size() {
        return this.size;
    }

    public Integer set(int i, Integer num) {
        return Integer.valueOf(setInt(i, num.intValue()));
    }

    public int setInt(int i, int i2) {
        ensureIsMutable();
        ensureIndexInRange(i);
        int[] iArr = this.array;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    public void add(int i, Integer num) {
        addInt(i, num.intValue());
    }

    @Override // com.google.protobuf.Internal.IntList
    public void addInt(int i) {
        addInt(this.size, i);
    }

    private void addInt(int i, int i2) {
        int i3;
        ensureIsMutable();
        if (i < 0 || i > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        int[] iArr = this.array;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[(((i3 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.array, i, iArr2, i + 1, this.size - i);
            this.array = iArr2;
        }
        this.array[i] = i2;
        this.size++;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.protobuf.AbstractProtobufList
    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        if (collection == null) {
            throw new NullPointerException();
        } else if (!(collection instanceof IntArrayList)) {
            return super.addAll(collection);
        } else {
            IntArrayList intArrayList = (IntArrayList) collection;
            int i = intArrayList.size;
            if (i == 0) {
                return false;
            }
            int i2 = this.size;
            if (Integer.MAX_VALUE - i2 >= i) {
                int i3 = i2 + i;
                int[] iArr = this.array;
                if (i3 > iArr.length) {
                    this.array = Arrays.copyOf(iArr, i3);
                }
                System.arraycopy(intArrayList.array, 0, this.array, this.size, intArrayList.size);
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
            if (obj.equals(Integer.valueOf(this.array[i]))) {
                int[] iArr = this.array;
                System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
                this.size--;
                ((AbstractList) this).modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.List, java.util.AbstractList
    public Integer remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        int[] iArr = this.array;
        int i2 = iArr[i];
        System.arraycopy(iArr, i + 1, iArr, i, this.size - i);
        this.size--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i2);
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
