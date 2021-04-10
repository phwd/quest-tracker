package com.google.common.base;

import java.util.Arrays;

public final class Objects extends ExtraObjectsMethodsForWeb {
    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
