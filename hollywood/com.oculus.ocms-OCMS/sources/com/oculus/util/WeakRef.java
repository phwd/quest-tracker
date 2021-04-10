package com.oculus.util;

import java.lang.ref.WeakReference;

public class WeakRef<T> extends WeakReference<T> {
    public WeakRef(T t) {
        super(t);
    }

    public int hashCode() {
        Object obj = get();
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof WeakRef)) {
            Object obj2 = get();
            Object obj3 = ((WeakRef) obj).get();
            if (!(obj2 == null || obj3 == null)) {
                return obj2.equals(obj3);
            }
        }
        return false;
    }
}
