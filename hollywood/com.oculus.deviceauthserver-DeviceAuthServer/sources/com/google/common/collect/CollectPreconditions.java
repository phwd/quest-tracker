package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

/* access modifiers changed from: package-private */
@GwtCompatible
public final class CollectPreconditions {
    CollectPreconditions() {
    }

    static void checkEntryNotNull(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("null key in entry: null=" + value);
        } else if (value == null) {
            throw new NullPointerException("null value in entry: " + key + "=null");
        }
    }

    static int checkNonnegative(int value, String name) {
        if (value >= 0) {
            return value;
        }
        throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }

    static void checkRemove(boolean canRemove) {
        Preconditions.checkState(canRemove, "no calls to next() since the last call to remove()");
    }
}
