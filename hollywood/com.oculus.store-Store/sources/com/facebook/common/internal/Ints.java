package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
public class Ints {
    private Ints() {
    }

    public static int max(int... array) {
        Preconditions.checkArgument(Boolean.valueOf(array.length > 0));
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
