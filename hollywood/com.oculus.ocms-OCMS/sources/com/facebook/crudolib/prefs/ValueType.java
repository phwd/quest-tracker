package com.facebook.crudolib.prefs;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Set;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValueType {
    public static final int BOOLEAN = 0;
    public static final int DOUBLE = 4;
    public static final int FLOAT = 3;
    public static final int INT = 1;
    public static final int LONG = 2;
    public static final int STRING = 5;
    public static final int STRING_SET = 6;

    ValueType() {
    }

    public static int getValueType(Object obj) {
        if (obj instanceof Boolean) {
            return 0;
        }
        if (obj instanceof Integer) {
            return 1;
        }
        if (obj instanceof Long) {
            return 2;
        }
        if (obj instanceof Float) {
            return 3;
        }
        if (obj instanceof Double) {
            return 4;
        }
        if (obj instanceof String) {
            return 5;
        }
        if (obj instanceof Set) {
            return 6;
        }
        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
    }
}
