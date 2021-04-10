package com.oculus.util;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class WeakRef<T> extends WeakReference<T> {
    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof WeakRef)) {
            T t = get();
            Object obj2 = ((Reference) obj).get();
            if (!(t == null || obj2 == null)) {
                return t.equals(obj2);
            }
        }
        return false;
    }

    public final int hashCode() {
        T t = get();
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    public WeakRef(T t) {
        super(t);
    }
}
