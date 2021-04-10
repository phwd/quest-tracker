package com.facebook.flatbuffers;

import com.facebook.common.foreach.RandomAccessList;
import com.facebook.flatbuffers.Flattenable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.stetho.common.Utf8Charset;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

public final class FlatBuffer {
    public static final String LOG_TAG = "FlatBuffer";
    private static final Charset UTF_8_CHARSET = Charset.forName(Utf8Charset.NAME);

    /* access modifiers changed from: package-private */
    public interface FlatBufferListAccessor<THelper, TItem> {
        @Nullable
        TItem get(THelper thelper, ByteBuffer byteBuffer, int i, int i2);
    }

    public static int getVectorStart(int i) {
        return i + 4;
    }

    public static byte getByte(ByteBuffer byteBuffer, int i, int i2, byte b) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.get(offsetForField) : b;
    }

    public static short getShort(ByteBuffer byteBuffer, int i, int i2, short s) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.getShort(offsetForField) : s;
    }

    public static int getInt(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.getInt(offsetForField) : i3;
    }

    public static long getLong(ByteBuffer byteBuffer, int i, int i2, long j) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.getLong(offsetForField) : j;
    }

    public static float getFloat(ByteBuffer byteBuffer, int i, int i2, float f) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.getFloat(offsetForField) : f;
    }

    public static double getDouble(ByteBuffer byteBuffer, int i, int i2, double d) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        return offsetForField != 0 ? byteBuffer.getDouble(offsetForField) : d;
    }

    public static boolean getBoolean(ByteBuffer byteBuffer, int i, int i2) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        if (offsetForField == 0 || byteBuffer.get(offsetForField) != 1) {
            return false;
        }
        return true;
    }

    public static boolean getBoolean(ByteBuffer byteBuffer, int i, int i2, boolean z) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        if (offsetForField != 0) {
            return byteBuffer.get(offsetForField) == 1;
        }
        return z;
    }

    @Nullable
    public static String resolveStringReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition != 0) {
            return getString(byteBuffer, referencePosition);
        }
        return null;
    }

    public static int peekStringLength(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return 0;
        }
        return byteBuffer.getInt(referencePosition);
    }

    @Nullable
    public static String resolveEnumString(ByteBuffer byteBuffer, int i, int i2) {
        return resolveStringReference(byteBuffer, i, i2);
    }

    @Nullable
    public static <T extends Flattenable> T resolveRootFlattenable(ByteBuffer byteBuffer, Class<T> cls) {
        int rootObjectPosition = getRootObjectPosition(byteBuffer);
        if (rootObjectPosition <= 0) {
            return null;
        }
        try {
            T newInstance = cls.newInstance();
            newInstance.initFromFlatBuffer(byteBuffer, rootObjectPosition);
            return newInstance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Illegal access for root object:" + cls.getSimpleName(), e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Not able to create root object:" + cls.getSimpleName(), e2);
        }
    }

    @Nullable
    public static <T> T resolveRootFlattenableWithFlattener(ByteBuffer byteBuffer, Flattener<T> flattener) {
        int rootObjectPosition = getRootObjectPosition(byteBuffer);
        if (rootObjectPosition <= 0) {
            return null;
        }
        try {
            return flattener.initFromFlatBuffer(byteBuffer, rootObjectPosition);
        } catch (InstantiationException e) {
            throw new RuntimeException("Illegal access for root object:" + flattener.getClass().getSimpleName(), e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Not able to create root object:" + flattener.getClass().getSimpleName(), e2);
        }
    }

    @Nullable
    public static Flattenable resolveRootFlattenable(ByteBuffer byteBuffer, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int rootObjectPosition = getRootObjectPosition(byteBuffer);
        if (rootObjectPosition <= 0) {
            return null;
        }
        short s = getShort(byteBuffer, rootObjectPosition, 0, 0);
        int referencePosition = getReferencePosition(byteBuffer, rootObjectPosition, 1);
        Flattenable resolveVirtualFlattenableType = virtualFlattenableResolver.resolveVirtualFlattenableType(s);
        if (resolveVirtualFlattenableType == null) {
            return null;
        }
        resolveVirtualFlattenableType.initFromFlatBuffer(byteBuffer, referencePosition);
        return resolveVirtualFlattenableType;
    }

    @Nullable
    public static <T extends Flattenable> T resolveFlattenableReference(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        try {
            T newInstance = cls.newInstance();
            newInstance.initFromFlatBuffer(byteBuffer, referencePosition);
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static <T> T resolveFlattenableWithFlattenerReference(ByteBuffer byteBuffer, int i, int i2, Flattener<T> flattener) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        try {
            return flattener.initFromFlatBuffer(byteBuffer, referencePosition);
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static Flattenable resolveVirtualFlattenableReference(ByteBuffer byteBuffer, int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        short s = getShort(byteBuffer, referencePosition, 0, 0);
        int referencePosition2 = getReferencePosition(byteBuffer, referencePosition, 1);
        Flattenable resolveVirtualFlattenableType = virtualFlattenableResolver.resolveVirtualFlattenableType(s);
        if (resolveVirtualFlattenableType == null) {
            return null;
        }
        resolveVirtualFlattenableType.initFromFlatBuffer(byteBuffer, referencePosition2);
        return resolveVirtualFlattenableType;
    }

    @Nullable
    public static byte[] resolveByteArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer resolveByteBufferReference = resolveByteBufferReference(byteBuffer, i, i2);
        if (resolveByteBufferReference == null) {
            return null;
        }
        byte[] bArr = new byte[resolveByteBufferReference.remaining()];
        resolveByteBufferReference.get(bArr);
        return bArr;
    }

    @Nullable
    static ByteBuffer resolveByteBufferReference(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer slice;
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        synchronized (byteBuffer) {
            slice = byteBuffer.slice();
        }
        int vectorStart = getVectorStart(referencePosition);
        slice.position(vectorStart);
        slice.limit(vectorStart + getVectorLength(byteBuffer, referencePosition));
        return slice;
    }

    @Nullable
    public static short[] resolveShortArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        short[] sArr = new short[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            sArr[i3] = byteBuffer.getShort((i3 * 2) + vectorStart);
        }
        return sArr;
    }

    @Nullable
    public static int[] resolveIntArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        int[] iArr = new int[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            iArr[i3] = byteBuffer.getInt((i3 * 4) + vectorStart);
        }
        return iArr;
    }

    @Nullable
    public static long[] resolveLongArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        long[] jArr = new long[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            jArr[i3] = byteBuffer.getLong((i3 * 8) + vectorStart);
        }
        return jArr;
    }

    @Nullable
    public static float[] resolveFloatArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        float[] fArr = new float[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            fArr[i3] = byteBuffer.getFloat((i3 * 4) + vectorStart);
        }
        return fArr;
    }

    @Nullable
    public static double[] resolveDoubleArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        double[] dArr = new double[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            dArr[i3] = byteBuffer.getDouble((i3 * 8) + vectorStart);
        }
        return dArr;
    }

    @Nullable
    public static boolean[] resolveBooleanArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        boolean[] zArr = new boolean[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            zArr[i3] = byteBuffer.get((i3 * 1) + vectorStart) != 0;
        }
        return zArr;
    }

    public static int findStringIndexInStringArrayReference(ByteBuffer byteBuffer, int i, int i2, String str) {
        int vectorLength;
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0 || (vectorLength = getVectorLength(byteBuffer, referencePosition)) == 0) {
            return -1;
        }
        int vectorStart = getVectorStart(referencePosition);
        int i3 = 0;
        int i4 = (vectorLength + 0) - 1;
        while (i4 >= i3) {
            int i5 = ((i4 - i3) / 2) + i3;
            int i6 = (i5 * 4) + vectorStart;
            int i7 = byteBuffer.getInt(i6);
            if (i7 == 0) {
                return -1;
            }
            int compareAsciiStringBytes = compareAsciiStringBytes(byteBuffer, i6 + i7, str);
            if (compareAsciiStringBytes == 0) {
                return i5;
            }
            if (compareAsciiStringBytes > 0) {
                i4 = i5 - 1;
            } else {
                i3 = i5 + 1;
            }
        }
        return -1;
    }

    @Nullable
    public static String[] resolveStringArrayReference(ByteBuffer byteBuffer, int i, int i2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        String[] strArr = new String[vectorLength];
        for (int i3 = 0; i3 < vectorLength; i3++) {
            int i4 = (i3 * 4) + vectorStart;
            int i5 = byteBuffer.getInt(i4);
            if (i5 == 0) {
                strArr[i3] = null;
            } else {
                strArr[i3] = getString(byteBuffer, i4 + i5);
            }
        }
        return strArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: T extends java.lang.Enum[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <T extends Enum> T[] resolveEnumStringArrayReference(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        T[] tArr = (T[]) ((Enum[]) Array.newInstance((Class<?>) cls, vectorLength));
        for (int i3 = 0; i3 < vectorLength; i3++) {
            int i4 = (i3 * 4) + vectorStart;
            int i5 = byteBuffer.getInt(i4);
            if (i5 == 0) {
                tArr[i3] = 0;
            } else {
                tArr[i3] = Enum.valueOf(cls, getString(byteBuffer, i4 + i5));
            }
        }
        return tArr;
    }

    @Nullable
    public static <F extends Flattenable> F[] resolveFlattenableArrayReference(ByteBuffer byteBuffer, int i, int i2, Class<F> cls) {
        try {
            int referencePosition = getReferencePosition(byteBuffer, i, i2);
            if (referencePosition == 0) {
                return null;
            }
            int vectorLength = getVectorLength(byteBuffer, referencePosition);
            int vectorStart = getVectorStart(referencePosition);
            F[] fArr = (F[]) ((Flattenable[]) Array.newInstance((Class<?>) cls, vectorLength));
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    fArr[i3] = null;
                } else {
                    F newInstance = cls.newInstance();
                    newInstance.initFromFlatBuffer(byteBuffer, i4 + i5);
                    fArr[i3] = newInstance;
                }
            }
            return fArr;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v2, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <T, FL extends Flattener<T>> T[] resolveFlattenableWithFlattenerArrayReference(ByteBuffer byteBuffer, int i, int i2, Class<T> cls, FL fl) {
        try {
            int referencePosition = getReferencePosition(byteBuffer, i, i2);
            if (referencePosition == 0) {
                return null;
            }
            int vectorLength = getVectorLength(byteBuffer, referencePosition);
            int vectorStart = getVectorStart(referencePosition);
            T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, vectorLength));
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    tArr[i3] = 0;
                } else {
                    tArr[i3] = fl.initFromFlatBuffer(byteBuffer, i4 + i5);
                }
            }
            return tArr;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v2, resolved type: F extends com.facebook.flatbuffers.Flattenable[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <F extends Flattenable> F[] resolveVirtualFlattenableArrayReference(ByteBuffer byteBuffer, int i, int i2, Class<F> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        F[] fArr = (F[]) ((Flattenable[]) Array.newInstance((Class<?>) cls, vectorLength));
        for (int i3 = 0; i3 < vectorLength; i3++) {
            int i4 = (i3 * 4) + vectorStart;
            int i5 = byteBuffer.getInt(i4);
            if (i5 == 0) {
                fArr[i3] = 0;
            } else {
                int i6 = i4 + i5;
                short s = getShort(byteBuffer, i6, 0, 0);
                int referencePosition2 = getReferencePosition(byteBuffer, i6, 1);
                Flattenable resolveVirtualFlattenableType = virtualFlattenableResolver.resolveVirtualFlattenableType(s);
                resolveVirtualFlattenableType.initFromFlatBuffer(byteBuffer, referencePosition2);
                fArr[i3] = resolveVirtualFlattenableType;
            }
        }
        return fArr;
    }

    @Nullable
    public static <L extends List<String>> L resolveStringListReference(ByteBuffer byteBuffer, int i, int i2, Class<L> cls) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        try {
            L newInstance = cls.newInstance();
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    newInstance.add(null);
                } else {
                    newInstance.add(getString(byteBuffer, i4 + i5));
                }
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static <V extends Enum, L extends List<V>> L resolveEnumStringListReference(ByteBuffer byteBuffer, int i, int i2, Class<L> cls, Class<V> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        int vectorLength = getVectorLength(byteBuffer, referencePosition);
        int vectorStart = getVectorStart(referencePosition);
        try {
            L newInstance = cls.newInstance();
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    newInstance.add(null);
                } else {
                    newInstance.add(Enum.valueOf(cls2, getString(byteBuffer, i4 + i5)));
                }
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static <T extends Flattenable, L extends List<T>> L resolveFlattenableListReference(ByteBuffer byteBuffer, int i, int i2, Class<L> cls, Class<T> cls2) {
        try {
            int referencePosition = getReferencePosition(byteBuffer, i, i2);
            if (referencePosition == 0) {
                return null;
            }
            int vectorLength = getVectorLength(byteBuffer, referencePosition);
            int vectorStart = getVectorStart(referencePosition);
            L newInstance = cls.newInstance();
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    newInstance.add(null);
                } else {
                    T newInstance2 = cls2.newInstance();
                    newInstance2.initFromFlatBuffer(byteBuffer, i4 + i5);
                    newInstance.add(newInstance2);
                }
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static <V, L extends List<V>, F extends Flattener<V>> L resolveFlattenableWithFlattenerListReference(ByteBuffer byteBuffer, int i, int i2, Class<L> cls, F f) {
        try {
            int referencePosition = getReferencePosition(byteBuffer, i, i2);
            if (referencePosition == 0) {
                return null;
            }
            int vectorLength = getVectorLength(byteBuffer, referencePosition);
            int vectorStart = getVectorStart(referencePosition);
            L newInstance = cls.newInstance();
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    newInstance.add(null);
                } else {
                    newInstance.add(f.initFromFlatBuffer(byteBuffer, i4 + i5));
                }
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static <F extends Flattenable, L extends List<F>> L resolveVirtualFlattenableListReference(ByteBuffer byteBuffer, int i, int i2, Class<L> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        try {
            int referencePosition = getReferencePosition(byteBuffer, i, i2);
            if (referencePosition == 0) {
                return null;
            }
            int vectorLength = getVectorLength(byteBuffer, referencePosition);
            int vectorStart = getVectorStart(referencePosition);
            L newInstance = cls.newInstance();
            for (int i3 = 0; i3 < vectorLength; i3++) {
                int i4 = (i3 * 4) + vectorStart;
                int i5 = byteBuffer.getInt(i4);
                if (i5 == 0) {
                    newInstance.add(null);
                } else {
                    int i6 = i4 + i5;
                    short s = getShort(byteBuffer, i6, 0, 0);
                    int referencePosition2 = getReferencePosition(byteBuffer, i6, 1);
                    Flattenable resolveVirtualFlattenableType = virtualFlattenableResolver.resolveVirtualFlattenableType(s);
                    resolveVirtualFlattenableType.initFromFlatBuffer(byteBuffer, referencePosition2);
                    newInstance.add(resolveVirtualFlattenableType);
                }
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    @Nullable
    public static Iterator<Integer> resolveIntegerListReference(ByteBuffer byteBuffer, int i, int i2) {
        return resolveIterator(byteBuffer, i, i2, IntegerListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<Integer> resolveIntegerListReference(ByteBuffer byteBuffer, int i) {
        return resolveIterator(byteBuffer, i, IntegerListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static RandomAccessList<Integer> resolveIntegerList(ByteBuffer byteBuffer, int i, int i2) {
        return resolveList(byteBuffer, i, i2, IntegerListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<Long> resolveLongListReference(ByteBuffer byteBuffer, int i, int i2) {
        return resolveIterator(byteBuffer, i, i2, LongListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<Long> resolveLongListReference(ByteBuffer byteBuffer, int i) {
        return resolveIterator(byteBuffer, i, LongListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static RandomAccessList<Long> resolveLongList(ByteBuffer byteBuffer, int i, int i2) {
        return resolveList(byteBuffer, i, i2, LongListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<Double> resolveDoubleListReference(ByteBuffer byteBuffer, int i, int i2) {
        return resolveIterator(byteBuffer, i, i2, DoubleListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<Double> resolveDoubleListReference(ByteBuffer byteBuffer, int i) {
        return resolveIterator(byteBuffer, i, DoubleListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<String> resolveStringListReference(ByteBuffer byteBuffer, int i, int i2) {
        return resolveIterator(byteBuffer, i, i2, StringListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static Iterator<String> resolveStringListReference(ByteBuffer byteBuffer, int i) {
        return resolveIterator(byteBuffer, i, StringListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static RandomAccessList<String> resolveStringList(ByteBuffer byteBuffer, int i, int i2) {
        return resolveList(byteBuffer, i, i2, StringListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static <T extends Enum> Iterator<T> resolveEnumStringListReference(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        Method method;
        try {
            method = cls.getMethod("fromString", String.class);
        } catch (Exception unused) {
            method = null;
        }
        return resolveIterator(byteBuffer, i, i2, new EnumStringListAccessor(method), cls);
    }

    @Nullable
    public static Iterator<String> resolveStringDefStringListReference(ByteBuffer byteBuffer, int i, int i2) {
        return resolveIterator(byteBuffer, i, i2, StringListAccessor.INSTANCE, Void.TYPE);
    }

    @Nullable
    public static <T extends Enum<T>> RandomAccessList<T> resolveEnumStringList(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        Method method;
        try {
            method = cls.getMethod("fromString", String.class);
        } catch (Exception unused) {
            method = null;
        }
        return resolveList(byteBuffer, i, i2, new EnumStringListAccessor(method), cls);
    }

    @Nullable
    public static <T extends Flattenable> Iterator<T> resolveFlattenableListReference(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        return resolveIterator(byteBuffer, i, i2, FlattenableListAccessor.INSTANCE, cls);
    }

    @Nullable
    public static <T extends Flattenable> RandomAccessList<T> resolveFlattenableList(ByteBuffer byteBuffer, int i, int i2, Class<T> cls) {
        return resolveList(byteBuffer, i, i2, FlattenableListAccessor.INSTANCE, cls);
    }

    @Nullable
    public static <TV, FLV extends Flattener<TV>> Iterator<TV> resolveFlattenableWithFlattenerListReference(ByteBuffer byteBuffer, int i, int i2, FLV flv) {
        return resolveIterator(byteBuffer, i, i2, FlattenableWithFlattenerListAccessor.INSTANCE, flv);
    }

    @Nullable
    public static <TV, FLV extends Flattener<TV>> RandomAccessList<TV> resolveFlattenableWithFlattenerList(ByteBuffer byteBuffer, int i, int i2, FLV flv) {
        return resolveList(byteBuffer, i, i2, FlattenableWithFlattenerListAccessor.INSTANCE, flv);
    }

    @Nullable
    public static <T extends Flattenable> Iterator<T> resolveVirtualFlattenableListReference(ByteBuffer byteBuffer, int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        return resolveIterator(byteBuffer, i, i2, VirtualFlattenableListAccessor.INSTANCE, virtualFlattenableResolver);
    }

    @Nullable
    public static <T extends Flattenable> RandomAccessList<T> resolveVirtualFlattenableList(ByteBuffer byteBuffer, int i, int i2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        return resolveList(byteBuffer, i, i2, VirtualFlattenableListAccessor.INSTANCE, virtualFlattenableResolver);
    }

    @Nullable
    public static <M extends Map<String, String>> M resolveStringToStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveStringListReference(byteBuffer, referencePosition, 0), resolveStringListReference(byteBuffer, referencePosition, 1));
    }

    @Nullable
    public static <EV extends Enum, M extends Map<String, EV>> M resolveStringToEnumStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveStringListReference(byteBuffer, referencePosition, 0), resolveEnumStringListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <FV extends Flattenable, M extends Map<String, FV>> M resolveStringToFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveStringListReference(byteBuffer, referencePosition, 0), resolveFlattenableListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <TV, FLV extends Flattener<TV>, M extends Map<String, TV>> M resolveStringToFlattenableWithFlattenerMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLV flv) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveStringListReference(byteBuffer, referencePosition, 0), resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 1, flv));
    }

    @Nullable
    public static <FV extends Flattenable, M extends Map<String, FV>> M resolveStringToVirtualFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveStringListReference(byteBuffer, referencePosition, 0), resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 1, virtualFlattenableResolver));
    }

    @Nullable
    public static <EK extends Enum, M extends Map<EK, String>> M resolveEnumStringToStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EK> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveEnumStringListReference(byteBuffer, referencePosition, 0, cls2), resolveStringListReference(byteBuffer, referencePosition, 1));
    }

    @Nullable
    public static <EK extends Enum, EV extends Enum, M extends Map<EK, EV>> M resolveEnumStringToEnumStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EK> cls2, Class<EV> cls3) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveEnumStringListReference(byteBuffer, referencePosition, 0, cls2), resolveEnumStringListReference(byteBuffer, referencePosition, 1, cls3));
    }

    @Nullable
    public static <EK extends Enum, FV extends Flattenable, M extends Map<EK, FV>> M resolveEnumStringToFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EK> cls2, Class<FV> cls3) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveEnumStringListReference(byteBuffer, referencePosition, 0, cls2), resolveFlattenableListReference(byteBuffer, referencePosition, 1, cls3));
    }

    @Nullable
    public static <EK extends Enum, TV, FLV extends Flattener<TV>, M extends Map<EK, TV>> M resolveEnumStringToFlattenableWithFlattenerMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EK> cls2, FLV flv) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveEnumStringListReference(byteBuffer, referencePosition, 0, cls2), resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 1, flv));
    }

    @Nullable
    public static <EK extends Enum, TV, FV extends Flattenable, M extends Map<EK, FV>> M resolveEnumStringToVirtualFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<EK> cls2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveEnumStringListReference(byteBuffer, referencePosition, 0, cls2), resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 1, virtualFlattenableResolver));
    }

    @Nullable
    public static <FK extends Flattenable, M extends Map<FK, String>> M resolveFlattenableToStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FK> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableListReference(byteBuffer, referencePosition, 0, cls2), resolveStringListReference(byteBuffer, referencePosition, 1));
    }

    @Nullable
    public static <FK extends Flattenable, EV extends Enum, M extends Map<FK, EV>> M resolveFlattenableToEnumStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FK> cls2, Class<EV> cls3) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableListReference(byteBuffer, referencePosition, 0, cls2), resolveEnumStringListReference(byteBuffer, referencePosition, 1, cls3));
    }

    @Nullable
    public static <FK extends Flattenable, FV extends Flattenable, M extends Map<FK, FV>> M resolveFlattenableToFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FK> cls2, Class<FV> cls3) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableListReference(byteBuffer, referencePosition, 0, cls2), resolveFlattenableListReference(byteBuffer, referencePosition, 1, cls3));
    }

    @Nullable
    public static <FK extends Flattenable, TV, FLV extends Flattener<TV>, M extends Map<FK, TV>> M resolveFlattenableToFlattenableWithFlattenerMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FK> cls2, FLV flv) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableListReference(byteBuffer, referencePosition, 0, cls2), resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 1, flv));
    }

    @Nullable
    public static <FK extends Flattenable, FV extends Flattenable, M extends Map<FK, FV>> M resolveFlattenableToVirtualFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Class<FK> cls2, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableListReference(byteBuffer, referencePosition, 0, cls2), resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 1, virtualFlattenableResolver));
    }

    @Nullable
    public static <FK extends Flattenable, M extends Map<FK, String>> M resolveVirtualFlattenableToStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 0, virtualFlattenableResolver), resolveStringListReference(byteBuffer, referencePosition, 1));
    }

    @Nullable
    public static <FK extends Flattenable, EV extends Enum, M extends Map<FK, EV>> M resolveVirtualFlattenableToEnumStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, Class<EV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 0, virtualFlattenableResolver), resolveEnumStringListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <FK extends Flattenable, FV extends Flattenable, M extends Map<FK, FV>> M resolveVirtualFlattenableToFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, Class<FV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 0, virtualFlattenableResolver), resolveFlattenableListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <FK extends Flattenable, TV, FLV extends Flattener<TV>, M extends Map<FK, TV>> M resolveVirtualFlattenableToFlattenableWithFlattenerMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, FLV flv) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 0, virtualFlattenableResolver), resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 1, flv));
    }

    @Nullable
    public static <FK extends Flattenable, FV extends Flattenable, M extends Map<FK, FV>> M resolveVirtualFlattenableToVirtualFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 0, virtualFlattenableResolver), resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 1, virtualFlattenableResolver2));
    }

    @Nullable
    public static <TK, FLK extends Flattener<TK>, M extends Map<TK, String>> M resolveFlattenableWithFlattenerToStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLK flk) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 0, flk), resolveStringListReference(byteBuffer, referencePosition, 1));
    }

    @Nullable
    public static <TK, FLK extends Flattener<TK>, EV extends Enum, M extends Map<TK, EV>> M resolveFlattenableWithFlattenerToEnumStringMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLK flk, Class<EV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 0, flk), resolveEnumStringListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <TK, FLK extends Flattener<TK>, FV extends Flattenable, M extends Map<TK, FV>> M resolveFlattenableWithFlattenerToFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLK flk, Class<FV> cls2) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 0, flk), resolveFlattenableListReference(byteBuffer, referencePosition, 1, cls2));
    }

    @Nullable
    public static <TK, FLK extends Flattener<TK>, TV, FLV extends Flattener<TV>, M extends Map<TK, TV>> M resolveFlattenableWithFlattenerToFlattenableWithFlattenerMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLK flk, FLV flv) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 0, flk), resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 1, flv));
    }

    @Nullable
    public static <TK, FLK extends Flattener<TK>, TV, FV extends Flattenable, M extends Map<TK, FV>> M resolveFlattenableWithFlattenerToVirtualFlattenableMapReference(ByteBuffer byteBuffer, int i, int i2, Class<M> cls, FLK flk, Flattenable.VirtualFlattenableResolver virtualFlattenableResolver) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return (M) buildMap(cls, resolveFlattenableWithFlattenerListReference(byteBuffer, referencePosition, 0, flk), resolveVirtualFlattenableListReference(byteBuffer, referencePosition, 1, virtualFlattenableResolver));
    }

    private static <K, V, M extends Map<K, V>> M buildMap(Class<M> cls, @Nullable Iterator<K> it, @Nullable Iterator<V> it2) {
        return (it == null || it2 == null) ? (M) buildIdentityMap(cls) : (M) buildMapFromKeysAndValues(cls, it, it2);
    }

    private static <M extends Map<?, ?>> M buildIdentityMap(Class<M> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    private static <K, V, M extends Map<K, V>> M buildMapFromKeysAndValues(Class<M> cls, Iterator<K> it, Iterator<V> it2) {
        try {
            M newInstance = cls.newInstance();
            while (it.hasNext() && it2.hasNext()) {
                newInstance.put(it.next(), it2.next());
            }
            return newInstance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Not able to create object", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Access to constructor denied", e2);
        }
    }

    public static String getString(ByteBuffer byteBuffer, int i) {
        int i2 = byteBuffer.getInt(i);
        if (byteBuffer.hasArray()) {
            return new String(byteBuffer.array(), byteBuffer.arrayOffset() + i + 4, i2, UTF_8_CHARSET);
        }
        byte[] bArr = new byte[i2];
        synchronized (byteBuffer) {
            int position = byteBuffer.position();
            byteBuffer.position(i + 4);
            byteBuffer.get(bArr);
            byteBuffer.position(position);
        }
        return new String(bArr, 0, bArr.length, UTF_8_CHARSET);
    }

    protected static int compareAsciiStringBytes(ByteBuffer byteBuffer, int i, String str) {
        int i2 = byteBuffer.getInt(i);
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2 && i4 < length) {
            int i5 = byteBuffer.get(i + 4 + i3) & 255;
            int charAt = str.charAt(i4) & 255;
            if (i5 != charAt) {
                return i5 - charAt;
            }
            i3++;
            i4++;
        }
        return i2 - length;
    }

    public static int getVectorInt(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i == 0) {
            return i3;
        }
        return (i2 < 0 || i2 >= getVectorLength(byteBuffer, i)) ? i3 : byteBuffer.getInt(getVectorStart(i) + (i2 * 4));
    }

    public static int getVectorReferencePosition(ByteBuffer byteBuffer, int i, int i2) {
        int vectorLength = getVectorLength(byteBuffer, i);
        if (i2 < 0 || i2 >= vectorLength) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        int vectorStart = getVectorStart(i) + (i2 * 4);
        int i3 = byteBuffer.getInt(vectorStart);
        if (i3 == 0) {
            return 0;
        }
        return vectorStart + i3;
    }

    public static int getVectorLength(ByteBuffer byteBuffer, int i) {
        int i2 = byteBuffer.getInt(i);
        if (i2 >= 0) {
            return i2;
        }
        throw new IndexOutOfBoundsException(Integer.toString(i2));
    }

    public static int getRootObjectPosition(ByteBuffer byteBuffer) {
        int position;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        synchronized (byteBuffer) {
            position = byteBuffer.position();
        }
        return byteBuffer.getInt(position) + position;
    }

    public static int getReferencePosition(ByteBuffer byteBuffer, int i, int i2) {
        int offsetForField = getOffsetForField(byteBuffer, i, i2);
        if (offsetForField != 0) {
            return offsetForField + byteBuffer.getInt(offsetForField);
        }
        return 0;
    }

    public static int getStructPosition(ByteBuffer byteBuffer, int i, int i2) {
        return getOffsetForField(byteBuffer, i, i2);
    }

    public static int getOffsetForField(ByteBuffer byteBuffer, int i, int i2) {
        short s;
        int i3 = i - byteBuffer.getInt(i);
        int i4 = (i2 * 2) + 4;
        if (i4 >= byteBuffer.getShort(i3) || (s = byteBuffer.getShort(i3 + i4)) == 0) {
            return 0;
        }
        return s + i;
    }

    public static int getNumFieldsFromVTable(ByteBuffer byteBuffer, int i) {
        ByteBuffer order = byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return (order.getShort(i - order.getInt(i)) - 4) / 2;
    }

    @Nullable
    static <THelper, TItem> FlatBufferList<THelper, TItem> resolveList(ByteBuffer byteBuffer, int i, int i2, FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor, THelper thelper) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return null;
        }
        return new FlatBufferList<>(byteBuffer, getVectorStart(referencePosition), getVectorLength(byteBuffer, referencePosition), flatBufferListAccessor, thelper);
    }

    @Nullable
    private static <THelper, TItem> FlatBufferIterator<THelper, TItem> resolveIterator(ByteBuffer byteBuffer, int i, int i2, FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor, THelper thelper) {
        return resolveIterator(byteBuffer, getReferencePosition(byteBuffer, i, i2), flatBufferListAccessor, thelper);
    }

    @Nullable
    private static <THelper, TItem> FlatBufferIterator<THelper, TItem> resolveIterator(ByteBuffer byteBuffer, int i, FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor, THelper thelper) {
        if (i == 0) {
            return null;
        }
        return new FlatBufferIterator<>(byteBuffer, getVectorStart(i), getVectorLength(byteBuffer, i), flatBufferListAccessor, thelper);
    }

    /* access modifiers changed from: package-private */
    public static class FlatBufferList<THelper, TItem> extends AbstractList<TItem> implements RandomAccessList<TItem> {
        private final FlatBufferListAccessor<THelper, TItem> accessor;
        private final ByteBuffer bb;
        private final THelper helper;
        private final int length;
        private final int start;

        FlatBufferList(ByteBuffer byteBuffer, int i, int i2, FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor, THelper thelper) {
            this.bb = byteBuffer;
            this.start = i;
            this.length = i2;
            this.accessor = flatBufferListAccessor;
            this.helper = thelper;
        }

        @Override // java.util.List, java.util.AbstractList
        @Nullable
        public TItem get(int i) {
            if (i >= 0 && i < this.length) {
                return this.accessor.get(this.helper, this.bb, this.start, i);
            }
            throw new IndexOutOfBoundsException();
        }

        public int size() {
            return this.length;
        }
    }

    /* access modifiers changed from: private */
    public static class FlatBufferIterator<THelper, TItem> implements Iterator<TItem> {
        private final FlatBufferListAccessor<THelper, TItem> accessor;
        private final ByteBuffer bb;
        private int current = 0;
        private final THelper helper;
        private final int length;
        private final int start;

        FlatBufferIterator(ByteBuffer byteBuffer, int i, int i2, FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor, THelper thelper) {
            this.bb = byteBuffer;
            this.start = i;
            this.length = i2;
            this.accessor = flatBufferListAccessor;
            this.helper = thelper;
        }

        public boolean hasNext() {
            return this.current < this.length;
        }

        @Override // java.util.Iterator
        @Nullable
        public TItem next() {
            int i = this.current;
            if (i < 0 || i >= this.length) {
                throw new NoSuchElementException("Out of bound for iteration");
            }
            FlatBufferListAccessor<THelper, TItem> flatBufferListAccessor = this.accessor;
            THelper thelper = this.helper;
            ByteBuffer byteBuffer = this.bb;
            int i2 = this.start;
            this.current = i + 1;
            return flatBufferListAccessor.get(thelper, byteBuffer, i2, i);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public static class FlattenableListAccessor<T extends Flattenable> implements FlatBufferListAccessor<Class<T>, T> {
        static final FlattenableListAccessor INSTANCE = new FlattenableListAccessor();

        FlattenableListAccessor() {
        }

        @Override // com.facebook.flatbuffers.FlatBuffer.FlatBufferListAccessor
        @Nullable
        public /* bridge */ /* synthetic */ Object get(Object obj, ByteBuffer byteBuffer, int i, int i2) {
            return get((Class) ((Class) obj), byteBuffer, i, i2);
        }

        @Nullable
        public T get(Class<T> cls, ByteBuffer byteBuffer, int i, int i2) {
            int i3 = i + (i2 * 4);
            int i4 = byteBuffer.getInt(i3);
            if (i4 == 0) {
                return null;
            }
            try {
                T newInstance = cls.newInstance();
                initialize(newInstance, byteBuffer, i3 + i4);
                return newInstance;
            } catch (InstantiationException e) {
                throw new RuntimeException("Not able to create object", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Access to constructor denied", e2);
            }
        }

        /* access modifiers changed from: package-private */
        public void initialize(Flattenable flattenable, ByteBuffer byteBuffer, int i) {
            flattenable.initFromFlatBuffer(byteBuffer, i);
        }
    }

    /* access modifiers changed from: private */
    public static class VirtualFlattenableListAccessor<T extends Flattenable> implements FlatBufferListAccessor<Flattenable.VirtualFlattenableResolver, T> {
        static final VirtualFlattenableListAccessor INSTANCE = new VirtualFlattenableListAccessor();

        private VirtualFlattenableListAccessor() {
        }

        @Nullable
        public T get(Flattenable.VirtualFlattenableResolver virtualFlattenableResolver, ByteBuffer byteBuffer, int i, int i2) {
            int i3 = i + (i2 * 4);
            int i4 = byteBuffer.getInt(i3);
            if (i4 == 0) {
                return null;
            }
            int i5 = i3 + i4;
            short s = FlatBuffer.getShort(byteBuffer, i5, 0, 0);
            int referencePosition = FlatBuffer.getReferencePosition(byteBuffer, i5, 1);
            Assertions.assertNotNull(virtualFlattenableResolver);
            T t = (T) virtualFlattenableResolver.resolveVirtualFlattenableType(s);
            if (t == null) {
                return null;
            }
            t.initFromFlatBuffer(byteBuffer, referencePosition);
            return t;
        }
    }

    /* access modifiers changed from: private */
    public static class FlattenableWithFlattenerListAccessor<T> implements FlatBufferListAccessor<Flattener<T>, T> {
        static final FlattenableWithFlattenerListAccessor INSTANCE = new FlattenableWithFlattenerListAccessor();

        private FlattenableWithFlattenerListAccessor() {
        }

        @Override // com.facebook.flatbuffers.FlatBuffer.FlatBufferListAccessor
        @Nullable
        public /* bridge */ /* synthetic */ Object get(Object obj, ByteBuffer byteBuffer, int i, int i2) {
            return get((Flattener) ((Flattener) obj), byteBuffer, i, i2);
        }

        @Nullable
        public T get(Flattener<T> flattener, ByteBuffer byteBuffer, int i, int i2) {
            int i3 = i + (i2 * 4);
            int i4 = byteBuffer.getInt(i3);
            if (i4 == 0) {
                return null;
            }
            try {
                return flattener.initFromFlatBuffer(byteBuffer, i3 + i4);
            } catch (InstantiationException e) {
                throw new RuntimeException("Not able to create object", e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Access to constructor denied", e2);
            }
        }
    }

    private static class IntegerListAccessor implements FlatBufferListAccessor<Class<Void>, Integer> {
        static final IntegerListAccessor INSTANCE = new IntegerListAccessor();

        private IntegerListAccessor() {
        }

        @Nullable
        public Integer get(Class<Void> cls, ByteBuffer byteBuffer, int i, int i2) {
            return Integer.valueOf(byteBuffer.getInt(i + (i2 * 4)));
        }
    }

    private static class LongListAccessor implements FlatBufferListAccessor<Class<Void>, Long> {
        static final LongListAccessor INSTANCE = new LongListAccessor();

        private LongListAccessor() {
        }

        @Nullable
        public Long get(Class<Void> cls, ByteBuffer byteBuffer, int i, int i2) {
            return Long.valueOf(byteBuffer.getLong(i + (i2 * 8)));
        }
    }

    private static class DoubleListAccessor implements FlatBufferListAccessor<Class<Void>, Double> {
        static final DoubleListAccessor INSTANCE = new DoubleListAccessor();

        private DoubleListAccessor() {
        }

        @Nullable
        public Double get(Class<Void> cls, ByteBuffer byteBuffer, int i, int i2) {
            return Double.valueOf(byteBuffer.getDouble(i + (i2 * 8)));
        }
    }

    /* access modifiers changed from: private */
    public static class StringListAccessor implements FlatBufferListAccessor<Class<Void>, String> {
        static final StringListAccessor INSTANCE = new StringListAccessor();

        private StringListAccessor() {
        }

        @Nullable
        public String get(Class<Void> cls, ByteBuffer byteBuffer, int i, int i2) {
            int i3 = i + (i2 * 4);
            int i4 = byteBuffer.getInt(i3);
            if (i4 == 0) {
                return null;
            }
            return FlatBuffer.getString(byteBuffer, i3 + i4);
        }
    }

    /* access modifiers changed from: private */
    public static class EnumStringListAccessor<T extends Enum> implements FlatBufferListAccessor<Class<T>, T> {
        @Nullable
        private final Method method;

        @Override // com.facebook.flatbuffers.FlatBuffer.FlatBufferListAccessor
        @Nullable
        public /* bridge */ /* synthetic */ Object get(Object obj, ByteBuffer byteBuffer, int i, int i2) {
            return get((Class) ((Class) obj), byteBuffer, i, i2);
        }

        EnumStringListAccessor(@Nullable Method method2) {
            this.method = method2;
        }

        @Nullable
        public T get(Class<T> cls, ByteBuffer byteBuffer, int i, int i2) {
            int i3 = i + (i2 * 4);
            int i4 = byteBuffer.getInt(i3);
            if (i4 == 0) {
                return null;
            }
            String string = FlatBuffer.getString(byteBuffer, i3 + i4);
            Method method2 = this.method;
            if (method2 != null) {
                try {
                    return (T) ((Enum) method2.invoke(null, string));
                } catch (Exception unused) {
                }
            }
            return (T) Enum.valueOf(cls, string);
        }
    }

    static boolean appendVectorString(ByteBuffer byteBuffer, int i, int i2, Appendable appendable) {
        int vectorStart;
        int i3;
        String string;
        if (i == 0) {
            return false;
        }
        int vectorLength = getVectorLength(byteBuffer, i);
        if (i2 < 0 || i2 >= vectorLength || (i3 = byteBuffer.getInt((vectorStart = getVectorStart(i) + (i2 * 4)))) == 0 || (string = getString(byteBuffer, vectorStart + i3)) == null) {
            return false;
        }
        appendString(string, appendable);
        return true;
    }

    private static void appendString(String str, Appendable appendable) {
        try {
            appendable.append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean appendString(ByteBuffer byteBuffer, int i, int i2, Appendable appendable) {
        int referencePosition = getReferencePosition(byteBuffer, i, i2);
        if (referencePosition == 0) {
            return false;
        }
        ByteBuffer order = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int capacity = order.capacity();
        int i3 = referencePosition + 4;
        int i4 = order.getInt(referencePosition);
        order.position(i3);
        int i5 = i3 + i4;
        if (i5 < 0 || i5 > capacity) {
            throw new IndexOutOfBoundsException("Bad limit (capacity " + capacity + "): " + i5);
        }
        order.limit(i5);
        int decode = Utf8Decoder.decode(order, appendable);
        if (Utf8Decoder.isError(decode)) {
            throw new IllegalStateException(Utf8Decoder.getErrorName(decode));
        } else if (i4 != 0) {
            return true;
        } else {
            appendString("", appendable);
            return true;
        }
    }
}
