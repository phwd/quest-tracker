package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public final class ArrayBuilders {
    private BooleanBuilder _booleanBuilder = null;
    private ByteBuilder _byteBuilder = null;
    private DoubleBuilder _doubleBuilder = null;
    private FloatBuilder _floatBuilder = null;
    private IntBuilder _intBuilder = null;
    private LongBuilder _longBuilder = null;
    private ShortBuilder _shortBuilder = null;

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public static final class BooleanBuilder extends PrimitiveArrayBuilder<boolean[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    public static final class ByteBuilder extends PrimitiveArrayBuilder<byte[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    public static final class ShortBuilder extends PrimitiveArrayBuilder<short[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public static final class IntBuilder extends PrimitiveArrayBuilder<int[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    public static final class LongBuilder extends PrimitiveArrayBuilder<long[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    public static final class FloatBuilder extends PrimitiveArrayBuilder<float[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    public static final class DoubleBuilder extends PrimitiveArrayBuilder<double[]> {
        @Override // com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder
        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    public static Object getArrayComparator(final Object obj) {
        final int length = Array.getLength(obj);
        final Class<?> cls = obj.getClass();
        return new Object() {
            /* class com.fasterxml.jackson.databind.util.ArrayBuilders.AnonymousClass1 */

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj != null && obj.getClass() == cls && Array.getLength(obj) == length)) {
                    return false;
                }
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    Object obj3 = Array.get(obj, i);
                    if (!(obj2 == obj3 || obj2 == null || obj2.equals(obj3))) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static <T> HashSet<T> arrayToSet(T[] tArr) {
        HashSet<T> hashSet = new HashSet<>();
        if (tArr != null) {
            for (T t : tArr) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }

    public static <T> ArrayList<T> arrayToList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (tArr != null) {
            for (T t : tArr) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static <T> HashSet<T> setAndArray(Set<T> set, T[] tArr) {
        HashSet<T> hashSet = new HashSet<>();
        if (set != null) {
            hashSet.addAll(set);
        }
        if (tArr != null) {
            for (T t : tArr) {
                hashSet.add(t);
            }
        }
        return hashSet;
    }

    public static <T> List<T> addToList(List<T> list, T t) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(t);
        return list;
    }

    public static <T> T[] insertInList(T[] tArr, T t) {
        int length = tArr.length;
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1));
        if (length > 0) {
            System.arraycopy(tArr, 0, tArr2, 1, length);
        }
        tArr2[0] = t;
        return tArr2;
    }

    public static <T> T[] insertInListNoDup(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (tArr[i] == t) {
                if (i == 0) {
                    return tArr;
                } else {
                    T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length));
                    System.arraycopy(tArr, 0, tArr2, 1, i);
                    tArr2[0] = t;
                    int i2 = i + 1;
                    int i3 = length - i2;
                    if (i3 > 0) {
                        System.arraycopy(tArr, i2, tArr2, i2, i3);
                    }
                    return tArr2;
                }
            }
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + 1));
        if (length > 0) {
            System.arraycopy(tArr, 0, tArr3, 1, length);
        }
        tArr3[0] = t;
        return tArr3;
    }

    public static <T> Iterator<T> arrayAsIterator(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    public static <T> Iterable<T> arrayAsIterable(T[] tArr) {
        return new ArrayIterator(tArr);
    }

    private static final class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
        private final T[] _array;
        private int _index = 0;

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this;
        }

        public ArrayIterator(T[] tArr) {
            this._array = tArr;
        }

        public boolean hasNext() {
            return this._index < this._array.length;
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this._index;
            T[] tArr = this._array;
            if (i < tArr.length) {
                this._index = i + 1;
                return tArr[i];
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
