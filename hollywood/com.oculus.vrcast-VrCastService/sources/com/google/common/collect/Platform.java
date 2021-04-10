package com.google.common.collect;

import java.lang.reflect.Array;

/* access modifiers changed from: package-private */
public final class Platform {
    static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        mapMaker.weakKeys();
        return mapMaker;
    }
}
