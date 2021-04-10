package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class Ints {
    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    private Ints() {
    }
}
