package com.google.common.collect;

public final class ObjectArrays {
    public static <T> T[] newArray(T[] reference, int length) {
        return (T[]) Platform.newArray(reference, length);
    }
}
