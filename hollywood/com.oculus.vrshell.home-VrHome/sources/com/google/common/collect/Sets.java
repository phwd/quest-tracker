package com.google.common.collect;

import java.util.Iterator;
import java.util.Set;

public final class Sets {
    static int hashCodeImpl(Set<?> s) {
        int hashCode = 0;
        Iterator<?> it = s.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            hashCode = ((hashCode + (o != null ? o.hashCode() : 0)) ^ -1) ^ -1;
        }
        return hashCode;
    }

    static boolean equalsImpl(Set<?> s, Object object) {
        if (s == object) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> o = (Set) object;
        try {
            return s.size() == o.size() && s.containsAll(o);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }
}
