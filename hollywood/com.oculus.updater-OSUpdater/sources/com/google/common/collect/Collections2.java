package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class Collections2 {
    private Collections2() {
    }

    static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, "size");
        return new StringBuilder((int) Math.min(((long) i) * 8, 1073741824L));
    }
}
