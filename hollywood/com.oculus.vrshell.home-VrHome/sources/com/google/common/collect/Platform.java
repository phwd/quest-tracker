package com.google.common.collect;

import java.lang.reflect.Array;

/* access modifiers changed from: package-private */
public final class Platform {
    static <T> T[] newArray(T[] reference, int length) {
        return (T[]) ((Object[]) Array.newInstance(reference.getClass().getComponentType(), length));
    }
}
