package com.google.common.collect;

public final class ObjectArrays {
    static final Object[] EMPTY_ARRAY = new Object[0];

    public static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) Platform.newArray(tArr, i);
    }

    static <T> T[] arraysCopyOf(T[] tArr, int i) {
        T[] tArr2 = (T[]) newArray(tArr, i);
        System.arraycopy(tArr, 0, tArr2, 0, Math.min(tArr.length, i));
        return tArr2;
    }

    static Object[] checkElementsNotNull(Object... objArr) {
        checkElementsNotNull(objArr, objArr.length);
        return objArr;
    }

    static Object[] checkElementsNotNull(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            checkElementNotNull(objArr[i2], i2);
        }
        return objArr;
    }

    static Object checkElementNotNull(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }
}
