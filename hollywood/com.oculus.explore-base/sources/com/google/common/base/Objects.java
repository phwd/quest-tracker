package com.google.common.base;

public final class Objects extends ExtraObjectsMethodsForWeb {
    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
